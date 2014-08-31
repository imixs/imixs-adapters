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
	 * Connection the Client with the Magento Service. The configuration
	 * ItemCollection contains optional properties to establish the connection.
	 * 
	 * @param config
	 */
	public void connect(ItemCollection config) throws MagentoException;

	/**
	 * Disconnects the Client with the Magento Service.
	 * 
	 * 
	 */
	public void disconnect();

	/**
	 * returns a List of itemCollection for magento product entries
	 * 
	 * @return
	 * @throws PluginException
	 */
	public List<ItemCollection> getProducts() throws MagentoException;

	public ItemCollection getCustomerById(String id) throws MagentoException;

	public ItemCollection getOrderById(String id) throws MagentoException;

	public List<ItemCollection> getOrders(String status, int page, int limit)
			throws MagentoException;

	/**
	 * returns a single itemCollection for a magento product entry
	 * 
	 * @param item_id
	 * @return
	 * @throws PluginException
	 */
	public ItemCollection getProductBySKU(String sku) throws MagentoException;



	/**
	 * adds a comment to an order 
	 * @param item_id
	 * @return
	 * @throws PluginException
	 */
	public void getAddOrderComment(String orderIncrementId,String status,String comment) throws MagentoException;




}
