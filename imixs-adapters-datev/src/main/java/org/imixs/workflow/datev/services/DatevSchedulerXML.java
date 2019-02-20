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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
import org.imixs.workflow.xml.XMLDataCollectionAdapter;
import org.imixs.workflow.xml.XSLHandler;

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

	public static final int MAX_COUNT = 999;

	@EJB
	DocumentService documentService;

	@EJB
	WorkflowService workflowService;

	@EJB
	DatevWorkflowService datevWorkflowService;

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

		ItemCollection invoiceReport = null;
		ItemCollection documentsReport = null;

		String reportNameInvoices = "";
		String reportNameDocuments = "";
		ItemCollection datevExport = null;
		int maxCount = configuration.getItemValueInteger("_maxcount");
		if (maxCount == 0) {
			maxCount = -1;
		}
		try {

			String modelVersion = configuration.getItemValueString(DatevWorkflowService.ITEM_MODEL_VERSION);
			int taskID = configuration.getItemValueInteger(DatevWorkflowService.ITEM_INITIAL_TASK);

			// fetch the inital event
			Model model = modelService.getModel(modelVersion);
			ItemCollection event = model.getEvent(taskID, DatevWorkflowService.EVENT_START);

			// load the report
			reportNameInvoices = configuration.getItemValueString("_report_invoices");
			invoiceReport = reportService.findReport(reportNameInvoices);
			if (invoiceReport == null) {
				throw new SchedulerException(DatevWorkflowService.REPORT_ERROR,
						"unable to load invoice report definition '" + reportNameInvoices
								+ "'. Please check the configuration");
			}
			reportNameDocuments = configuration.getItemValueString("_report_documents");
			documentsReport = reportService.findReport(reportNameDocuments);
			if (documentsReport == null) {
				throw new SchedulerException(DatevWorkflowService.REPORT_ERROR,
						"unable to load documents report definition '" + reportNameDocuments
								+ "'. Please check the configuration");
			}

			// get the data source based on the report definition....
			List<ItemCollection> masterDataSet = reportService.getDataSource(invoiceReport, MAX_COUNT, 0, "$created",
					false, null);

			DatevWorkflowService.logMessage("...DATEV export started....", configuration, null);
			DatevWorkflowService.logMessage("...found " + masterDataSet.size() + " invoices...", configuration, null);

			// update the invoices with optional datev_client_id if not provided
			// link the invoices with the datev workitem.
			if (masterDataSet.size() > 0) {
				// add ITEM_DATEV_CLIENT_ID from the DATEV config if missing
				for (ItemCollection invoice : masterDataSet) {
					if (invoice.getItemValueString(DatevWorkflowService.ITEM_DATEV_CLIENT_ID).isEmpty()) {
						invoice.replaceItemValue(DatevWorkflowService.ITEM_DATEV_CLIENT_ID,
								configuration.getItemValue(DatevWorkflowService.ITEM_DATEV_CLIENT_ID));
					}
				}

				Map<String, List<ItemCollection>> invoiceGroups = groupInvoicesBy(masterDataSet,
						DatevWorkflowService.ITEM_DATEV_CLIENT_ID);

				// now we iterate over each invoice grouped by the _datev_client_id
				for (String key : invoiceGroups.keySet()) {

					// get list of invoices by mandant id
					List<ItemCollection> data = invoiceGroups.get(key);
					// create export workitem with attached zip file....
					datevExport = buildZipFile(data, configuration, invoiceReport, documentsReport);

					// update and process invoices in new trasaction to avoid partial updates...
					datevWorkflowService.processInvoices(datevExport, data, event, configuration);
					// success full finished!
					DatevWorkflowService.logMessage("...DATEV export ClientID=" + key + "  finished.", configuration,
							datevExport);

				}
				DatevWorkflowService.logMessage("...DATEV export completed", configuration, null);

			} else {
				// no invoices found - so we terminate
				logger.finest("......no invoices found.");
				return configuration;
			}

		} catch (PluginException e) {
			// In case of a plugin exeption we continue the scheduler and mark the export as
			// failed
			try {
				if (datevExport != null) {
					// execute datev workflow with EVENT_FAILED
					DatevWorkflowService.logMessage("Failed: " + e.getMessage(), configuration, datevExport);
					datevExport.event(DatevWorkflowService.EVENT_FAILED);
					workflowService.processWorkItem(datevExport);
				}
			} catch (AccessDeniedException | ProcessingErrorException | PluginException | ModelException e1) {
				throw new SchedulerException(DatevWorkflowService.REPORT_ERROR,
						"Failed to execute DATEV report '" + reportNameInvoices + "' : " + e.getMessage(), e);
			}

		} catch (ModelException | AccessDeniedException | ProcessingErrorException | QueryException e) {
			try {
				if (datevExport != null) {
					// execute datev workflow with EVENT_FAILED
					DatevWorkflowService.logMessage("Failed: " + e.getMessage(), configuration, datevExport);
					datevExport.event(DatevWorkflowService.EVENT_FAILED);
					workflowService.processWorkItem(datevExport);
				}
			} catch (Exception e1) {
				throw new SchedulerException(DatevWorkflowService.REPORT_ERROR,
						"Failed to execute DATEV report '" + reportNameInvoices + "' : " + e.getMessage(), e);
			}

			throw new SchedulerException(DatevWorkflowService.REPORT_ERROR,
					"Failed to execute DATEV report '" + reportNameInvoices + "' : " + e.getMessage(), e);
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
	 * This method builds an export workitem containg the datev zip file.
	 * 
	 * @param data
	 * @return
	 * @throws SchedulerException
	 */
	private ItemCollection buildZipFile(List<ItemCollection> data, ItemCollection configuration,
			ItemCollection invoiceReport, ItemCollection documentsReport) throws SchedulerException {

		ZipOutputStream datevZip = null;
		ByteArrayOutputStream zipOutputStream = null;

		String modelVersion = configuration.getItemValueString(DatevWorkflowService.ITEM_MODEL_VERSION);
		int taskID = configuration.getItemValueInteger(DatevWorkflowService.ITEM_INITIAL_TASK);

		// build the datev export workitem....
		ItemCollection datevExport = new ItemCollection().model(modelVersion).task(taskID);
		datevExport.replaceItemValue(WorkflowKernel.CREATED, new Date());
		datevExport.replaceItemValue(WorkflowKernel.MODIFIED, new Date());
		// set unqiueid, needed for xslt
		datevExport.setItemValue(WorkflowKernel.UNIQUEID, WorkflowKernel.generateUniqueID());
		// copy datev_client_id
		// datevExport.setItemValue(DatevWorkflowService.ITEM_DATEV_CLIENT_ID, key);

		// set _datev_fiscal_start (date) from first invoice if available...
		ItemCollection firstInvoice = data.get(0);
		if (firstInvoice.hasItem(DatevWorkflowService.ITEM_DATEV_FISCAL_START)) {
			datevExport.setItemValue(DatevWorkflowService.ITEM_DATEV_FISCAL_START,
					firstInvoice.getItemValue(DatevWorkflowService.ITEM_DATEV_FISCAL_START));
			datevExport.setItemValue(DatevWorkflowService.ITEM_DATEV_CLIENT_ID,
					firstInvoice.getItemValue(DatevWorkflowService.ITEM_DATEV_CLIENT_ID));
		}

		datevExport.setItemValue(DatevWorkflowService.ITEM_DATEV_CONSULTANT_ID,
				configuration.getItemValue(DatevWorkflowService.ITEM_DATEV_CONSULTANT_ID));
		// datevExport.setItemValue(WorkflowKernel.WORKFLOWGROUP,
		// task.getItemValue("txtworkflowgroup"));

		DatevWorkflowService.logMessage("...build new DATEV export...", configuration, datevExport);

		try {
			// out put file
			zipOutputStream = new ByteArrayOutputStream();
			datevZip = new ZipOutputStream(zipOutputStream);

			// now we iterate over all invoices in this group
			// and create a XML file with belegsatzdaten for each invoice
			String xslInvoice = invoiceReport.getItemValueString("XSL").trim();
			if (xslInvoice.isEmpty()) {
				throw new SchedulerException(DatevWorkflowService.REPORT_ERROR, "Failed to build DATEV zip archive '"
						+ invoiceReport.getItemValueString("txtname") + " XSL content is missing.");
			}
			String xslDocuments = documentsReport.getItemValueString("XSL").trim();
			if (xslDocuments.isEmpty()) {
				throw new SchedulerException(DatevWorkflowService.REPORT_ERROR, "Failed to build DATEV zip archive '"
						+ documentsReport.getItemValueString("txtname") + " XSL content is missing.");
			}

			String encoding = invoiceReport.getItemValueString("encoding");
			for (ItemCollection invoice : data) {
				// first link invoices with export workitem....
				datevExport.appendItemValue(DatevWorkflowService.LINK_PROPERTY, invoice.getUniqueID());

				// create XML file
				// we need a set of two documents - the DatevExport configuration Document and
				// the invoice
				ByteArrayOutputStream outputStream = null;
				try {
					// ByteArray of a XMLDataCollection
					byte[] xmlData = XMLDataCollectionAdapter.writeItemCollection(invoice);
					outputStream = new ByteArrayOutputStream();

					String xml = new String(xmlData);
					XSLHandler.transform(new String(xml), xslInvoice, encoding, outputStream);
					byte[] byteData = outputStream.toByteArray();

					// name the file inside the zip file and add a new entry
					datevZip.putNextEntry(new ZipEntry(invoice.getUniqueID() + ".xml"));
					// write data and close entry
					datevZip.write(byteData);
					datevZip.closeEntry();

				} catch (IOException | TransformerException | JAXBException e) {
					throw new SchedulerException(DatevWorkflowService.REPORT_ERROR,
							"Failed to build DATEV zip archive '" + invoiceReport.getItemValueString("txtname") + "' : "
									+ e.getMessage(),
							e);
				}

				// and now we add the attachment.....
				FileData fileData = getWorkItemFile(invoice);
				if (fileData != null) {
					// name the file inside the zip file and add a new entry
					datevZip.putNextEntry(new ZipEntry(fileData.getName()));
					// write data and close entry
					datevZip.write(fileData.getContent());
					datevZip.closeEntry();
				}

				// write log
				DatevWorkflowService.logMessage("......Invoice: " + invoice.getUniqueID() + " added. ", configuration,
						datevExport);
			}

			// now we need to construct the document.xml file containing the attachment
			// information
			ByteArrayOutputStream outputStream = null;
			try {
				byte[] xmlData = XMLDataCollectionAdapter.writeItemCollection(data);
				outputStream = new ByteArrayOutputStream();
				String xml = new String(xmlData);
				XSLHandler.transform(new String(xml), xslDocuments, encoding, outputStream);
				byte[] byteData = outputStream.toByteArray();

				// name the file inside the zip file and add a new entry
				datevZip.putNextEntry(new ZipEntry("document.xml"));
				// write data and close entry
				datevZip.write(byteData);
				datevZip.closeEntry();
			} catch (IOException | TransformerException | JAXBException e) {
				throw new SchedulerException(DatevWorkflowService.REPORT_ERROR, "Failed to build DATEV zip archive '"
						+ documentsReport.getItemValueString("txtname") + "' : " + e.getMessage(), e);
			}

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HHmm");
			String datevFileName = "datev_buchungsstapel_" + df.format(new Date()) + ".zip";

			datevZip.close();
			FileData zipFileData = new FileData(datevFileName, zipOutputStream.toByteArray(), "application/zip",null);
			datevExport.addFileData(zipFileData);

		} catch (IOException e) {
			throw new SchedulerException(DatevWorkflowService.REPORT_ERROR, "Failed to create DATEV archive '"
					+ invoiceReport.getItemValueString("txtname") + "' : " + e.getMessage(), e);
		} finally {
			try {
				// try to close the streams (unclear if necessary here...)
				if (datevZip != null) {
					datevZip.close();
				}
				if (zipOutputStream != null) {
					zipOutputStream.close();
				}
			} catch (IOException e) {
				throw new SchedulerException(DatevWorkflowService.REPORT_ERROR, "Failed to close DATEV archive '"
						+ invoiceReport.getItemValueString("txtname") + "' : " + e.getMessage(), e);
			}

		}
		return datevExport;
	}

	/**
	 * This method returns the first fileData from a snapshot by a given invoice
	 * workItem.
	 * 
	 * @param uniqueid
	 * @param file
	 *            - file name
	 * @return FileData object for the given filename.
	 */
	private FileData getWorkItemFile(ItemCollection invoice) {
		String file;
		String snapshotID;

		List<String> filenames = invoice.getFileNames();
		if (filenames != null && filenames.size() > 0) {
			file = filenames.get(0);
			// test if we have a $snapshotid
			snapshotID = invoice.getItemValueString("$snapshotid");

			ItemCollection snapshot = documentService.load(snapshotID);
			if (snapshot != null) {
				return snapshot.getFileData(file);
			}
		}

		return null;
	}
}
