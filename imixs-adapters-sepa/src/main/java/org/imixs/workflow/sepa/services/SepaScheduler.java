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

import javax.ejb.EJB;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.engine.ModelService;
import org.imixs.workflow.engine.scheduler.Scheduler;
import org.imixs.workflow.exceptions.QueryException;

/**
 * SEPA Scheduler implementation.
 * 
 * @author rsoika
 * 
 */
public class SepaScheduler implements Scheduler {

	@EJB
	DocumentService documentService;

	@EJB
	ModelService modelService;
	public static final String SEPA_CONFIGURATION = "SEPA_CONFIGURATION";

	/**
	 * This is the method which processes the timeout event depending on the running
	 * timer settings.
	 * 
	 * 
	 * 
	 * @param timer
	 * @throws Exception
	 * @throws QueryException
	 */
	public ItemCollection run(ItemCollection configuration) {

		int maxCount = configuration.getItemValueInteger("_maxcount");
		if (maxCount == 0) {
			maxCount = -1;
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
