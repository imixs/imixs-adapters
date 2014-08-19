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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.jee.ejb.WorkflowService;
import org.imixs.workflow.jee.util.PropertyService;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * This EJB provides methods to interact with a magento instance through the
 * magento rest api. This service EJB is also used by the MagentoPlugin class.
 * 
 * The service initialize the OAuth Data to access the Magento Web Service Rest
 * API. The configuration can be stored in a config Entity 'ConfigMagento' or in
 * the imixs.properties file.
 * 
 * The configuration is read during the @PostConstruct call back method.
 * 
 * The method getService() returns a OAuthService object.
 * 
 * @author rsoika
 */
public class MagentoService {

	public final static String ERROR_MESSAGE = "ERROR_MESSAGE";
	public static final String ENTITY_TYPE = "ConfigMagento";
	private ItemCollection magentoConfiguration = null;

	private MagentoApi magentoApi = null;

	String magentoApiURL = null;
	String magentoBasisURL = null;

	String magentoConsumerKey = null;
	String magentoConsumerSecret = null;

	String magentoAccessKey = null;
	String magentoAccessSecret = null;
	Token accessToken = null;

	boolean debugMode = false;

	@EJB
	PropertyService propertyService = null;

	@EJB
	WorkflowService workflowSerivice = null;

	private static Logger logger = Logger.getLogger(MagentoService.class
			.getName());

	/**
	 * initial setup for magento REST API
	 */
	@PostConstruct
	public void init() {

		// first try to lookup the mangento configuration entity. If found we
		// can read the configuration there.
		// if not we ask the property service.
		magentoConfiguration = loadConfiguration();

		// read data from config entity....
		if (magentoConfiguration != null) {
			magentoBasisURL = magentoConfiguration
					.getItemValueString("txtMagentoUriBasis");
			magentoApiURL = magentoConfiguration
					.getItemValueString("txtMagentoUriApi");
			magentoConsumerKey = magentoConfiguration
					.getItemValueString("txtMagentoTokenConsumerKey");
			magentoConsumerSecret = magentoConfiguration
					.getItemValueString("txtMagentoTokenConsumerSecret");
			magentoAccessKey = magentoConfiguration
					.getItemValueString("txtMagentoTokenAccessKey");
			magentoAccessSecret = magentoConfiguration
					.getItemValueString("txtMagentoTokenAccessSecret");
		} else {
			// read data from property service
			magentoBasisURL = propertyService.getProperties().getProperty(
					"magento.uri-basis");
			magentoApiURL = propertyService.getProperties().getProperty(
					"magento.uri-api");
			magentoConsumerKey = propertyService.getProperties().getProperty(
					"magento.token.consumer-key");
			magentoConsumerSecret = propertyService.getProperties()
					.getProperty("magento.token.consumer-secret");
			magentoAccessKey = propertyService.getProperties().getProperty(
					"magento.token.access-key");

			magentoAccessSecret = propertyService.getProperties().getProperty(
					"magento.token.access-secret");
		}

		if ("true".equals(propertyService.getProperties().getProperty(
				"magento.debug")))
			debugMode = true;

		logger.fine("[MagentoPlugin] magentoApiKey='" + magentoConsumerKey
				+ "'");
		logger.fine("[MagentoPlugin] magentoApiURL='" + magentoApiURL + "'");
		logger.fine("[MagentoPlugin] magentoBasisURL='" + magentoBasisURL + "'");
		logger.fine("[MagentoPlugin] magentoTokenKey='" + magentoAccessKey
				+ "'");

		// create api
		magentoApi = new MagentoApi(magentoBasisURL);
		magentoApi.setAdminAPI(debugMode);

		// Create a signed token....
		logger.fine("[MagentoPlugin] generate access token: "
				+ magentoAccessKey + " - " + magentoAccessSecret);
		if (magentoAccessKey != null && magentoAccessSecret != null) {
			accessToken = new Token(magentoAccessKey, magentoAccessSecret);
		}

	}

	/**
	 * Loads the Magento configuration entity
	 * 
	 * @return
	 */
	public ItemCollection loadConfiguration() {

		// try to lookup the mangento configuration entity.
		String sQuery = "SELECT";
		sQuery += " wi FROM Entity as wi " + " WHERE wi.type='"
				+ MagentoService.ENTITY_TYPE + "'";
		Collection<ItemCollection> col = workflowSerivice.getEntityService()
				.findAllEntities(sQuery, 0, 1);
		if (col.size() > 0) {
			return col.iterator().next();
		} else {
			return null;
		}
	}

	/**
	 * returns a OAuthService object...
	 * 
	 * @return
	 */
	public OAuthService getService() {
		if (debugMode)
			return new ServiceBuilder().provider(magentoApi)
					.apiKey(magentoConsumerKey)
					.apiSecret(magentoConsumerSecret).debug().build();
		else
			return new ServiceBuilder().provider(magentoApi)
					.apiKey(magentoConsumerKey)
					.apiSecret(magentoConsumerSecret).build();
	}

	/**
	 * returns a new request token...
	 * 
	 * I cast here to class 'OAuth10aServiceImpl' to be able to use the
	 * RequestTuner here.
	 * 
	 * @see https://github.com/fernandezpablo85/scribe-java/issues/504
	 * 
	 * @return
	 */
	public Token getRequestToken() {
		// get a request token...
		return getService().getRequestToken();
	}

	/**
	 * returns a new authorization url...
	 * 
	 * @return
	 */
	public String getAuthorizationUrl(Token requestToken) {
		return getService().getAuthorizationUrl(requestToken);
	}

	/**
	 * This method requests a new access token....
	 * 
	 * @return
	 */
	public Token getAccessToken(Token requestToken, String averifier) {

		Verifier verifier = new Verifier(averifier);
		accessToken = getService().getAccessToken(requestToken, verifier);
		return accessToken;
	}

	public List<ItemCollection> getProducts() throws PluginException {
		// Now let's go and ask for a protected resource!
		OAuthRequest request = new OAuthRequest(Verb.GET, magentoApiURL
				+ "/products");
		getService().signRequest(accessToken, request);

		Response response = request.send();

		List<ItemCollection> result = new ArrayList<ItemCollection>();
		result = MagentoJsonParser.parseObjectList(response.getBody());

		return result;
	}

	/**
	 * returns a single itemCollection for a magento product entry
	 * 
	 * @param item_id
	 * @return
	 * @throws PluginException
	 */
	public ItemCollection getProductBySKU(String sku) {
		if (sku == null || sku.isEmpty())
			return null;

		String sURL = magentoApiURL + "/products";
		// add filter
		sURL += "?filter[1][attribute]=sku&filter[1][in]=" + sku;

		logger.fine("[MagentoPlugin] getProductBySKU : " + sURL);
		// Now let's go and ask for a protected resource!
		OAuthRequest request = new OAuthRequest(Verb.GET, sURL);
		getService().signRequest(accessToken, request);
		Response response = request.send();
		List<ItemCollection> result = new ArrayList<ItemCollection>();

		try {
			result = MagentoJsonParser.parseObjectList(response.getBody());
		} catch (PluginException e) {
			logger.warning("[MagentoPlugin] getProductBySKU not found (" + sURL
					+ ") : " + e.getMessage());
			return null;
		}
		return result.get(0);
	}

	public List<ItemCollection> getStockitems() throws PluginException {
		// Now let's go and ask for a protected resource!
		OAuthRequest request = new OAuthRequest(Verb.GET, magentoApiURL
				+ "/stockitems");

		// addBasicAuthHeader(request);

		getService().signRequest(accessToken, request);
		Response response = request.send();

		List<ItemCollection> result = new ArrayList<ItemCollection>();
		result = MagentoJsonParser.parseObjectList(response.getBody());

		return result;
	}

	public List<ItemCollection> getOrders(String status, int page, int limit)
			throws PluginException {

		if (limit <= 0)
			limit = 100;
		if (page < 0)
			page = 0;

		String requestURL = magentoApiURL + "/orders?limit=" + limit + "&page="
				+ page;

		if (status != null && !status.isEmpty()) {
			requestURL += "&filter[1][attribute]=status&filter[1][in]="
					+ status;
		}

		// Now let's go and ask for a protected resource!
		OAuthRequest request = new OAuthRequest(Verb.GET, requestURL);

		getService().signRequest(accessToken, request);
		Response response = request.send();

		List<ItemCollection> result = new ArrayList<ItemCollection>();
		result = MagentoJsonParser.parseObjectList(response.getBody());

		return result;
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
		Collection<ItemCollection> col = workflowSerivice.getEntityService()
				.findAllEntities(sQuery, 0, 1);
		if (col.size() > 0) {
			return col.iterator().next();
		}
		// no order found
		return null;

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
	 * This method adds the properties form a magento entity to an existing
	 * workitem. Each property of the magento entity will be prafixed with 'm_'.
	 * 
	 * <code>
	 *   entity_id => m_entity_id
	 * </code>
	 * 
	 * The method also clears all existing magento proeprties
	 * 
	 * @param workitem
	 *            - a workItem instance
	 * @param magentoEntity
	 *            - holds the properties to be added into the workItem
	 */
	@SuppressWarnings("unchecked")
	public ItemCollection addMagentoEntity(ItemCollection workitem,
			ItemCollection magentoEntity) {
		// clear old magento proeperties
		Iterator<String> keys = workitem.getAllItems().keySet().iterator();
		while (keys.hasNext()) {
			String sName = keys.next();
			if (sName.startsWith("m_")) {
				workitem.removeItem(sName);
			}
		}

		// add magento proeprties
		keys = magentoEntity.getAllItems().keySet().iterator();
		while (keys.hasNext()) {
			String sName = keys.next();
			workitem.replaceItemValue("m_" + sName,
					magentoEntity.getItemValue(sName));
		}
		return workitem;
	}

	/**
	 * This method extracts all properties with the prafix 'm_' from a workitem
	 * and creates a Magento ItemCollection. This ItemCollection can be write
	 * back to the Magento Rest API.
	 * 
	 * @param workitem
	 * @return a magento entity
	 */
	@SuppressWarnings("unchecked")
	public ItemCollection createMagentoEntityFromWorkitem(
			ItemCollection workitem) {
		ItemCollection magentoEntity = new ItemCollection();
		Iterator<String> keys = workitem.getAllItems().keySet().iterator();

		while (keys.hasNext()) {

			String sName = keys.next();
			if (sName.startsWith("m_")) {
				String sMagentoItemName = sName.substring(2);
				magentoEntity.replaceItemValue(sMagentoItemName,
						workitem.getItemValue(sName));
			}
		}
		return magentoEntity;
	}
}
