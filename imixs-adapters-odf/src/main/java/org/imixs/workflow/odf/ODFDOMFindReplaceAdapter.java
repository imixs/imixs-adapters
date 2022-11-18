package org.imixs.workflow.odf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.imixs.archive.core.SnapshotService;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.engine.ReportService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.util.XMLParser;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.incubator.search.InvalidNavigationException;
import org.odftoolkit.odfdom.incubator.search.TextNavigation;
import org.odftoolkit.odfdom.incubator.search.TextSelection;

/**
 * This ODFDOMFindReplaceAdapter can be used to find and replace text fragements
 * in a Open Document Format (ODF) documents (e.g. .odt, .ods).
 * <p>
 * The adapter can be configured by the event workflow result:
 * <p>
 * 
 * <pre>
 * {@code
      <odf-update name=
        "filename">PLA Membership Agreement-<itemvalue>numsequencenumber</itemvalue>.odt</odf-update>
        <odf-update name="findreplace">
               <find>[company.name]</find>
               <replace><itemvalue>company.name</itemvalue></replace>
        </odf-update>
        <odf-update name="findreplace">
               <find>[company.country]</find>
               <replace><itemvalue>company.country</itemvalue></replace>
        </odf-update>
        <odf-update name="findreplace">
               <find>[contract.startdate]</find>
               <replace><itemvalue format=
        "EEE, MMM d, yyyy">contract.start</itemvalue></replace>
       </odf-update>
   }
 * </pre>
 * 
 * 
 * @version 1.0
 * @author rsoika
 */
public class ODFDOMFindReplaceAdapter implements SignalAdapter {

	private static Logger logger = Logger.getLogger(ODFDOMFindReplaceAdapter.class.getName());
	public static final String DOCUMENT_ERROR = "DOCUMENT_ERROR";
	public static final String CONFIG_ERROR = "CONFIG_ERROR";

	@Inject
	WorkflowService workflowService;

	@Inject
	SnapshotService snapshotService;

	@Inject
	ReportService reportService;

	/**
	 * This method computes the findreplace configuration and updates an attachments
	 * form type .docx
	 * 
	 * @throws PluginException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ItemCollection execute(ItemCollection document, ItemCollection event)
			throws AdapterException, PluginException {

		// read the config
		ItemCollection odfConfig = workflowService.evalWorkflowResult(event, "odf-update", document, false);
		if (odfConfig == null || !odfConfig.hasItem("findreplace")) {
			throw new PluginException(ODFDOMFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
					"wrong odf configuration");
		}
		String fileName = odfConfig.getItemValueString("filename");
		fileName = workflowService.adaptText(fileName, document);

		if (!fileName.toLowerCase().endsWith(".odt") && !fileName.toLowerCase().endsWith(".ods")) {
			throw new PluginException(ODFDOMFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
					"Only .odt, .ods files are supported!");
		}
		List<String> replaceDevList = odfConfig.getItemValue("findreplace");
		String eval = odfConfig.getItemValueString("eval");
		if (replaceDevList.size() == 0) {
			throw new PluginException(ODFDOMFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
					"wrong odf configuration");
		}

		logger.info("...starting update file: " + fileName + "...");

		// First we test if the fileName is unique. If not found we test regular
		// expressions...
		boolean foundFile = false;
		FileData fileData = document.getFileData(fileName);
		if (fileData != null) {
			// file data found - so we can updated it....
			foundFile = true;
			updateFileData(fileData, document, replaceDevList, eval);
		} else {
			// not found, we can test regular expressions...
			List<String> fileNames = document.getFileNames();
			Pattern pattern = Pattern.compile(fileName);
			// get all fileNames....
			for (String aFileName : fileNames) {
				// test if aFilename matches the pattern or the pattern is null
				if (pattern.matcher(aFileName).find()) {
					// fetch the file
					fileData = document.getFileData(aFileName);
					if (fileData != null) {
						// file data found - so we can updated it....
						foundFile = true;
						updateFileData(fileData, document, replaceDevList, eval);
					}

				}
			}
		}

		// did we found at least one file
		if (foundFile == false) {
			throw new PluginException(ODFDOMFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
					"wrong odf configuration - no file found matching '" + fileName + "' !");
		}

		return document;
	}

	/**
	 * This helper method updates the content of a given FileData object with a
	 * replaceDevList
	 * <p>
	 * The method verifies if the content of the file need to be loaded from the
	 * snapshot
	 * <p>
	 * 
	 * @param fileData       - the fileData object containing the text or calc file
	 * @param replaceDevList - list of text markers or cell positions to be replaced
	 * @param eval           - optional list of cell positions to be evaluated after
	 *                       update (this is for XSSFWorkbooks only)
	 * @throws IOException
	 * @throws PluginException
	 * 
	 */
	public void updateFileData(FileData fileData, ItemCollection document, List<String> replaceDevList, String eval)
			throws PluginException {
		byte[] newContent = null;
		
		logger.info("....updateFileData - file="+fileData.getName());

		String fileName = fileData.getName();
		if (fileData.getContent() == null || fileData.getContent().length < 3) {
			// load the snapshot
			fileData = snapshotService.getWorkItemFile(document.getUniqueID(), fileName);
		}

		// odf files....
		if (fileName.toLowerCase().endsWith(".odt")) {

			try (InputStream inputStream = new ByteArrayInputStream(fileData.getContent())) {

				OdfTextDocument doc = (OdfTextDocument) OdfDocument.loadDocument(inputStream);

				logger.info("OdfDocument loaded");

				for (String entityDev : replaceDevList) {
					ItemCollection entityData = XMLParser.parseItemStructure(entityDev);

					if (entityData != null) {
						String find = entityData.getItemValueString("find");
						find = workflowService.adaptText(find, document);
						String replace = entityData.getItemValueString("replace");
						replace = workflowService.adaptText(replace, document);
						replaceODFDocument(doc, find, replace);
					}
				}

				logger.info("findreplace completed");

				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				doc.save(byteArrayOutputStream);
				doc.close();
				newContent = byteArrayOutputStream.toByteArray();

			} catch (Exception e) {
				logger.warning("Failed to convert document: " + e.getMessage());
				throw new PluginException(ODFDOMFindReplaceAdapter.class.getSimpleName(), DOCUMENT_ERROR,
						"unable to update document '" + fileName + "': " + e.getMessage());
			}

			FileData fileDataNew = new FileData(fileData.getName(), newContent, fileData.getContentType(), null);
			// update the fileData
			document.addFileData(fileDataNew);
			logger.info("new document added");
		}
	}

	/**
	 * Helper method replaces a given text in a OdfTextDocument
	 * 
	 * @throws InvalidNavigationException
	 */
	private void replaceODFDocument(OdfTextDocument doc, String pattern, String replace)
			throws InvalidNavigationException {

		TextNavigation searchPattern = new TextNavigation(pattern, doc);
		while (searchPattern.hasNext()) {
			logger.info("..found match!");

			TextSelection textSelection = (TextSelection) searchPattern.getCurrentItem();

			logger.info("...Position=" + textSelection.getIndex());
			logger.info("...Text=" + textSelection.getText());

			textSelection.replaceWith(replace);
		}

	}

}