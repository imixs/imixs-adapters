package org.imixs.workflow.datev.imports;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.ItemCollectionComparator;
import org.imixs.workflow.datev.export.DatevExportService;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.engine.index.SchemaService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;

/**
 * This Service provides methods to import data from a DATEV file.
 * <p>
 * The import file must contain 2 header rows. The 1st row contains the object
 * type, the 2nd row contains the filed names.
 * <p>
 * Example 1st line:
 * 
 * <pre>
 * "DTVF";700;20;"Kontenbeschriftungen";2;20180917165240335;;"RE";"Farida.Weikard";"";217386;21010;20180101;6;;;"";"";;0;;"";;"";;141987;"04";;;"";""
 * </pre>
 * 
 * Example 2nd line:
 * 
 * <pre>
 * Konto;Kontobeschriftung;SprachId
 * </pre>
 * 
 * <p>
 * The service provides methods to search for DATEV objects by key or search
 * phrase. A search result can be optional restricted to a specific DATEV
 * client. NOTE: the item _datev_client_id must be part of the property
 * lucence.indexFieldListNoAnalyze to use this feature.
 * 
 * @see DatevImportService to import datev data and assign the data to a
 *      workflow model.
 * 
 * 
 * @author rsoika
 *
 */
@DeclareRoles({ "org.imixs.ACCESSLEVEL.NOACCESS", "org.imixs.ACCESSLEVEL.READERACCESS",
		"org.imixs.ACCESSLEVEL.AUTHORACCESS", "org.imixs.ACCESSLEVEL.EDITORACCESS",
		"org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@RolesAllowed({ "org.imixs.ACCESSLEVEL.NOACCESS", "org.imixs.ACCESSLEVEL.READERACCESS",
		"org.imixs.ACCESSLEVEL.AUTHORACCESS", "org.imixs.ACCESSLEVEL.EDITORACCESS",
		"org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@Stateless
@LocalBean
public class DatevImportService {

	public static final String DATA_ERROR = "DATA_ERROR";
	public static final String IMPORT_ERROR = "IMPORT_ERROR";
	public static final int MAX_SEARCH_RESULT = 100;
	public final static String ISO8601_FORMAT_DATETIME = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public final static String ISO8601_FORMAT_DATE = "yyyy-MM-dd";

	@EJB
	DocumentService documentService;

	@EJB
	SchemaService schemaService;

	private static Logger logger = Logger.getLogger(DatevImportService.class.getName());

	/**
	 * This method finds a datev entity by the attribute 'txtName'
	 * <p>
	 * The param clientID is optional and restricts the result to a specific DATEV
	 * client. NOTE: the item _datev_client_id must be part of the property
	 * lucence.indexFieldListNoAnalyze.
	 * 
	 * @param key
	 *                 - name of the object (txtname)
	 * @param type
	 *                 - DATEV type of the object
	 * @param clientID
	 *                 - optional restriction to a specific client id
	 *                 (_datev_client_id)
	 * 
	 * @return DATEV entity or null if no entity with the given name exits
	 */
	public ItemCollection findEntityByName(String key, String type, String clientID) {

		String searchTerm = "(type:\"" + type + "\" AND txtname:\"" + key + "\"";

		// restrict to client id?
		if (clientID != null && !clientID.isEmpty()) {
			searchTerm += " AND _datev_client_id:\"" + clientID + "\"";
		}

		searchTerm += ")";

		Collection<ItemCollection> col;
		try {
			col = documentService.find(searchTerm, 1, 0);
			if (col.size() > 0) {
				return col.iterator().next();
			}
		} catch (QueryException e) {
			logger.warning(e.getMessage());
		}

		// no order found
		return null;

	}

	/**
	 * This method returns a list of ItemCollections matching the search phrase and
	 * type. The type depends on the datev import file
	 * <p>
	 * The param clientID is optional and restricts the result to a specific DATEV
	 * client. NOTE: the item _datev_client_id must be part of the property
	 * lucence.indexFieldListNoAnalyze.
	 * 
	 * @param phrase
	 *                 - search phrase
	 * @param type
	 *                 - DATEV type of the object
	 * @param clientID
	 *                 - optional restriction to a specific client id
	 *                 (_datev_client_id)
	 * @return - list of matching profiles
	 */
	public List<ItemCollection> searchEntity(String phrase, String type, String clientID) {

		List<ItemCollection> searchResult = new ArrayList<ItemCollection>();

		if (phrase == null || phrase.isEmpty())
			return searchResult;

		Collection<ItemCollection> col = null;
		try {
			phrase = phrase.trim();
			// phrase = LuceneSearchService.escapeSearchTerm(phrase);
			phrase = schemaService.normalizeSearchTerm(phrase);
			String sQuery = "(type:\"" + type + "\"";

			// restrict to client id?
			if (clientID != null && !clientID.isEmpty()) {
				sQuery += " AND _datev_client_id:\"" + clientID + "\"";
			}

			sQuery += ")";

			sQuery += " AND (" + phrase + "*)";

			logger.finest("searchprofile: " + sQuery);

			// start lucene search
			logger.fine("searchWorkitems: " + sQuery);
			col = documentService.find(sQuery, MAX_SEARCH_RESULT, 0);
		} catch (Exception e) {
			logger.warning("  lucene error - " + e.getMessage());
		}

		for (ItemCollection kreditor : col) {
			searchResult.add(kreditor);
		}
		// sort by txtname..
		Collections.sort(searchResult, new ItemCollectionComparator("txtname", true));

		return searchResult;

	}

	/**
	 * This method imports all entities from a csv file. The file must have two
	 * header lines. 1st Line=object type, 2nd line = fieldnames.
	 * <p>
	 * The method runs in a new transaction so processing exceptions can be caught.
	 * <p>
	 * All existing entries not listed in the current file will be removed.
	 * 
	 * <p>
	 * Each imported document will have the item '_datev_client_id' and
	 * '_datev_consultant_id'. These item are mapped to the "Mandant id" and
	 * "Berater id" from Datev. These categories allow the import of data from
	 * different clients and consults. It is important that the fields
	 * '_datev_client_id' and '_datev_consultant_id' are added to the lucene index
	 * so that a search for data of a specific client is possible.
	 * <p>
	 * {@code
	 *   lucence.indexFieldListNoAnalyze=....,_datev_client_id,_datev_consultant_id
	 * }
	 * 
	 * <p>
	 * 
	 * The method returns a log . If an error occurs a plugin exception is thrown
	 * 
	 * @return ErrorMessage or empty String
	 * @throws PluginException
	 */
	@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
	public String importData(InputStream imputStream, String encoding) throws PluginException {
		String type = null;
		String clientID = null;
		String consultenID = null;
		logger.info("...starting csv data import...");
		String log = "";
		int line = 0;
		String dataLine = null;
		List<String> idCache = new ArrayList<>();

		int workitemsTotal = 0;
		int workitemsImported = 0;
		int workitemsUpdated = 0;
		int workitemsDeleted = 0;
		int workitemsFailed = 0;
		int blockSize = 0;

		if (encoding == null) {
			encoding = "UTF-8";
		}

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(imputStream, encoding));

			// read first line containing the object type
			String header1 = in.readLine();
			String[] header1List = header1.split(";(?=([^\"]*\"[^\"]*\")*[^\"]*$)", 99);
			header1List = normalizeValueList(header1List);
			if (header1List == null || header1List.length < 4) {
				throw new PluginException(this.getClass().getName(), IMPORT_ERROR,
						"File Format not supported, 1st line must contain the fromatname in column 4 (type).");
			}
			type = header1List[3];

			if (type == null || type.isEmpty()) {
				throw new PluginException(this.getClass().getName(), IMPORT_ERROR,
						"File Format not supported, 1st line must contain the fromatname (type).");

			}

			clientID = header1List[11];
			consultenID = header1List[12];
			if (clientID == null || clientID.isEmpty() || consultenID == null || consultenID.isEmpty()) {
				throw new PluginException(this.getClass().getName(), IMPORT_ERROR,
						"File Format not supported, 1st line must contain the Mandant and Berater ID.");

			}

			type = type.trim().toLowerCase();
			logger.info("...object type=" + type);
			line++;

			// read the 2nd first line containing the field names
			String fieldnames = in.readLine();
			line++;
			List<String> fields = parseFieldList(fieldnames);
			// the first field is the keyfield
			String keyField = fields.get(0).trim();

			// read content....
			while ((dataLine = in.readLine()) != null) {

				blockSize++;
				line++;
				workitemsTotal++;
				ItemCollection entity = readEntity(dataLine, fields);
				// add client and consulten id
				entity.replaceItemValue("_datev_client_id", clientID);
				entity.replaceItemValue("_datev_consultant_id", consultenID);

				// replace txtName by the key field
				entity.replaceItemValue("txtname", entity.getItemValue(keyField));

				// resolve name for debitor/kreditor
				String name = entity.getItemValueString("_name_(adressattyp_unternehmen)");
				if (name.isEmpty()) {
					name = entity.getItemValueString("_name_(adressattyp_keine_angabe)");
				}
				if (name.isEmpty()) {
					name = entity.getItemValueString("_name_(adressattyp_natürl_person)");
				}
				if (name.isEmpty()) {
					name = entity.getItemValueString("_kurzbezeichnung");
				}
				entity.setItemValue("_name", name);

				// store id into cache
				idCache.add(entity.getItemValueString("txtname"));

				// test if entity already exits....
				ItemCollection oldEntity = findEntityByName(entity.getItemValueString("txtName"), type, clientID);
				if (oldEntity == null) {
					// create new workitem
					saveEntry(entity, type, clientID, consultenID);
					workitemsImported++;
				} else {
					// test if modified....
					if (!isEqualEntity(oldEntity, entity, fields)) {
						logger.fine("update exsting entity: " + oldEntity.getUniqueID());
						// copy all datev entries from the import into the
						// existing entity
						oldEntity.replaceAllItems(entity.getAllItems());
						saveEntry(oldEntity, type, clientID, consultenID);
						workitemsUpdated++;
					}
				}

				if (blockSize >= 100) {
					blockSize = 0;
					logger.info(workitemsTotal + " entries read....");
				}
			}

			logger.info("completed: " + workitemsTotal + " entries successful read");

		} catch (Exception e) {
			// Catch Workflow Exceptions
			workitemsFailed++;
			String sError = "import error at line " + line + ": " + e + " Datensatz=" + dataLine;
			logger.severe(sError);
			throw new PluginException(DatevImportService.class.getName(), DATA_ERROR, sError, e);
		}

		finally {
			// Close the input stream
			try {
				if (imputStream != null) {
					imputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			logger.info("removing deprecated entries...");
			// now we remove all existing entries not listed in the file
			String sQuery = "(type:\"" + type + "\"";
			if (clientID != null && !clientID.isEmpty()) {
				sQuery += " AND _datev_client_id:\"" + clientID + "\"";
			}
			sQuery += ")";
			List<ItemCollection> entries = documentService.find(sQuery, 999, 0);
			if (entries != null && entries.size() > 0) {
				for (ItemCollection entity : entries) {
					String id = entity.getItemValueString("txtname");
					if (!idCache.contains(id)) {
						documentService.remove(entity);
						workitemsDeleted++;
					}
				}
			}
		} catch (QueryException e) {
			// Catch Workflow Exceptions
			String sError = "import error: unable to delete data";
			logger.severe(sError);
			throw new PluginException(DatevImportService.class.getName(), DATA_ERROR, sError, e);
		}

		log += workitemsTotal + " entries read \n" + workitemsImported + " new entries \n" + workitemsUpdated
				+ " updates \n" + workitemsDeleted + " deletions \n" + workitemsFailed + " errors";

		logger.info(log);
		return log;
	}

	/**
	 * This method removes the " from a value list
	 * 
	 * 
	 * @param data
	 * @return
	 */
	public String[] normalizeValueList(String[] data) {

		for (int i = 0; i < data.length; i++) {
			String value = data[i];
			if (value.startsWith("\"") && value.endsWith("\"")) {
				value = value.substring(1, value.length() - 1);
				data[i] = value;
			}

		}
		return data;
	}

	/**
	 * This method parses a DATEV field description (first line of the csv file)
	 * 
	 * @return list of fieldnames
	 */
	public List<String> parseFieldList(String data) {
		List<String> result = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(data, ";");
		while (st.hasMoreTokens()) {
			String field = st.nextToken().trim();
			if (!field.isEmpty()) {
				field = field.replace(".", "");
				field = field.replace(' ', '_');
				field = field.replace('/', '_');
				field = field.replace('\\', '_');
				field = field.replace('.', '_');
				field = field.replace('>', '_');
				field = field.replace('<', '_');
				field = field.replace('&', '_');
				result.add("_" + field.trim());
			} else {
				// add dummy entry
				result.add(null);
			}

		}
		return result;
	}

	/**
	 * This method process a single workIten in a new transaction. The method adds
	 * the type 'kreditor' to the entity.
	 * 
	 * @param aWorkitem
	 * @throws PluginException
	 * @throws ProcessingErrorException
	 * @throws AccessDeniedException
	 * @throws ModelException
	 */
	@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
	public void saveEntry(ItemCollection aWorkitem, String type, String clientID, String consultenID)
			throws AccessDeniedException, ProcessingErrorException, PluginException, ModelException {

		// add type...
		aWorkitem.replaceItemValue("type", type);
		// add client and consult id...
		aWorkitem.replaceItemValue(DatevExportService.ITEM_DATEV_CLIENT_ID, clientID);
		aWorkitem.replaceItemValue(DatevExportService.ITEM_DATEV_CONSULTANT_ID, consultenID);

		documentService.save(aWorkitem);
	}

	/**
	 * This method creates a ItemCollection from a csv file data line
	 * 
	 * The method also creates a txtworkflowabstract to support fulltext search
	 * 
	 * @param data
	 * @param fieldnames
	 * @return
	 */
	public ItemCollection readEntity(String data, List<String> fieldnames) {
		ItemCollection result = new ItemCollection();
		int iCol = 0;
		// @see
		// http://stackoverflow.com/questions/2241758/regarding-java-split-command-parsing-csv-file
		String[] valuList = data.split(";(?=([^\"]*\"[^\"]*\")*[^\"]*$)", 999);
		valuList = normalizeValueList(valuList);
		for (String itemValue : valuList) {
			// test if the token has content
			itemValue = itemValue.trim();
			if (itemValue != null && !itemValue.isEmpty()) {
				// logger.info("...col " + iCol + " name=" +fieldnames.get(iCol) +" value=" +
				// itemValue);
				// create a itemvalue with the corresponding fieldname
				result.replaceItemValue(fieldnames.get(iCol), itemValue);
			} else {
				// empty value
				result.replaceItemValue(fieldnames.get(iCol), "");
			}
			iCol++;
			if (iCol >= fieldnames.size()) {
				break;
			}
		}
		return result;
	}

	/**
	 * This method compares two entities based on the csv fields
	 * 
	 * @param oldEntity
	 * @param entity
	 * @param fields
	 * @return
	 */
	private boolean isEqualEntity(ItemCollection oldEntity, ItemCollection entity, List<String> fields) {

		// test datev client id and consulten id
		if (!entity.getItemValue("_datev_client_id").equals(oldEntity.getItemValue("_datev_client_id"))) {
			// not equal
			return false;
		}
		if (!entity.getItemValue("_datev_consultant_id").equals(oldEntity.getItemValue("_datev_consultant_id"))) {
			// not equal
			return false;
		}

		if (!entity.getItemValue("_name").equals(oldEntity.getItemValue("_name"))) {
			// not equal
			return false;
		}

		for (String itemName : fields) {
			if (!entity.getItemValue(itemName).equals(oldEntity.getItemValue(itemName))) {
				// not equal
				return false;
			}
		}
		return true;
	}
}
