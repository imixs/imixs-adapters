package org.imixs.workflow.sepa.adapter;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.ModelManager;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.WorkflowKernel;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.sepa.services.SepaWorkflowService;
import org.openbpmn.bpmn.BPMNModel;

import jakarta.inject.Inject;

/**
 * The SEPAAdapter is used for linking a workitem (invoice) with a SEPA Export.
 * For
 * payment advises the dbtr.iban/ dbtr.bic items are resolved. For direct debit
 * the
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
 * 
 * <pre>
 * {@code
<sepa name="ADD">
   <modelversion>sepa-export-manual-3.0</modelversion>
   <task>1000</task>
   <event>20</event>
   <key><itemvalue>invoice.payment.type</key>
   <type>IN|OUT</type>
</sepa>
 * }
 * </pre>
 * 
 * Example to execute a sepa list
 * 
 * <pre>
 * {@code
<sepa name="EXECUTE">
</sepa>
 * }
 * </pre>
 * 
 * 
 * <p>
 * If a workitem is already been linked to a SEPA Export, nothing happens.
 * 
 * @version 1.0
 * @author rsoika
 */
public class SEPAAdapter implements SignalAdapter {

    private static Logger logger = Logger.getLogger(SEPAAdapter.class.getName());

    public static final String MODE_ADD = "add";
    public static final String MODE_REMOVE = "remove";
    public static final String MODE_EXECUTE = "execute";

    public static final String ERROR_CONFIG = "CONFIG_ERROR";

    @Inject
    SepaWorkflowService sepaWorkflowService;

    @Inject
    WorkflowService workflowService;

    /**
     * This method finds or create the SEPA Export and adds a reference
     * ($workitemref) to the current workitem.
     * 
     * @throws PluginException
     */
    @Override
    public ItemCollection execute(ItemCollection workitem, ItemCollection event)
            throws AdapterException, PluginException {

        long processingTime = System.currentTimeMillis();

        // read optional configuration form the model or imixs.properties....

        List<ItemCollection> addDefinitions = workflowService.evalWorkflowResultXML(event, "sepa",
                MODE_ADD, workitem, true);
        List<ItemCollection> removeDefinitions = workflowService.evalWorkflowResultXML(event, "sepa",
                MODE_REMOVE, workitem, true);
        List<ItemCollection> executeDefinitions = workflowService.evalWorkflowResultXML(event, "sepa",
                MODE_EXECUTE, workitem, true);

        /**
         * Iterate over each definition and process the data group
         */
        if (addDefinitions != null) {
            for (ItemCollection groupDefinition : addDefinitions) {
                addWorkitemToSepa(workitem, event, groupDefinition);
            }

        }

        // // verify REMOVE mode
        // if (removeDefinitions != null) {
        // for (ItemCollection groupDefinition : removeDefinitions) {
        // removeWorkitemFromDataGroup(workitem, groupDefinition);
        // }
        // }

        // // verify EXECUTE mode
        // if (executeDefinitions != null) {
        // for (ItemCollection groupDefinition : executeDefinitions) {
        // executeWorkitemFromDataGroup(workitem, groupDefinition);
        // }
        // }

        logger.info("├── ✅ completed (" + (System.currentTimeMillis() - processingTime) + "ms)");

        return workitem;
    }

    /**
     * Add the current workitem to a sepa run
     * 
     * @param workitem
     * @param event
     * @param sepaConfig
     * @throws PluginException
     */
    public void addWorkitemToSepa(ItemCollection workitem, ItemCollection event, ItemCollection sepaConfig)
            throws PluginException {

        String modelVersion = sepaConfig.getItemValueString("modelversion");
        String key = sepaConfig.getItemValueString("key");
        int initTask = sepaConfig.getItemValueInteger("task");
        int initEvent = sepaConfig.getItemValueInteger("event");

        // We test the config item "type". If it is set to OUT than a
        String type = "OUT"; // default
        if (sepaConfig != null && !sepaConfig.isItemEmpty("type")) {
            type = sepaConfig.getItemValueString("type");
        }
        if ("OUT".equalsIgnoreCase(type)) {
            // validate workitem
            sepaWorkflowService.validateCdtrData(workitem);
        } else {
            // validate workitem
            sepaWorkflowService.validateDbtrData(workitem);
        }

        if (key.isBlank()) {
            throw new PluginException(SEPARefAddAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Unable to add Invoice to SEPA Export: missing key attribute! Verify BPMN configuration.");
        }

        logger.info("......Update SEPA export for: '" + key + "'...");
        ItemCollection sepaExport;
        try {

            sepaExport = sepaWorkflowService.findSEPAExportByTask(key, initTask);
            if (sepaExport == null) {
                // create a new one
                // sepaExport = sepaWorkflowService.createNewSEPAExport(key, workitem, event);
                sepaExport = createNewSEPAExport(key, modelVersion, initTask, initEvent, type, workitem);
                sepaExport = workflowService.processWorkItem(sepaExport);
            }

            // also add the ref of this workitem to the invoice
            // This is just to align the behavoir to the new DataGroup Feature
            workitem.appendItemValueUnique("$workitemref", sepaExport.getUniqueID());

        } catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
            throw new PluginException(SEPARefAddAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Unable to add Invoice to SEPA Export: " + e1.getMessage());
        }

    }

    /**
     * Helper method to create a new sepa export workitem
     * 
     * @throws QueryException
     */
    private ItemCollection createNewSEPAExport(String key, String modelVersion, int taskID, int eventId, String type,
            ItemCollection workitem)
            throws ModelException, PluginException, QueryException {

        // build the sepa export workitem....
        ItemCollection sepaExport = new ItemCollection().model(modelVersion).task(taskID).event(eventId);

        // Lookup SEPA config....
        ItemCollection bankConfig = null;
        ItemCollection sepaConfig = loadSepaConfig();
        if ("OUT".equalsIgnoreCase(type)) {
            bankConfig = sepaWorkflowService.findBankDataByPaymentType(key, sepaConfig, "dbtr.config");
        } else {
            bankConfig = sepaWorkflowService.findBankDataByPaymentType(key, sepaConfig, "cdtr.config");
        }
        if (bankConfig == null) {
            throw new PluginException(SEPARefAddAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Unable to find BANK configuration for '" + key + "'");
        }

        sepaExport.replaceItemValue(WorkflowKernel.CREATED, new Date());
        sepaExport.replaceItemValue(WorkflowKernel.MODIFIED, new Date());
        // set uniqueId, needed for xslt
        sepaExport.setItemValue(WorkflowKernel.UNIQUEID, WorkflowKernel.generateUniqueID());

        // Invoice/Lastschrift?
        if ("OUT".equalsIgnoreCase(type)) {
            // copy dbtr data...
            sepaExport.setItemValue(SepaWorkflowService.ITEM_DBTR_NAME,
                    bankConfig.getItemValue(SepaWorkflowService.ITEM_DBTR_NAME));

            // IBAN
            sepaExport.setItemValue(SepaWorkflowService.ITEM_DBTR_IBAN,
                    bankConfig.getItemValue(SepaWorkflowService.ITEM_DBTR_IBAN));

            // BIC
            sepaExport.setItemValue(SepaWorkflowService.ITEM_DBTR_BIC,
                    bankConfig.getItemValue(SepaWorkflowService.ITEM_DBTR_BIC));

        } else {
            // copy cdtr data...
            sepaExport.setItemValue(SepaWorkflowService.ITEM_CDTR_NAME,
                    bankConfig.getItemValue(SepaWorkflowService.ITEM_CDTR_NAME));

            // IBAN
            sepaExport.setItemValue(SepaWorkflowService.ITEM_CDTR_IBAN,
                    bankConfig.getItemValue(SepaWorkflowService.ITEM_CDTR_IBAN));

            // BIC
            sepaExport.setItemValue(SepaWorkflowService.ITEM_CDTR_BIC,
                    bankConfig.getItemValue(SepaWorkflowService.ITEM_CDTR_BIC));
        }

        sepaExport.setItemValue(SepaWorkflowService.ITEM_PAYMENT_TYPE, key);

        // set sepa.report from first ref if available...
        sepaExport.setItemValue(SepaWorkflowService.ITEM_SEPA_REPORT,
                bankConfig.getItemValue(SepaWorkflowService.ITEM_SEPA_REPORT));

        // set workflow group name from the Task Element to identify document in xslt
        ModelManager modelManager = new ModelManager(workflowService);
        BPMNModel model = modelManager.getModel(modelVersion);
        ItemCollection task = modelManager.findTaskByID(model, taskID);

        // model.openDefaultProces().fin(type);.getTask(taskID);
        String modelTaskGroupName = task.getItemValueString("txtworkflowgroup"); // DO NOT CHANGE!
        sepaExport.setItemValue(WorkflowKernel.WORKFLOWGROUP, modelTaskGroupName);

        logger.info("...created new SEPA export for iban=" + key + "...");

        return sepaExport;
    }

    /**
     * Loads the scheduler configuration entity by name. The method returns null if
     * no scheduler configuration exits.
     * 
     * @return
     */
    private ItemCollection loadSepaConfig() {

        try {
            // support deprecated txtname attribure
            String sQuery = "(type:\"scheduler\" AND name:\"SEPA_CONFIGURATION\")";
            Collection<ItemCollection> col = workflowService.getDocumentService().find(sQuery, 1, 0);
            // check if we found a scheduler configuration
            if (col.size() > 0) {
                ItemCollection configuration = col.iterator().next();
                return configuration;
            }
        } catch (QueryException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
