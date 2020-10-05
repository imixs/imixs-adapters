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
package org.imixs.workflow.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.scheduler.SchedulerController;
import org.imixs.workflow.engine.scheduler.SchedulerService;

/**
 * The DocumentImportController is used to configure the import scheduler.
 * <p>
 * The configuration is based on the default scheduler
 * <p>
 * The configuration object holds a item named 'sources'. This item contains a
 * list of options (Map). These source definitions are consumed by
 * ImportObserver implementations. This is an generic extension for custom
 * implementations. There are different import adapters already defined like
 * EmailImportAdapter or the FTPImportAdatper to process a single import source.
 * <p>
 * Sources are organized in the item 'sources' in a ordered list of Map objects.
 * Internally the map objects are converted into ItemCollection to simplify the
 * handling in JSF forms.
 * <p>
 * Each source object has a internal index number (pos) to identify the source
 * unique. The sources can be reordered by calling the method up() and down().
 * 
 * @see FTPImportAdapter, EmailImportAdapter
 * @author rsoika
 * @version 1.0
 */

@Named
@ViewScoped
public class DocumentImportController extends SchedulerController {

    private static final long serialVersionUID = 1L;

    protected List<ItemCollection> sources = null;
    protected ItemCollection source = null;// selected source

    @EJB
    SchedulerService schedulerService;

    @EJB
    DocumentImportService documentImportService;

    private static Logger logger = Logger.getLogger(DocumentImportController.class.getName());

    /**
     * This method initializes the default sync date
     * 
     */
    @PostConstruct
    @Override
    public void init() {
        super.init();
        // load sources from configuration
        sources = documentImportService.loadSourcesFromConfiguration(getConfiguration());
    }

    @Override
    public String getName() {
        return DocumentImportScheduler.DOCUMENT_IMPORTER_NAME;
    }

    /**
     * Returns the sepa scheduler class name
     */
    @Override
    public String getSchedulerClass() {
        return DocumentImportScheduler.class.getName();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void saveConfiguration() {
        ItemCollection config = getConfiguration();
        List<Map> mapItemList = new ArrayList<Map>();
        // convert the option ItemCollection elements into a List of Map
        if (sources != null) {
            logger.fine("Convert option items into Map...");
            // iterate over all items..
            for (ItemCollection orderItem : sources) {
                mapItemList.add(orderItem.getAllItems());
            }
            config.replaceItemValue(DocumentImportService.ITEM_SOURCES, mapItemList);
        }

        super.saveConfiguration();
    }

    /**
     * Returns a list of all source objects
     * 
     * @return
     */
    public List<ItemCollection> getSources() {
        return sources;
    }

    public void setSources(List<ItemCollection> sources) {
        this.sources = sources;
    }

    /**
     * Returns the currently selected source
     * 
     * @return
     */
    public ItemCollection getSource() {
        return source;
    }

    public void setSource(ItemCollection source) {
        this.source = source;
    }

    /**
     * Adds a new filter option. The type of the option (FTP|IMAP) must be
     * specified.
     */
    public void addSource() {
        if (sources == null) {
            sources = new ArrayList<ItemCollection>();
        }
        source = new ItemCollection();
        source.setItemValue("index", sources.size());
        // itemCol.replaceItemValue("type", type);
        sources.add(source);
    }

    /**
     * Selects the current source by index
     * 
     * @param optionName
     */
    public void selectSource(int index) {
        if (sources != null) {
            source = sources.get(index);
        }
    }

    /**
     * Removes an option by index
     * 
     * @param optionName
     */
    public void removeSource(int index) {
        if (sources != null) {
            sources.remove(index);
        }
    }

    /**
     * Moves the current source object one position down in the list of sources
     * 
     * @param optionName
     */
    public void moveSourceDown(int index) {
        if (sources != null && index < sources.size() - 1) {
            source = sources.get(index);
            sources.remove(index);
            sources.add(index + 1, source);

            // re-index sources....
            int iPos = 0;
            for (ItemCollection item : sources) {
                item.setItemValue("index", iPos);
                iPos++;
            }
        }
    }

    /**
     * Moves the current source object one position up in the list of sources
     * 
     * @param optionName
     */
    public void moveSourceUp(int index) {
        if (sources != null && index > 0) {
            source = sources.get(index);
            sources.remove(index);
            sources.add(index - 1, source);

            // re-index sources....
            int iPos = 0;
            for (ItemCollection item : sources) {
                item.setItemValue("index", iPos);
                iPos++;
            }
        }
    }

}
