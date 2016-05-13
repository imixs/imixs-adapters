package org.imixs.workflow.datev;
/*******************************************************************************
 *  Imixs IX Workflow Technology
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.AccessDeniedException;
import org.imixs.workflow.jee.ejb.EntityService;
import org.imixs.workflow.jee.ejb.WorkflowService;
import org.imixs.workflow.xml.XMLItemCollection;
import org.imixs.workflow.xml.XMLItemCollectionAdapter;

/**
 * Datev - Scheduler
 * 
 * This is the implementation of a scheduler service. The EJB implementation can
 * be used as a Timer Service to process scheduled activities.
 * 
 * 
 * The TimerService can be started using the method start(). The Methods
 * findTimerDescription and findAllTimerDescriptions are used to lookup enabled
 * and running service instances.
 * 
 * Each Method expects or generates a TimerDescription Object. This object is an
 * instance of a ItemCollection. To create a new timer the ItemCollection should
 * contain the following attributes:
 * 
 * datstart - Date Object
 * 
 * datstop - Date Object
 * 
 * numInterval - Integer Object (interval in seconds)
 * 
 * id - String - unique identifier for the schedule Service.
 * 
 * $modelversion - String - identifies the model which schould be managed by the
 * service
 * 
 * the following additional attributes are generated by the finder methods and
 * can be used by an application to verfiy the status of a running instance:
 * 
 * nextTimeout - Next Timeout - pint of time when the service will be scheduled
 * 
 * timeRemaining - Timeout in milliseconds
 * 
 * statusmessage - text message
 * 
 * 
 * 
 ******** Datev Import *************************************
 * 
 * The timer service starts the processImport method. this method is responsible
 * for the import of a datev csv file.
 * 
 * @author rsoika
 * 
 */
@Stateless
@LocalBean
@DeclareRoles({ "org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@RunAs("org.imixs.ACCESSLEVEL.MANAGERACCESS")
public class DatevSchedulerService {

	private Date endDate;

	public final static String IMPORT_ERROR = "IMPORT_ERROR";

	@Resource
	SessionContext ctx;

	@EJB
	WorkflowService workflowService;

	@EJB
	DatevService datevService;

	@Resource
	javax.ejb.TimerService timerService;

	private static Logger logger = Logger.getLogger(DatevSchedulerService.class.getName());

	/**
	 * This method saves the timer configuration. The method ensures that the
	 * following properties are set to default.
	 * <ul>
	 * <li>type</li>
	 * <li>txtName</li>
	 * <li>$writeAccess</li>
	 * <li>$readAccess</li>
	 * </ul>
	 * 
	 * The method also updates the timer details of a running timer.
	 * 
	 * @return
	 * @throws AccessDeniedException
	 */
	public ItemCollection saveConfiguration(ItemCollection configItemCollection) throws AccessDeniedException {
		// update write and read access
		configItemCollection.replaceItemValue("type", DatevService.TYPE);
		// configItemCollection.replaceItemValue("txtName", NAME);
		configItemCollection.replaceItemValue("$writeAccess", "org.imixs.ACCESSLEVEL.MANAGERACCESS");
		configItemCollection.replaceItemValue("$readAccess", "org.imixs.ACCESSLEVEL.MANAGERACCESS");

		// configItemCollection.replaceItemValue("$writeAccess", "");
		// configItemCollection.replaceItemValue("$readAccess", "");

		configItemCollection = updateTimerDetails(configItemCollection, false);
		// save entity
		configItemCollection = workflowService.getEntityService().save(configItemCollection);

		return configItemCollection;
	}

	/**
	 * This Method starts the TimerService.
	 * 
	 * The Timer can be started based on a Calendar setting stored in the
	 * property txtConfiguration, or by interval based on the properties
	 * datStart, datStop, numIntervall.
	 * 
	 * 
	 * The method loads the configuration entity and evaluates the timer
	 * configuration. THe $UniqueID of the configuration entity is the id of the
	 * timer to be controlled.
	 * 
	 * $uniqueid - String - identifier for the Timer Service.
	 * 
	 * txtConfiguration - calendarBasedTimer configuration
	 * 
	 * datstart - Date Object
	 * 
	 * datstop - Date Object
	 * 
	 * numInterval - Integer Object (interval in seconds)
	 * 
	 * 
	 * The method throws an exception if the configuration entity contains
	 * invalid attributes or values.
	 * 
	 * After the timer was started the configuration is updated with the latest
	 * statusmessage
	 * 
	 * The method returns the current configuration
	 * 
	 * @throws AccessDeniedException
	 * @throws ParseException
	 */
	public ItemCollection start(ItemCollection configItemCollection) throws AccessDeniedException, ParseException {
		Timer timer = null;
		if (configItemCollection == null)
			return null;

		String id = configItemCollection.getItemValueString("$uniqueid");

		// try to cancel an existing timer for this workflowinstance
		while (this.findTimer(id) != null) {
			this.findTimer(id).cancel();
		}

		String sConfiguation = configItemCollection.getItemValueString("txtConfiguration");

		if (!sConfiguation.isEmpty()) {
			// New timer will be started on calendar confiugration
			timer = createTimerOnCalendar(configItemCollection);
		} else {
			// update the interval based on hour/minute configuration
			int hours = configItemCollection.getItemValueInteger("hours");
			int minutes = configItemCollection.getItemValueInteger("minutes");
			long interval = (hours * 60 + minutes) * 60 * 1000;
			configItemCollection.replaceItemValue("numInterval", new Long(interval));

			timer = createTimerOnInterval(configItemCollection);
		}

		// start and set statusmessage
		if (timer != null) {

			Calendar calNow = Calendar.getInstance();
			SimpleDateFormat dateFormatDE = new SimpleDateFormat("dd.MM.yy hh:mm:ss");
			String msg = "started at " + dateFormatDE.format(calNow.getTime()) + " by "
					+ ctx.getCallerPrincipal().getName();
			configItemCollection.replaceItemValue("statusmessage", msg);

			if (timer.isCalendarTimer()) {
				configItemCollection.replaceItemValue("Schedule", timer.getSchedule().toString());
			} else {
				configItemCollection.replaceItemValue("Schedule", "");

			}
			logger.info("[DatevSchedulerService] " + configItemCollection.getItemValueString("txtName") + " started: "
					+ id);
		}
		configItemCollection.replaceItemValue("errormessage", "");
		configItemCollection = saveConfiguration(configItemCollection);

		return configItemCollection;
	}

	/**
	 * Cancels a running timer instance. After cancel a timer the corresponding
	 * timerDescripton (ItemCollection) is no longer valid
	 * 
	 */
	public ItemCollection stop(ItemCollection config) throws Exception {
		String id = config.getItemValueString(EntityService.UNIQUEID);
		boolean found = false;
		while (this.findTimer(id) != null) {
			this.findTimer(id).cancel();
			found = true;
		}
		if (found) {
			ItemCollection configuration = workflowService.getWorkItem(id);
			Calendar calNow = Calendar.getInstance();
			SimpleDateFormat dateFormatDE = new SimpleDateFormat("dd.MM.yy hh:mm:ss");

			String msg = "stopped at " + dateFormatDE.format(calNow.getTime()) + " by "
					+ ctx.getCallerPrincipal().getName();
			configuration.replaceItemValue("statusmessage", msg);

			logger.info("[DatevSchedulerService] " + configuration.getItemValueString("txtName") + " stopped: " + id);

			configuration.removeItem("nextTimeout");
			configuration.removeItem("timeRemaining");

			configuration = saveConfiguration(configuration);
			return configuration;
		}
		return config;
	}

	/**
	 * This method returns a timer for a corresponding id if such a timer object
	 * exists.
	 * 
	 * @param id
	 * @return Timer
	 * @throws Exception
	 */
	private Timer findTimer(String id) {
		for (Object obj : timerService.getTimers()) {
			Timer timer = (javax.ejb.Timer) obj;

			if (timer.getInfo() instanceof XMLItemCollection) {
				XMLItemCollection xmlItemCollection = (XMLItemCollection) timer.getInfo();
				ItemCollection adescription = XMLItemCollectionAdapter.getItemCollection(xmlItemCollection);
				if (id.equals(adescription.getItemValueString("$uniqueid"))) {
					return timer;
				}
			}
		}
		return null;
	}

	/**
	 * Update the timer details of a running timer service. The method updates
	 * the properties netxtTimeout and timeRemaining and store them into the
	 * timer configuration.
	 * 
	 * @param configuration
	 * @param reload
	 *            - if true the confiugration will be reloaded from the database
	 */
	public ItemCollection updateTimerDetails(ItemCollection configuration, boolean reload) {
		if (configuration == null)
			return configuration;
		String id = configuration.getItemValueString("$uniqueid");

		// reload from database...
		if (reload) {
			configuration = workflowService.getEntityService().load(id);
		}

		Timer timer;
		try {
			timer = this.findTimer(id);

			if (timer != null) {
				// load current timer details
				configuration.replaceItemValue("nextTimeout", timer.getNextTimeout());
				configuration.replaceItemValue("timeRemaining", timer.getTimeRemaining());
			} else {
				configuration.removeItem("nextTimeout");
				configuration.removeItem("timeRemaining");

			}
		} catch (Exception e) {
			logger.warning("[DatevSchedulerService] unable to updateTimerDetails: " + e.getMessage());
			configuration.removeItem("nextTimeout");
			configuration.removeItem("timeRemaining");

		}
		return configuration;
	}

	/**
	 * This is the method which processes the timeout event depending on the
	 * running timer settings.
	 * 
	 * The method imports datev entities form a file.
	 * 
	 * @param timer
	 */
	@Timeout
	public void processImport(javax.ejb.Timer timer) {
		String sTimerID = null;
		

		// Startzeit ermitteln
		long lProfiler = System.currentTimeMillis();

		logger.info("[DatevSchedulerService] processing import....");

		// load configuration...

		XMLItemCollection xmlItemCollection = (XMLItemCollection) timer.getInfo();
		ItemCollection configuration = XMLItemCollectionAdapter.getItemCollection(xmlItemCollection);
		sTimerID = configuration.getItemValueString(EntityService.UNIQUEID);
		configuration = workflowService.getEntityService().load(sTimerID);
		try {
			configuration=datevService.importEntities(configuration,0,-1);

			
		} catch (DatevException e) {
			// in case of an exception we did not cancel the Timer service
			if (logger.isLoggable(Level.FINE)) {
				e.printStackTrace();
			}
			logger.severe("[DatevSchedulerService] importOrders failed for: " + sTimerID + " Error=" + e.getMessage());
			configuration.replaceItemValue("errormessage", e.getMessage());

		}

		// Save statistic in configuration
		try {
			configuration = this.saveConfiguration(configuration);
		} catch (Exception e2) {
			e2.printStackTrace();

		}

		logger.info("[DatevSchedulerService] import finished successfull: " + ((System.currentTimeMillis()) - lProfiler)
				+ " ms");

		/*
		 * Check if Timer should be canceld now?
		 */
		if (endDate != null) {
			Calendar calNow = Calendar.getInstance();

			if (calNow.getTime().after(endDate)) {
				timer.cancel();
				System.out.println("[DatevSchedulerService] Timeout sevice stopped: " + sTimerID);
			}
		}
	}

	
	

	
	/**
	 * Create an interval timer whose first expiration occurs at a given point
	 * in time and whose subsequent expirations occur after a specified
	 * interval.
	 **/
	Timer createTimerOnInterval(ItemCollection configItemCollection) {

		// Create an interval timer
		Date startDate = configItemCollection.getItemValueDate("datstart");
		Date endDate = configItemCollection.getItemValueDate("datstop");
		long interval = configItemCollection.getItemValueInteger("numInterval");
		// if endDate is in the past we do not start the timer!
		Calendar calNow = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();

		if (endDate != null)
			calEnd.setTime(endDate);
		if (calNow.after(calEnd)) {
			logger.warning("[DatevSchedulerService] " + configItemCollection.getItemValueString("txtName")
					+ " stop-date is in the past");

			endDate = startDate;
		}

		XMLItemCollection xmlConfigItem = null;
		try {
			xmlConfigItem = XMLItemCollectionAdapter.putItemCollection(configItemCollection);
		} catch (Exception e) {
			logger.severe("Unable to serialize confitItemCollection into a XML object");
			e.printStackTrace();
			return null;
		}

		Timer timer = timerService.createTimer(startDate, interval, xmlConfigItem);

		return timer;

	}

	/**
	 * Create a calendar-based timer based on a input schedule expression. The
	 * expression will be parsed by this method.
	 * 
	 * Example: <code>
	 *   second=0
	 *   minute=0
	 *   hour=*
	 *   dayOfWeek=
	 *   dayOfMonth=25–Last,1–5
	 *   month=
	 *   year=*
	 * </code>
	 * 
	 * @param sConfiguation
	 * @return
	 * @throws ParseException
	 */
	Timer createTimerOnCalendar(ItemCollection configItemCollection) throws ParseException {

		TimerConfig timerConfig = new TimerConfig();

		XMLItemCollection xmlConfigItem = null;
		try {
			xmlConfigItem = XMLItemCollectionAdapter.putItemCollection(configItemCollection);
		} catch (Exception e) {
			logger.severe("Unable to serialize confitItemCollection into a XML object");
			e.printStackTrace();
			return null;
		}

		timerConfig.setInfo(xmlConfigItem);
		ScheduleExpression scheduerExpression = new ScheduleExpression();

		@SuppressWarnings("unchecked")
		List<String> calendarConfiguation = configItemCollection.getItemValue("txtConfiguration");
		// try to parse the configuration list....
		for (String confgEntry : calendarConfiguation) {

			if (confgEntry.startsWith("second=")) {
				scheduerExpression.second(confgEntry.substring(confgEntry.indexOf('=') + 1));
			}
			if (confgEntry.startsWith("minute=")) {
				scheduerExpression.minute(confgEntry.substring(confgEntry.indexOf('=') + 1));
			}
			if (confgEntry.startsWith("hour=")) {
				scheduerExpression.hour(confgEntry.substring(confgEntry.indexOf('=') + 1));
			}
			if (confgEntry.startsWith("dayOfWeek=")) {
				scheduerExpression.dayOfWeek(confgEntry.substring(confgEntry.indexOf('=') + 1));
			}
			if (confgEntry.startsWith("dayOfMonth=")) {
				scheduerExpression.dayOfMonth(confgEntry.substring(confgEntry.indexOf('=') + 1));
			}
			if (confgEntry.startsWith("month=")) {
				scheduerExpression.month(confgEntry.substring(confgEntry.indexOf('=') + 1));
			}
			if (confgEntry.startsWith("year=")) {
				scheduerExpression.year(confgEntry.substring(confgEntry.indexOf('=') + 1));
			}
			if (confgEntry.startsWith("timezone=")) {
				scheduerExpression.timezone(confgEntry.substring(confgEntry.indexOf('=') + 1));
			}

			/* Start date */
			if (confgEntry.startsWith("start=")) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date convertedDate = dateFormat.parse(confgEntry.substring(confgEntry.indexOf('=') + 1));
				scheduerExpression.start(convertedDate);
			}

			/* End date */
			if (confgEntry.startsWith("end=")) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date convertedDate = dateFormat.parse(confgEntry.substring(confgEntry.indexOf('=') + 1));
				scheduerExpression.end(convertedDate);
			}

		}

		Timer timer = timerService.createCalendarTimer(scheduerExpression, timerConfig);

		return timer;

	}

}
