package org.imixs.workflow.datev.export;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;

import jakarta.inject.Inject;

/**
 * Der KriegerDatevRemoveAdapter enfernt die Verknüpfung einer Rechnung mit
 * einem DATEV Export
 * 
 * @see DatevRefAdapter
 * 
 * @version 1.0
 * @author rsoika
 */
public class DatevRemoveAdapter implements SignalAdapter {

	private static Logger logger = Logger.getLogger(DatevRemoveAdapter.class.getName());

	public static final String ERROR_MISSING_DATA = "MISSING_DATA";
	public static final String ERROR_CONFIG = "CONFIG_ERROR";

	public static final String ITEM_DATEV_CLIENT_ID = "_datev_client_id";
	public static final String ITEM_DATEV_BOOKING_PERIOD = "_datev_booking_period";
	public static final String ITEM_DBTR_NAME = "_dbtr_name";
	public static final String CHILD_ITEM_PROPERTY = "_ChildItems";

	@Inject
	DatevExportService datevExportService;

	/**
	 * This method finds the Datev export and removes a reference
	 * ($workitemref) to the current invoice.
	 * 
	 * @throws PluginException
	 */
	@Override
	public ItemCollection execute(ItemCollection document, ItemCollection event)
			throws AdapterException, PluginException {

		removeInvoice(document);
		return document;
	}

	/**
	 * Diese method entfernt eine referenz der aktuellen Rechnung aus dem Datev
	 * export
	 * 
	 * @param invoice
	 * @throws PluginException
	 */
	@SuppressWarnings("unchecked")
	private void removeInvoice(ItemCollection invoice) throws PluginException {

		ItemCollection datevConfig = datevExportService.loadConfiguration(DatevExportService.DEFAULT_CONFIG_NAME);
		if (datevConfig == null) {
			throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
					"Datev Export kann nicht erzeugt werden da keine DATEV Konfiguration vorliegt.");
		}

		String datevClientID = invoice.getItemValueString(ITEM_DATEV_CLIENT_ID);

		if (datevClientID == null || datevClientID.isEmpty()) {
			throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
					"Datev Export kann nicht korrigiert werden da keine DATEV Client ID definiert wurde. Bitte prüfen Sie die DATEV Konfiguration.");
		}

		Date datInvoice = invoice.getItemValueDate("_invoicedate");

		if (datInvoice == null) {
			throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
					"Datev Export kann nicht erzeugt werden da kein Buchungsdatum angegeben wurde.");
		}
		String key = datevExportService.computeKey(invoice, datevClientID);

		logger.info("......Update DATEV export for: '" + key + "'...");
		ItemCollection datevExport;
		try {
			datevExport = datevExportService.findDatevExport(key);
			if (datevExport != null) {
				// Invoice wieder aus dem DATEV Export heruasnehmen
				List<String> refList = datevExport.getItemValue("$workitemref");
				if (refList.contains(invoice.getUniqueID())) {
					refList.remove(invoice.getUniqueID());
					datevExport.setItemValue("$workitemref", refList);
					// set event 100
					datevExport.event(100);
					datevExportService.processDatevExport(datevExport);
				}
			}

		} catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
			throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
					"Es konnte kein DATEV Export zugewiesen werden: " + e1.getMessage());

		}
	}

}