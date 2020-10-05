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

package org.imixs.workflow.importer.adapter;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import org.imixs.workflow.engine.ModelService;
import org.imixs.workflow.engine.WorkflowService;
import org.imixs.workflow.importer.DocumentImportEvent;
import org.imixs.workflow.importer.DocumentImportService;

/**
 * The EmailImportAdapter scanns a IMAP account
 * 
 * @author rsoika
 *
 */
@Stateless
public class EmailImportAdapter {

    private static Logger logger = Logger.getLogger(EmailImportAdapter.class.getName());

    @EJB
    WorkflowService workflowService;
    
    @EJB
    ModelService modelService;

    @EJB
    DocumentImportService documentImportService;
    
    /**
     * This method reacts on a CDI ImportEvent and reads documents form a ftp server.
     * 
     * 
     */
    public void onEvent(@Observes DocumentImportEvent event) {
     
        
        // TODO https://sandstorm.de/de/blog/post/emails-abfragen-per-imap-in-java.html
        
    }

  
    
}
