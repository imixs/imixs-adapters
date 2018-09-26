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

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.imixs.workflow.engine.scheduler.SchedulerController;
import org.imixs.workflow.engine.scheduler.SchedulerService;
import org.imixs.workflow.sepa.services.SepaScheduler;

/**
 * The SepaController is used to configure the SepaService. This service
 * is used to generate sepa export workitems.
 * <p>
 * The Controller creates a configuration entity "type=configuration;
 * txtname=sepa".
 * <p>
 * The following config items are defined:
 * 
 * <pre>
 * _model_version = model version for the SEPA export
 * _initial_task = inital task ID
 * _dbtr_IBAN = default IBAN for the SEPA export file
 * _dbtr_BIC = default BIC for the SEPA export file
 * </pre>
 * 
 * 
 * @author rsoika
 * @version 1.0
 */
 
@Named
@RequestScoped
public class SepaController extends SchedulerController {

	public static final String SEPA_CONFIGURATION = "SEPA_CONFIGURATION";

	// is empty or match iban/bic pattern
	public static final String IBAN_PATTERN = "^$|(^[A-Z]{2}(?:[ ]?[0-9]){18,20}$)";
	public static final String BIC_PATTERN = "^$|(^([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?))";

	private static final long serialVersionUID = 1L;

	@EJB
	SchedulerService schedulerService;

	private static Logger logger = Logger.getLogger(SepaController.class.getName());

	
	@Override
	public String getName() {
		return SEPA_CONFIGURATION;
	}
	
	

	/**
	 * Returns the sepa scheduler class name
	 */
	@Override
	public String getSchedulerClass() {
		return SepaScheduler.class.getName();
	}

	

	/** Kreditor **/
	
	@Valid
	@Pattern(regexp = SepaController.BIC_PATTERN)
	public String getCdtrBic() {
		return getConfiguration().getItemValueString("_cdtr_bic");
	}

	public void setCdtrBic(String bic) {
		logger.finest("......validate _cdtr_bic...");
		getConfiguration().setItemValue("_cdtr_bic", bic);
	}

	@Valid
	@Pattern(regexp = SepaController.IBAN_PATTERN)
	public String getCdtrIban() {
		logger.finest("......validate _cdtr_iban...");
		return getConfiguration().getItemValueString("_cdtr_iban");
	}

	public void setCdtrIban(String iban) {
		getConfiguration().setItemValue("_cdtr_iban", iban);
	}
	
	
	
	/**  Debtor **/
	
	@Valid
	@Pattern(regexp = SepaController.BIC_PATTERN)
	public String getDbtrBic() {
		return getConfiguration().getItemValueString("_dbtr_bic");
	}

	public void setDbtrBic(String bic) {
		logger.finest("......validate _dbtr_bic...");
		getConfiguration().setItemValue("_dbtr_bic", bic);
	}

	@Valid
	@Pattern(regexp = SepaController.IBAN_PATTERN)
	public String getDbtrIban() {
		logger.finest("......validate _dbtr_iban...");
		return getConfiguration().getItemValueString("_dbtr_iban");
	}

	public void setDbtrIban(String iban) {
		getConfiguration().setItemValue("_dbtr_iban", iban);
	}

	
	
	
	
	
	
}
