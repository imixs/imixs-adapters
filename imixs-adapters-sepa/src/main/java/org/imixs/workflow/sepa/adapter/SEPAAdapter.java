package org.imixs.workflow.sepa.adapter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.xml.transform.TransformerException;

import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.SignalAdapter;
import org.imixs.workflow.WorkflowKernel;
import org.imixs.workflow.engine.ReportService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.sepa.services.SepaWorkflowService;

import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;

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
   <workflowgroup>Payment Run</workflowgroup>
   <init.task>1000</init.task>
   <init.event>20</init.event>
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
    public static final String MODE_FINISH = "finish";

    public static final String ERROR_CONFIG = "CONFIG_ERROR";

    @Inject
    SepaWorkflowService sepaWorkflowService;

    @Inject
    WorkflowService workflowService;

    @Inject
    ReportService reportService;

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
        List<ItemCollection> finishDefinitions = workflowService.evalWorkflowResultXML(event, "sepa",
                MODE_FINISH, workitem, true);

        /**
         * Iterate over each definition and process the data group
         */
        if (addDefinitions != null) {
            for (ItemCollection sepaDefinition : addDefinitions) {
                addWorkitemToSepa(workitem, event, sepaDefinition);
            }

        }

        // verify REMOVE mode
        if (removeDefinitions != null) {
            for (ItemCollection sepaDefinition : removeDefinitions) {
                removeWorkitemFromDataGroup(workitem, sepaDefinition);
            }
        }

        // verify EXECUTE mode
        if (executeDefinitions != null) {
            for (ItemCollection sepaDefinition : executeDefinitions) {
                executeSepaRun(workitem, sepaDefinition);
            }
        }

        // verify FINISH mode
        if (finishDefinitions != null) {
            for (ItemCollection sepaDefinition : finishDefinitions) {
                finishSepaRun(workitem, sepaDefinition);
            }
        }

        return workitem;
    }

    /**
     * This method collects a data set with all invoices and computes a SEPA file
     * 
     * @throws PluginException
     */
    public ItemCollection executeSepaRun(ItemCollection sepaExport, ItemCollection sepaConfig)
            throws AdapterException, PluginException {

        String reportName = sepaConfig.getItemValueString("report");
        boolean debug = sepaConfig.getItemValueBoolean("debug");

        if (debug) {
            logger.info("├── Execute SEPA Run - report: '" + reportName + "'...");
        }

        // load the report
        ItemCollection report = reportService.findReport(reportName);
        if (report == null) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.REPORT_ERROR,
                    "Missing report definition. Unable to load report '" + reportName
                            + "'. Please check  model configuration ");
        }

        try {
            List<ItemCollection> data = new ArrayList<ItemCollection>();
            ItemCollection configuration = sepaWorkflowService.loadConfiguration();

            // Lookup invoices
            String query = "(type:workitem OR type:workitemarchive) AND ($workitemref:"
                    + sepaExport.getUniqueID() + ")";
            List<ItemCollection> refList = workflowService.getDocumentService().find(query, 999, 0);
            if (debug) {
                logger.info("├── " + refList.size() + " invoices found");
            }

            for (ItemCollection invoice : refList) {
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
        } catch (JAXBException | IOException | TransformerException | QueryException e) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.REPORT_ERROR,
                    "Failed to generate SEPA File:" + e.getMessage());
        }

        // write log
        if (debug) {
            logger.info("└── ✓ SEPA Run Executed executed! ");
        }
        return sepaExport;
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

        String workflowgroup = sepaConfig.getItemValueString("workflowgroup");
        String key = sepaConfig.getItemValueString("key");
        int initTask = sepaConfig.getItemValueInteger("init.task");
        int initEvent = sepaConfig.getItemValueInteger("init.event");
        boolean debug = sepaConfig.getItemValueBoolean("debug");

        if (workflowgroup.isBlank()) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Invalid SEPA configuration - missing tag workflowgroup! Verify BPMN configuration.");
        }
        if (initTask == 0) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Invalid SEPA configuration - missing init.task! Verify BPMN configuration.");
        }
        if (initEvent == 0) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Invalid SEPA configuration - missing init.event! Verify BPMN configuration.");
        }
        if (key.isBlank()) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Invalid SEPA configuration - missing key! Verify BPMN configuration.");
        }

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
        if (debug) {
            logger.info("├── Adding workitem " + workitem.getUniqueID() + " to SEPA group '" + key + "'...");
        }
        ItemCollection sepaExport;
        try {
            sepaExport = sepaWorkflowService.findSEPAExportByWorkflowGroup(workflowgroup, key, initTask);
            if (sepaExport == null) {
                // create a new one
                sepaExport = createNewSEPAExport(key, workflowgroup, initTask, initEvent, type, workitem, debug);
                sepaExport = workflowService.processWorkItem(sepaExport);
            }

            // also add the ref of this workitem to the invoice
            // This is just to align the behavior to the new DataGroup Feature
            workitem.appendItemValueUnique("$workitemref", sepaExport.getUniqueID());
            if (debug) {
                logger.info("└── ✓ Added to SEPA group " + sepaExport.getUniqueID());
            }
        } catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Unable to add Invoice to SEPA Export: " + e1.getMessage());
        }

    }

    /**
     * Removes a workitem form a sepa group
     * 
     * @param workitem
     * @param sepaConfig
     * @throws PluginException
     */
    private void removeWorkitemFromDataGroup(ItemCollection workitem, ItemCollection sepaConfig)
            throws PluginException {
        String workflowgroup = sepaConfig.getItemValueString("workflowgroup");
        String key = sepaConfig.getItemValueString("key");

        boolean debug = sepaConfig.getItemValueBoolean("debug");

        if (workflowgroup.isBlank()) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Invalid SEPA configuration - missing tag workflowgroup! Verify BPMN configuration.");
        }
        if (key.isBlank()) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Invalid SEPA configuration - missing key! Verify BPMN configuration.");
        }

        if (debug) {
            logger.info("├── Removing workitem " + workitem.getUniqueID() + " from SEPA group '" + key + "'...");
        }
        ItemCollection sepaExport;
        try {

            sepaExport = sepaWorkflowService.findSEPAExportByWorkflowGroup(workflowgroup, key, 0);
            if (sepaExport != null) {
                // Sepa group found remove reference
                List<String> refs = workitem.getItemValueList("$workitemref", String.class);
                refs.remove(sepaExport.getUniqueID());
                workitem.setItemValue("$workitemref", refs);
                if (debug) {
                    logger.info("└── ✓ Removed from SEPA group " + sepaExport.getUniqueID());
                }
            } else {
                // Not found!
            }

        } catch (QueryException | AccessDeniedException | ProcessingErrorException e1) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Unable to add Invoice to SEPA Export: " + e1.getMessage());
        }

    }

    /**
     * This method expects a list of Subprocess definitions. The method updates and
     * processes each existing invoice.
     * <p>
     * The definition is expected in the following format (were regular expressions
     * are allowed)
     * 
     * <pre>
     * {@code
     * <sepa name="FINISH">
     *    <modelversion>1.0.0</modelversion>
     *    <task>100</task>
     *    <event>20</event>
     * </sepa>
     * }
     * </pre>
     * 
     * @see org.imixs.workflow.engine.plugins.SplitAndJoinPlugin.java
     * 
     * @param sepaExport - sepa export workitem
     * @param invoices   - list of invoices
     * @param event      - current sepa export event containing the invoice_update
     *                   definition.
     * @throws AccessDeniedException
     * @throws ProcessingErrorException
     * @throws PluginException
     * @throws ModelException
     */
    public void finishSepaRun(ItemCollection sepaExport, ItemCollection sepaConfig)
            throws PluginException {
        boolean debug = sepaConfig.getItemValueBoolean("debug");
        String model_pattern = sepaConfig.getItemValueString("modelversion");
        String process_pattern = sepaConfig.getItemValueString("task");
        int event = sepaConfig.getItemValueInteger("event");

        if (debug) {
            logger.info("├── Finish SEPA Run...");
        }

        try {
            List<ItemCollection> data = new ArrayList<ItemCollection>();
            // Lookup invoices
            String query = "(type:workitem OR type:workitemarchive) AND ($workitemref:"
                    + sepaExport.getUniqueID() + ")";
            data = workflowService.getDocumentService().find(query, 999, 0);
            if (debug) {
                logger.info("├── " + data.size() + " invoices found");
            }

            // process all subprcess matching...
            for (ItemCollection _invoice : data) {

                // load the full invoice workitem....
                ItemCollection invoice = workflowService.getWorkItem(_invoice.getUniqueID());

                if (invoice != null) {
                    // test if invoice matches update criteria....
                    String subModelVersion = invoice.getModelVersion();
                    String subProcessID = "" + invoice.getTaskID();
                    if (Pattern.compile(model_pattern).matcher(subModelVersion).find()
                            && Pattern.compile(process_pattern).matcher(subProcessID).find()) {
                        logger.finest("...... subprocess matches criteria.");
                        invoice.setEventID(event);
                        // process the exisitng subprocess...
                        invoice = workflowService.processWorkItem(invoice);

                    }
                }
            }
        } catch (QueryException | AccessDeniedException | ProcessingErrorException | ModelException e1) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Unable to finish Invoice for SEPA Export: " + e1.getMessage());
        }
    }

    /**
     * Helper method to create a new sepa export workitem
     * 
     * @throws QueryException
     */
    private ItemCollection createNewSEPAExport(String key, String workflowgroup, int taskID, int eventId, String type,
            ItemCollection workitem, boolean debug)
            throws ModelException, PluginException, QueryException {

        // build the sepa export workitem....
        ItemCollection sepaExport = new ItemCollection()
                .workflowGroup(workflowgroup)
                .task(taskID)
                .event(eventId);

        // Lookup SEPA config....
        ItemCollection bankConfig = null;
        ItemCollection sepaConfig = loadSepaConfig();
        if ("OUT".equalsIgnoreCase(type)) {
            bankConfig = sepaWorkflowService.findBankDataByPaymentType(key, sepaConfig, "dbtr.config");
        } else {
            bankConfig = sepaWorkflowService.findBankDataByPaymentType(key, sepaConfig, "cdtr.config");
        }
        if (bankConfig == null) {
            throw new PluginException(SEPAAdapter.class.getName(), SepaWorkflowService.ERROR_MISSING_DATA,
                    "Unable to find BANK configuration for '" + key + "'");
        }

        sepaExport.replaceItemValue(WorkflowKernel.CREATED, new Date());
        sepaExport.replaceItemValue(WorkflowKernel.MODIFIED, new Date());
        // set uniqueId, needed for xslt
        sepaExport.setItemValue(WorkflowKernel.UNIQUEID, WorkflowKernel.generateUniqueID());
        sepaExport.setItemValue("name", key);
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
        // ModelManager modelManager = new ModelManager(workflowService);
        // BPMNModel model = modelManager.getModel(modelVersion);
        // ItemCollection task = modelManager.findTaskByID(model, taskID);
        // // model.openDefaultProces().fin(type);.getTask(taskID);
        // String modelTaskGroupName = task.getItemValueString("txtworkflowgroup"); //
        // DO NOT CHANGE!
        // sepaExport.setItemValue(WorkflowKernel.WORKFLOWGROUP, modelTaskGroupName);
        if (debug) {
            logger.info("├── Created new SEPA group=" + key + "");
        }
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
