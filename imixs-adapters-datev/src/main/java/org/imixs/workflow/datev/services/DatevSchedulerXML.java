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
package org.imixs.workflow.datev.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.imixs.workflow.engine.scheduler.SchedulerService;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.util.XMLParser;

/**
 * The DatevSchedulerXML implementation exports workflow invoice data into the
 * DATEV Belegsatzschnittstelle im XML-Format
 * <p>
 * The class implements the interface
 * _org.imixs.workflow.engine.scheduler.Scheduler_ and can be used in
 * combination with the Imxis-Workflow Scheduler Service.
 * <p>
 * The goal of the DatevSchedulerXML is to create a ZIP file containing the
 * invoices files and the DATEV Belegsatz data in xml format. One XML file is
 * generated for each invoide. The file "document.xml" contains the links to the
 * invoice attachments.
 * 
 * <pre>
	├── document.xml/
	├── Eingangsrechnung_001.pdf
	├── Eingangsrechnung_001.xml
	├── Eingangsrechnung_002.pdf
	├── Eingangsrechnung_002.xml
 * </pre>
 *
 * @see SchedulerService
 * @author rsoika
 * 
 */
public class DatevSchedulerXML implements Scheduler {

	public static final String DATEV_CONFIGURATION = "DATEV_CONFIGURATION";

	public static final int EVENT_START = 100;
	public static final int EVENT_SUCCESS = 200;
	public static final int EVENT_FAILED = 300;
	public static final String INVOICE_UPDATE = "invoice_update";
	public static final String LINK_PROPERTY = "txtworkitemref";

	public static final String ITEM_MODEL_VERSION = "_model_version";
	public static final String ITEM_INITIAL_TASK = "_initial_task";

	public static final String ITEM_DATEV_CLIENT_ID = "_datev_client_id";
	public static final String ITEM_DATEV_CONSULTANT_ID = "_datev_consultant_id";
	public static final String ITEM_DATEV_FISCAL_START = "_datev_fiscal_start";

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

	private static Logger logger = Logger.getLogger(DatevSchedulerXML.class.getName());

	/**
	 * This is the method which processes the timeout event depending on the running
	 * timer settings.
	 * 
	 *
	 * 
	 * 
	 * @param timer
	 * @throws QueryException
	 */
	public ItemCollection run(ItemCollection configuration) throws SchedulerException {
		ByteArrayOutputStream outputStream = null;
		String reportName = "";
		ItemCollection datevExport = null;
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

			// get the data source based on the report definition....
			List<ItemCollection> masterDataSet = reportService.getDataSource(report, MAX_COUNT, 0, "$created", false,
					null);

			logMessage("...DATEV export started....", configuration, null);
			logMessage("...found " + masterDataSet.size() + " invoices...", configuration, null);

			// update the invoices with optional datev_client_id if not provided
			// link the invoices with the datev workitem.
			if (masterDataSet.size() > 0) {
				// add ITEM_DATEV_CLIENT_ID from the DATEV config if missing
				for (ItemCollection invoice : masterDataSet) {
					if (invoice.getItemValueString(ITEM_DATEV_CLIENT_ID).isEmpty()) {
						invoice.replaceItemValue(ITEM_DATEV_CLIENT_ID,
								configuration.getItemValue(ITEM_DATEV_CLIENT_ID));
					}
				}

				Map<String, List<ItemCollection>> invoiceGroups = groupInvoicesBy(masterDataSet, ITEM_DATEV_CLIENT_ID);

				// now we iterate over each invoice grouped by the _datev_client_id
				for (String key : invoiceGroups.keySet()) {

					List<ItemCollection> data = invoiceGroups.get(key);
					int groupCount = data.size();
					// build the datev export workitem....
					datevExport = new ItemCollection().model(modelVersion).task(taskID);
					datevExport.replaceItemValue(WorkflowKernel.CREATED, new Date());
					datevExport.replaceItemValue(WorkflowKernel.MODIFIED, new Date());
					// set unqiueid, needed for xslt
					datevExport.setItemValue(WorkflowKernel.UNIQUEID, WorkflowKernel.generateUniqueID());
					// copy datev_client_id
					datevExport.setItemValue(ITEM_DATEV_CLIENT_ID, key);

					// set _datev_fiscal_start (date) from first invoice if available...
					ItemCollection firstInvoice = data.get(0);
					if (firstInvoice.hasItem(ITEM_DATEV_FISCAL_START)) {
						datevExport.setItemValue(ITEM_DATEV_FISCAL_START,
								firstInvoice.getItemValue(ITEM_DATEV_FISCAL_START));
					}

					datevExport.setItemValue(ITEM_DATEV_CONSULTANT_ID,
							configuration.getItemValue(ITEM_DATEV_CONSULTANT_ID));
					datevExport.setItemValue(WorkflowKernel.WORKFLOWGROUP, task.getItemValue("txtworkflowgroup"));

					logMessage("...starting DATEV export for ClientID=" + key + "...", configuration, datevExport);

				
					
					// now we iterate over all invoices in this group
					// and create a XML file with belegsatzdaten for each invoice
					
					for (ItemCollection invoice : data) {
						// first link invoices with export workitem....
							datevExport.appendItemValue(LINK_PROPERTY, invoice.getUniqueID());

							
						// create XML file
							// we need a set of two documents - the DatevExport configuration Document and the invoice 
							
							
							
							
							// write log
						logMessage("......Invoice: " + invoice.getUniqueID() + " added. ", configuration, datevExport);
					}

					
					
					
					// finally we add the datev export document to the data collection
					data.add(datevExport);

					// create the attachment based on the report definition
					// write a file to workitem
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HHmm");
					String datevFileName = "EXTF_Buchungsstapel_" + df.format(new Date()) + ".csv";

					FileData filedata = reportService.transformDataSource(report, data, datevFileName);

					// attach the file
					datevExport.addFileData(filedata);

					// update and process invoices...
					processInvoices(datevExport, data, event, configuration);

					// write log
					logMessage("...DATEV export ClientID=" + key + "  finished.", configuration, datevExport);
					logMessage("..." + groupCount + " invoices exported. ", configuration, datevExport);

					// finish by proessing the datev export workitem....
					datevExport.event(EVENT_START).event(EVENT_SUCCESS);
					workflowService.processWorkItem(datevExport);

				}

				logMessage("...DATEV export completed", configuration, null);

			} else {
				// no invoices found - so we terminate
				logger.finest("......no invoices found.");
				return configuration;
			}

		} catch (ModelException | JAXBException | TransformerException | IOException | AccessDeniedException
				| ProcessingErrorException | PluginException | QueryException e) {
			try {
				if (datevExport != null) {
					// execute datev workflow with EVENT_FAILED
					logMessage("Failed: " + e.getMessage(), configuration, datevExport);
					datevExport.event(EVENT_FAILED);
					workflowService.processWorkItem(datevExport);
				}
			} catch (AccessDeniedException | ProcessingErrorException | PluginException | ModelException e1) {
				throw new SchedulerException(REPORT_ERROR,
						"Failed to execute DATEV report '" + reportName + "' : " + e.getMessage(), e);
			}

			throw new SchedulerException(REPORT_ERROR,
					"Failed to execute DATEV report '" + reportName + "' : " + e.getMessage(), e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					throw new SchedulerException(REPORT_ERROR,
							"Failed to execute DATEV report '" + reportName + "' : " + e.getMessage(), e);

				}
			}
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
	 * @param datevExport
	 *            - datev export workitem
	 * @param invoices
	 *            - list of invoices
	 * @param event
	 *            - current datev export event containing the invoice_update
	 *            definition.
	 * @throws AccessDeniedException
	 * @throws ProcessingErrorException
	 * @throws PluginException
	 * @throws ModelException
	 */
	@SuppressWarnings("unchecked")
	protected void processInvoices(ItemCollection datevExport, List<ItemCollection> invoices,
			final ItemCollection event, ItemCollection configuration)
			throws AccessDeniedException, ProcessingErrorException, PluginException, ModelException {

		List<String> subProcessDefinitions = null;
		// test for items with name subprocess_update definition.
		ItemCollection evalItemCollection = workflowService.evalWorkflowResult(event, datevExport, false);

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
								logger.warning("subprocess itemList is not supported by the DatevScheduler!");
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

}
