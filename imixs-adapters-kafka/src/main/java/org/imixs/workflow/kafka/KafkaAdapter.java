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

package org.imixs.workflow.kafka;

import java.util.logging.Logger;

import jakarta.ejb.EJB;

import org.imixs.workflow.Adapter;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.AdapterException;

/**
 * This adapter class sends a kafka message 
 * 
 * @author Ralph Soika
 * @version 1.0 
 *
 */
public class KafkaAdapter implements Adapter {
	private static Logger logger = Logger.getLogger(KafkaAdapter.class.getName());

	// inject services...
	@EJB
	ProducerService producerService;

	/**
	 * Send kafka message based on the current workitem
	 */
	@Override
	public ItemCollection execute(ItemCollection workitem, ItemCollection event) throws AdapterException {
		logger.info("execute kafka adapter - send message...");
		producerService.sendWorkitem(workitem);
		return workitem;
	}

}
