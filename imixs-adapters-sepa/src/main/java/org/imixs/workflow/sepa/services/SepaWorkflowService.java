/*******************************************************************************
 *  Imixs Workflow 
 *  Copyright (C) 2001, 2011 Imixs Software Solutions GmbH,  
 *  http://www.imixs.com
 *  
 *  This program is free software; you can redistribute it and/or 
 *  modify it under the terms of the GNU General Public License 
 *  as published by the Free Software Foundation; either version 2 
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 *  General Public License for more details.
 *  
 *  You can receive a copy of the GNU General Public
 *  License at http://www.gnu.org/licenses/gpl.html
 *  
 *  Project: 
 *  	http://www.imixs.org
 *  	http://java.net/projects/imixs-workflow
 *  
 *  Contributors:  
 *  	Imixs Software Solutions GmbH - initial API and implementation
 *  	Ralph Soika - Software Developer
 *******************************************************************************/

package org.imixs.workflow.sepa.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.Model;
import org.imixs.workflow.WorkflowKernel;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.engine.ModelService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.engine.scheduler.Scheduler;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.util.XMLParser;

/**
 * This EJB provides methods to process invoiced during a sepa run.
 * 
 * @see SepaImportService
 * 
 * @author rsoika
 */

@DeclareRoles({ "org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@Stateless
@RunAs("org.imixs.ACCESSLEVEL.MANAGERACCESS")
@LocalBean
public class SepaWorkflowService {

    public static final String SEPA_CONFIGURATION = "SEPA_CONFIGURATION";
    public static final String ERROR_MISSING_DATA = "MISSING_DATA";

    public static final int EVENT_START = 100;
    public static final int EVENT_SUCCESS = 200;
    public static final int EVENT_FAILED = 300;
    public static final int EVENT_ADD_REF = 100;
    public static final int EVENT_REMOVE_REF = 200;

    public static final String INVOICE_UPDATE = "invoice_update";
    public static final String LINK_PROPERTY = "$workitemref";

    public static final String ITEM_MODEL_VERSION = "_model_version";
    public static final String ITEM_INITIAL_TASK = "_initial_task";

    public static final String ITEM_PAYMENT_TYPE = "payment.type";
    public static final String ITEM_SEPA_REPORT = "sepa.report";

    public static final String ITEM_DBTR_IBAN = "dbtr.iban";
    public static final String ITEM_DBTR_BIC = "dbtr.bic";
    public static final String ITEM_DBTR_NAME = "dbtr.name";

    public static final String ITEM_CDTR_IBAN = "cdtr.iban";
    public static final String ITEM_CDTR_BIC = "cdtr.bic";
    public static final String ITEM_CDTR_NAME = "cdtr.name";

    public static final String ITEM_DBTR_CONFIG = "dbtr.config";
    public static final String ITEM_CDTR_CONFIG = "cdtr.config";

    public static final String REPORT_ERROR = "REPORT_ERROR";

    @EJB
    WorkflowService workflowService = null;

    @EJB
    ModelService modelService;

    @Inject
    DocumentService documentService;

    private static Logger logger = Logger.getLogger(SepaWorkflowService.class.getName());

    /**
     * This method expects a list of Subprocess definitions. The method updates and
     * processes each existing invoice.
     * <p>
     * The definition is expected in the following format (were regular expressions
     * are allowed)
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
    // @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW) -
    // Disabled see Issue #115
    @SuppressWarnings("unchecked")
    public void processInvoices(ItemCollection sepaExport, List<ItemCollection> invoices, final ItemCollection event,
            ItemCollection configuration)
            throws AccessDeniedException, ProcessingErrorException, PluginException, ModelException {

        List<String> subProcessDefinitions = null;
        // test for items with name subprocess_update definition.
        ItemCollection evalItemCollection = workflowService.evalWorkflowResult(event, "sepa", sepaExport, false);
        if (evalItemCollection == null) {
            logger.warning("...expected sepa item in workflow result is missing - data will not be processed!");
            return;
        }

        subProcessDefinitions = evalItemCollection.getItemValue(INVOICE_UPDATE);

        if (subProcessDefinitions == null || subProcessDefinitions.size() == 0) {
            // no definition found
            return;
        }
        // we iterate over each declaration of a SUBPROCESS_CREATE item....
        for (String processValue : subProcessDefinitions) {

            if (processValue.trim().isEmpty()) {
                // no definition
                continue;
            }
            // evaluate the item content (XML format expected here!)
            ItemCollection processData = XMLParser.parseItemStructure(processValue);

            if (processData != null) {
                // we need to lookup all subprocess instances which are matching
                // the process definition

                String model_pattern = processData.getItemValueString("modelversion");
                String process_pattern = processData.getItemValueString("task");

                // process all subprcess matching...
                for (ItemCollection _invoice : invoices) {

                    // load the full invoice workitem....
                    ItemCollection invoice = workflowService.getWorkItem(_invoice.getUniqueID());

                    if (invoice != null) {
                        // test if invoice matches update criteria....
                        String subModelVersion = invoice.getModelVersion();
                        String subProcessID = "" + invoice.getTaskID();
                        if (Pattern.compile(model_pattern).matcher(subModelVersion).find()
                                && Pattern.compile(process_pattern).matcher(subProcessID).find()) {

                            logger.finest("...... subprocess matches criteria.");
                            // test for field list...
                            if (processData.hasItem("items")) {
                                logger.warning("subprocess itemList is not supported by the SepaScheduler!");
                            }
                            try {
                                invoice.setEventID(Integer.valueOf(processData.getItemValueString("event")));
                            } catch (java.lang.NumberFormatException e) {
                                throw new ModelException(ModelException.INVALID_MODEL_ENTRY,
                                        "unable to parse event '" + processData.getItemValueString("event")
                                                + "'. Please check your model definition '" + invoice.getModelVersion()
                                                + "'!",
                                        e);
                            }
                            // process the exisitng subprocess...
                            invoice = workflowService.processWorkItem(invoice);
                            logMessage("...invoice " + _invoice.getUniqueID() + " processed.", configuration, null);
                        }
                    }
                }
            }

        }
    }

    /**
     * Creates a new log entry stored in the item _scheduler_log. The log can be
     * writen optional to the configuraiton and the workitem
     * 
     * @param message
     * @param configuration
     */
    public void logMessage(String message, ItemCollection configuration, ItemCollection workitem) {
        if (configuration != null) {
            configuration.appendItemValue(Scheduler.ITEM_LOGMESSAGE, message);
        }
        if (workitem != null) {
            workitem.appendItemValue(Scheduler.ITEM_LOGMESSAGE, message);
        }

        logger.info(message);

    }

    /**
     * This method returns a ItemColleciton containing the bank data (Name/IBAN/BIC) for a given configuration type (dbtr|cdtr)
     * 
     * @param paymentType   - name of the payment
     * @param configuration - the sepa configuration document
     * @param configType    - the config type (ITEM_DBTR_CONFIG | ITEM_CDTR_CONFIG)
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ItemCollection findBankDataByPaymentType(String paymentType, ItemCollection configuration,
            String configType) {
        if (paymentType == null) {
            return null;
        }
        // load dbtr list from configuration
        ArrayList<ItemCollection> bankDataList = new ArrayList<ItemCollection>();
        List<Object> mapItems = configuration.getItemValue(configType);
        for (Object mapOderItem : mapItems) {
            if (mapOderItem instanceof Map) {
                ItemCollection itemCol = new ItemCollection((Map) mapOderItem);
                bankDataList.add(itemCol);
            }
        }
        // test for maching dbtr option by name...
        for (ItemCollection data : bankDataList) {
            if (paymentType.equals(data.getItemValueString("name"))) {
                return data;
            }
        }
        return null;
    }

    /**
     * Helper Method to compute the grouping key from a workitem. The default key is
     * the item "dbtr.iban". Optional the key can be overwritten by the model with a
     * itemvalue expression provided in the event.
     * <p>
     * Example:
     * 
     * <pre>
     * {@code
     <sepa-export name="key">
        <itemvalue>dbtr.iban</itemvalue>-<itemvalue>currency</itemvalue>
     </sepa-export>
    }</pre>
     * <p>
     * The method throws a PluginException if the given invoice did not provide a
     * dbtr.iban item.
     * 
     * @return
     * @throws PluginException
     */
    public String computeKey(ItemCollection invoice, ItemCollection event) throws PluginException {
        String key = null;
        // test if we have a key definition in the provided event
        ItemCollection sepaConfig = workflowService.evalWorkflowResult(event, "sepa-export", invoice, true);
        if (sepaConfig != null && sepaConfig.hasItem("key")) {
            logger.fine("read key information from event");
            key = sepaConfig.getItemValueString("key");
        } else {
            // take the default key
            key = invoice.getItemValueString(SepaWorkflowService.ITEM_DBTR_IBAN);
        }
        if (key.isEmpty()) {
            throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
                    "Invalid Data - item 'dbtr.iban' is missing or key definition is empty!");
        }
        return key;
    }

    /**
     * Helper method to create a new SEPA Export workitem
     * 
     * @param key
     * @param workitem
     * @return
     * @throws ModelException
     * @throws PluginException
     */
    public ItemCollection createNewSEPAExport(String key, ItemCollection workitem, ItemCollection event)
            throws ModelException, PluginException {
        String modelVersion = null;
        int taskID = -1;

        // test if the event provides a sepa export configuration
        ItemCollection sepaConfig = workflowService.evalWorkflowResult(event, "sepa-export", workitem, true);
        if (sepaConfig != null && sepaConfig.hasItem("modelversion") && sepaConfig.hasItem("task")) {
            logger.fine("read model information from event");
            modelVersion = sepaConfig.getItemValueString("modelVersion");
            taskID = sepaConfig.getItemValueInteger("task");
        } else {
            logger.fine("read model information from configuration");
            ItemCollection configuration = loadConfiguration();
            if (configuration == null) {
                throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
                        "Unable to load sepa configuration!");
            }
            modelVersion = configuration.getItemValueString(SepaWorkflowService.ITEM_MODEL_VERSION);
            taskID = configuration.getItemValueInteger(SepaWorkflowService.ITEM_INITIAL_TASK);
        }

        // build the sepa export workitem....
        ItemCollection sepaExport = new ItemCollection().model(modelVersion).task(taskID);
        sepaExport.replaceItemValue("name", key);
        sepaExport.replaceItemValue(WorkflowKernel.CREATED, new Date());
        sepaExport.replaceItemValue(WorkflowKernel.MODIFIED, new Date());
        // set unqiueid, needed for xslt
        sepaExport.setItemValue(WorkflowKernel.UNIQUEID, WorkflowKernel.generateUniqueID());

        // copy dbtr_iban
        sepaExport.setItemValue(SepaWorkflowService.ITEM_DBTR_IBAN,
                workitem.getItemValue(SepaWorkflowService.ITEM_DBTR_IBAN));

        // set additional data (e.g _dbtr_name) from first invoice...
        if (workitem.hasItem(SepaWorkflowService.ITEM_DBTR_NAME)) {
            sepaExport.setItemValue(SepaWorkflowService.ITEM_DBTR_NAME,
                    workitem.getItemValue(SepaWorkflowService.ITEM_DBTR_NAME));
        }
        // set _dbtr_bic from first invoice if available...
        if (workitem.hasItem(SepaWorkflowService.ITEM_DBTR_BIC)) {
            sepaExport.setItemValue(SepaWorkflowService.ITEM_DBTR_BIC,
                    workitem.getItemValue(SepaWorkflowService.ITEM_DBTR_BIC));
        }
        // set payment.type from first invoice if available...
        if (workitem.hasItem(SepaWorkflowService.ITEM_PAYMENT_TYPE)) {
            sepaExport.setItemValue(SepaWorkflowService.ITEM_PAYMENT_TYPE,
                    workitem.getItemValue(SepaWorkflowService.ITEM_PAYMENT_TYPE));
        }
        // set sepa.report from first invoice if available...
        if (workitem.hasItem(SepaWorkflowService.ITEM_SEPA_REPORT)) {
            sepaExport.setItemValue(SepaWorkflowService.ITEM_SEPA_REPORT,
                    workitem.getItemValue(SepaWorkflowService.ITEM_SEPA_REPORT));
        }

        // set workflow group name from the Task Element to identify document in xslt
        Model model = modelService.getModel(modelVersion);
        ItemCollection task = model.getTask(taskID);
        String modelTaskGroupName = task.getItemValueString("txtworkflowgroup"); // DO NOT CHANGE!
        sepaExport.setItemValue(WorkflowKernel.WORKFLOWGROUP, modelTaskGroupName);

        logger.info("...created new SEPA export for iban=" + key + "...");

        return sepaExport;
    }

    /**
     * Helper method to process a SEPA Exprot with on manager access level.
     * 
     * @param datevExport
     * @return
     * @throws ModelException
     * @throws PluginException
     * @throws ProcessingErrorException
     * @throws AccessDeniedException
     */
    public ItemCollection processSEPAExport(ItemCollection datevExport)
            throws AccessDeniedException, ProcessingErrorException, PluginException, ModelException {
        return workflowService.processWorkItem(datevExport);
    }

    /**
     * Helper method verifies all open SEPA exports and returns the latest for the
     * given key name. If no open SEPA export exists the method returns null.
     * 
     * @param key
     * @param taskID - optional can be used to restrict the lookup for a specific
     *               task
     * @return
     * @throws QueryException
     */
    public ItemCollection findSEPAExportByTask(String key, int taskID) throws QueryException {
        String query = "";
        if (taskID <= 0) {
            query = "(type:workitem) AND ($modelversion:sepa-export-manual*) AND (name:\"" + key + "\")";
        } else {
            query = "(type:workitem) AND ($taskid:" + taskID + ") AND ($modelversion:sepa-export-manual*) AND (name:\""
                    + key + "\")";
        }

        List<ItemCollection> resultList = workflowService.getDocumentService().find(query, 1, 0, "$modified", true);

        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        // no sepa export found
        return null;
    }

    /**
     * This method adds the DBTR default information to a workitem if the workitem
     * did not yet provide any debitor data. The data it read form the configuration
     * dbtr list and the payment.type stored in the workitem.
     * <p>
     * As payment can be distinguished between in and out. The method first test if
     * the worktiem contains a item <code>payment.out.type</code>. If not the item
     * <code>payment.type</code> is taken. In this way a workitem can provide
     * different payment types for outgoing payments and incoming payments (direct
     * debit).
     * <p>
     * The method throws a PluginException in case not dbtr data is available
     * 
     * @param workitem
     * @throws PluginException - if dbtr data is missing
     */
    public void updateDbtrDefaultData(ItemCollection workitem) throws PluginException {

        // check payment type
        String paymentType = workitem.getItemValueString("payment.out.type");
        if (paymentType.isEmpty()) {
            // test default payment type
            paymentType = workitem.getItemValueString("payment.type");
        }

        // test if the workitem has a dbtr.iban / dbtr.bic or a cdtr.iban / cdtr.bic
        if (!paymentType.isEmpty() && (workitem.getItemValueString(SepaWorkflowService.ITEM_DBTR_IBAN).isEmpty()
                || workitem.getItemValueString(SepaWorkflowService.ITEM_DBTR_BIC).isEmpty())) {
            // overtake dbtr.iban from sepa configuration...
            ItemCollection bankData = findBankDataByPaymentType(paymentType, loadConfiguration(), ITEM_DBTR_CONFIG);
            if (bankData != null) {
                workitem.setItemValue(SepaWorkflowService.ITEM_DBTR_IBAN,
                        bankData.getItemValue(SepaWorkflowService.ITEM_DBTR_IBAN));
                workitem.setItemValue(SepaWorkflowService.ITEM_DBTR_BIC,
                        bankData.getItemValue(SepaWorkflowService.ITEM_DBTR_BIC));
                workitem.setItemValue(SepaWorkflowService.ITEM_DBTR_NAME,
                        bankData.getItemValue(SepaWorkflowService.ITEM_DBTR_NAME));

                // set optional SEPA report definition
                workitem.setItemValue(SepaWorkflowService.ITEM_SEPA_REPORT,
                        bankData.getItemValue(SepaWorkflowService.ITEM_SEPA_REPORT));
            } else {
                throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
                        "No dbtr information found for '" + paymentType + "' in SEPA configuration!");
            }
        }
    }

    /**
     * This method adds the CDTR default information to a workitem if the workitem
     * did not yet provide any creditor data. The data it read form the
     * configuration cdtr list and the payment.type stored in the workitem.
     * <p>
     * As payment can be distinguished between in and out. The method first test if
     * the worktiem contains a item <code>payment.in.type</code>. If not the item
     * <code>payment.type</code> is taken. In this way a workitem can provide
     * different payment types for outgoing payments and incoming payments (direct
     * debit).
     * 
     * 
     * 
     * @param workitem
     * @throws PluginException - if cdtr data is missing
     */
    public void updateCdtrDefaultData(ItemCollection workitem) throws PluginException {

        // check payment type
        String paymentType = workitem.getItemValueString("payment.in.type");
        if (paymentType.isEmpty()) {
            // test default payment type
            paymentType = workitem.getItemValueString("payment.type");
        }

        // test if the workitem has a dbtr.iban / dbtr.bic or a cdtr.iban / cdtr.bic
        if (!paymentType.isEmpty() && (workitem.getItemValueString(SepaWorkflowService.ITEM_CDTR_IBAN).isEmpty()
                || workitem.getItemValueString(SepaWorkflowService.ITEM_CDTR_BIC).isEmpty())) {
            // overtake dbtr.iban from sepa configuration...
            ItemCollection bankData = findBankDataByPaymentType(paymentType, loadConfiguration(), ITEM_CDTR_CONFIG);
            if (bankData != null) {
                workitem.setItemValue(SepaWorkflowService.ITEM_CDTR_IBAN,
                        bankData.getItemValue(SepaWorkflowService.ITEM_CDTR_IBAN));
                workitem.setItemValue(SepaWorkflowService.ITEM_CDTR_BIC,
                        bankData.getItemValue(SepaWorkflowService.ITEM_CDTR_BIC));
                workitem.setItemValue(SepaWorkflowService.ITEM_CDTR_NAME,
                        bankData.getItemValue(SepaWorkflowService.ITEM_CDTR_NAME));

                // set optional SEPA report definition
                workitem.setItemValue(SepaWorkflowService.ITEM_SEPA_REPORT,
                        bankData.getItemValue(SepaWorkflowService.ITEM_SEPA_REPORT));
            } else {
                throw new PluginException(PluginException.class.getName(), ERROR_MISSING_DATA,
                        "No cdtr information found for '" + paymentType + "' in SEPA configuration!");
            }
        }
    }

    /**
     * Helper method verifies all open SEPA exports and returns the latest for the
     * given key name. If no open SEPA export exists the method returns null.
     * <p>
     * This method does not restrict the lookup for a specific taskID. This is used
     * for removal
     * 
     * @param key
     * @param taskID - optional can be used to restrict the lookup for a specific
     *               task
     * @return
     * @throws QueryException
     */
    public ItemCollection findSEPAExport(String key) throws QueryException {
        return findSEPAExportByTask(key, -1);
    }

    /**
     * Remove characters like '&', '<' and '>' form sepa fields
     * 
     * @param invoice
     * @return
     */
    public ItemCollection harmonizeSEPAItem(ItemCollection invoice, String itemName) {
        String value = null;
        value = invoice.getItemValueString(itemName);
        value = value.replace("&", " ");
        value = value.replace(">", " ");
        value = value.replace("<", " ");
        invoice.replaceItemValue(itemName, value);
        return invoice;

    }

    /**
     * Helper method to load the SEPA scheduler configuration entity. The method
     * returns null if no scheduler configuration exits.
     * 
     * @return
     */
    public ItemCollection loadConfiguration() {
        try {
            // support deprecated txtname attribure
            String sQuery = "(type:\"scheduler\" AND (name:\"" + SepaWorkflowService.SEPA_CONFIGURATION + "\" ) )";
            Collection<ItemCollection> col = documentService.find(sQuery, 1, 0);
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

    /**
     * Helper method to find and load a invoice on manager access level.
     * 
     * @param ref
     * @return
     */
    public ItemCollection loadInvoice(String ref) {
        return documentService.load(ref);
    }
}
