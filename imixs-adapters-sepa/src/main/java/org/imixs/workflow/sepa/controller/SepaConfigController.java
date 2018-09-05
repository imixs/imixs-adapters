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

import org.imixs.workflow.engine.scheduler.SchedulerController;
import org.imixs.workflow.engine.scheduler.SchedulerService;
import org.imixs.workflow.sepa.services.SepaScheduler;

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
public class SepaConfigController extends SchedulerController {

	public static final String SEPA_CONFIGURATION = "SEPA_CONFIGURATION";

	public static final String IBAN_PATTERN = "^[A-Z]{2}(?:[ ]?[0-9]){18,20}$";
	public static final String BIC_PATTERN = "^([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?)";

	private static final long serialVersionUID = 1L;

	@EJB
	SchedulerService schedulerService;

	private static Logger logger = Logger.getLogger(SepaConfigController.class.getName());

	
	@Override
	public String getName() {
		return SEPA_CONFIGURATION;
	}

	@Valid
	@Pattern(regexp = BIC_PATTERN)
	public String getBic() {
		return this.getConfiguration().getItemValueString("_bic");
	}

	public void setBic(String bic) {
		logger.finest("......validate bic...");
		this.getConfiguration().setItemValue("_bic", bic);
	}

	@Valid
	@Pattern(regexp = IBAN_PATTERN)
	public String getIban() {
		logger.finest("......validate iban...");
		return this.getConfiguration().getItemValueString("_iban");
	}

	public void setIban(String iban) {
		this.getConfiguration().setItemValue("_iban", iban);
	}
	
	
	
	public void refresh() throws Exception {
		getSchedulerService().updateTimerDetails(getConfiguration());
	}
	
	
}
