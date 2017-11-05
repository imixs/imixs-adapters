package org.imixs.workflow.documents;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.ItemCollectionComparator;
import org.imixs.workflow.engine.PropertyService;
import org.imixs.workflow.engine.plugins.AbstractPlugin;
import org.imixs.workflow.exceptions.PluginException;

/**
 * The DMSPlugin handles the item 'dms' which is holding meta information about
 * file attachments.
 * 
 * @version 1.0
 * @author rsoika
 */
public class DMSPlugin extends AbstractPlugin {

	public final static String DMS_ITEM = "dms";
	public final static String DMS_FILE_NAMES = "dms_names"; // list of files
	public final static String DMS_FILE_COUNT = "dms_count"; // count of files
	public final static String CHECKSUM_ERROR = "CHECKSUM_ERROR";

	@Resource
	SessionContext ctx;

	@EJB
	PropertyService propertyService;

	private static Logger logger = Logger.getLogger(DMSPlugin.class.getName());

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
		try {
			updateDMSMetaData(document);
		} catch (NoSuchAlgorithmException e) {
			logger.severe("failed to compute MD5 checksum: " + document.getUniqueID() + " - " + e.getMessage());
			throw new PluginException(DMSPlugin.class.getSimpleName(), CHECKSUM_ERROR,
					"failed to compute MD5 checksum: " + document.getUniqueID() + "(" + e.getMessage() + ")", e);

		}

		return document;

	}

	/**
	 * This method returns a list of ItemCollections for all DMS elements attached
	 * to the current workitem. The DMS meta data is read from the property 'dms'.
	 * 
	 * The dms property is updated in the run() method of this plug-in.
	 * 
	 * The method is used by the DmsController to display the dms meta data.
	 * 
	 * @param workitem
	 *            - source of meta data, sorted by $creation
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<ItemCollection> getDmsList(ItemCollection workitem) {
		// build a new fileList and test if each file contained in the $files is
		// listed
		List<ItemCollection> dmsList = new ArrayList<ItemCollection>();
		if (workitem == null)
			return dmsList;

		List<Map> vDMS = workitem.getItemValue(DMS_ITEM);
		// first we add all existing dms informations
		for (Map aMetadata : vDMS) {
			dmsList.add(new ItemCollection(aMetadata));
		}

		// sort list by name
		// Collections.sort(dmsList, new ItemCollectionComparator("txtname",
		// true));
		// sort list by $modified
		Collections.sort(dmsList, new ItemCollectionComparator("$created", true));

		return dmsList;
	}

	/**
	 * This method converts a list of ItemCollections for DMS elements into Map
	 * objects and updates the workitem property 'dms'.
	 * 
	 * The method is used by the DmsController to update dms data provided by the
	 * user.
	 * 
	 * @param workitem
	 *            - the workitem to be updated
	 * @param dmsList
	 *            - the dms metha data to be put into the workitem
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public static void putDmsList(ItemCollection workitem, List<ItemCollection> dmsList) {
		// convert the List<ItemCollection> into a List<Map>
		List<Map> vDMSnew = new ArrayList<Map>();
		if (dmsList != null) {
			for (ItemCollection dmsEntry : dmsList) {
				vDMSnew.add(dmsEntry.getAllItems());
			}
		}
		// update the workitem
		workitem.replaceItemValue(DMS_ITEM, vDMSnew);
	}

	/**
	 * This method returns the meta data of a specific file in the exiting filelist.
	 * 
	 * @return
	 */
	public static ItemCollection findDMSEntry(String aFilename, List<ItemCollection> dmsList) {

		for (ItemCollection dmsEntry : dmsList) {
			// test if filename matches...
			String sName = dmsEntry.getItemValueString("txtname");
			if (sName.equals(aFilename))
				return dmsEntry;
		}
		// no matching meta data found!
		return null;
	}

	/**
	 * This method updates the property 'dms' of the current workitem with the meta
	 * data of attached files or links.
	 * 
	 * This method creates new empty DMS entries in the current workitem for new
	 * uploaded files which are still not contained in the dms item list. The
	 * workitem will only hold a empty byte array for files.
	 * 
	 * If a new file content was added, the MD5 checksum will be generated.
	 * 
	 * The parsed text content for .pdf, .doc, .xls, .ppt will be stored into the
	 * dms list with within the item 'content'.
	 * 
	 * @param aWorkitem
	 * @param dmsList
	 *            - map with meta information for each file entry
	 * @param defaultUsername
	 *            - default username for new dms entries
	 * @return true if the dms item was changed
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	private boolean updateDMSMetaData(ItemCollection aWorkitem) throws NoSuchAlgorithmException {
		boolean updateBlob = false;

		String username = ctx.getCallerPrincipal().getName();

		List<ItemCollection> currentDmsList = getDmsList(aWorkitem);
		List<String> fileNames = aWorkitem.getFileNames();
		Map<String, List<Object>> files = aWorkitem.getFiles();

		// first we remove all DMS entries which did not have a matching
		// $File-Entry and are not from type link
		for (Iterator<ItemCollection> iterator = currentDmsList.iterator(); iterator.hasNext();) {
			ItemCollection dmsEntry = iterator.next();
			String sName = dmsEntry.getItemValueString("txtName");
			String sURL = dmsEntry.getItemValueString("url");
			if (sURL.isEmpty() && !fileNames.contains(sName)) {
				// Remove the current element from the iterator and the list.
				logger.fine("remove dms entry '" + sName + "'");
				iterator.remove();
				// update = true;
			}
		}

		// now we test for each file entry if a dms meta data entry
		// exists. If not we create a new one...
		if (files != null) {
			for (Entry<String, List<Object>> entry : files.entrySet()) {
				String fileName = entry.getKey();
				List<?> fileData = entry.getValue();

				ItemCollection dmsEntry = findDMSEntry(fileName, currentDmsList);
				if (dmsEntry == null) {
					// no meta data exists.... create a new meta object
					dmsEntry = new ItemCollection();
					dmsEntry.replaceItemValue("txtname", fileName);

					dmsEntry.replaceItemValue("$created", new Date());
					dmsEntry.replaceItemValue("namCreator", username);// deprecated
					dmsEntry.replaceItemValue("$Creator", username);
					dmsEntry.replaceItemValue("txtcomment", "");

					// compute md5 checksum
					byte[] fileContent = (byte[]) fileData.get(1);
					dmsEntry.replaceItemValue("md5Checksum", generateMD5(fileContent));

					currentDmsList.add(dmsEntry);
				} else {
					// dms entry exists. We update if new file content was added
					byte[] fileContent = (byte[]) fileData.get(1);
					if (fileContent != null && fileContent.length > 1) {
						dmsEntry.replaceItemValue("md5Checksum", generateMD5(fileContent));
						dmsEntry.replaceItemValue("$modified", new Date());
						dmsEntry.replaceItemValue("$editor", username);

					}

				}

			}
		}

		// finally update the modified dms list....
		putDmsList(aWorkitem, currentDmsList);

		// add $filecount
		aWorkitem.replaceItemValue(DMS_FILE_COUNT, aWorkitem.getFileNames().size());
		// add $filenames
		aWorkitem.replaceItemValue(DMS_FILE_NAMES, aWorkitem.getFileNames());

		return updateBlob;
	}

	/**
	 * Generates a MD5 from a byte array
	 * 
	 * @param b
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static String generateMD5(byte[] b) throws NoSuchAlgorithmException {
		byte[] hash_bytes = MessageDigest.getInstance("MD5").digest(b);
		return DatatypeConverter.printHexBinary(hash_bytes);
	}

}