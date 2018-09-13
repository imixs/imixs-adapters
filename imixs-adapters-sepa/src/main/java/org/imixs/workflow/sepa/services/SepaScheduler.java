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
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

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
import org.imixs.workflow.xml.XSLHandler;

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

			sepaExport = new ItemCollection().model(modelVersion).task(taskID);
			// set unqiueid, needed for xslt
			sepaExport.setItemValue(WorkflowKernel.UNIQUEID, WorkflowKernel.generateUniqueID());
			// copy iban/bic
			sepaExport.setItemValue("_iban", configuration.getItemValue("_iban"));
			sepaExport.setItemValue("_bic", configuration.getItemValue("_bic"));
			sepaExport.setItemValue("_subject", configuration.getItemValue("_subject"));
			sepaExport.setItemValue(WorkflowKernel.WORKFLOWGROUP, task.getItemValue("txtworkflowgroup"));

			// load the report
			reportName = event.getItemValueString("txtReportName");
			ItemCollection report = reportService.getReport(reportName);

			if (report == null) {
				throw new SchedulerException(REPORT_ERROR, "unable to load report '" + reportName
						+ "'. Please check  model configuration '" + modelVersion + "'");
			}

			// now find the invoices....
			List<ItemCollection> data = reportService.executeReport(reportName, MAX_COUNT, 0, "$created", false, null);
			logMessage("Sepa export started....", configuration,null);
			logMessage("...found " + data.size() + " invoices...", configuration,null);

			if (data.size() > 0) {

				// update the invoices with the _sepa_iban if not provided
				// link the invoices with the sepa workitem. Count invoices and controll sum
				int count = 0;
				float amount = 0;
				for (ItemCollection invoice : data) {
					// test if invoice has a _sepa_iban and _sepa_bic

					if (invoice.getItemValueString("_sepa_iban").isEmpty()) {
						// overtake iban from sepa export
						invoice.setItemValue("_sepa_iban", sepaExport.getItemValue("_iban"));
					}
					if (invoice.getItemValueString("_sepa_bic").isEmpty()) {
						// overtake iban from sepa export
						invoice.setItemValue("_sepa_bic", sepaExport.getItemValue("_bic"));
					}

					sepaExport.appendItemValue(LINK_PROPERTY, invoice.getUniqueID());

					// write log
					logMessage( "Invoice: " + invoice.getUniqueID() + " added. ", configuration,sepaExport);

				}

				// add sepa data....
//				sepaExport.setItemValue("NbOfTxs",

				String xslTemplate = report.getItemValueString("xsl").trim();
				// execute the transformation based on the report defintion....
				String sContentType = report.getItemValueString("contenttype");
				if ("".equals(sContentType)) {
					sContentType = MediaType.TEXT_XML;
				}
				String encoding = report.getItemValueString("encoding");
				if ("".equals(encoding)) {
					// no encoding defined so we default to UTF-8
					encoding = "UTF-8";
				}

				// now add the sepa export document to the data collection
				data.add(sepaExport);

				// create a ByteArray Output Stream
				// outputStream = new ByteArrayOutputStream();
				byte[] _bytes = XSLHandler.transform(data, xslTemplate, encoding, outputStream);
				// write to workitem
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HHmm");
				String sepaFileName = "sepa_" + df.format(new Date()) + ".xml";
				// byte[] _bytes=outputStream.toByteArray();
				sepaExport.addFile(_bytes, sepaFileName, sContentType);

				// update and process invoices...
				processInvoices(sepaExport, data, event);

				// write log
				logMessage("Sepa export finished.",configuration,sepaExport);
				logMessage( data.size() + " invoices exported. ",configuration,sepaExport);
				// finish sepa export
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
					logMessage("Failed: " + e.getMessage(),configuration,sepaExport);
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
	 * Creates a new log entry stored in the item _scheduler_log.
	 * The log can be writen optional to the configuraiton and the workitem
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
	protected void processInvoices(ItemCollection sepaExport, List<ItemCollection> invoices, final ItemCollection event)
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
				for (ItemCollection invoice : invoices) {

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
						invoice.setEventID(Integer.valueOf(processData.getItemValueString("event")));
						// process the exisitng subprocess...
						invoice = workflowService.processWorkItem(invoice);
						logger.finest("...... successful updated subprocess.");
					}
				}
			}

		}
	}

}
