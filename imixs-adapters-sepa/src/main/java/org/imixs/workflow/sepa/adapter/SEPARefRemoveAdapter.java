package org.imixs.workflow.sepa.adapter;

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
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.sepa.services.SepaWorkflowService;

/**
 * The SEPARefRemoveAdapter removes the linking of an invoice with a SEPA export
 * 
 * @see SEPARefAddAdapter
 * 
 * @version 1.0
 * @author rsoika
 */
public class SEPARefRemoveAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(SEPARefRemoveAdapter.class.getName());

    public static final String ERROR_MISSING_DATA = "MISSING_DATA";
    public static final String ERROR_CONFIG = "CONFIG_ERROR";

    @Inject
    SepaWorkflowService sepaWorkflowService;

    @Inject
    WorkflowService workflowService;

    /**
     * This method finds the SEPA export and removes a reference ($workitemref) to
     * the current invoice.
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
     * This method removes an existing workitem reference 
     * 
     * @param invoice
     * @throws PluginException
     */
    @SuppressWarnings("unchecked")
    private void removeInvoice(ItemCollection invoice) throws PluginException {
        String key = sepaWorkflowService.computeKey(invoice);

        logger.info("......Update SEPA export for: '" + key + "'...");
        ItemCollection sepaExport;
        try {
            sepaExport = sepaWorkflowService.findSEPAExport(key);
            if (sepaExport != null) {
                // Invoice wieder aus dem DATEV Export heruasnehmen
                List<String> refList = sepaExport.getItemValue("$workitemref");
                if (refList.contains(invoice.getUniqueID())) {
                    refList.remove(invoice.getUniqueID());
                    sepaExport.setItemValue("$workitemref", refList);
                    // set event 100
                    sepaExport.event(100);
                    workflowService.processWorkItem(sepaExport);
                }
            }

        } catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
            throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
                    "Es konnte kein DATEV Export zugewiesen werden: " + e1.getMessage());

        }
    }


}