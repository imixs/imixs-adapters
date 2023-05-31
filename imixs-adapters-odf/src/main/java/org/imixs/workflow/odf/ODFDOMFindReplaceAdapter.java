package org.imixs.workflow.odf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

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
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.incubator.search.InvalidNavigationException;
import org.odftoolkit.odfdom.incubator.search.TextNavigation;
import org.odftoolkit.odfdom.incubator.search.TextSelection;

import jakarta.inject.Inject;

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
	public ItemCollection execute(ItemCollection workitem, ItemCollection event)
			throws AdapterException, PluginException {

		// read the config
		ItemCollection odfConfig = workflowService.evalWorkflowResult(event, "odf-update", workitem, false);
		if (odfConfig == null || !odfConfig.hasItem("findreplace")) {
			throw new PluginException(ODFDOMFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
					"Missing odf configuration");
		}
		String fileName = odfConfig.getItemValueString("filename");
		fileName = workflowService.adaptText(fileName, workitem);

		if (!fileName.toLowerCase().endsWith(".odt") && !fileName.toLowerCase().endsWith(".ods")) {
			throw new PluginException(ODFDOMFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
					"Only .odt, .ods files are supported!");
		}
		List<String> replaceDevList = odfConfig.getItemValue("findreplace");
		String eval = odfConfig.getItemValueString("eval");
		if (replaceDevList.size() == 0) {
			throw new PluginException(ODFDOMFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
					"Wrong odf configuration");
		}

		logger.info("...starting update file: " + fileName + "...");

		// First we test if the fileName is unique. If not found we test regular
		// expressions...
		boolean foundFile = false;
		FileData fileData = workitem.getFileData(fileName);
		if (fileData != null) {
			// file data found - so we can updated it....
			foundFile = true;
			updateFileData(fileData, workitem, replaceDevList, eval);
		} else {
			// not found, we can test regular expressions...
			List<String> fileNames = workitem.getFileNames();
			Pattern pattern = Pattern.compile(fileName);
			// get all fileNames....
			for (String aFileName : fileNames) {
				// test if aFilename matches the pattern or the pattern is null
				if (pattern.matcher(aFileName).find()) {
					// fetch the file
					fileData = workitem.getFileData(aFileName);
					if (fileData != null) {
						// file data found - so we can updated it....
						foundFile = true;
						updateFileData(fileData, workitem, replaceDevList, eval);
					}

				}
			}
		}

		// did we found at least one file
		if (foundFile == false) {
			throw new PluginException(ODFDOMFindReplaceAdapter.class.getSimpleName(), CONFIG_ERROR,
					"wrong odf configuration - no file found matching '" + fileName + "' !");
		}

		return workitem;
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
	public void updateFileData(FileData fileData, ItemCollection workitem, List<String> replaceDevList, String eval)
			throws PluginException {
		byte[] newContent = null;
		
		logger.info("....updateFileData - file="+fileData.getName());

		String fileName = fileData.getName();
		if (fileData.getContent() == null || fileData.getContent().length < 3) {
			// load the snapshot
			fileData = snapshotService.getWorkItemFile(workitem.getUniqueID(), fileName);
		}

		// odt files....
		if (fileName.toLowerCase().endsWith(".odt")  || fileName.toLowerCase().endsWith(".ods")) {

			try (InputStream inputStream = new ByteArrayInputStream(fileData.getContent())) {
				OdfDocument odfDoc = OdfDocument.loadDocument(inputStream);

			
				logger.fine("OdfTextDocument loaded");
				updateODFDocument(odfDoc,workitem,replaceDevList);

			

				logger.fine("findreplace completed");

				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				odfDoc.save(byteArrayOutputStream);
				odfDoc.close();
				newContent = byteArrayOutputStream.toByteArray();

			} catch (Exception e) {
				logger.warning("Failed to convert document: " + e.getMessage());
				throw new PluginException(ODFDOMFindReplaceAdapter.class.getSimpleName(), DOCUMENT_ERROR,
						"unable to update document '" + fileName + "': " + e.getMessage());
			}

			FileData fileDataNew = new FileData(fileData.getName(), newContent, fileData.getContentType(), null);
			// update the fileData
			workitem.addFileData(fileDataNew);
			logger.fine("new document added");
		}



		
	}

	/**
	 * Updates a Text Document
	 * @param odfDoc
	 * @param workitem
	 * @param replaceDevList
	 * @throws PluginException
	 * @throws InvalidNavigationException
	 */
	public void updateODFDocument(OdfDocument odfDoc, ItemCollection workitem, List<String> replaceDevList) throws PluginException, InvalidNavigationException {
		logger.fine("OdfTextDocument loaded");

		for (String entityDev : replaceDevList) {
			ItemCollection entityData = XMLParser.parseItemStructure(entityDev);

			if (entityData != null) {
				String find = entityData.getItemValueString("find");
				find = workflowService.adaptText(find, workitem);
				String replace = entityData.getItemValueString("replace");
				replace = workflowService.adaptText(replace, workitem);

				if (odfDoc instanceof OdfTextDocument) {
					replaceODFTextFragment((OdfTextDocument) odfDoc, find, replace);
				}
				if (odfDoc instanceof OdfSpreadsheetDocument) {
					replaceODFCell((OdfSpreadsheetDocument) odfDoc, find, replace);
				}
				
			}
		}
	}



	/**
	 * Helper method replaces a given text in a OdfTextDocument
	 * 
	 * @throws InvalidNavigationException
	 */
	private void replaceODFTextFragment(OdfTextDocument doc, String pattern, String replace)
			throws InvalidNavigationException {

		TextNavigation searchPattern = new TextNavigation(pattern, doc);
		while (searchPattern.hasNext()) {
			logger.fine("..found match!");
			TextSelection textSelection = (TextSelection) searchPattern.getCurrentItem();
			textSelection.replaceWith(replace);
		}

	}


	/**
	 * Helper method replaces a given text in a OdfTextDocument
	 * 
	 * @throws InvalidNavigationException
	 */
	private void replaceODFCell(OdfSpreadsheetDocument doc, String address, String replace)
			throws InvalidNavigationException {

		// get first table sheet...
		OdfTable tbl = doc.getTableList().get(0);
		OdfTableCell cell = tbl.getCellByPosition(address);
		cell.setStringValue(replace);

	}

}