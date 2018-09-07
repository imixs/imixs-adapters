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

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.engine.ModelService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.engine.scheduler.Scheduler;
import org.imixs.workflow.engine.scheduler.SchedulerException;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.exceptions.QueryException;
import org.imixs.workflow.exceptions.WorkflowException;

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

	public static final int MAX_COUNT = 999;

	@EJB
	DocumentService documentService;

	@EJB
	WorkflowService workflowService;

	@EJB
	ModelService modelService;

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

		ItemCollection sepaExport;
		int maxCount = configuration.getItemValueInteger("_maxcount");
		if (maxCount == 0) {
			maxCount = -1;
		}

		// load the model
	try {

			String modelVersion = configuration.getItemValueString(ITEM_MODEL_VERSION);
			int taskID = configuration.getItemValueInteger(ITEM_INITIAL_TASK);
			String query = configuration.getItemValueString(ITEM_QUERY);

			sepaExport = new ItemCollection().model(modelVersion).task(taskID);

			// find invoices....
			List<ItemCollection> invoices = workflowService.getDocumentService().find(query, MAX_COUNT, 0);

			logger.info("...found " + invoices.size() + " invoices...");

			if (invoices.size() > 0) {
				sepaExport.event(EVENT_SUCCESS);
				workflowService.processWorkItem(sepaExport);
			}

	} catch (WorkflowException e) {
		logger.warning("...processing error: " + e.getMessage());
		throw new SchedulerException(e.getErrorContext(),e.getErrorCode(),e.getMessage(), e);
	}
		// create 3 new Export workitems for DirectDebit, KPMG and Online
		// Banking
		int errors = 0;
		int checked = 0;
		int exports = 0;

		ItemCollection workitem = null;

		// 1. run

		return configuration;
	}

	
	
}
