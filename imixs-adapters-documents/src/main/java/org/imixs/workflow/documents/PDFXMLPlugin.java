package org.imixs.workflow.documents;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode;
import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;
import org.apache.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.imixs.archive.core.DMSHandler;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.documents.parser.DocumentCoreParser;

import org.imixs.workflow.engine.plugins.AbstractPlugin;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.util.XMLParser;
import org.junit.Assert;

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
	private static Logger logger = Logger.getLogger(PDFXMLPlugin.class.getName());

	
	
	/**
	 * This method parses the content of new attached office documents (.pdf, .doc,
	 * ...) and updates the DMS item of workitems before the processing life-cycle
	 * starts.
	 * 
	 * @throws PluginException
	 */
	@Override
	public ItemCollection run(ItemCollection document, ItemCollection event) throws PluginException {
		
		ItemCollection evalItemCollection = getWorkflowService().evalWorkflowResult(event, document, false);

		if (evalItemCollection == null) {
			return document;
		}

		String processValue = evalItemCollection.getItemValueString(PDFXMLPLUGIN);
		if (!processValue.isEmpty()) {
			ItemCollection processData = XMLParser.parseItemStructure(processValue);
			String report = processData.getItemValueString("report");
			String file_pattern = processData.getItemValueString("filename");

			// verify all attached PDF files
			List<String> filenames = document.getFileNames();
			for (String filename : filenames) {
				if (filename.toLowerCase().endsWith(".pdf")) {
					logger.info("...extract embedded XML from '" + filename + "'");
					// we only support the first embedded XML file here!

					FileData pdfFileData = document.getFileData(filename);

					getFirstEmbeddedXML(pdfFileData.getContent());

				}
			}

			// verify all files....

		}

		return document;

	}

	public static Object getFirstEmbeddedXML(byte[] content) {
		PDDocument doc = null;
		try {

			doc = PDDocument.load(content);

			PDDocumentNameDictionary namesDictionary = new PDDocumentNameDictionary(doc.getDocumentCatalog());
			PDEmbeddedFilesNameTreeNode efTree = namesDictionary.getEmbeddedFiles();
			if (efTree != null) {
				Map<String, PDComplexFileSpecification> names = efTree.getNames();
				if (names != null) {
					extractFiles(names);
				} else {
					List<PDNameTreeNode<PDComplexFileSpecification>> kids = efTree.getKids();
					for (PDNameTreeNode<PDComplexFileSpecification> node : kids) {
						names = node.getNames();
						extractFiles(names);
					}
				}
			}
		} catch (IOException e) {
			logger.warning("unable to load embedded xml : " +e.getMessage());

		} finally {
			if (doc != null) {
				try {
					doc.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		return null;
	}

	/**
	 * Helper method to extract the files from a PDComplexFileSpecification
	 * 
	 * @param names
	 * @throws IOException
	 */
	private static void extractFiles(Map<String, PDComplexFileSpecification> names) throws IOException {
		for (Map.Entry<String, PDComplexFileSpecification> entry : names.entrySet()) {
			PDComplexFileSpecification fileSpec = entry.getValue();
			String filename = fileSpec.getFile();
			PDEmbeddedFile embeddedFile = getEmbeddedFile(fileSpec);
			extractFile(filename, embeddedFile);
		}
	}

	private static void extractFile(String filename, PDEmbeddedFile embeddedFile) throws IOException {
		// String embeddedFilename = OUTPUT_DIR + filename;
		File file = new File(filename);
		System.out.println("Writing " + filename);

		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(embeddedFile.toByteArray());
		}
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