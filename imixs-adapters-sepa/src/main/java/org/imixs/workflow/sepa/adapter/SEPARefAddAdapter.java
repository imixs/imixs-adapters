package org.imixs.workflow.sepa.adapter;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.sepa.services.SepaWorkflowService;

/**
 * The SEPARefAddAdapter is used for linking a workitem with a SEPA Export. For
 * payment advises the dbtr.iban/bic items are resolved. For direct debit the
 * adapter uses the cdtr.iban/bic item.
 * <p>
 * Depending on the information stored in a workitem the adapter automatically
 * resolves the dbtr/cdtr information form the SEPA configuration document.
 * <p>
 * SEPA Export workitems are grouped by an individual key defined in the model.
 * If there is currently no open SEPA Export for a given key, the adapter
 * automatically creates a new process instance.
 * <p>
 * Event Configuration:
 * <p>
 * 
 * <pre>
 * {@code
 * <sepa-export name="modelversion">sepa-export-manual-de-3.0</sepa-export>
   <sepa-export name="task">1000</sepa-export>
   <sepa-export name="key">SEPA Lastschriftverfahren</sepa-export> 
   }</pre>
 * <p>
 * If a workitem is already been linked to a SEPA Export, nothing happens.
 * 
 * @version 1.0
 * @author rsoika
 */
public class SEPARefAddAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(SEPARefAddAdapter.class.getName());

    public static final String ERROR_CONFIG = "CONFIG_ERROR";

    @Inject
    SepaWorkflowService sepaWorkflowService;

    /**
     * This method finds or create the SEPA Export and adds a reference
     * ($workitemref) to the current workitem.
     * 
     * @throws PluginException
     */
    @SuppressWarnings("unchecked")
    @Override
    public ItemCollection execute(ItemCollection workitem, ItemCollection event)
            throws AdapterException, PluginException {

        
        // test if the workitem has a dbtr.iban / dbtr.bic or a cdtr.iban / cdtr.bic
        sepaWorkflowService.updateDbtrDefaultData(workitem);
        sepaWorkflowService.updateCdtrDefaultData(workitem);
       

        String key = sepaWorkflowService.computeKey(workitem, event);

        logger.info("......Update SEPA export for: '" + key + "'...");
        ItemCollection sepaExport;
        try {
            sepaExport = sepaWorkflowService.findSEPAExportByTask(key, 1000);
            if (sepaExport == null) {
                // create a new one
                sepaExport = sepaWorkflowService.createNewSEPAExport(key, workitem, event);
            }

            // add Invoice to SEPA export
            List<String> refList = sepaExport.getItemValue("$workitemref");
            if (!refList.contains(workitem.getUniqueID())) {
                sepaExport.appendItemValueUnique("$workitemref", workitem.getUniqueID());
                // set event 100
                sepaExport.event(SepaWorkflowService.EVENT_ADD_REF);
                sepaWorkflowService.processSEPAExport(sepaExport);
            }

        } catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
            throw new PluginException(SEPARefAddAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Unable to add Invoice to SEPA Export: " + e1.getMessage());
        }

        return workitem;
    }

}