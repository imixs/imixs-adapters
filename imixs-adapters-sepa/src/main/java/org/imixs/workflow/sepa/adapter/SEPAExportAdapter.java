package org.imixs.workflow.sepa.adapter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.engine.ReportService;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.sepa.services.SepaWorkflowService;

/**
 * The SEPAExportAdapter executes the SEPA export and generates a SEPA XML file
 * from all referred workitems.
 * 
 * @version 1.0
 * @author rsoika
 */
public class SEPAExportAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(SEPAExportAdapter.class.getName());

    public static final String ERROR_CONFIG = "CONFIG_ERROR";
    public static final String ERROR_MISSING_INVOICE = "ERROR_MISSING_INVOICE";

    @EJB
    ReportService reportService;

    @Inject
    SepaWorkflowService sepaWorkflowService;

    /**
     * This method collects a data set with all invoices and computes a SEPA file
     * 
     * @throws PluginException
     */
    @SuppressWarnings("unchecked")
    @Override
    public ItemCollection execute(ItemCollection sepaExport, ItemCollection event)
            throws AdapterException, PluginException {

        String key = sepaExport.getItemValueString("name");

        // load the report
        ItemCollection report = reportService.findReport(event.getItemValueString("txtReportName"));
        if (report == null) {
            throw new PluginException(SEPAExportAdapter.class.getName(), SepaWorkflowService.REPORT_ERROR,
                    "Missing report definition. Unable to load report '" + event.getItemValueString("txtReportName")
                            + "'. Please check  model configuration ");
        }

        // get the data source based on the $workitemref ....
        List<String> refList = sepaExport.getItemValue("$workitemref");
        List<ItemCollection> data = new ArrayList<ItemCollection>();
        ItemCollection configuration = sepaWorkflowService.loadConfiguration();

        logger.info("...SEPA export started - " + refList.size() + " invoices found...");

        for (String ref : refList) {
            // load invoice
            ItemCollection invoice = sepaWorkflowService.loadInvoice(ref);
            if (invoice == null) {
                logger.warning("Invoice '" + ref + "' not found! SEPA Export can not be executed");
                continue;
            }
            // avoid unsupported characters in sepa fields
            invoice = sepaWorkflowService.harmonizeSEPAItem(invoice, SepaWorkflowService.ITEM_CDTR_NAME);
            invoice = sepaWorkflowService.harmonizeSEPAItem(invoice, SepaWorkflowService.ITEM_DBTR_NAME);
            data.add(invoice);
        }

        // finally we add the sepa export document to the data collection
        data.add(sepaExport);

        // create the attachment based on the report definition
        // attach a file to the current workitem
        // create a harmonized debitor name for the filename.....
        String sDepName = sepaExport.getItemValueString(SepaWorkflowService.ITEM_DBTR_NAME);
        sDepName = sDepName.replace("&", "_");
        sDepName = sDepName.replace(">", "_");
        sDepName = sDepName.replace("<", "_");
        sDepName = sDepName.replace(" ", "_");
        // build a timestamp for the filename
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HHmm");
        String sepaFileName = "sepa_" + sDepName + "_" + df.format(new Date()) + ".xml";
        try {
            // now we transform the data source
            // if an optional report definition was defined, this report is used for XSL
            // processing if not, than the main report definition is used
            FileData filedata = null;
            String optionalSepaReport = sepaExport.getItemValueString(SepaWorkflowService.ITEM_SEPA_REPORT);
            ItemCollection reportOptional = reportService.findReport(optionalSepaReport);
            if (reportOptional != null) {
                // use optional report
                filedata = reportService.transformDataSource(reportOptional, data, sepaFileName);
                sepaWorkflowService.logMessage(
                        "...SEPA export report=" + sepaExport.getItemValueString(SepaWorkflowService.ITEM_SEPA_REPORT),
                        configuration, sepaExport);
            } else {
                if (!optionalSepaReport.isEmpty()) {
                    sepaWorkflowService.logMessage("...WARNING - SEPA export report " + optionalSepaReport
                            + " not found! Default report will be used.", configuration, sepaExport);
                }
                // use the default report
                filedata = reportService.transformDataSource(report, data, sepaFileName);
            }
            // attach the file
            sepaExport.addFileData(filedata);
        } catch (JAXBException| IOException | TransformerException e) {
            throw new PluginException(SEPAExportAdapter.class.getName(), SepaWorkflowService.REPORT_ERROR,
                    "Failed to generate SEPA File:" + e.getMessage());
        }

        // write log
        logger.info("...SEPA export " + key + "  finished.");

        return sepaExport;
    }

}