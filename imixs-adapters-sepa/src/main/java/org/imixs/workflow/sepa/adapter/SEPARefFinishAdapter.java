package org.imixs.workflow.sepa.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.sepa.services.SepaWorkflowService;

/**
 * The SEPARefFinishAdapter processes all invoices referred by this sepa export.
 * 
 * The definition for the event to be processed is expected in the following
 * format
 * 
 * <pre>
 * {@code
 * <sepa name="invoice_update">
 *    <modelversion>1.0.0</modelversion>
 *    <task>100</task>
 *    <event>20</event>
 * </sepa>
 * }
 * </pre>
 * 
 * @version 1.0
 * @author rsoika
 */
public class SEPARefFinishAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(SEPARefFinishAdapter.class.getName());

    public static final String ERROR_CONFIG = "CONFIG_ERROR";
    public static final String ERROR_MISSING_INVOICE = "ERROR_MISSING_INVOICE";

    @Inject
    SepaWorkflowService sepaWorkflowService;

    @Inject
    WorkflowService workflowService;

    /**
     * This method collects a data set with all invoices and computes a SEPA file
     * 
     * @throws PluginException
     */
    @SuppressWarnings("unchecked")
    @Override
    public ItemCollection execute(ItemCollection sepaExport, ItemCollection event)
            throws AdapterException, PluginException {

        logger.info("...SEPA finish adapter started");
        // get the data source based on the $workitemref ....
        List<String> refList = sepaExport.getItemValue("$workitemref");
        List<ItemCollection> data = new ArrayList<ItemCollection>();
        for (String ref : refList) {
            // load invoice
            ItemCollection invoice = sepaWorkflowService.loadInvoice(ref);
            if (invoice == null) {
                logger.warning("Invoice '" + ref + "' not found! Invoice can not be close");
                continue;
            }
            data.add(invoice);
        }

        logger.info("...SEPA finish start processing - " + data.size() + " invoices...");
        try {
            sepaWorkflowService.processInvoices(sepaExport, data, event, null);
        } catch (AccessDeniedException | ProcessingErrorException | PluginException | ModelException e) {
            throw new PluginException(PluginException.class.getName(), ERROR_MISSING_INVOICE,
                    "Invalid Data - invoice list could not be processed !");
        }

        return sepaExport;
    }

}