package org.imixs.workflow.datev.controller;
/*******************************************************************************
 *  Imixs Workflow Technology
 *  Copyright (C) 2003, 2008 Imixs Software Solutions GmbH,  
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
 *  
 *******************************************************************************/

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.imixs.workflow.datev.services.DatevSchedulerCSV;
import org.imixs.workflow.datev.services.DatevSchedulerXML;
import org.imixs.workflow.engine.scheduler.SchedulerController;

/**
 * The DatevController is used to configure the DatevScheduler. This service is
 * used to generate datev export workitems.
 * <p>
 * The Controller creates a configuration entity "type=configuration;
 * txtname=datev".
 * <p>
 * The following config items are defined:
 * 
 * The following config items are defined:
 * 
 * <pre>
 * _model_version = model version for the SEPA export
 * _initial_task = inital task ID
 * </pre>
 * 
 * 
 * @author rsoika
 * 
 */
@Named
@RequestScoped
public class DatevController extends SchedulerController {

	public static final String DATEV_CONFIGURATION = "DATEV_CONFIGURATION";

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DatevController.class.getName());

	@Override
	public String getName() {
		return DATEV_CONFIGURATION;
	}

	/**
	 * Returns the sepa scheduler class name. This name depends on the _export_type.
	 * 
	 * There are two export interfaces available - csv and XML
	 * 
	 */
	@Override
	public String getSchedulerClass() {

		String schedulerClass = null;
		if ("xml".equals(this.getConfiguration().getItemValueString("_export_type"))) {
			schedulerClass = DatevSchedulerXML.class.getName();
		} else {
			// default is CSV
			schedulerClass = DatevSchedulerCSV.class.getName();
		}

		logger.finest("......datev scheduler: " + schedulerClass);
		return schedulerClass;
	}

}
