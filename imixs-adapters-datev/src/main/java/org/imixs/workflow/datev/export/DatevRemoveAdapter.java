package org.imixs.workflow.datev.export;

import java.util.List;
import java.util.logging.Logger;

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
 * Der DatevRemoveAdapter enfernt eine Verknüpfung aus
 * einem DATEV Export
 * 
 * @see DatevRefAdapter
 * 
 * @version 1.0
 * @author rsoika
 */
public class DatevRemoveAdapter implements SignalAdapter {

	private static Logger logger = Logger.getLogger(DatevRemoveAdapter.class.getName());

	@Inject
	DatevExportService datevExportService;

	@Inject
	DatevService datevService;

	/**
	 * This method finds the Datev export and removes a reference
	 * ($workitemref) to the current invoice.
	 * 
	 * @throws PluginException
	 */
	@Override
	public ItemCollection execute(ItemCollection document, ItemCollection event)
			throws AdapterException, PluginException {

		removeWorkitem(document);
		return document;
	}

	/**
	 * Diese method entfernt eine referenz der aktuellen Rechnung aus dem Datev
	 * export
	 * 
	 * @param workitem
	 * @throws PluginException
	 */
	@SuppressWarnings("unchecked")
	private void removeWorkitem(ItemCollection workitem) throws PluginException {

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

		String key = datevService.computeKey(workitem, datevClientID);
		logger.info("......Update DATEV export for: '" + key + "'...");
		ItemCollection datevExport;
		try {
			datevExport = datevExportService.findDatevExport(key);
			if (datevExport != null) {
				// Invoice wieder aus dem DATEV Export heruasnehmen
				List<String> refList = datevExport.getItemValue("$workitemref");
				if (refList.contains(workitem.getUniqueID())) {
					refList.remove(workitem.getUniqueID());
					datevExport.setItemValue("$workitemref", refList);
					// set event 100
					datevExport.event(100);
					datevExportService.processDatevExport(datevExport);
				}
			}

		} catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
			throw new PluginException(PluginException.class.getName(), DatevException.DATEV_DATA_ERROR,
					"Es konnte kein DATEV Export zugewiesen werden: " + e1.getMessage());

		}
	}

}