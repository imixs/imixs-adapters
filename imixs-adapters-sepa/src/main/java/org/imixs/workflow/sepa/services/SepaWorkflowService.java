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
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.engine.scheduler.Scheduler;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
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

	public static final int EVENT_START = 100;
	public static final int EVENT_SUCCESS = 200;
	public static final int EVENT_FAILED = 300;
	public static final String INVOICE_UPDATE = "invoice_update";
	public static final String LINK_PROPERTY = "$workitemref";

	public static final String ITEM_MODEL_VERSION = "_model_version";
	public static final String ITEM_INITIAL_TASK = "_initial_task";

    public static final String ITEM_PAYMENT_TYPE = "payment.type";

	public static final String ITEM_DBTR_IBAN = "dbtr.iban";
	public static final String ITEM_DBTR_BIC = "dbtr.bic";
	public static final String ITEM_DBTR_NAME = "dbtr.name";

	public static final String ITEM_CDTR_IBAN = "cdtr.iban";
	public static final String ITEM_CDTR_BIC = "cdtr.bic";
	public static final String ITEM_CDTR_NAME = "cdtr.name";

	public static final String ITEM_DBTR_CONFIG = "dbtr.config";

	public static final String REPORT_ERROR = "REPORT_ERROR";

	@EJB
	WorkflowService workflowService = null;

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
	@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	public void processInvoices(ItemCollection sepaExport, List<ItemCollection> invoices, final ItemCollection event,
			ItemCollection configuration)
			throws AccessDeniedException, ProcessingErrorException, PluginException, ModelException {

		List<String> subProcessDefinitions = null;
		// test for items with name subprocess_update definition.
		ItemCollection evalItemCollection = workflowService.evalWorkflowResult(event, "sepa", sepaExport, false);
		if (evalItemCollection == null) {
			logger.warning("...exprected sepa item in workflow result is missing");
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
	 * This method returns a ItemColleciton containing the items
	 * ITEM_DBTR_IBAN,ITEM_DBTR_BIC,ITEM_DBTR_NAME.
	 * 
	 * @param paymentType
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ItemCollection findDbtrOptionByPaymentType(String paymentType, ItemCollection configuration) {
		if (paymentType == null) {
			return null;
		}
		// load dbtr list from configuration
		ArrayList<ItemCollection> dbtrList = new ArrayList<ItemCollection>();
		List<Object> mapItems = configuration.getItemValue(ITEM_DBTR_CONFIG);
		for (Object mapOderItem : mapItems) {
			if (mapOderItem instanceof Map) {
				ItemCollection itemCol = new ItemCollection((Map) mapOderItem);
				dbtrList.add(itemCol);
			}
		}
		// test for maching dbtr option by name...
		for (ItemCollection dbtr : dbtrList) {
			if (paymentType.equals(dbtr.getItemValueString("name"))) {
				return dbtr;
			}
		}
		return null;
	}

}
