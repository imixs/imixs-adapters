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

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.jee.util.PropertyService;

/**
 * The MagentoCache is a sigelton EJB providing an application wide cache
 * mechanism to cache products and customer data.
 * 
 * The property 'refreshtime' defines the max time a data will be cached. There
 * for the bean stores the time of caching in the property $cached of an entiy.
 * 
 * The method flush() will remove all entities where the $cached timestamp is
 * older then the refresh time frame.
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
	private int refresh = 600; // default 10 minutes

	private static Logger logger = Logger.getLogger(MagentoCache.class
			.getName());

	@EJB
	PropertyService propertyService;

	@PostConstruct
	public void initialize() {
		clearCache();

		// read configuration
		String sRefresh = propertyService.getProperties().getProperty(
				"magento.cache.refresh", "600");
		try {
			refresh = Integer.parseInt(sRefresh);
		} catch (NumberFormatException e) {
			refresh = 600; // default 10 minutes
		}

	}

	/**
	 * get refresh time in seconds
	 * 
	 * @return
	 */
	public int getRefresh() {
		return refresh;
	}

	public void setRefresh(int refresh) {
		this.refresh = refresh;
	}

	public void clearCache() {
		logger.info("[MagentoCache] clear cache...");
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
		return getEntity(productCache, id);
	}

	/**
	 * Returns a customer from the cache
	 * 
	 * @param productid
	 * @return
	 */
	public ItemCollection getCustomer(String id) {

		return getEntity(customerCache, id);

	}

	/**
	 * puts an entity into the cache and adds the property $cached with the
	 * current timestamp
	 * 
	 * @param id
	 * @param product
	 */
	public void cacheProduct(String id, ItemCollection product) {
		// add the cached timestamp
		product.replaceItemValue("$cached", new Date());
		productCache.put(id, product);
	}

	/**
	 * puts an entity into the cache and adds the property $cached with the
	 * current timestamp
	 * 
	 * @param id
	 * @param customer
	 */
	public void cacheCustomer(String id, ItemCollection customer) {
		// add the cached timestamp
		customer.replaceItemValue("$cached", new Date());
		customerCache.put(id, customer);
	}

	/**
	 * This method removes all entities form the cache where the $cached
	 * timestamp is older then the refreshTime.
	 */
	public void flush() {
		logger.info("[MagentoCache] flush cache...");
		// check products....
		flushCache(productCache);

		// check customers....
		flushCache(customerCache);
	}

	/**
	 * gets an entity from a cache. The method compares the $cached timestamp
	 * with the refreshtime. If the entity is deprecated it will be removed.
	 * Then the method returns null.
	 * 
	 * @param key
	 */
	private ItemCollection getEntity(
			ConcurrentHashMap<String, ItemCollection> cache, String key) {
		Date now = new Date();
		ItemCollection entity = cache.get(key);
		// check date
		if (entity != null) {
			Date cached = entity.getItemValueDate("$cached");
			if (cached == null
					|| ((now.getTime() - cached.getTime()) / 1000) > refresh) {
				logger.fine("[MagentoCache] deprecated entity: '" + key
						+ "' will be removed");
				cache.remove(key);
				entity = null;
			}
		}

		return entity;
	}

	/**
	 * This method removes all entities form the cache where the $cached
	 * timestamp is older then the refreshTime.
	 */
	private void flushCache(ConcurrentHashMap<String, ItemCollection> cache) {
		Date now = new Date();
		// check products....
		Iterator<String> iter = cache.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next().toString();
			ItemCollection entity = cache.get(key);
			Date cached = entity.getItemValueDate("$cached");
			if (cached == null
					|| ((now.getTime() - cached.getTime()) / 1000) > refresh) {
				logger.fine("[MagentoCache] flush: '" + key + "'");
				iter.remove();
			}
		}

	}

}
