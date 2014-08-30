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

package org.imixs.workflow.magento;

import java.util.List;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;

/**
 * This interface defines methods to access the Magento Web Serivce API. Magento
 * provides two different sevice implementation. A Rest Service based on the
 * OAuth Authentification and a SOAP Service.
 * 
 * The Interface is used to hide the implementation details from the Imixs
 * Magento Adapter
 * 
 * @author rsoika
 * 
 */
public interface MagentoClient {
	
	/**
	 * returns a List of itemCollection for  magento product entries
	 * 
	 * @return
	 * @throws PluginException
	 */
	public List<ItemCollection> getProducts() throws PluginException;

	/**
	 * returns a single itemCollection for a magento product entry
	 * 
	 * @param item_id
	 * @return
	 * @throws PluginException
	 */
	public ItemCollection getProductBySKU(String sku);

	public ItemCollection getCustomerById(String id);

	public ItemCollection getOrderById(String id);

	public List<ItemCollection> getStockitems() throws PluginException;

	public List<ItemCollection> getOrders(String status, int page, int limit);

}
