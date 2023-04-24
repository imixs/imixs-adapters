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

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

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
@ViewScoped
public class SepaController extends SchedulerController {

    private static final long serialVersionUID = 1L;

    protected List<ItemCollection> dbtrList = null;
    protected List<ItemCollection> cdtrList = null;

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
        loadDbtrCdtrListFromConfiguration();
    }

    @Override
    public String getName() {
        return SepaWorkflowService.SEPA_CONFIGURATION;
    }

    /**
     * Returns the sepa scheduler class name
     */
    @Override
    public String getSchedulerClass() {
        return SepaScheduler.class.getName();
    }

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
     * This method loads the dbtr.config and cdtr.config into a list of ItemCollection objects representing the sources
     * defined in a Importer configuration.
     *
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void loadDbtrCdtrListFromConfiguration() {
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
        
        
        // load cdtr list from configuration
        cdtrList = new ArrayList<ItemCollection>();
        mapItems = this.getConfiguration().getItemValue(SepaWorkflowService.ITEM_CDTR_CONFIG);
        for (Object mapOderItem : mapItems) {
            if (mapOderItem instanceof Map) {
                ItemCollection itemCol = new ItemCollection((Map) mapOderItem);
                cdtrList.add(itemCol);
            }
        }

    }

    @SuppressWarnings("rawtypes")
    @Override
    public void saveConfiguration() {
        ItemCollection config = getConfiguration();
        
        // convert the option ItemCollection elements into a List of Map
        if (dbtrList != null) {
            List<Map> mapItemList = new ArrayList<Map>();
            logger.fine("Convert option items into Map...");
            // iterate over all items..
            for (ItemCollection dbtrItem : dbtrList) {
                mapItemList.add(dbtrItem.getAllItems());
            }
            config.replaceItemValue(SepaWorkflowService.ITEM_DBTR_CONFIG, mapItemList);
        }
        // convert the option ItemCollection elements into a List of Map
        if (cdtrList != null) {
            List<Map> mapItemList = new ArrayList<Map>();
            logger.fine("Convert option items into Map...");
            // iterate over all items..
            for (ItemCollection cdtrItem : cdtrList) {
                mapItemList.add(cdtrItem.getAllItems());
            }
            config.replaceItemValue(SepaWorkflowService.ITEM_CDTR_CONFIG, mapItemList);
        }

        super.saveConfiguration();
    }

    public List<ItemCollection> getDbtrList() {
        return dbtrList;
    }

    public List<ItemCollection> getCdtrList() {
        return cdtrList;
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
    public void addCdtr() {
        if (cdtrList == null) {
            cdtrList = new ArrayList<ItemCollection>();
        }
        ItemCollection source = new ItemCollection();
        cdtrList.add(source);
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
    
    public void removeCdtr(String name) {
        if (name != null && cdtrList != null) {
            for (ItemCollection cdtr : cdtrList) {
                if (name.equals(cdtr.getItemValueString("name"))) {
                    cdtrList.remove(cdtr);
                    break;
                }
            }
        }
    }

}
