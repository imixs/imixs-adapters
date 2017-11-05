package org.imixs.workflow.documents;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.documents.parser.DocumentCoreParser;
import org.imixs.workflow.engine.plugins.AbstractPlugin;
import org.imixs.workflow.exceptions.PluginException;

/**
 * The DocumentParserPlugin parses the content of .pdf and ms-doc documents and
 * store the information into the item 'content' of the dms field. This
 * information can be used by plugins to analyze the textual information of a
 * document.
 * 
 * @version 1.0
 * @author rsoika
 */
public class DocumentCoreParserPlugin extends AbstractPlugin {

	public static final String PARSING_EXCEPTION = "PARSING_EXCEPTION";
	public static final String PLUGIN_ERROR = "PLUGIN_ERROR";

	private static Logger logger = Logger.getLogger(DocumentCoreParserPlugin.class.getName());

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
	private void updateDMSMetaData(ItemCollection aWorkitem) throws PluginException {
		boolean updateBlob = false;

		List<ItemCollection> currentDmsList = DMSPlugin.getDmsList(aWorkitem);
		Map<String, List<Object>> files = aWorkitem.getFiles();

		// now we test for each file entry if a content exits. In this case we parse the
		// content and update the dms entry...
		if (files != null) {

			for (Entry<String, List<Object>> entry : files.entrySet()) {
				String fileName = entry.getKey();
				List<?> fileData = entry.getValue();

				ItemCollection dmsEntry = DMSPlugin.findDMSEntry(fileName, currentDmsList);
				if (dmsEntry != null) {
					// dms entry exists. We parse the file content if a new file content was added
					byte[] fileContent = (byte[]) fileData.get(1);
					if (fileContent != null && fileContent.length > 1) {
						// parse content...
						try {
							String searchContent = DocumentCoreParser.parse(fileName, fileData);
							dmsEntry.replaceItemValue("content", searchContent);
							updateBlob = true;
						} catch (IOException e) {
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
		if (updateBlob) {
			DMSPlugin.putDmsList(aWorkitem, currentDmsList);
		}

	}

}