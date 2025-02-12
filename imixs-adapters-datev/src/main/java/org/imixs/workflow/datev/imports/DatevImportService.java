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
import org.imixs.workflow.datev.DatevException;
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
 * client. NOTE: the item datev.client.id must be part of the property
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

	public static final int MAX_SEARCH_RESULT = 100;
	public final static String ISO8601_FORMAT_DATETIME = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public final static String ISO8601_FORMAT_DATE = "yyyy-MM-dd";

	@EJB
	DocumentService documentService;

	@EJB
	SchemaService schemaService;

	private static Logger logger = Logger.getLogger(DatevImportService.class.getName());

	/**
	 * This method finds a datev entity by the attribute 'name'
	 * <p>
	 * The name includes the datev.consulten.id and datev.client.id
	 * 
	 * @param key
	 *             - name of the object
	 * @param type
	 *             - DATEV type of the object
	 * 
	 * @return DATEV entity or null if no entity with the given name exits
	 */
	public ItemCollection findEntityByPrimaryKey(String key, String type) {

		String searchTerm = "(type:\"" + type + "\" AND name:\"" + key + "\")";
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
	 * client. NOTE: the item datev.client.id must be part of the property
	 * lucence.indexFieldListNoAnalyze.
	 * 
	 * @param phrase
	 *                 - search phrase
	 * @param type
	 *                 - DATEV type of the object
	 * @param clientID
	 *                 - optional restriction to a specific client id
	 *                 (datev.client.id)
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
				sQuery += " AND datev.client.id:\"" + clientID + "\"";
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
		// sort by name..
		Collections.sort(searchResult, new ItemCollectionComparator("name", true));

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
	 * Each imported document will have the item 'datev.client.id' and
	 * '_datev_consultant_id'. These item are mapped to the "Mandant id" and
	 * "Berater id" from Datev. These categories allow the import of data from
	 * different clients and consults. It is important that the fields
	 * 'datev.client.id' and '_datev_consultant_id' are added to the lucene index
	 * so that a search for data of a specific client is possible.
	 * <p>
	 * {@code
	 *   lucence.indexFieldListNoAnalyze=....,datev.client.id,_datev_consultant_id
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
		StringBuffer log = new StringBuffer();
		int line = 0;
		long l = System.currentTimeMillis();
		String dataLine = null;
		List<String> idCache = new ArrayList<>();

		int workitemsTotal = 0;
		int workitemsImported = 0;
		int workitemsUpdated = 0;
		int workitemsDeleted = 0;
		int workitemsFailed = 0;
		int blockSize = 0;

		log(log, "├── starte Dateimport");
		if (encoding == null) {
			encoding = "UTF-8";
		}
		log(log, "│   ├── encoding=" + encoding);

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(imputStream, encoding));

			// read first line containing the object type
			String header1 = in.readLine();
			String[] header1List = header1.split(";(?=([^\"]*\"[^\"]*\")*[^\"]*$)", 99);
			header1List = normalizeValueList(header1List);
			if (header1List == null || header1List.length < 4) {
				throw new PluginException(this.getClass().getName(), DatevException.DATEV_IMPORT_ERROR,
						"File Format not supported, 1st line must contain the data format in column 4 (type).");
			}
			type = header1List[3];

			if (type == null || type.isEmpty()) {
				throw new PluginException(this.getClass().getName(), DatevException.DATEV_IMPORT_ERROR,
						"File Format not supported, 1st line must contain the data format in column 4 (type).");

			}

			clientID = header1List[10];
			log(log, "│   ├── Mandant ID= " + clientID);
			consultenID = header1List[11];
			log(log, "│   ├── Berater ID= " + consultenID);
			if (clientID == null || clientID.isEmpty() || consultenID == null || consultenID.isEmpty()) {
				throw new PluginException(this.getClass().getName(), DatevException.DATEV_IMPORT_ERROR,
						"File Format not supported, 1st line must contain the Mandant and Berater ID.");
			}

			type = type.trim().toLowerCase();
			log(log, "│   └── Objekt Type=" + type);
			line++;

			log(log, "├── Lese Daten...");
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
				entity.replaceItemValue("datev.client.id", clientID);
				entity.replaceItemValue("datev.consultant.id", consultenID);

				// replace Name by the key field
				String keyValue = entity.getItemValueString(keyField);

				// resolve name for _kontobeschriftung or debitor/kreditor
				String name = entity.getItemValueString("_kontobeschriftung");
				if (name.isEmpty()) {
					name = entity.getItemValueString("_name_(adressattyp_unternehmen)");
				}
				if (name.isEmpty()) {
					name = entity.getItemValueString("_name_(adressattyp_keine_angabe)");
				}
				if (name.isEmpty()) {
					name = entity.getItemValueString("_name_(adressattyp_natürl_person)");
				}
				if (name.isEmpty()) {
					name = entity.getItemValueString("_kurzbezeichnung");
				}
				if (name.isEmpty()) {
					// should not happen!
					name = entity.getItemValueString(keyValue);
				}
				entity.setItemValue("_name", name);

				// create unique 'name' item
				String masterKey = consultenID + "_" + clientID + "_" + keyValue;
				entity.setItemValue("name", masterKey);
				// store id into cache
				idCache.add(masterKey);

				// test if entity already exits....
				ItemCollection oldEntity = findEntityByPrimaryKey(masterKey, type);
				if (oldEntity == null) {
					// create new workitem
					saveEntry(entity, type, clientID, consultenID);
					workitemsImported++;
				} else {
					// test if modified....
					if (!isEqualEntity(oldEntity, entity, fields)) {
						logger.fine("update existing entity: " + oldEntity.getUniqueID());
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

		} catch (Exception e) {
			// Catch Workflow Exceptions
			workitemsFailed++;
			String sError = "import error at line " + line + ": " + e + " Datensatz=" + dataLine;
			log(log, sError);
			throw new PluginException(DatevImportService.class.getName(), DatevException.DATEV_DATA_ERROR, sError, e);
		} finally {
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
			log(log, "├── Lösche veraltete Daten...");
			// now we remove all existing entries not listed in the file
			String sQuery = "(type:\"" + type + "\"";
			if (clientID != null && !clientID.isEmpty()) {
				sQuery += " AND datev.client.id:\"" + clientID + "\"";
			}
			sQuery += ")";
			List<ItemCollection> entries = documentService.find(sQuery, 999, 0);
			if (entries != null && entries.size() > 0) {
				for (ItemCollection entity : entries) {
					String id = entity.getItemValueString("name");
					if (!idCache.contains(id)) {
						documentService.remove(entity);
						workitemsDeleted++;
					}
				}
			}
		} catch (QueryException e) {
			// Catch Workflow Exceptions
			String sError = "import error: unable to delete data";
			log(log, sError);
			throw new PluginException(DatevImportService.class.getName(), DatevException.DATEV_DATA_ERROR, sError, e);
		}

		log(log, "│   ├── " + workitemsTotal + " Einträge gelesen ");
		log(log, "│   ├── " + workitemsImported + " neue Einträge  ");
		log(log, "│   ├── " + workitemsUpdated + " aktualisierte Einträge  ");
		log(log, "│   ├── " + workitemsDeleted + " gelöschte Einträge  ");
		log(log, "│   └── " + workitemsFailed + " fehlerhafte Einträge  ");
		log(log, "└── Abgeschlossen in " + (System.currentTimeMillis() - l) + " ms");
		return log.toString();
	}

	private void log(StringBuffer log, String message) {
		log.append(message + "\n");
		logger.info(message);
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
		aWorkitem.replaceItemValue(DatevService.ITEM_DATEV_CLIENT_ID, clientID);
		aWorkitem.replaceItemValue(DatevService.ITEM_DATEV_CONSULTANT_ID, consultenID);

		// no snapshot
		aWorkitem.setItemValue("$nosnapshot", true);

		// Build summary
		if ("kontenbeschriftungen".equals(type)) {
			String summary = aWorkitem.getItemValueString("_konto") + " "
					+ aWorkitem.getItemValueString("_kontobeschriftung");
			aWorkitem.setItemValue("$workflowsummary", summary);
		}
		if ("debitoren/kreditoren".equals(type)) {
			String summary = aWorkitem.getItemValueString("_konto") + " "
					+ aWorkitem.getItemValueString("_name");
			aWorkitem.setItemValue("$workflowsummary", summary);
		}
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
		if (!entity.getItemValue("datev.client.id").equals(oldEntity.getItemValue("datev.client.id"))) {
			// not equal
			return false;
		}
		if (!entity.getItemValue("datev.consultant.id").equals(oldEntity.getItemValue("datev.consultant.id"))) {
			// not equal
			return false;
		}

		if (!entity.getItemValue("name").equals(oldEntity.getItemValue("name"))) {
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
