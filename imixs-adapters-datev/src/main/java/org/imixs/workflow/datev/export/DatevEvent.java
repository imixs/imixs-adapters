/*  
 *  Imixs-Workflow 
 *  
 *  Copyright (C) 2001-2020 Imixs Software Solutions GmbH,  
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
 *      https://www.imixs.org
 *      https://github.com/imixs/imixs-workflow
 *  
 *  Contributors:  
 *      Imixs Software Solutions GmbH - Project Management
 *      Ralph Soika - Software Developer
 */

package org.imixs.workflow.datev.export;

import org.imixs.workflow.FileData;
import org.imixs.workflow.ItemCollection;

/**
 * The DatevEvent provides a CDI observer pattern. The DatevEvent is fired
 * by the DatevExportService. An event Observer can react on event to adapt the
 * export behaviour.
 * 
 * @author Ralph Soika
 * @version 1.0
 * @see org.imixs.workflow.datev.DatevService
 */
public class DatevEvent {

    public static final int ON_EXPORT_FILE = 1;

    private int eventType;
    private ItemCollection workitem;
    private FileData fileData = null;

    /**
     * Creates a datev event based on a existing workitem
     * 
     * 
     * @param workitem  - optional profile ItemCollection
     * @param eventType
     */
    public DatevEvent(ItemCollection workitem, int eventType) {
        this.eventType = eventType;
        this.workitem = workitem;
    }

    public int getEventType() {
        return eventType;
    }

    public ItemCollection getWorkitem() {
        return workitem;
    }

    public void setWorkitem(ItemCollection filter) {
        this.workitem = filter;
    }

    public FileData getFileData() {
        return fileData;
    }

    public void setFileData(FileData fileData) {
        this.fileData = fileData;
    }

}
