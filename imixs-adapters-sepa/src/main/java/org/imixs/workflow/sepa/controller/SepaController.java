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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.engine.scheduler.SchedulerController;
import org.imixs.workflow.engine.scheduler.SchedulerService;
import org.imixs.workflow.sepa.services.SepaScheduler;
import org.imixs.workflow.sepa.services.SepaWorkflowService;

/**
 * The SepaController is used to configure the SepaService. This service is used
 * to generate sepa export workitems.
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
//@RequestScoped
@ViewScoped
public class SepaController extends SchedulerController {

	public static final String SEPA_CONFIGURATION = "SEPA_CONFIGURATION";

	// is empty or match iban/bic pattern
	public static final String IBAN_PATTERN = "^$|(^[A-Z]{2}(?:[ ]?[A-Z0-9]){13,32}$)";
	public static final String BIC_PATTERN = "^$|(^([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?))";

	private static final long serialVersionUID = 1L;

	
	protected List<ItemCollection> dbtrList = null;

	@EJB
	SchedulerService schedulerService;

	@EJB
	DocumentService documentService;

	private static Logger logger = Logger.getLogger(SepaController.class.getName());

	/**
	 * This method loads the dbtr list from the configuration document
	 * 
	 */
	@PostConstruct
	@Override
	public void init() {
		super.init();
		// load sepa dbtr list from configuration
		loadDbtrListFromConfiguration();
	}

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

//	@Valid
//	@Pattern(regexp = SepaController.BIC_PATTERN)
//	public String getCdtrBic() {
//		return getConfiguration().getItemValueString("cdtr.bic");
//	}
//
//	public void setCdtrBic(String bic) {
//		logger.finest("......validate cdtr.bic...");
//		getConfiguration().setItemValue("cdtr.bic", bic);
//	}
//
//	@Valid
//	@Pattern(regexp = SepaController.IBAN_PATTERN)
//	public String getCdtrIban() {
//		logger.finest("......validate _cdtr_iban...");
//		return getConfiguration().getItemValueString("cdtr.iban");
//	}
//
//	public void setCdtrIban(String iban) {
//		getConfiguration().setItemValue("cdtr.iban", iban);
//	}

	/** Debtor **/
//
//	@Valid
//	@Pattern(regexp = SepaController.BIC_PATTERN)
//	public String getDbtrBic() {
//		return getConfiguration().getItemValueString("dbtr.bic");
//	}
//
//	public void setDbtrBic(String bic) {
//		logger.finest("......validate _dbtr_bic...");
//		getConfiguration().setItemValue("dbtr.bic", bic);
//	}
//
//	@Valid
//	@Pattern(regexp = SepaController.IBAN_PATTERN)
//	public String getDbtrIban() {
//		logger.finest("......validate _dbtr_iban...");
//		return getConfiguration().getItemValueString("dbtr.iban");
//	}
//
//	public void setDbtrIban(String iban) {
//		getConfiguration().setItemValue("dbtr.iban", iban);
//	}

	/**
	 * This method computes the sum for a given item in a list of workitems. The
	 * result is rounded to 2 digits.
	 * 
	 * @param refids - list of workitem uniqueIds
	 * @param item   - name of the item to summarize
	 * @return sum rounded to 2 digits
	 */
	public double calculateSum(List<String> refids, String item) {
		double result = 0;
		for (String id : refids) {
			ItemCollection doc = documentService.load(id);
			if (doc != null) {
				result = result + doc.getItemValueDouble(item);
			} else {
				logger.warning("invalid read access to calculate sepa sum");
			}
		}
		// rond with 2 digits
		return Math.round(result * 100.0) / 100.0;

	}

	/**
	 * This method returns a list of ItemCollection objects representing the sources
	 * defined in a Importer configuration.
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadDbtrListFromConfiguration() {
		// load dbtr list from configuration
		dbtrList = new ArrayList<ItemCollection>();
		List<Object> mapItems = this.getConfiguration().getItemValue(SepaWorkflowService.ITEM_DBTR_CONFIG);
		for (Object mapOderItem : mapItems) {
			if (mapOderItem instanceof Map) {
				ItemCollection itemCol = new ItemCollection((Map) mapOderItem);
				dbtrList.add(itemCol);
			}
		}

		// Migration of deprecated config
		// In case we found no dbtrList but we have the old dbtr. items than we
		// automatically migrate the old data into the new list
		if (dbtrList.size() == 0) {

			if (this.getConfiguration().hasItem("_dbtr_name")) {
				logger.info("...migrate deprecated sepa configuration..");
				ItemCollection dbtr = new ItemCollection();
				dbtr.setItemValue("dbtr.name", this.getConfiguration().getItemValue("_dbtr_name"));
				dbtr.setItemValue("dbtr.iban", this.getConfiguration().getItemValue("_dbtr_iban"));
				dbtr.setItemValue("dbtr.bic", this.getConfiguration().getItemValue("_dbtr_bic"));
				dbtr.setItemValue("name", "SEPA");// default config
				dbtrList.add(dbtr);
				
				// remove deprecated fields
				this.getConfiguration().removeItem("_dbtr_name");
				this.getConfiguration().removeItem("_dbtr_iban");
				this.getConfiguration().removeItem("_dbtr_bic");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void saveConfiguration() {
		ItemCollection config = getConfiguration();
		List<Map> mapItemList = new ArrayList<Map>();
		// convert the option ItemCollection elements into a List of Map
		if (dbtrList != null) {
			logger.fine("Convert option items into Map...");
			// iterate over all items..
			for (ItemCollection dbtrItem : dbtrList) {
				mapItemList.add(dbtrItem.getAllItems());
			}
			config.replaceItemValue(SepaWorkflowService.ITEM_DBTR_CONFIG, mapItemList);
		}

		super.saveConfiguration();
	}

	public List<ItemCollection> getDbtrList() {
		return dbtrList;
	}

	/**
	 * Adds a new dbtr object.
	 */
	public void addDbtr() {
		if (dbtrList == null) {
			dbtrList = new ArrayList<ItemCollection>();
		}
		ItemCollection source = new ItemCollection();
		dbtrList.add(source);
	}

	/**
	 * Removes an dbtr item from the list
	 * 
	 * @param name - name of dbtr 
	 */
	public void removeDbtr(String name) {
		if (name != null && dbtrList != null) {
			for (ItemCollection dbtr : dbtrList) {
				if (name.equals(dbtr.getItemValueString("name"))) {
					dbtrList.remove(dbtr);
					break;
				}
			}
		}
	}

}
