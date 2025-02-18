package org.imixs.workflow.datev.export;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.net.ftp.FTP;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.ItemCollectionComparator;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.datev.DatevException;
import org.imixs.workflow.datev.DatevService;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;

import jakarta.inject.Inject;

/**
 * Der DATEVExportAdapter erstellt einen DATEV Export zu allen im Datev-Export
 * worktiem referenzierten Rechnungen. Ein Export besteht aus einer CSV Datei
 * mit den Buchungszeilen und einer ZIP Datei mit den Rechnungs PDF Dokumenten.
 * <p>
 * Der DATEVExportAdapter fasst die Funktion der CSV und XML Schnittstelle in
 * einem Prozesschritt zusammen und überträgt die Daten auf einen FTP Server.
 * 
 * <p>
 * ***************************************************************************
 * HINTERGRUND FÜR DATEV Modul "Belege Online"
 * ***************************************************************************
 * <p>
 * Die vom Workflowsystem so generierte ZIP Datei kann anschließend über das
 * DATEV Modul "Unternehmen Online" importiert werden.
 * <p>
 * Der DATEV Import über "Unternehmen Online" bietet dem Anwender keine oder
 * unzureichende Möglichkeiten zu überpüfen, ob alle Datensätze importiert
 * wurden bzw. ob beim Import einzelner Datensätze ein Fehler auftrat. Um den
 * Prozess zu verbessern und gleichzeitig zu vereinfachen, sollten künftig die
 * Buchungssätze direkt mit dem DATEV Modul "Belege Online" importiert werden.
 * Der Umweg über das Modul "Unternehmen Online" soll vermieden werden und
 * dadurch der Improt beschleunigt werden.
 * <p>
 * Eine wesentliche Funktion dieses neuen DATEV Imports besteht darin, dass
 * DATEV ein zuvor definiertes Import Verzeichnis überwachen kann und den Import
 * automatisch anstößt. Dies hilft den Ablauf innerhalb der Steuerkanzlei zu
 * beschleunigen.
 * <p>
 * Damit das neue Verfahren genutzt werden kann sind folgende Änderungen
 * notwendig:
 * <p>
 * Die bisherige XML Datei document.xml wird um zusätzliche Attribute ergänzt,
 * welche einen Import über "Unternehmen Online" ermöglichen Es wird ein neues
 * ZIP Format festgelegt in dem nur noch die Belege und die Document.xml Datei
 * zusammengefasst werden Der Export des Buchunsstapel wird von XML auf CSV
 * zurückgestellt. Die CSV Datei wird mit einer neuen Information GUID für das
 * Belegabbild ergänzt. Die DATEV Export Files sollen nicht mehr in die Aufgabe
 * im Workflow angehängt werden (bisheriger Workflow) sondern künftig direkt auf
 * einem FTP Server abgelegt werden. Dieser FTP Server wird von der KRIEGER GmbH
 * bereitgestellt.
 * <p>
 * 1.) Anpassung der Document.xml
 * <p>
 * Für das neue Exportformat muss die bisherige document.xml datei erweitert und
 * angepasst werden. Es wird die sogenannte GUID ergänzt. Diese ID kann von
 * DATEV dazu verwendet werden, Buchungssätze, welche aus einer CSV Datei
 * importiert werden, einem Beleg zuzuordnen, welcher in der Document.xml Datei
 * beschreiben ist. Die generierte GUID ist eindeutig und basiert auf dem Format
 * der in Imixs verwenden Document UniqueID (UID).
 * <p>
 * Des weiteren werden die Attribute 'processID' und ''type' mit statischen
 * Werten ergänzt. Die processID bestimmt, wie der Beleg verarbeitet werden
 * soll.
 * <p>
 * „1“ steht für „zu buchen“ (damit wird der Beleg als noch zu buchender Beleg
 * in Kanzlei Rechnungswesen angezeigt) „2“ steht für „festzuschreiben“ (der
 * Beleg wird von DATEV damit sofort nach dem Import festgeschrieben).
 * <p>
 * Für die ProzessID wird der Wert „2“ festgeschrieben, da der Beleg nicht mehr
 * verändert werden soll, sobald er in Belege Online imprtiert wurde.
 * <p>
 * Der document type bestimmt die Art des Dokuments.
 * <p>
 * „1“ steht für Eingangsrechnung „2“ steht für Ausgangsrechnung.
 * <p>
 * Der Typ wird auf "1" gesetzt.
 * <p>
 * Das zusätzlch optionale Feld 'keywords' wird mit dem Inhalt des Feldes
 * "_subject' aus dem Workflow belegt.
 * <p>
 * 2.) Zusammenfassung der Belege in einer ZIP Datei
 * <p>
 * Abweichend zum bisherigen Verfahren werden die Belege zusammen mit der
 * Document.xml Datei in einer ZIP Datei zusammengefasst. Die XML Buchunsbelege
 * werden hierbei nicht erzeugt. Das bestehende Exportverfahren wird an dieser
 * Stelle angepasst.
 * <p>
 * 3.) Generierung der CSV Datei
 * <p>
 * Anstelle der beim bisherigen Verfahren erzeugten einzelnen Buchunsdateien im
 * XML Format wird das bereits implementierte CSV Format verwendet, um den
 * Buchungsstapel zu generieren.
 * <p>
 * Die GUID aus der zuvor generierten Document.xml Datei muss dabei zusätzlich
 * in das Feld „Beleglink“ in der CSV eingetragen werden (BEDI „GUID“). Die
 * beiden Exportverfahren werden dazu in einem Prozessschritt gekoppelt.
 * <p>
 * 4.) Export der CSV Datei und ZIP Datei an einen FTP Server
 * <p>
 * Die generierte ZIP Datei mit den Belegdaten sowie die CSV Datei mit dem
 * Buchunsstapel muss auf einen FTP Server übertragen werden. Für diesen Export
 * muss die Konfiguration erweitert werden, so dass die Verbindungsdaten zu
 * einem FTP Server mit UserID/Passwort sowie entsprechende Zielordner angeben
 * werden können.
 * <p>
 * Der DATEV Scheduler wird angepasst, so dass künftig die ZIP Datei und die CSV
 * Datei in einer Transaktion generiert und in getrennnte Verzeichnisse auf dem
 * FTP Server übertragen werden. Die beiden Verzeichnisse sind konfigurierbar.
 * <p>
 * Das Ergebnis des Export Prozesses wird dabei weiterhin in einer Aufgabe
 * innerhalb des Workflows protokolliert und archiviert. Die Dateien selbst
 * werden dabei nicht mehr an die Aufgabe angehangen.
 * 
 * <p>
 * Änderung: Die Gruppierung der Daten erfolgt nun nach Buchungsperiode
 * (Jahr/Monat)
 * 
 * @see DatevSchedulerXML
 * @author rsoika
 * 
 * 
 * @version 1.0
 * @author rsoika
 */
public class DatevExportAdapter implements SignalAdapter {

	public static final int EVENT_INVOICE_COMPLETED = 200;

	private static Logger logger = Logger.getLogger(DatevExportAdapter.class.getName());

	@Inject
	WorkflowService workflowService;

	@Inject
	DocumentService documentService;

	@Inject
	DatevExportService datevExportService;

	@Inject
	DatevService datevService;

	/**
	 * This method finds or create the Zahlungsavis and adds a reference
	 * ($workitemref) to the current invoice.
	 * 
	 * @throws PluginException
	 * @throws
	 */
	@Override
	public ItemCollection execute(ItemCollection datevExport, ItemCollection event)
			throws AdapterException, PluginException {

		try {
			ItemCollection configuration = datevService.loadConfiguration();
			// get the data source based on the $workitemref....
			List<ItemCollection> masterDataSet = buildMasterDataSet(datevExport);

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
			}

			// update and process invoices in new transaction to avoid partial updates...
			processDatevExportEntities(datevExport, masterDataSet, event, configuration);

		} catch (AccessDeniedException | ProcessingErrorException | ModelException e) {
			throw new PluginException(DatevExportAdapter.class.getName(), DatevException.DATEV_EXPORT_ERROR,
					e.getMessage(), e);
		}

		return datevExport;
	}

	/**
	 * This method creates a collection of all referred invoices in this export
	 * <p>
	 * The result is sorted by $created
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<ItemCollection> buildMasterDataSet(ItemCollection workitem) {
		List<ItemCollection> result = new ArrayList<ItemCollection>();
		List<String> uniqueIDs = workitem.getItemValue("$workitemref");
		for (String id : uniqueIDs) {
			ItemCollection invoice = workflowService.getWorkItem(id);
			if (invoice != null) {
				result.add(invoice);
			} else {
				logger.warning("Invoice '" + id + "' no longer valid. Invoice will be excluded from DATEV export.");
			}
		}
		// sort by $created..
		Collections.sort(result, new ItemCollectionComparator("$created", true));

		return result;
	}

	/**
	 * This method finishes the workflow process of invoices
	 * 
	 * <p>
	 * The update is only performed if not taskID=5900
	 * 
	 */
	private void processDatevExportEntities(ItemCollection datevExport, List<ItemCollection> datevExportEntities,
			final ItemCollection event, ItemCollection configuration)
			throws AccessDeniedException, ProcessingErrorException, PluginException, ModelException {

		// process all invoices...
		for (ItemCollection invoice : datevExportEntities) {
			if (invoice.getTaskID() != 5900) {
				invoice.event(EVENT_INVOICE_COMPLETED);
				workflowService.processWorkItem(invoice);
			}
		}
		// write log
		logger.info("..." + datevExportEntities.size() + " invoices exported. ");
	}

}
