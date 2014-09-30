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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.jee.ejb.WorkflowService;
import org.imixs.workflow.jee.util.PropertyService;
import org.imixs.workflow.magento.html.MagentoHTMLClient;

/**
 * This EJB provides methods to interact with a magento instance through the
 * MagentoClient. This service EJB is also used by the MagentoPlugin class.
 * 
 * The service initialize a Client Implementation based on a configuration.
 * 
 * The Service EJB provides to both client types the MagentoSOAPClient and the
 * MagnetoRestClient. The clients are lazy loaded in the getter methods.
 * 
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
@Stateless
@LocalBean
public class MagentoService {

	public final static String ERROR_MESSAGE = "ERROR_MESSAGE";

	final static public String TYPE = "magento";

	@EJB
	PropertyService propertyService = null;

	@EJB
	WorkflowService workflowService = null;

	@EJB
	MagentoCache magentoCache = null;

	// private MagentoClient magentoSOAPClient = null;
	// private MagentoClient magentoRestClient = null;
	// private MagentoHTMLClient magentoHTMLClient = null;
	private Map<String, ItemCollection> configurations = null;
	private Map<String, Object> clients = null;
	private static Logger logger = Logger.getLogger(MagentoService.class
			.getName());

	/**
	 * initial setup the magento client implementation
	 */
	@PostConstruct
	public void init() {
		// initialize the configuration cache
		configurations = new HashMap<String, ItemCollection>();
		clients = new HashMap<String, Object>();
	}

	/**
	 * resets the connections
	 */
	public void reset() {
		// reset cache
		configurations = new HashMap<String, ItemCollection>();

		// disconnect magento clients
		if (clients != null) {
			for (Map.Entry<String, Object> entry : clients.entrySet()) {
				Object client=entry.getValue();
				if (client instanceof MagentoClient) {
					((MagentoClient) entry.getValue()).disconnect();
				}
			}
		}
		// reset clients
		clients = new HashMap<String, Object>();

	}

	/***
	 * retruns a list of all existing Magento Shop Configurations
	 * 
	 * @return
	 */
	public List<ItemCollection> findAllConfigurations() {
		// load all configurations...
		String sQuery = "SELECT config FROM Entity AS config "
				+ " JOIN config.textItems t1" + " WHERE config.type = '"
				+ MagentoService.TYPE + "'" + " AND t1.itemName='txtname'"
				+ " ORDER BY t1.itemValue";
		List<ItemCollection> col = workflowService.getEntityService()
				.findAllEntities(sQuery, 0, -1);
		return col;
	}

	/**
	 * Returns an instance of a SOAPClient for a specified magento shop
	 * configuration
	 * 
	 * @param configID
	 *            - id of the shop configuration entity
	 * @return
	 */
	public MagentoClient getSOAPClient(String configID) {

		// try to get client form cache
		MagentoClient client = (MagentoClient) clients.get(configID);
		if (client == null) {
			client = MagentoClientFactory
					.createClient("org.imixs.workflow.magento.soap.MagentoSOAPClient");
			try {
				client.connect(loadConfiguration(configID));
				clients.put(configID, client);
			} catch (MagentoException e) {
				logger.severe("[MagentoService] unable to connect SOAP Client ! "
						+ e.getMessage());
				e.printStackTrace();
				client = null;
			}
		}

		return client;
	}

	/**
	 * Returns an instance of a Rest Client for a specified magento shop
	 * configuration
	 * 
	 * @param configID
	 *            - id of the shop configuration entity
	 * @return
	 */
	public MagentoClient getRestClient(String configID) {
		// try to get client form cache
		MagentoClient client = (MagentoClient) clients.get(configID);
		if (client == null) {
			client = MagentoClientFactory
					.createClient("org.imixs.workflow.magento.rest.MagentoRestClient");

			try {
				client.connect(loadConfiguration(configID));
				clients.put(configID, client);
			} catch (MagentoException e) {
				logger.severe("[MagentoService] unable to connect Rest Client ! "
						+ e.getMessage());
				e.printStackTrace();
				client = null;
			}
		}

		return client;
	}

	/**
	 * Returns an instance of a HTMLClient for a specified magento shop
	 * configuration
	 * 
	 * @param configID
	 *            - id of the shop configuration entity
	 * @return
	 */
	public MagentoHTMLClient getHTMLClient(String configID) {
	
		// try to get client form cache
		MagentoHTMLClient client = (MagentoHTMLClient) clients.get(configID);
		if (client == null) {
			ItemCollection configuration = loadConfiguration(configID);
			// read data from config entity....
			if (configuration != null) {
				String magentoBasisURL = configuration
						.getItemValueString("txtMagentoHTMLUriBasis");

				String magentoAccessKey = configuration
						.getItemValueString("txtMagentoHTMLAccessKey");
				String magentoAccessSecret = configuration
						.getItemValueString("txtMagentoHTMLAccessSecret");

				logger.fine("[MagentoService] magentoHTMLBasisURL='"
						+ magentoBasisURL + "'");
				logger.fine("[MagentoService] magentoHTMLAccessKey='"
						+ magentoAccessKey + "'");
				logger.fine("[MagentoService] magentoBasisURL='"
						+ magentoBasisURL + "'");

				client = new MagentoHTMLClient(magentoAccessKey,
						magentoAccessSecret, magentoBasisURL);
				clients.put(configID, client);
			}

		}

		return client;
	}

	/**
	 * This method loads a magento configuration. If no configuration entity yet
	 * exists the method returns an empty ItemCollection. The method updates the
	 * timer details netxtTimeout and timeRemaining of a running timer service.
	 * 
	 * The method uses a caching mechanism to store still loaded conigurations.
	 * 
	 * @return configuration ItemCollection
	 */
	public ItemCollection loadConfiguration(String id) {

		if (id == null || id.isEmpty()) {
			logger.warning("[MagentoService] invalid shop configuration id="
					+ id);
		}
		ItemCollection configItemCollection = configurations.get(id);
		if (configItemCollection == null) {
			// try to load....
			String sQuery = "SELECT config FROM Entity AS config "
					+ " JOIN config.textItems AS t2" + " WHERE config.type = '"
					+ TYPE + "'" + " AND t2.itemName = 'txtname'"
					+ " AND t2.itemValue = '" + id + "'"
					+ " ORDER BY t2.itemValue asc";
			Collection<ItemCollection> col = workflowService.getEntityService()
					.findAllEntities(sQuery, 0, 1);

			if (col.size() > 0) {
				configItemCollection = col.iterator().next();
				logger.fine("[MagentoService] shop configuration id=" + id
						+ " loaded");
				// put new configuration into cache
				configurations.put(id, configItemCollection);

			} else {
				logger.warning("[MagentoService] shop configuration id=" + id
						+ " not defined!");

			}

		}
		return configItemCollection;
	}

	/**
	 * This method finds a workitem for a magento order id. If no worktiem exits
	 * the method returns null.
	 * 
	 * The order ID is stored in the proeprty txtName with the following format:
	 * 
	 * <code>
	 *    magento:order:1
	 *  </code>
	 * 
	 * @return workitem or null if no workitem exits
	 */
	public ItemCollection findWorkitemByOrder(ItemCollection order) {

		String sKey = getOrderID(order);
		String sQuery = "SELECT wi FROM Entity as wi";
		sQuery += " JOIN wi.textItems as t ";
		sQuery += " WHERE wi.type IN ('workitem','workitemarchive')";
		sQuery += " AND t.itemName='txtname' AND t.itemValue='" + sKey + "'";
		Collection<ItemCollection> col = workflowService.getEntityService()
				.findAllEntities(sQuery, 0, 1);
		if (col.size() > 0) {
			return col.iterator().next();
		}
		// no order found
		return null;

	}

	/**
	 * This method finds the magento order entity for a workitem.
	 * 
	 * The order ID is stored in the property txtName with the following format:
	 * 
	 * <code>
	 *    magento:order:1
	 *  </code>
	 * 
	 * @return order or null if no order exits
	 * @throws MagentoException
	 */
	public ItemCollection findOrderByWorkitem(ItemCollection workitem)
			throws MagentoException {

		String sKey = workitem.getItemValueString("txtName");
		if (sKey.isEmpty() || !sKey.startsWith("magento:order:")) {
			logger.warning("[MagentoService] findOrderByWorkitem - wrong format of order id txtname='"
					+ sKey + "' !");
			return null;
		}

		sKey = sKey.substring(14);

		// test configuration....
		String sConfigurationID = workitem
				.getItemValueString("txtMagentoConfiguration");

		ItemCollection order = this.getRestClient(sConfigurationID)
				.getOrderById(sKey);
		return order;

	}

	/**
	 * this method creates the Magento oder ID to be stored in the property
	 * 'txtName'. This property value need to be unique. The plugin can be
	 * overwritten to change this behavior.
	 * **/
	public String getOrderID(ItemCollection order) {
		String sKey = "magento:order:" + order.getItemValueString("entity_id");
		return sKey;
	}

	/**
	 * returns a single itemCollection for a magento product entry. The method
	 * uses a cache
	 * 
	 * @param id
	 * @param configID
	 *            - id of the shop configuration entity
	 * @return
	 * @throws MagentoException
	 * @throws PluginException
	 */
	public ItemCollection getCustomerById(String id, String configID)
			throws MagentoException {
		if (id == null || id.isEmpty())
			return null;

		ItemCollection customer = magentoCache.getCustomer(id);
		if (customer == null) {

			customer = this.getRestClient(configID).getCustomerById(
					new Integer(id));
			// cache product;
			if (customer != null) {
				magentoCache.cacheCustomer(id, customer);
			} else {
				magentoCache.cacheCustomer(id, new ItemCollection());
			}

		}
		return customer;
	}

	/**
	 * returns a single itemCollection for a magento product entry. The method
	 * uses cache.
	 * 
	 * @param sku
	 * @param configID
	 *            - id of the shop configuration entity
	 * @return
	 * @throws MagentoException
	 * @throws PluginException
	 */
	public ItemCollection getProductBySKU(String sku, String configID)
			throws MagentoException {
		if (sku == null || sku.isEmpty())
			return null;

		ItemCollection product = magentoCache.getProduct(sku);
		if (product == null) {

			product = this.getRestClient(configID).getProductBySKU(sku);

			// cache product;
			if (product != null) {
				magentoCache.cacheProduct(sku, product);
			} else {
				// cache empty ItemCollection
				magentoCache.cacheProduct(sku, new ItemCollection());
			}
		}

		return product;
	}

	/**
	 * This method adds the properties form a magento entity to an existing
	 * workitem. Each property of the magento entity will be prafixed with 'm_'.
	 * 
	 * <code>
	 *   entity_id => m_entity_id
	 * </code>
	 * 
	 * The method also clears all existing magento properties before!
	 * 
	 * @param workitem
	 *            - a workItem instance
	 * @param magentoEntity
	 *            - holds the properties to be added into the workItem
	 */
	@SuppressWarnings("unchecked")
	public ItemCollection addMagentoEntity(ItemCollection workitem,
			ItemCollection magentoEntity) {

		// add magento proeprties
		Iterator<String> keys = magentoEntity.getAllItems().keySet().iterator();
		while (keys.hasNext()) {
			String sName = keys.next();
			workitem.replaceItemValue("m_" + sName,
					magentoEntity.getItemValue(sName));
		}

		// now we need to verify if the workitem has more magento properties as
		// the magento Entity.
		Vector<String> removeItemList = new Vector<String>();
		keys = workitem.getAllItems().keySet().iterator();
		while (keys.hasNext()) {
			String sName = keys.next();
			// magento property??
			if (sName.startsWith("m_")) {
				String sMagento = sName.substring(2);
				if (!magentoEntity.hasItem(sMagento)) {
					removeItemList.add(sName);
				}
			}
		}
		for (String aName : removeItemList) {
			workitem.removeItem(aName);
		}

		return workitem;
	}

	/**
	 * This method compares the data of a Imixs Workitem with the data of a
	 * magento entity. In a imixs worktiem all magento properties are starting
	 * with 'm_'
	 * 
	 * @param workitem
	 * @param magentoEntity
	 * @return true if all magento properties of workitem equals to
	 *         magentoEntity
	 */
	@SuppressWarnings("unchecked")
	public boolean isWorkitemEqualsToMagentoEntity(ItemCollection workitem,
			ItemCollection magentoEntity) {

		if (workitem == null && magentoEntity == null)
			return true;

		if (workitem == null || magentoEntity == null)
			return false;

		// first check the data of all magento proeprties.....
		Iterator<String> keys = magentoEntity.getAllItems().keySet().iterator();

		while (keys.hasNext()) {
			String sName = keys.next();

			Object valueMagento = magentoEntity.getItemValue(sName);
			Object valueWorkitem = workitem.getItemValue("m_" + sName);

			if (!valueMagento.equals(valueWorkitem)) {
				return false;
			}
		}

		// now we need to verify if the workitem has more magento properties as
		// the magento Entity.
		// first check the data of all magento proeprties.....
		keys = workitem.getAllItems().keySet().iterator();
		while (keys.hasNext()) {
			String sName = keys.next();
			// magento property??
			if (sName.startsWith("m_")) {
				sName = sName.substring(2);
				if (!magentoEntity.hasItem(sName)) {
					return false;
				}
			}
		}

		return true;
	}

	public void clearCache() {
		magentoCache.clearCache();
	}
}
