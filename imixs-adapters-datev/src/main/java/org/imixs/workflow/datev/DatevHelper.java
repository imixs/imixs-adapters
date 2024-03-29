/*******************************************************************************
 *  Imixs Workflow 
 *  Copyright (C) 2001, 2011 Imixs Software Solutions GmbH,  
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
 *  Project: 
 *  	http://www.imixs.org
 *  	http://java.net/projects/imixs-workflow
 *  
 *  Contributors:  
 *  	Imixs Software Solutions GmbH - initial API and implementation
 *  	Ralph Soika - Software Developer
 *******************************************************************************/

package org.imixs.workflow.datev;

import java.util.logging.Logger;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.scheduler.Scheduler;

/**
 * Der DatevHelper prvides static helper methods
 * 
 * @author rsoika
 * @version 1.0
 */
public class DatevHelper {

    private static Logger logger = Logger.getLogger(DatevHelper.class.getName());

    /**
     * Creates a new log entry stored in the item _scheduler_log. The log can be
     * writen optional to the configuraiton and the workitem
     * 
     * @param message
     * @param configuration
     */
    public static void logMessage(String message, ItemCollection configuration, ItemCollection workitem) {
        if (configuration != null) {
            configuration.appendItemValue(Scheduler.ITEM_LOGMESSAGE, message);
        }
        if (workitem != null) {
            workitem.appendItemValue(Scheduler.ITEM_LOGMESSAGE, message);
        }
        logger.info(message);
    }

}
