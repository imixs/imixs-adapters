package org.imixs.workflow.documents;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.apache.pdfbox.cos.COSInputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode;
import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;
import org.apache.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.plugins.AbstractPlugin;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.util.XMLParser;

/**
 * The PDFXMLPlugin extracts embedded files from a PDF and transforms the
 * content into a Imixs Workitem Format. This data can be added into the current
 * workitem for further processing.
 * <p>
 * The plugin is based on the Apache PDFBox project.
 * <p>
 * To activate the plugin the BPMN event must contain the following item
 * definition
 * 
 * <pre>
 * {@code
     <item name="PDFXMLPlugin">
    <filename>*.xml</filename>
    <report>myReport</report>
   </item>
   }
 * </pre>
 * 
 * 
 * 
 * 
 * @version 1.0
 * @author rsoika
 */
public class PDFXMLPlugin extends AbstractPlugin {

	public static final String PDFXMLPLUGIN = "PDFXMLPlugin";

	public static final String PARSING_EXCEPTION = "PARSING_EXCEPTION";
	public static final String PLUGIN_ERROR = "PLUGIN_ERROR";

	public static final String FILE_PATTERN_PDF = ".[pP][dD][fF]";
	public static final String FILE_PATTERN_XML = ".[xX][mM][lL]";

	private static Logger logger = Logger.getLogger(PDFXMLPlugin.class.getName());

	/**
	 * This method parses the content of an attached pdf file and extracts an
	 * embedded XML file. This xml file will than be transformed by a given report
	 * definition into a Imixs XMLDocument. The content of the XMLDocument is than
	 * merged into the current document.
	 * 
	 * @throws PluginException
	 */
	@Override
	public ItemCollection run(ItemCollection document, ItemCollection event) throws PluginException {
		byte[] xmlData = null;
		ItemCollection evalItemCollection = getWorkflowService().evalWorkflowResult(event, document, false);

		if (evalItemCollection == null) {
			return document;
		}

		String processValue = evalItemCollection.getItemValueString(PDFXMLPLUGIN);
		if (!processValue.isEmpty()) {
			ItemCollection processData = XMLParser.parseItemStructure(processValue);
			String report = processData.getItemValueString("report");
			String file_pattern = processData.getItemValueString("filename");

			xmlData = getXMLFile(document, file_pattern);

			if (xmlData != null) {

				logger.info("...do something with the xml file.." + report);

			}

			// verify all files....

		}

		return document;

	}

	/**
	 * This method searches attached PDF files of a workitem and extracts an
	 * embedded XML file.
	 * <p>
	 * The method only returns the first embedded xml file and does not support
	 * multiple xml files embedded in one pdf file.
	 * 
	 * @param document
	 * @param filePattern
	 * @return
	 * @throws PluginException
	 */
	public static byte[] getXMLFile(ItemCollection document, String file_pattern) throws PluginException {

		// verify all attached PDF files
		List<String> filenames = document.getFileNames();
		for (String filename : filenames) {

			if (Pattern.compile(file_pattern).matcher(filename).find()) {

				logger.info("...extract embedded XML from '" + filename + "'");

				// we only support the first embedded XML file here!
				FileData pdfFileData = document.getFileData(filename);
				return getFirstEmbeddedXML(pdfFileData.getContent());

			}
		}

		// no data found
		return null;
	}

	/**
	 * This method converts a inputStream into a byte array.
	 * 
	 * @param ins
	 * @return
	 * @throws IOException
	 */
	public static byte[] streamToByteArray(InputStream ins) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] byteBuffer = new byte[1024];
		int len;
		while ((len = ins.read(byteBuffer)) > -1) {
			baos.write(byteBuffer, 0, len);
		}
		baos.flush();
		return baos.toByteArray();
	}

	/**
	 * This method extract the first XML file form a pdf file content
	 * 
	 * @param content
	 * @return a byte array with the xml data or null if no xml file was found in
	 *         the pdf file.
	 */
	private static byte[] getFirstEmbeddedXML(byte[] content) {
		PDDocument doc = null;
		byte[] result = null;
		try {

			doc = PDDocument.load(content);

			PDDocumentNameDictionary namesDictionary = new PDDocumentNameDictionary(doc.getDocumentCatalog());
			PDEmbeddedFilesNameTreeNode efTree = namesDictionary.getEmbeddedFiles();
			if (efTree != null) {
				Map<String, PDComplexFileSpecification> names = efTree.getNames();
				if (names != null) {
					result = extractFirstXMLFile(names);
				} else {
					List<PDNameTreeNode<PDComplexFileSpecification>> kids = efTree.getKids();
					for (PDNameTreeNode<PDComplexFileSpecification> node : kids) {
						names = node.getNames();
						result = extractFirstXMLFile(names);
					}
				}
			}
		} catch (IOException e) {
			logger.warning("unable to load embedded xml : " + e.getMessage());

		} finally {
			if (doc != null) {
				try {
					doc.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		return result;
	}

	/**
	 * Helper method to extract the first XML file from a list of files
	 * 
	 * @param names
	 * @return
	 * @throws IOException
	 */
	private static byte[] extractFirstXMLFile(Map<String, PDComplexFileSpecification> names) throws IOException {
		for (Map.Entry<String, PDComplexFileSpecification> entry : names.entrySet()) {
			PDComplexFileSpecification fileSpec = entry.getValue();
			String filename = fileSpec.getFile();

			// test if file is a xml file
			if (Pattern.compile(FILE_PATTERN_XML).matcher(filename).find()) {
				// we found an xml file!
				PDEmbeddedFile embeddedFile = getEmbeddedFile(fileSpec);
				COSInputStream inStream = embeddedFile.createInputStream();

				return streamToByteArray(inStream);

			}
		}

		// no XML file found
		return null;
	}

	/**
	 * Helper method to extract platform specific embedded file format.
	 * 
	 * @param fileSpec
	 * @return
	 */
	private static PDEmbeddedFile getEmbeddedFile(PDComplexFileSpecification fileSpec) {
		PDEmbeddedFile embeddedFile = null;
		if (fileSpec != null) {
			embeddedFile = fileSpec.getEmbeddedFileUnicode();
			if (embeddedFile == null) {
				embeddedFile = fileSpec.getEmbeddedFileDos();
			}
			if (embeddedFile == null) {
				embeddedFile = fileSpec.getEmbeddedFileMac();
			}
			if (embeddedFile == null) {
				embeddedFile = fileSpec.getEmbeddedFileUnix();
			}
			if (embeddedFile == null) {
				embeddedFile = fileSpec.getEmbeddedFile();
			}
		}
		return embeddedFile;
	}

}