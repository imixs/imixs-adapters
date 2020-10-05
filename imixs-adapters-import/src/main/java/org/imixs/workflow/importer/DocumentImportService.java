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
package org.imixs.workflow.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.engine.scheduler.Scheduler;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.util.XMLParser;

/**
 * The DocumentImportService provides definitions and methods to process a
 * import source.
 * 
 * @author rsoika
 *  
 */
@Stateless 
@LocalBean
public class DocumentImportService {

    public final static String ITEM_SOURCES = "sources";
    public final static String SOURCE_ITEM_SERVER = "server";
    public final static String SOURCE_ITEM_PORT = "port";
    public final static String SOURCE_ITEM_USER = "user";
    public final static String SOURCE_ITEM_PASSWORD = "password";
    public final static String SOURCE_ITEM_TASK = "task";
    public final static String SOURCE_ITEM_EVENT = "event";
    public final static String SOURCE_ITEM_WORKFLOWGROUP = "workflowgroup";
    public final static String SOURCE_ITEM_MODELVERSION = "modelversion";
    public final static String SOURCE_ITEM_SELECTOR = "selector";
    public final static String SOURCE_ITEM_OPTIONS = "options";

    private static Logger logger = Logger.getLogger(DocumentImportService.class.getName());

    @EJB
    WorkflowService workflowService;

    /**
     * This method returns a list of ItemCollection objects representing the sources
     * defined in a Importer configuration.
     *
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<ItemCollection> loadSourcesFromConfiguration(ItemCollection configuration) {
        // load sources from configuration
        ArrayList<ItemCollection> sources = new ArrayList<ItemCollection>();
        List<Object> mapItems = configuration.getItemValue(ITEM_SOURCES);
        for (Object mapOderItem : mapItems) {
            if (mapOderItem instanceof Map) {
                ItemCollection itemCol = new ItemCollection((Map) mapOderItem);
                sources.add(itemCol);
            }
        }
        return sources;
    }

    /**
     * Creates a new log entry and stores the message into an optional event
     * 
     * @param message
     * @param source
     */
    public void logMessage(String message, DocumentImportEvent event) {
        if (event != null) {
            event.appendMessage(message);
        }
        logger.info(message);

    }

    /**
     * Creates a new log entry and stores the message into the item
     * '_scheduler_logmessage' of an optional scheduler document
     * 
     * @param message
     * @param source
     */
    public void logMessage(String message, ItemCollection config) {
        if (config != null) {
            config.appendItemValueUnique(Scheduler.ITEM_LOGMESSAGE, message);
        }
        logger.info(message);

    }

    /**
     * This helper method evaluates the options and applies "items" tags to the new
     * workitem if defined.
     * 
     * <pre>
     * {@code
    <items>
        <process.ref>Inbox</process.ref>
        <space.ref>Development</space.ref>
    </items>}
     * </pre>
     * 
     * *
     * 
     * @throws PluginException
     */
    public void evalOptions(ItemCollection source, ItemCollection documentContext) throws PluginException {
        String options = source.getItemValueString(SOURCE_ITEM_OPTIONS);
        ItemCollection itemsData = XMLParser.parseTag(options, "items");
        documentContext.replaceAllItems(itemsData.getAllItems());

    }

}
