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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

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
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.util.XMLParser;

/**
 * SEPA Scheduler implementation.
 * 
 * @author rsoika
 * 
 */
public class SepaScheduler implements Scheduler {

	public static final String SEPA_CONFIGURATION = "SEPA_CONFIGURATION";

	public static final int EVENT_START = 100;
	public static final int EVENT_SUCCESS = 200;
	public static final int EVENT_FAILED = 300;
	public static final String INVOICE_UPDATE = "invoice_update";
	public static final String LINK_PROPERTY = "txtworkitemref";

	public static final String ITEM_MODEL_VERSION = "_model_version";
	public static final String ITEM_INITIAL_TASK = "_initial_task";

	public static final String REPORT_ERROR = "REPORT_ERROR";

	public static final int MAX_COUNT = 999;

	@EJB
	DocumentService documentService;

	@EJB
	WorkflowService workflowService;

	@EJB
	ModelService modelService;

	@EJB
	ReportService reportService;

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
		ByteArrayOutputStream outputStream = null;
		String reportName = "";
		ItemCollection sepaExport = null;
		int maxCount = configuration.getItemValueInteger("_maxcount");
		if (maxCount == 0) {
			maxCount = -1;
		}
		try {

			String modelVersion = configuration.getItemValueString(ITEM_MODEL_VERSION);
			int taskID = configuration.getItemValueInteger(ITEM_INITIAL_TASK);

			// fetch the inital event
			Model model = modelService.getModel(modelVersion);
			ItemCollection event = model.getEvent(taskID, EVENT_START);
			ItemCollection task = model.getTask(taskID);

			// load the report
			ItemCollection report = reportService.findReport(event.getItemValueString("txtReportName"));
			if (report == null) {
				throw new SchedulerException(REPORT_ERROR,
						"unable to load report '" + reportName + "'. Please check  model configuration");
			}

			// build the sepa export workitem....
			sepaExport = new ItemCollection().model(modelVersion).task(taskID);
			// set unqiueid, needed for xslt
			sepaExport.setItemValue(WorkflowKernel.UNIQUEID, WorkflowKernel.generateUniqueID());
			// copy iban/bic
			sepaExport.setItemValue("_dbtr_iban", configuration.getItemValue("_dbtr_iban"));
			sepaExport.setItemValue("_dbtr_bic", configuration.getItemValue("_dbtr_bic"));
			sepaExport.setItemValue("_subject", configuration.getItemValue("_subject"));
			sepaExport.setItemValue(WorkflowKernel.WORKFLOWGROUP, task.getItemValue("txtworkflowgroup"));

			// get the data source based on the report definition....
			List<ItemCollection> data = reportService.getDataSource(report, MAX_COUNT, 0,
					"$created", false, null);

			logMessage("Sepa export started....", configuration, null);
			logMessage("...found " + data.size() + " invoices...", configuration, null);

			// update the invoices with the _sepa_iban if not provided
			// link the invoices with the sepa workitem. Count invoices and controll sum
			if (data.size() > 0) {
				int count = data.size();
				for (ItemCollection invoice : data) {
					// test if invoice has a _dbtr_iban and _dbtr_bic

					if (invoice.getItemValueString("_dbtr_iban").isEmpty()) {
						// overtake _dbtr_iban from sepa export
						invoice.setItemValue("_dbtr_iban", sepaExport.getItemValue("_dbtr_iban"));
					}
					if (invoice.getItemValueString("_dbtr_bic").isEmpty()) {
						// overtake _dbtr_bic from sepa export
						invoice.setItemValue("_dbtr_bic", sepaExport.getItemValue("_dbtr_bic"));
					}
					sepaExport.appendItemValue(LINK_PROPERTY, invoice.getUniqueID());
					// write log
					logMessage("Invoice: " + invoice.getUniqueID() + " added. ", configuration, sepaExport);

				}

				// finally we add the sepa export document to the data collection
				data.add(sepaExport);

				// create the attachment based on the report definition
				// write a file to workitem
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HHmm");
				String sepaFileName = "sepa_" + df.format(new Date()) + ".xml";
				
				FileData filedata = reportService.transformDataSource(report, data,sepaFileName);

				// attach the file
				sepaExport.addFileData(filedata);

				// update and process invoices...
				processInvoices(sepaExport, data, event, configuration);

				// write log
				logMessage("Sepa export finished.", configuration, sepaExport);
				logMessage(count + " invoices exported. ", configuration, sepaExport);

				// finish by proessing the sepa export workitem....
				sepaExport.event(EVENT_START).event(EVENT_SUCCESS);
				workflowService.processWorkItem(sepaExport);

			} else {
				// no invoices found - so we terminate
				logger.finest("......no invoices found.");
				return configuration;
			}

		} catch (ModelException | JAXBException | TransformerException | IOException | AccessDeniedException
				| ProcessingErrorException | PluginException | QueryException e) {
			try {
				if (sepaExport != null) {
					// execute sepa workflow with EVENT_FAILED
					logMessage("Failed: " + e.getMessage(), configuration, sepaExport);
					sepaExport.event(EVENT_FAILED);
					workflowService.processWorkItem(sepaExport);
				}
			} catch (AccessDeniedException | ProcessingErrorException | PluginException | ModelException e1) {
				throw new SchedulerException(REPORT_ERROR,
						"Failed to execute sepa report '" + reportName + "' : " + e.getMessage(), e);
			}

			throw new SchedulerException(REPORT_ERROR,
					"Failed to execute sepa report '" + reportName + "' : " + e.getMessage(), e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					throw new SchedulerException(REPORT_ERROR,
							"Failed to execute sepa report '" + reportName + "' : " + e.getMessage(), e);

				}
			}
		}

		return configuration;
	}



	
	/**
	 * Creates a new log entry stored in the item _scheduler_log. The log can be
	 * writen optional to the configuraiton and the workitem
	 * 
	 * @param message
	 * @param configuration
	 */
	private void logMessage(String message, ItemCollection configuration, ItemCollection workitem) {
		if (configuration != null) {
			configuration.appendItemValue("_scheduler_log", message);
		}
		if (workitem != null) {
			workitem.appendItemValue("_scheduler_log", message);
		}

		logger.info(message);

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
	 * <item name="invoice_update">
	 *    <modelversion>1.0.0</modelversion>
	 *    <task>100</task>
	 *    <event>20</event>
	 * </item>
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
	@SuppressWarnings("unchecked")
	protected void processInvoices(ItemCollection sepaExport, List<ItemCollection> invoices, final ItemCollection event,
			ItemCollection configuration)
			throws AccessDeniedException, ProcessingErrorException, PluginException, ModelException {

		List<String> subProcessDefinitions = null;
		// test for items with name subprocess_update definition.
		ItemCollection evalItemCollection = workflowService.evalWorkflowResult(event, sepaExport, false);

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

						// test if process matches
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
					} else {
						logMessage("...invoice " + _invoice.getUniqueID() + " could not be loaded!", configuration,
								sepaExport);
					}
				}
			}

		}
	}

}
