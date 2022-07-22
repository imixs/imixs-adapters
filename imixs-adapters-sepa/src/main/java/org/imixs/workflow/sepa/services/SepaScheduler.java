/*******************************************************************************
 *  Imixs Workflow Technology
 *  Copyright (C) 2001, 2008 Imixs Software Solutions GmbH,  
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
 *  Contributors:  
 *  	Imixs Software Solutions GmbH - initial API and implementation
 *  	Ralph Soika
 *******************************************************************************/
package org.imixs.workflow.sepa.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.Model;
import org.imixs.workflow.WorkflowKernel;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.engine.ModelService;
import org.imixs.workflow.engine.ReportService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.engine.scheduler.Scheduler;
import org.imixs.workflow.engine.scheduler.SchedulerException;
import org.imixs.workflow.engine.scheduler.SchedulerService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;

/**
 * The SepaScheduler implementation exports workflow invoice data into a SEPA
 * file..
 * <p>
 * The class implements the interface
 * _org.imixs.workflow.engine.scheduler.Scheduler_ and can be used in
 * combination with the Imxis-Workflow Scheduler Service.
 * 
 * @see SchedulerService
 * @author rsoika
 * 
 */
public class SepaScheduler implements Scheduler {
   
    @EJB
    DocumentService documentService;

    @EJB
    WorkflowService workflowService;

    @EJB
    SepaWorkflowService sepaWorkflowService;

    @EJB
    ModelService modelService;

    @EJB
    ReportService reportService;

    @Inject
    @ConfigProperty(name = "sepa.invoices.maxcount", defaultValue = "100") // maximum 100 invoices in one run
    int invoicesMaxCount; // default = 100

    
    
    private static Logger logger = Logger.getLogger(SepaScheduler.class.getName());

    /**
     * This is the method which processes the timeout event depending on the running
     * timer settings.
     * 
     * 
     * 
     * @param timer
     * @throws QueryException
     */
    public ItemCollection run(ItemCollection configuration) throws SchedulerException {
        String reportName = "";
        ItemCollection sepaExport = null;
       
        try {

            String modelVersion = configuration.getItemValueString(SepaWorkflowService.ITEM_MODEL_VERSION);
            int taskID = configuration.getItemValueInteger(SepaWorkflowService.ITEM_INITIAL_TASK);

            // fetch the initial event
            Model model = modelService.getModel(modelVersion);
            ItemCollection event = model.getEvent(taskID, SepaWorkflowService.EVENT_START);
            ItemCollection task = model.getTask(taskID);

            // load the report
            ItemCollection report = reportService.findReport(event.getItemValueString("txtReportName"));
            if (report == null) {
                throw new SchedulerException(SepaWorkflowService.REPORT_ERROR,
                        "Missing report definition. Unable to load report '" + reportName
                                + "'. Please check  model configuration " + taskID + "."
                                + SepaWorkflowService.EVENT_START);
            }

            // get the data source based on the report definition....
            List<ItemCollection> masterDataSet = reportService.getDataSource(report, invoicesMaxCount, 0, "$created", false,
                    null);

            sepaWorkflowService.logMessage("...SEPA export started....", configuration, null);
            sepaWorkflowService.logMessage("...found " + masterDataSet.size() + " new invoices...", configuration,
                    null);

            // update the invoices with ITEM_DBTR_IBAN
            if (masterDataSet.size() > 0) {
                // if ITEM_DBTR_IBAN is missing, then we take the default form the configuration
                for (ItemCollection invoice : masterDataSet) {
                    // test if invoice has a _dbtr_iban and _dbtr_bic
                    if (invoice.getItemValueString(SepaWorkflowService.ITEM_DBTR_IBAN).isEmpty()) {
                        // overtake _dbtr_iban from sepa configuration...
                        String paymentType = invoice.getItemValueString("payment.type");
                        ItemCollection dbtrOption = sepaWorkflowService.findDbtrOptionByPaymentType(paymentType,
                                configuration);
                        if (dbtrOption != null) {
                            invoice.setItemValue(SepaWorkflowService.ITEM_DBTR_IBAN,
                                    dbtrOption.getItemValue(SepaWorkflowService.ITEM_DBTR_IBAN));
                            invoice.setItemValue(SepaWorkflowService.ITEM_DBTR_BIC,
                                    dbtrOption.getItemValue(SepaWorkflowService.ITEM_DBTR_BIC));
                            invoice.setItemValue(SepaWorkflowService.ITEM_DBTR_NAME,
                                    dbtrOption.getItemValue(SepaWorkflowService.ITEM_DBTR_NAME));

                            // set optional SEPA report definition
                            invoice.setItemValue(SepaWorkflowService.ITEM_SEPA_REPORT,
                                    dbtrOption.getItemValue(SepaWorkflowService.ITEM_SEPA_REPORT));
                        } else {
                            sepaWorkflowService.logMessage(
                                    "...Warning: payment.type '" + paymentType + "' not found in SEPA configuration",
                                    configuration, null);
                        }
                    }
                }

                // group all invoices by the IBAN number
                Map<String, List<ItemCollection>> invoiceGroups = groupInvoicesBy(masterDataSet,
                        SepaWorkflowService.ITEM_DBTR_IBAN);

                // now we iterate over each invoice grouped by the ITEM_DBTR_IBAN
                // so we create one export file for a group of invoices.
                for (String key : invoiceGroups.keySet()) {

                    List<ItemCollection> data = invoiceGroups.get(key);
                    int groupCount = data.size();
                    // build the sepa export workitem....
                    sepaExport = new ItemCollection().model(modelVersion).task(taskID);
                    sepaExport.replaceItemValue(WorkflowKernel.CREATED, new Date());
                    sepaExport.replaceItemValue(WorkflowKernel.MODIFIED, new Date());
                    // set unqiueid, needed for xslt
                    sepaExport.setItemValue(WorkflowKernel.UNIQUEID, WorkflowKernel.generateUniqueID());

                    // copy dbtr_iban
                    sepaExport.setItemValue(SepaWorkflowService.ITEM_DBTR_IBAN, key);

                    // set _dbtr_name from first invoice if available...
                    ItemCollection firstInvoice = data.get(0);
                    if (firstInvoice.hasItem(SepaWorkflowService.ITEM_DBTR_NAME)) {
                        sepaExport.setItemValue(SepaWorkflowService.ITEM_DBTR_NAME,
                                firstInvoice.getItemValue(SepaWorkflowService.ITEM_DBTR_NAME));
                    }
                    // set _dbtr_bic from first invoice if available...
                    if (firstInvoice.hasItem(SepaWorkflowService.ITEM_DBTR_BIC)) {
                        sepaExport.setItemValue(SepaWorkflowService.ITEM_DBTR_BIC,
                                firstInvoice.getItemValue(SepaWorkflowService.ITEM_DBTR_BIC));
                    }
                    // set payment.type from first invoice if available...
                    if (firstInvoice.hasItem(SepaWorkflowService.ITEM_PAYMENT_TYPE)) {
                        sepaExport.setItemValue(SepaWorkflowService.ITEM_PAYMENT_TYPE,
                                firstInvoice.getItemValue(SepaWorkflowService.ITEM_PAYMENT_TYPE));
                    }
                    // set sepa.report from first invoice if available...
                    if (firstInvoice.hasItem(SepaWorkflowService.ITEM_SEPA_REPORT)) {
                        sepaExport.setItemValue(SepaWorkflowService.ITEM_SEPA_REPORT,
                                firstInvoice.getItemValue(SepaWorkflowService.ITEM_SEPA_REPORT));
                    }

                    // set workflow group name from the Task Element to identify document in xslt
                    String modelTaskGroupName = task.getItemValueString("txtworkflowgroup"); // DO NOT CHANGE!
                    sepaExport.setItemValue(WorkflowKernel.WORKFLOWGROUP, modelTaskGroupName);

                    sepaWorkflowService.logMessage("...starting SEPA export for iban=" + key + "...", configuration,
                            sepaExport);

                    // link invoices with export workitem....
                    for (ItemCollection invoice : data) {
                        sepaExport.appendItemValue(SepaWorkflowService.LINK_PROPERTY, invoice.getUniqueID());
                        // support deprecated ref field
                        sepaExport.appendItemValue("txtworkitemref", invoice.getUniqueID());

                        // avoid unsupported characters in sepa fields
                        invoice = sepaWorkflowService.harmonizeSEPAItem(invoice, SepaWorkflowService.ITEM_CDTR_NAME);
                        invoice = sepaWorkflowService.harmonizeSEPAItem(invoice, SepaWorkflowService.ITEM_DBTR_NAME);
                        logger.finest("......Invoice: " + invoice.getUniqueID() + " added. ");
                    }

                    // finally we add the sepa export document to the data collection
                    data.add(sepaExport);

                    // create the attachment based on the report definition
                    // write a file to workitem

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
                                "...SEPA export report="
                                        + sepaExport.getItemValueString(SepaWorkflowService.ITEM_SEPA_REPORT),
                                configuration, sepaExport);
                    } else {
                        if (!optionalSepaReport.isEmpty()) {
                            sepaWorkflowService.logMessage(
                                    "...WARNING - SEPA export report " + optionalSepaReport + " not found! Default report will be used.",
                                    configuration, sepaExport);
                        }
                        // use the default report
                        filedata = reportService.transformDataSource(report, data, sepaFileName);
                    }

                    // attach the file
                    sepaExport.addFileData(filedata);

                    // update and process invoices...
                    sepaWorkflowService.processInvoices(sepaExport, data, event, configuration);

                    // write log
                    sepaWorkflowService.logMessage("...SEPA export " + key + "  finished.", configuration, sepaExport);
                    sepaWorkflowService.logMessage("..." + groupCount + " invoices exported. ", configuration,
                            sepaExport);

                    // finish by processing the export workitem....
                    sepaExport.event(SepaWorkflowService.EVENT_START).event(SepaWorkflowService.EVENT_SUCCESS);
                    workflowService.processWorkItem(sepaExport);

                }

                sepaWorkflowService.logMessage("...SEPA export completed", configuration, null);

            } else {
                // no invoices found - so we terminate
                logger.finest("......no invoices found.");
                return configuration;
            }

        } catch (PluginException e) {
            // In case of a plugin exeption we continue the scheduler and mark the export as
            // failed
            try {
                if (sepaExport != null) {
                    // execute sepa workflow with EVENT_FAILED
                    sepaWorkflowService.logMessage("Failed: " + e.getMessage(), configuration, sepaExport);
                    sepaExport.event(SepaWorkflowService.EVENT_FAILED);
                    workflowService.processWorkItem(sepaExport);
                }
            } catch (AccessDeniedException | ProcessingErrorException | PluginException | ModelException e1) {
                throw new SchedulerException(SepaWorkflowService.REPORT_ERROR,
                        "Failed to execute sepa report '" + reportName + "' : " + e.getMessage(), e);
            }
        } catch (ModelException | JAXBException | TransformerException | IOException | AccessDeniedException
                | ProcessingErrorException | QueryException e) {
            // in all other cases we stop the processing
            try {
                if (sepaExport != null) {
                    // execute sepa workflow with EVENT_FAILED
                    sepaWorkflowService.logMessage("Failed: " + e.getMessage(), configuration, sepaExport);
                    sepaExport.event(SepaWorkflowService.EVENT_FAILED);
                    workflowService.processWorkItem(sepaExport);
                }
            } catch (AccessDeniedException | ProcessingErrorException | PluginException | ModelException e1) {
                throw new SchedulerException(SepaWorkflowService.REPORT_ERROR,
                        "Failed to execute sepa report '" + reportName + "' : " + e.getMessage(), e);
            }

            throw new SchedulerException(SepaWorkflowService.REPORT_ERROR,
                    "Failed to execute sepa report '" + reportName + "' : " + e.getMessage(), e);
        } catch (RuntimeException re) {
            logger.severe("...SEPA Runtime Error");
            sepaWorkflowService.logMessage("!!ERROR!! Failed to run SEPA scheduler: " + re.getMessage(), configuration,
                    null);
        }

        return configuration;
    }

    /**
     * This method groups a collection of invoices by a given key item.
     * 
     * @return a map with keys and lists of ItemCollection objects.
     */
    private Map<String, List<ItemCollection>> groupInvoicesBy(List<ItemCollection> datasource, String keyItem) {
        Map<String, List<ItemCollection>> result = new HashMap<>();
        logger.info("......grouping invoices by '" + keyItem + "'");
        for (ItemCollection invoice : datasource) {
            String key = invoice.getItemValueString(keyItem);
            logger.info("......building invoice group for '" + key + "'");
            List<ItemCollection> group = result.get(key);
            if (group == null) {
                group = new ArrayList<ItemCollection>();
            }
            group.add(invoice);
            result.put(key, group);
        }

        return result;
    }

   

}
