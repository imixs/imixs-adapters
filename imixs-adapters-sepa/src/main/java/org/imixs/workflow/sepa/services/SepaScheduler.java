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

import javax.ejb.EJB;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.Model;
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
import org.imixs.workflow.xml.XSLHandler;

/**
 * SEPA Scheduler implementation.
 * 
 * @author rsoika
 * 
 */
public class SepaScheduler implements Scheduler {

	public static final String SEPA_CONFIGURATION = "SEPA_CONFIGURATION";
	public static final int EVENT_SUCCESS = 100;
	public static final int EVENT_FAILED = 200;

	public static final String ITEM_MODEL_VERSION = "_model_version";
	public static final String ITEM_INITIAL_TASK = "_initial_task";
	public static final String ITEM_QUERY = "_query";

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
			String query = configuration.getItemValueString(ITEM_QUERY);

			sepaExport = new ItemCollection().model(modelVersion).task(taskID);

			Model model;

			model = modelService.getModel(modelVersion);

			ItemCollection event = model.getEvent(taskID, EVENT_SUCCESS);
			// load the report
			reportName = event.getItemValueString("txtReportName");
			ItemCollection report = reportService.getReport(reportName);

			if (report == null) {
				throw new SchedulerException(REPORT_ERROR,
						"unable to load report '" + reportName + "'. Please check model configuration!");
			}

			// now find the invoices....
			List<ItemCollection> invoices = workflowService.getDocumentService().find(query, MAX_COUNT, 0);

			logger.info("...found " + invoices.size() + " invoices...");

			if (invoices.size() > 0) {
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

				// create a ByteArray Output Stream
				//outputStream = new ByteArrayOutputStream();
				byte[] _bytes=XSLHandler.transform(invoices, xslTemplate, encoding, outputStream);
				// write to workitem
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HHmm");
				String sepaFileName = "sepa_" + df.format(new Date()) + ".xml";
				//byte[] _bytes=outputStream.toByteArray();
				sepaExport.addFile(_bytes, sepaFileName, sContentType);

				sepaExport.event(EVENT_SUCCESS);
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

}
