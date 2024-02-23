package org.imixs.workflow.sepa.adapter;

import java.util.List;
import java.util.logging.Logger;

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

import jakarta.inject.Inject;

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
   <sepa-export name="maxcount">100</sepa-export>
   <sepa-export name="maxcount-event">20</sepa-export>   
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

    @Inject
    WorkflowService workflowService;

    int invoicesMaxCount = 0;
    int invoicesMaxCountEvent = 0;

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

        // We test the config item "type". If it is set to OUT than a
        //

        // test if the workitem has a dbtr.iban / dbtr.bic or a cdtr.iban / cdtr.bic
        ItemCollection sepaConfig = workflowService.evalWorkflowResult(event, "sepa-export", workitem, true);

        // compute maxcount properties....
        if (sepaConfig != null) {
            logger.fine("read max count configuration from event");
            if (sepaConfig.getItemValueInteger("maxcount") > 0) {
                invoicesMaxCount = sepaConfig.getItemValueInteger("maxcount");
                invoicesMaxCountEvent = sepaConfig.getItemValueInteger("maxcount-event");
            }
        }

        String type = "OUT"; // default
        if (sepaConfig != null && !sepaConfig.isItemEmpty("type")) {
            type = sepaConfig.getItemValueString("type");
        }
        if ("OUT".equalsIgnoreCase(type)) {
            sepaWorkflowService.updateDbtrDefaultData(workitem);
            // validate workitem
            sepaWorkflowService.validateCdtrData(workitem);
        } else {
            sepaWorkflowService.updateCdtrDefaultData(workitem);
            // validate workitem
            sepaWorkflowService.validateDbtrData(workitem);
        }

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

                // test if the max count was reached.
                if (invoicesMaxCount > 0 && sepaExport.getItemValue("$workitemref").size() >= invoicesMaxCount) {
                    logger.info("......Max Count of " + invoicesMaxCount
                            + " invoices reached, executing maxcount-event=" + invoicesMaxCountEvent);
                    // process the maxcoutn event on the sepa export
                    sepaExport.event(invoicesMaxCountEvent);
                    sepaWorkflowService.processSEPAExport(sepaExport);
                }
            }

        } catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
            throw new PluginException(SEPARefAddAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Unable to add Invoice to SEPA Export: " + e1.getMessage());
        }

        return workitem;
    }

}