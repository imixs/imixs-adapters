package org.imixs.workflow.sepa.adapter;

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
import org.imixs.workflow.sepa.services.SepaWorkflowService;

import jakarta.inject.Inject;

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

    /**
     * This method finds the SEPA export and removes a reference ($workitemref) to
     * the current invoice.
     * 
     * @throws PluginException
     */
    @Override
    @SuppressWarnings("unchecked")
    public ItemCollection execute(ItemCollection invoice, ItemCollection event)
            throws AdapterException, PluginException {

        String key = sepaWorkflowService.computeKey(invoice, event);
        ItemCollection sepaExport;
        try {
            sepaExport = sepaWorkflowService.findSEPAExport(key);
            if (sepaExport != null) {
                logger.fine("......Update SEPA export for: '" + key + "'...");
                // remove invoice from SePA export
                List<String> refList = sepaExport.getItemValue("$workitemref");
                if (refList.contains(invoice.getUniqueID())) {
                    refList.remove(invoice.getUniqueID());
                    sepaExport.setItemValue("$workitemref", refList);
                    // set event 200
                    sepaExport.event(SepaWorkflowService.EVENT_REMOVE_REF);
                    sepaWorkflowService.processSEPAExport(sepaExport);
                }
            } else {
                // No action needed
            }
        } catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
            throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
                    "SEPA Export not found: " + e1.getMessage());
        }

        return invoice;
    }

}