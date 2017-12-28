package org.imixs.workflow.documents;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.tika.exception.TikaException;
import org.imixs.archive.core.DMSHandler;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.documents.parser.DocumentTikaParser;
import org.imixs.workflow.engine.plugins.AbstractPlugin;
import org.imixs.workflow.exceptions.PluginException;
import org.xml.sax.SAXException;

/**
 * The DocumentParserPlugin parses the content of .pdf and ms-doc documents and
 * store the information into the item 'content' of the dms field. This
 * information can be used by plugins to analyze the textual information of a
 * document.
 * 
 * @version 1.0
 * @author rsoika
 */
public class DocumentTikaParserPlugin extends AbstractPlugin {

	public static final String PARSING_EXCEPTION = "PARSING_EXCEPTION";
	public static final String PLUGIN_ERROR = "PLUGIN_ERROR";

	private static Logger logger = Logger.getLogger(DocumentTikaParserPlugin.class.getName());

	/**
	 * This method parses the content of new attached office documents (.pdf, .doc,
	 * ...) and updates the DMS item of workitems before the processing life-cycle
	 * starts.
	 * 
	 * @throws PluginException
	 */
	@Override
	public ItemCollection run(ItemCollection document, ItemCollection event) throws PluginException {

		// update the dms meta data
		updateDMSMetaData(document);
		return document;

	}

	/**
	 * This method iterates over the item 'dms' and parsees the text content for
	 * .pdf, .doc, .xls, .ppt and .docx files. The method updates the result into
	 * the dms list with within the item 'content'.
	 * 
	 * @param aWorkitem
	 * @return true if the dms item was changed
	 * @throws PluginException
	 */
	private void updateDMSMetaData(ItemCollection workitem) throws  PluginException {
		//boolean updateDmsItem = false;

		
		try {
			DMSHandler.updateDMSMetaData(workitem, this.getWorkflowService().getUserName());
		} catch (NoSuchAlgorithmException e) {
			logger.warning("Unable to update dms meta data: " + e.getMessage());
			throw new PluginException(DocumentCoreParserPlugin.class.getSimpleName(), PARSING_EXCEPTION,
					"Unable to update dms meta data", e);
		}
		//List<ItemCollection> currentDmsList = DMSHandler.getDmsList(workitem);
		Map<String, List<Object>> files = workitem.getFiles();
		
		//List<Map> vDMS = workitem.getItemValue(DMSHandler.DMS_ITEM);

		// now we test for each file entry if a content exits. In this case we parse the
		// content and update the dms entry...
		if (files != null) {
 
			for (Entry<String, List<Object>> entry : files.entrySet()) {
				String fileName = entry.getKey();
				List<?> fileData = entry.getValue();

				ItemCollection dmsEntry = DMSHandler.getDMSEntry(fileName,workitem);
				if (dmsEntry==null) {
					// create a new dmsEntry object
					logger.warning("Invalid DMS List, missing entry '" + fileName + "'");
				}
				
				if (dmsEntry != null) {
					// dms entry exists. We parse the file content if a new file content was added
					byte[] fileContent = (byte[]) fileData.get(1);
					if (fileContent != null && fileContent.length > 1) {
						// parse content...
						try { 
							String searchContent = DocumentTikaParser.parse(fileName, fileData);
							dmsEntry.replaceItemValue("content", searchContent);
							
							DMSHandler.putDMSEntry(dmsEntry, workitem);
							
						} catch (IOException | SAXException | TikaException e) {
							logger.warning("Unable to parse attached document " + fileName + " : " + e.getMessage());
							throw new PluginException(DocumentCoreParserPlugin.class.getSimpleName(), PARSING_EXCEPTION,
									"Unable to parse attached document '" + fileName + "'", e);
						}
					}
				} else {
					throw new PluginException(DocumentCoreParserPlugin.class.getSimpleName(), PLUGIN_ERROR,
							"No DMS data found. DMSPlugin must be executed before - please verify model.");
				}

			}
		}

		// finally update the modified dms list....
//		if (updateDmsItem) {
//			DMSHandler.putDmsList(aWorkitem, currentDmsList);
//		}

	}
	
	

}