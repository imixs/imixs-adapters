package org.imixs.workflow.sepa.adapter;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.sepa.services.SepaWorkflowService;

/**
 * The SEPARefAddAdapter links an invoice with a SEPA Export for the dbtr.iban
 * item . If there is currently no open SEPA Export for this key, the adapter
 * automatically creates a new process instance.
 * <p>
 * If the invoice has already been linked to a SEPA Export, nothing happens.
 * <p>
 * 
 * @version 1.0
 * @author rsoika
 */
public class SEPARefAddAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(SEPARefAddAdapter.class.getName());

    public static final String ERROR_CONFIG = "CONFIG_ERROR";

    @Inject
    @ConfigProperty(name = "datev.defaultkonto", defaultValue = "1370")
    private String datevDefaultKonto;

    @Inject
    WorkflowService workflowService;

    @Inject
    SepaWorkflowService sepaWorkflowService;

    /**
     * This method finds or create the Datev Export and adds a reference
     * ($workitemref) to the current invoice.
     * 
     * @throws PluginException
     */
    @Override
    public ItemCollection execute(ItemCollection document, ItemCollection event)
            throws AdapterException, PluginException {

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

        String key = sepaWorkflowService.computeKey(invoice);

        logger.info("......Update SEPA export for: '" + key + "'...");
        ItemCollection sepaExport;
        try {
            sepaExport = sepaWorkflowService.findSEPAExport(key);
            if (sepaExport == null) {
                // create a new one
                sepaExport = sepaWorkflowService.createNewSEPAExport(key, invoice);
            }

            // add Invoice to SEPA export
            List<String> refList = sepaExport.getItemValue("$workitemref");
            if (!refList.contains(invoice.getUniqueID())) {
                sepaExport.appendItemValueUnique("$workitemref", invoice.getUniqueID());
                // set event 100
                sepaExport.event(100);
                workflowService.processWorkItem(sepaExport);
            }

        } catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
            throw new PluginException(PluginException.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Unable to add Invoice to SEPA Export: " + e1.getMessage());
        }
    }

}