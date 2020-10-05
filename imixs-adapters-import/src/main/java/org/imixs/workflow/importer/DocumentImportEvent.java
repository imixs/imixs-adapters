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

package org.imixs.workflow.importer;

import java.util.ArrayList;
import java.util.List;

import org.imixs.workflow.ItemCollection;

/**
 * The DocumentImportEvent provides a CDI observer pattern. The
 * DocumentImportEvent is fired by the DocumentImportScheduler EJB. An event
 * Observer can react on this event to process imports form a external data
 * source.
 * <p>
 * The DocumentImportEvent contains the property 'source' with a description of
 * the data source. The source object provides the following items:
 * <ul>
 * <li>type - type of the data source (e.g. FTP, IMAP, ...)
 * <li>server - server name of the data source
 * <li>port - optional server port
 * <li>userId - optional userId to access the server
 * <li>password - optional password to access the server
 * <li>workflowGroup - the workflow group a new document should be assigned to.
 * </ul>
 * <p>
 * The property 'result' indicates if the source was processed completely or if
 * the source can be processed by the next processor. The result is evaluated by
 * the DocumentImportScheduler service.
 * <p>
 * If the data source was completely processed by the adapter the adapter should
 * return the processing result PROCESSING_COMPLETED = 1 . Otherwise
 * PROCESSING_OUTSTANDING = 0.
 * <p>
 * An adapter can signal an error by setting the processing result to
 * PROCESSING_ERROR = 2 . The property 'message' can be filled with a error
 * message text.
 * 
 * 
 * @author Ralph Soika
 * @version 1.0
 * @see org.imixs.workflow.engine.SetupService
 */
public class DocumentImportEvent {

    public static final int PROCESSING_OUTSTANDING = 0;
    public static final int PROCESSING_COMPLETED = 1;
    public static final int PROCESSING_ERROR = 2;

    private int result;
    private List<String> messages;
    private ItemCollection source;

    public DocumentImportEvent(ItemCollection source) {
        super();
        this.source = source;
        this.messages = new ArrayList<String>();
    }

    public ItemCollection getSource() {
        return source;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void appendMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}
