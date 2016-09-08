package org.imixs.workflow.datev.web;
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


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.datev.DatevSchedulerService;
import org.imixs.workflow.datev.DatevService;
import org.imixs.workflow.engine.DocumentService;

/**
 * 
 * 
 * @author rsoika
 * 
 */
@Named
@SessionScoped
public class DatevController implements Serializable {
	public final static String FIELD_ORDER_ITEMS = "txtorderitems";

	private static final long serialVersionUID = 1L;

	
	private ItemCollection configItemCollection;

	private List<ItemCollection> configurations = null;

	@EJB
	DatevSchedulerService datevSchedulerService;

	
	@EJB
	DatevService datevService;
	@EJB
	DocumentService documentService;

	private static Logger logger = Logger.getLogger(DatevController.class.getName());

	@PostConstruct
	public void init() {
		// load COnfig
		try {
			configurations = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DatevService getDatevService() {
		return datevService;
	}

	/**
	 * returns all Datev shop config entities
	 * 
	 * @return
	 */
	public List<ItemCollection> getConfigurations() {
		if (configurations == null) {
			configurations = datevService.findAllConfigurations();
		}

		return configurations;
	}

	/**
	 * returns the current configuration workitem.
	 * 
	 * @return
	 */
	public ItemCollection getConfiguration() {
		return this.configItemCollection;
	}

	/**
	 * Creates a new Datev Configuation Entity
	 * 
	 * @return
	 */
	public ItemCollection createConfiguration() {
		configItemCollection = new ItemCollection();
		configItemCollection.replaceItemValue("type", DatevService.TYPE);

		return configItemCollection;

	}

	/**
	 * save method tries to load the config entity. if not availabe. the method
	 * will create the entity the first time
	 * 
	 * @return
	 * @throws Exception
	 */
	public void saveConfiguration(ActionEvent event) {
		// save entity
		try {
			configItemCollection.replaceItemValue("datLastModified", new Date());
			configItemCollection = datevSchedulerService.saveConfiguration(configItemCollection);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
			e.printStackTrace();

		}

		reset();

	}

	/**
	 * save method tries to load the config entity. if not availabe. the method
	 * will create the entity the first time
	 * 
	 * @return
	 * @throws Exception
	 */
	public void loadConfiguration(String uniqueid) {
		configItemCollection = documentService.load(uniqueid);
	}

	/**
	 * Creates a existing Datev Configuration Entity
	 * 
	 * @return
	 */
	public void deleteConfiguration(String uniqueid) {
		configItemCollection = documentService.load(uniqueid);
		if (configItemCollection != null) {
			try {
				datevSchedulerService.stop(configItemCollection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		documentService.remove(configItemCollection);
		reset();
	}



	public void refresh() throws Exception {
		configItemCollection = datevSchedulerService.updateTimerDetails(configItemCollection, true);
	}

	public void reset() {
		logger.fine("DatevController - +++ reset configuration lists +++");
		
		configurations = null;
		
	}

	
	


	/**
	 * 
	 * converts time (in milliseconds) to human-readable format "<dd:>hh:mm:ss"
	 * 
	 * @return
	 */
	public String millisToShortDHMS(int duration) {

		String res = "";
		long days = TimeUnit.MILLISECONDS.toDays(duration);
		long hours = TimeUnit.MILLISECONDS.toHours(duration)
				- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
				- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
		if (days == 0) {
			res = String.format("%d hours, %d minutes, %d seconds", hours, minutes, seconds);
		} else {
			res = String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
		}
		return res;

	}

	/**
	 * starts the timer service
	 * 
	 * @return
	 * @throws Exception
	 */
	public void doStartScheduler(ActionEvent event) {
		configItemCollection.replaceItemValue("_enabled", true);
		try {
			configItemCollection = datevSchedulerService.saveConfiguration(configItemCollection);
			configItemCollection = datevSchedulerService.start(configItemCollection);
		} catch (Exception e) {
			String message = "";

			if (e.getCause() != null)
				message = e.getCause().getMessage();
			else
				message = e.getMessage();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
			e.printStackTrace();

		}

	}

	public void doStopScheduler(ActionEvent event) throws Exception {
		configItemCollection.replaceItemValue("_enabled", false);
		configItemCollection = datevSchedulerService.saveConfiguration(configItemCollection);
		configItemCollection = datevSchedulerService.stop(configItemCollection);
	}

	public void doRestartScheduler(ActionEvent event) throws Exception {
		logger.fine("[WorkflowSchedulerCOntroller] restart timer service");
		doStopScheduler(event);
		doStartScheduler(event);
	}

	

	public double convertDouble(String aValue) {
		if (aValue == null || aValue.isEmpty())
			return 0;

		return new Double(aValue);
	}



}
