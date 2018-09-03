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
package org.imixs.workflow.sepa.controller;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.imixs.workflow.sepa.services.SepaSchedulerService;

/**
 * The SepaConfigController is used to configure the SepaService. This service
 * is used to generate sepa export workitems.
 * <p>
 * The SepaConfigController creates a configuration entity "type=configuration;
 * txtname=sepa".
 * <p>
 * The following config items are defined:
 * 
 * <pre>
 * _bic - bic validated
 * _iban - iban validated
 * </pre>
 * 
 * 
 * @author rsoika
 * @version 1.0
 */
 
@Named
@RequestScoped
public class SepaConfigController extends org.imixs.marty.config.ConfigController {

	public static final String SEPA_CONFIGURATION = "SEPA_CONFIGURATION";

	public static final String IBAN_PATTERN = "^[A-Z]{2}(?:[ ]?[0-9]){18,20}$";
	public static final String BIC_PATTERN = "^([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?)";

	private static final long serialVersionUID = 1L;

	@EJB
	SepaSchedulerService sepaSchedulerService;

	private static Logger logger = Logger.getLogger(SepaConfigController.class.getName());


	
	
	public SepaConfigController() {
		super();
		this.setName(SEPA_CONFIGURATION);
	}

	@Valid
	@Pattern(regexp = BIC_PATTERN)
	public String getBic() {
		return this.getWorkitem().getItemValueString("_bic");
	}

	public void setBic(String bic) {
		this.getWorkitem().setItemValue("_bic", bic);
	}

	@Valid
	@Pattern(regexp = IBAN_PATTERN)
	public String getIban() {
		return this.getWorkitem().getItemValueString("_iban");
	}

	public void setIban(String iban) {
		this.getWorkitem().setItemValue("_iban", iban);
	}
	
	
	
	public void refresh() throws Exception {
		sepaSchedulerService.updateTimerDetails(getWorkitem());
	}
	/**
	 * starts the timer service
	 * 
	 * @return
	 * @throws Exception
	 */
	public void startScheduler(ActionEvent event) {
		getWorkitem().replaceItemValue("_enabled", true);
		try {
			sepaSchedulerService.start(getWorkitem());
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

	public void stopScheduler(ActionEvent event) throws Exception {
		getWorkitem().replaceItemValue("_enabled", false);
		sepaSchedulerService.saveConfiguration(getWorkitem());
		sepaSchedulerService.stop(getWorkitem());
	}

	public void restartScheduler(ActionEvent event) throws Exception {
		logger.fine("[WorkflowSchedulerCOntroller] restart timer service");
		stopScheduler(event);
		startScheduler(event);
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

}
