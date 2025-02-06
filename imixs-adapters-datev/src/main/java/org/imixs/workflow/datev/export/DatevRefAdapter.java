package org.imixs.workflow.datev.export;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.datev.DatevException;
import org.imixs.workflow.datev.imports.DatevService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;

import jakarta.inject.Inject;

/**
 * Der DATEVRefAdapter verknüpft eine Rechnung mit einem DATEV Export zum
 * aktuellen Kreiditor und der Buchungsperiode. Existiert aktuell kein offener
 * DATEV Export für diesen Kreditor/Buchungsperiode, erzeugt der Adapter
 * automatisch eine neue Prozessinstanz.
 * <p>
 * Wurde die Rechnugn bereits einem DATEV Export zugeordnet passiert nichts.
 * <p>
 * Existieren innerhalb der Rechnung noch keien Detailbuchugnssätze (ChildItems)
 * dann erzeugt der Adaper einen Default Buchungssatz .
 * 
 * 
 * @version 1.0
 * @author rsoika
 */
public class DatevRefAdapter implements SignalAdapter {

	private static Logger logger = Logger.getLogger(DatevRefAdapter.class.getName());

	public static final String ERROR_MISSING_DATA = "MISSING_DATA";
	public static final String ERROR_CONFIG = "CONFIG_ERROR";

	@Inject
	@ConfigProperty(name = "datev.defaultkonto", defaultValue = "1370")
	private String datevDefaultKonto;

	@Inject
	DatevExportService datevExportService;

	@Inject
	DatevService datevService;

	/**
	 * This method finds or create the Datev Export and adds a reference
	 * ($workitemref) to the current invoice.
	 * 
	 * @throws PluginException
	 */
	@Override
	public ItemCollection execute(ItemCollection document, ItemCollection event)
			throws AdapterException, PluginException {

		validateDefaultBooking(document);
		appendInvoice(document);
		return document;
	}

	/**
	 * Diese method hängt eine referenz der aktuellen Rechnung an den DATEV Export
	 * 
	 * @param invoice
	 * @throws PluginException
	 */
	@SuppressWarnings("unchecked")
	private void appendInvoice(ItemCollection invoice) throws PluginException {

		ItemCollection datevConfig = datevService.loadConfiguration();
		if (datevConfig == null) {
			throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
					"Datev Export kann nicht erzeugt werden da keine DATEV Konfiguration vorliegt.");
		}

		String datevClientID = datevConfig.getItemValueString(DatevService.ITEM_DATEV_CLIENT_ID);

		if (datevClientID == null || datevClientID.isEmpty()) {
			throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
					"Datev Export kann nicht erzeugt werden da keine DATEV Client ID definiert wurde. Bitte prüfen Sie die DATEV Konfiguration.");
		}

		Date datInvoice = invoice.getItemValueDate("invoice.date");
		if (datInvoice == null) {
			throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
					"Datev Export kann nicht erzeugt werden da kein Buchungsdatum angegeben wurde.");
		}

		String key = datevExportService.computeKey(invoice, datevClientID);
		// Optional - Berechnung der Buchungsperiode
		DateFormat df = new SimpleDateFormat("yyyy/MM");
		String keyPeriode = df.format(datInvoice);

		logger.info("......Update DATEV export for: '" + key + "'...");
		ItemCollection datevExport;
		try {
			datevExport = datevExportService.findDatevExport(key);
			if (datevExport == null) {
				// create a new one
				datevExport = new ItemCollection().workflowGroup("DATEV-Export").task(1000);
				// add cdtr.name
				datevExport.setItemValue(DatevService.ITEM_DATEV_CLIENT_ID,
						datevConfig.getItemValue(DatevService.ITEM_DATEV_CLIENT_ID));
				datevExport.setItemValue(DatevService.ITEM_DATEV_CLIENT_NAME,
						datevConfig.getItemValue(DatevService.ITEM_DATEV_CLIENT_NAME));

				// set consultant ID
				datevExport.setItemValue(DatevService.ITEM_DATEV_CONSULTANT_ID,
						datevConfig.getItemValue(DatevService.ITEM_DATEV_CONSULTANT_ID));

				datevExport.setItemValue(DatevService.ITEM_DATEV_BOOKING_PERIOD, keyPeriode);
				datevExport.setItemValue("name", key);
			}

			// Invoice mit DATEV export verknüpften (falls noch nicht verknüpft)
			List<String> refList = datevExport.getItemValue("$workitemref");
			if (!refList.contains(invoice.getUniqueID())) {
				datevExport.appendItemValueUnique("$workitemref", invoice.getUniqueID());
				// set event 100
				datevExport.event(100);
				datevExportService.processDatevExport(datevExport);
			}

		} catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
			throw new DatevException(DatevRefAdapter.class.getName(), ERROR_MISSING_DATA,
					"Es konnte kein DATEV Export zugewiesen werden: " + e1.getMessage());

		}
	}

	/**
	 * Diese Hilfsmethode erzeugt eine Default Buchung wenn bisher keine
	 * Splitbuchungen bei der Vorkontierung eingetragen wurden.
	 * <p>
	 * 
	 * 
	 * @param invoice
	 */
	private void validateDefaultBooking(ItemCollection invoice) {

		List<ItemCollection> buchunssaetze = explodeChildList(invoice);
		if (buchunssaetze.size() == 0
				|| (buchunssaetze.size() == 1 && buchunssaetze.get(0).getItemValueString("_konto").isEmpty())) {
			// create a default booking item.
			ItemCollection bookingItem = null;
			if (buchunssaetze.size() == 0) {
				bookingItem = new ItemCollection();
			} else {
				bookingItem = buchunssaetze.get(0);
			}
			bookingItem.setItemValue("_konto", datevDefaultKonto);
			bookingItem.setItemValue("_amount", invoice.getItemValue("invoice.amount"));

			if (buchunssaetze.size() == 1) {
				buchunssaetze.remove(0);
			}
			buchunssaetze.add(bookingItem);

		}

		// write data back...
		implodeChildList(invoice, buchunssaetze);
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
			workitem.replaceItemValue(DatevExportService.CHILD_ITEM_PROPERTY, mapOrderItems);
		}
	}

	/**
	 * converts the Map List of a workitem into a List of ItemCollectons
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<ItemCollection> explodeChildList(ItemCollection workitem) {
		// convert current list of childItems into ItemCollection elements
		ArrayList<ItemCollection> childItems = new ArrayList<ItemCollection>();

		List<Object> mapOrderItems = workitem.getItemValue(DatevExportService.CHILD_ITEM_PROPERTY);
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
}