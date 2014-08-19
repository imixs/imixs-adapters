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

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

import org.imixs.workflow.ItemCollection;

/**
 * The MagentoCache is a sigelton EJB providing an application wide cache
 * mechanism to cache products and customer data
 * 
 * 
 * @see http
 *      ://www.adam-bien.com/roller/abien/entry/singleton_the_perfect_cache_facade
 * @author rsoika
 */

@DeclareRoles({ "org.imixs.ACCESSLEVEL.NOACCESS",
		"org.imixs.ACCESSLEVEL.READERACCESS",
		"org.imixs.ACCESSLEVEL.AUTHORACCESS",
		"org.imixs.ACCESSLEVEL.EDITORACCESS",
		"org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@RolesAllowed({ "org.imixs.ACCESSLEVEL.NOACCESS",
		"org.imixs.ACCESSLEVEL.READERACCESS",
		"org.imixs.ACCESSLEVEL.AUTHORACCESS",
		"org.imixs.ACCESSLEVEL.EDITORACCESS",
		"org.imixs.ACCESSLEVEL.MANAGERACCESS" })
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MagentoCache {

	private ConcurrentHashMap<String, ItemCollection> customerCache = null;
	private ConcurrentHashMap<String, ItemCollection> productCache = null;

	@PostConstruct
	public void initialize() {
		clearCache();
	}

	
	public void clearCache() {
		this.customerCache = new ConcurrentHashMap<String, ItemCollection>();
		this.productCache = new ConcurrentHashMap<String, ItemCollection>();
		
	}
	
	/**
	 * Returns a product from the cache
	 * 
	 * @param productid
	 * @return
	 */
	public ItemCollection getProduct(String id) {

		return productCache.get(id);

	}
	
	/**
	 * Returns a customer from the cache
	 * 
	 * @param productid
	 * @return
	 */
	public ItemCollection getCustomer(String id) {

		return customerCache.get(id);

	}
	
	
	public void cacheProduct(String id,ItemCollection product) {
		productCache.put(id, product);
	}
	
	
	public void cacheCustomer(String id,ItemCollection customer) {
		customerCache.put(id, customer);
	}

}
