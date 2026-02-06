package org.imixs.workflow.datev.export;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTP;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.datev.DatevException;
import org.imixs.workflow.datev.DatevService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;

import jakarta.inject.Inject;

/**
 * Der DatevDataGroupAdapter verknüpft ein workitem mit einem DATEV Export. Er
 * basiert auf dem Imixs-Data framework.
 * <p>
 * Existiert aktuell kein offener DATEV Export für die Buchungsperiode, erzeugt
 * der Adapter automatisch eine neue Prozessinstanz.
 * <p>
 * Wurde der Beleg bereits einem DATEV Export zugeordnet passiert nichts.
 * <p>
 * Existieren innerhalb der Rechnung noch keien Detailbuchugnssätze (ChildItems)
 * dann erzeugt der Adapter einen Default Buchungssatz .
 * 
 * Example to add a workitem to a data group:
 * 
 * <pre>
 * {@code
<datev name="ADD">
    <query>(type:workitem) AND ($modelversion:sepa-export-manual*)</query>
    <init.model>sepa-export-manual-1.0</init.model>
    <init.task>1000</init.task>
    <init.event>10</init.event>
    <!-- Optional -->
    <update.event>20</update.event>
</imixs-data-group>
 * }
 * </pre>
 * 
 * Example to execute all referred workitem in a data group:
 * 
 * <pre>
 * {@code
<datev name="EXECUTE">
    <event>20</event>
</imixs-data-group>
 * }
 * </pre>
 * 
 * @version 1.0
 * @author rsoika
 */
public class DatevDataGroupAdapter implements SignalAdapter {

	private static Logger logger = Logger.getLogger(DatevDataGroupAdapter.class.getName());

	public static final String MODE_ADD = "add";
	public static final String MODE_REMOVE = "remove";
	public static final String MODE_EXECUTE = "execute";
	public static final String ITEM_WORKITEMREF = "$workitemref";
	public static final String API_ERROR = "API_ERROR";
	public static final String ERROR_MISSING_DATA = "MISSING_DATA";

	@Inject
	@ConfigProperty(name = "datev.defaultkonto", defaultValue = "1370")
	private String datevDefaultKonto;

	@Inject
	DatevExportService datevExportService;

	@Inject
	DatevService datevService;

	@Inject
	private WorkflowService workflowService;

	/**
	 * This method finds or create the Datev Export and adds a reference
	 * ($workitemref) to the current workitem.
	 * 
	 * @throws PluginException
	 */
	@Override
	public ItemCollection execute(ItemCollection workitem, ItemCollection event)
			throws AdapterException, PluginException {

		long processingTime = System.currentTimeMillis();
		logger.info("├── DATEV Update...");
		// read optional configuration form the model or imixs.properties....

		List<ItemCollection> addDefinitions = workflowService.evalWorkflowResultXML(event, "datev",
				MODE_ADD, workitem, true);
		List<ItemCollection> removeDefinitions = workflowService.evalWorkflowResultXML(event, "datev",
				MODE_REMOVE, workitem, true);
		List<ItemCollection> executeDefinitions = workflowService.evalWorkflowResultXML(event, "datev",
				MODE_EXECUTE, workitem, true);

		/**
		 * Iterate over each definition and process the data group
		 */
		if (addDefinitions != null) {
			for (ItemCollection groupDefinition : addDefinitions) {
				validateDefaultBooking(workitem);
				appendBeleg(workitem, groupDefinition);
			}

		}

		// verify REMOVE mode
		if (removeDefinitions != null) {
			for (ItemCollection groupDefinition : removeDefinitions) {
				removeBeleg(workitem, groupDefinition);
			}
		}

		// verify EXECUTE mode
		if (executeDefinitions != null) {
			for (ItemCollection groupDefinition : executeDefinitions) {
				executeBuchungsstapel(workitem, groupDefinition);
			}
		}

		logger.info("├── ✅ completed (" + (System.currentTimeMillis() - processingTime) + "ms)");

		return workitem;
	}

	/**
	 * Diese method verknüpft die aktuelle Rechnung mit dem DATEV Export
	 * 
	 * @param workitem
	 * @throws PluginException
	 */
	private void appendBeleg(ItemCollection workitem, ItemCollection groupDefinition) throws PluginException {

		boolean debug = groupDefinition.getItemValueBoolean("debug");
		String initModel = groupDefinition.getItemValueString("init.model");
		int initTaskId = groupDefinition.getItemValueInteger("init.task");
		int initEventId = groupDefinition.getItemValueInteger("init.event");
		String itemList = groupDefinition.getItemValueString("init.items");
		int updateEventId = groupDefinition.getItemValueInteger("update.event");

		logger.info("│   ├── append Beleg '" + workitem.getUniqueID() + "'...");

		ItemCollection datevConfig = datevService.loadConfiguration();
		if (datevConfig == null) {
			throw new PluginException(PluginException.class.getName(), DatevException.DATEV_CONFIG_ERROR,
					"Datev Export kann nicht erzeugt werden da keine DATEV Konfiguration vorliegt.");
		}

		String datevClientID = datevConfig.getItemValueString(DatevService.ITEM_DATEV_CLIENT_ID);

		if (datevClientID == null || datevClientID.isEmpty()) {
			throw new PluginException(PluginException.class.getName(), DatevException.DATEV_CONFIG_ERROR,
					"Datev Export kann nicht erzeugt werden da keine DATEV Client ID definiert wurde. Bitte prüfen Sie die DATEV Konfiguration.");
		}

		if (initTaskId == 0 || initEventId == 0 || initModel.isEmpty()) {
			throw new PluginException(PluginException.class.getName(), DatevException.DATEV_CONFIG_ERROR,
					"Fehlerhafte DATEV Konfiguration - init.model, init.task, init.event required! Bitte prüfen Sie das Model.");
		}

		String key = datevService.computeKey(workitem, datevClientID);

		logger.info("│   ├── Buchungsstapel: " + key);

		// Optional - Berechnung der Buchungsperiode
		DateFormat df = new SimpleDateFormat("yyyy/MM");
		Date datBelegdatum = workitem.getItemValueDate(DatevService.ITEM_DATEV_BELEGDATUM);
		String keyPeriode = df.format(datBelegdatum);
		logger.info("│   ├── Buchungsperiode: " + keyPeriode);
		ItemCollection datevExport;
		try {
			datevExport = datevExportService.findDatevExport(key);
			if (datevExport == null) {
				if (debug) {
					logger.info(
							"│   ├── Erstelle neuen Buchungsstapel " + initModel + " " + initTaskId + "."
									+ initEventId);
				}
				// create a new one
				datevExport = new ItemCollection().model(initModel).task(initTaskId);
				// add cdtr.name
				datevExport.setItemValue(DatevService.ITEM_DATEV_CLIENT_ID,
						datevConfig.getItemValue(DatevService.ITEM_DATEV_CLIENT_ID));
				datevExport.setItemValue(DatevService.ITEM_DATEV_CLIENT_NAME,
						datevConfig.getItemValue(DatevService.ITEM_DATEV_CLIENT_NAME));

				// set consultant ID
				datevExport.setItemValue(DatevService.ITEM_DATEV_CONSULTANT_ID,
						datevConfig.getItemValue(DatevService.ITEM_DATEV_CONSULTANT_ID));
				datevExport.setItemValue(DatevService.ITEM_DATEV_FISCAL_START,
						datevConfig.getItemValue(DatevService.ITEM_DATEV_FISCAL_START));

				datevExport.setItemValue(DatevService.ITEM_DATEV_BOOKING_PERIOD, keyPeriode);
				datevExport.setItemValue("name", key);
				datevExport.event(initEventId);

				copyItemList(itemList, workitem, datevExport);
				datevExport = datevExportService.processDatevExport(datevExport);
			} else {
				// Optional process datev export
				if (updateEventId > 0) {
					datevExport.event(updateEventId);
					datevExport = datevExportService.processDatevExport(datevExport);
				}
			}

			// Workitem mit DATEV export verknüpften
			workitem.appendItemValueUnique(ITEM_WORKITEMREF, datevExport.getUniqueID());

		} catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
			throw new DatevException(DatevDataGroupAdapter.class.getName(), DatevException.DATEV_CONFIG_ERROR,
					"Es konnte kein DATEV Export zugewiesen werden: " + e1.getMessage());

		}
	}

	/**
	 * Diese method entfernt eine bestehende Verknüpfung der aktuellen Rechnung mit
	 * dem DATEV Export
	 * 
	 * @param workitem
	 * @throws PluginException
	 */
	@SuppressWarnings("unchecked")
	private void removeBeleg(ItemCollection workitem, ItemCollection groupDefinition) throws PluginException {
		boolean debug = groupDefinition.getItemValueBoolean("debug");

		ItemCollection datevConfig = datevService.loadConfiguration();
		String datevClientID = datevConfig.getItemValueString(DatevService.ITEM_DATEV_CLIENT_ID);

		if (datevClientID == null || datevClientID.isEmpty()) {
			throw new PluginException(PluginException.class.getName(), DatevException.DATEV_CONFIG_ERROR,
					"Datev Export kann nicht erzeugt werden da keine DATEV Client ID definiert wurde. Bitte prüfen Sie die DATEV Konfiguration.");
		}
		String key = datevService.computeKey(workitem, datevClientID);

		// find group
		try {
			ItemCollection datevExport = datevExportService.findDatevExport(key);
			if (datevExport != null) {
				if (debug) {
					logger.info(
							"│   ├── remove workitem '" + workitem.getUniqueID() + "' from datevexport "
									+ datevExport.getUniqueID());
				}
				List<String> refList = workitem.getItemValue(ITEM_WORKITEMREF);
				while (refList.contains(datevExport.getUniqueID())) {
					refList.remove(datevExport.getUniqueID());
					workitem.setItemValue(ITEM_WORKITEMREF, refList);
				}

			} else {
				logger.info(
						"│   ├── ⚠️ no matching datev export found");
			}

		} catch (QueryException | ProcessingErrorException e) {
			logger.warning("├── ⚠️ Failed to update dataGroup: " + e.getMessage());
			throw new PluginException(DatevDataGroupAdapter.class.getName(),
					API_ERROR,
					"⚠️ Failed to update dataGroup: " + e.getMessage(), e);
		}
	}

	/**
	 * Diese Methode erzeugt den Buchungsstapel für den Export.
	 * Die Method aktualisiert optional auch die Beleg Workitems.
	 * 
	 * @param groupDefinition
	 * @param datevExport
	 * @throws PluginException
	 */
	private void executeBuchungsstapel(ItemCollection datevExport, ItemCollection groupDefinition)
			throws PluginException {
		String modelVersion = datevExport.getModelVersion();
		if (modelVersion.startsWith("datev-export-de-2.") || modelVersion.startsWith("datev-export-de-1.")) {
			throw new PluginException(PluginException.class.getName(), DatevException.DATEV_CONFIG_ERROR,
					"DatevDataGroupAdapter erfordert Model Verison datev-export-de-3.0 oder höher.");
		}
		logger.info("│   ├── execute Buchungsstapel...");

		int updateEventId = groupDefinition.getItemValueInteger("update.event");

		ItemCollection configuration = datevService.loadConfiguration();
		// get the data source based on the $workitemref....
		List<ItemCollection> masterDataSet = datevService.getBuchungsstapel(datevExport);
		// first we need to extend the Export Workitem
		datevExportService.updateExportWorkitem(datevExport, configuration, masterDataSet);
		String datevClientID = datevExport.getItemValueString(DatevService.ITEM_DATEV_CLIENT_ID);

		if (masterDataSet.size() > 0) {
			// =====================================
			// 2nd export buchungsstapel via CSV
			// =====================================
			datevExportService.buildCSVFile(datevExport, masterDataSet, datevClientID, configuration);

			// =====================================
			// 3nd create export workitem with attached zip file....
			// =====================================
			datevExportService.buildDocumentsZipFile(datevExport, masterDataSet, datevClientID, configuration);

			// finally copy attachments via FTP...
			List<FileData> files = datevExport.getFileData();
			for (FileData filedata : files) {
				if (filedata.getName().endsWith(".csv")) {
					datevExportService.putFileData(filedata, configuration,
							configuration.getItemValueString("_datev_ftp_path_buchungsstapel"), datevExport,
							FTP.ASCII_FILE_TYPE);
				}
				if (filedata.getName().endsWith(".zip")) {
					datevExportService.putFileData(filedata, configuration,
							configuration.getItemValueString("_datev_ftp_path_belege"), datevExport,
							FTP.BINARY_FILE_TYPE);
				}

			}

			// Optional - alle belege aktualisieren
			if (updateEventId > 0) {
				for (ItemCollection beleg : masterDataSet) {
					try {
						beleg.setEventID(updateEventId);
						workflowService.processWorkItem(beleg);
					} catch (AccessDeniedException | ProcessingErrorException | ModelException e) {
						logger.warning("│   ├── Beleg '" + beleg.getUniqueID() + "' konnte nicht aktualisiert werden: "
								+ e.getMessage());
					}

				}
			}
		}

	}

	/**
	 * Diese Hilfsmethode erzeugt eine Default Buchung wenn bisher keine
	 * Splitbuchungen bei der Vorkontierung eingetragen wurden.
	 * <p>
	 * 
	 * 
	 * @param workitem
	 */
	private void validateDefaultBooking(ItemCollection workitem) {

		List<ItemCollection> buchunssaetze = explodeChildList(workitem);
		if (buchunssaetze.size() == 0
				|| (buchunssaetze.size() == 1
						&& buchunssaetze.get(0).getItemValueString(DatevService.ITEM_DATEV_KONTO).isEmpty())) {
			// create a default booking item.
			ItemCollection bookingItem = null;
			if (buchunssaetze.size() == 0) {
				bookingItem = new ItemCollection();
			} else {
				bookingItem = buchunssaetze.get(0);
			}
			bookingItem.setItemValue(DatevService.ITEM_DATEV_KONTO, datevDefaultKonto);
			bookingItem.setItemValue(DatevService.ITEM_DATEV_BETRAG,
					workitem.getItemValue(DatevService.ITEM_DATEV_BETRAG));

			if (buchunssaetze.size() == 1) {
				buchunssaetze.remove(0);
			}
			buchunssaetze.add(bookingItem);

		}

		// write data back...
		implodeChildList(workitem, buchunssaetze);
	}

	/**
	 * Convert the List of ItemCollections back into a List of Map elements
	 * 
	 * @param workitem
	 */
	@SuppressWarnings({ "rawtypes" })
	protected void implodeChildList(ItemCollection workitem, List<ItemCollection> childItems) {
		List<Map> mapOrderItems = new ArrayList<Map>();
		// convert the child ItemCollection elements into a List of Map
		if (childItems != null) {
			logger.fine("Convert child items into Map...");
			// iterate over all order items..
			for (ItemCollection orderItem : childItems) {
				mapOrderItems.add(orderItem.getAllItems());
			}
			workitem.replaceItemValue(DatevService.ITEM_DATEV_BOOKING_LIST, mapOrderItems);
		}
	}

	/**
	 * converts the Map List of a workitem into a List of ItemCollectons
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<ItemCollection> explodeChildList(ItemCollection workitem) {
		// convert current list of childItems into ItemCollection elements
		ArrayList<ItemCollection> childItems = new ArrayList<ItemCollection>();

		List<Object> mapOrderItems = workitem.getItemValue(DatevService.ITEM_DATEV_BOOKING_LIST);
		int pos = 1;
		for (Object mapOderItem : mapOrderItems) {

			if (mapOderItem instanceof Map) {
				ItemCollection itemCol = new ItemCollection((Map) mapOderItem);
				itemCol.replaceItemValue("numPos", pos);
				childItems.add(itemCol);
				pos++;
			}
		}

		return childItems;
	}

	/**
	 * This Method copies the fields defined in 'items' into the targetWorkitem.
	 * Multiple values are separated with comma ','.
	 * <p>
	 * In case a item name contains '|' the target field name will become the right
	 * part of the item name.
	 * <p>
	 * Example: {@code
	 *   txttitle,txtfirstname
	 *   
	 *   txttitle|newitem1,txtfirstname|newitem2
	 *   
	 * }
	 * 
	 * <p>
	 * Optional also reg expressions are supported. In this case mapping of the item
	 * name is not supported.
	 * <p>
	 * Example: {@code
	 *   (^artikel$|^invoice$),txtTitel|txtNewTitel
	 *   
	 *   
	 * } A reg expression must be includes in brackets.
	 * 
	 */
	protected void copyItemList(String items, ItemCollection source, ItemCollection target) {
		// clone the field list...
		logger.info("copy itemlist: " + items);
		StringTokenizer st = new StringTokenizer(items, ",");
		while (st.hasMoreTokens()) {
			String field = st.nextToken().trim();

			// test if field is a reg ex
			if (field.startsWith("(") && field.endsWith(")")) {
				Pattern itemPattern = Pattern.compile(field);
				Map<String, List<Object>> map = source.getAllItems();
				for (String itemName : map.keySet()) {
					if (itemPattern.matcher(itemName).find()) {
						target.replaceItemValue(itemName, source.getItemValue(itemName));
					}
				}

			} else {
				// default behavior without reg ex
				int pos = field.indexOf('|');
				if (pos > -1) {
					target.replaceItemValue(field.substring(pos + 1).trim(),
							source.getItemValue(field.substring(0, pos).trim()));
				} else {
					target.replaceItemValue(field, source.getItemValue(field));
				}
			}
		}
	}

}