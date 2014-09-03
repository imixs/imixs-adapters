/*******************************************************************************
 *  Imixs Workflow 
 *  Copyright (C) 2001, 2011, 2012, 2013, 2014 Imixs Software Solutions GmbH,  
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
 *  	https://github.com/imixs
 *  
 *  Contributors:  
 *  	Imixs Software Solutions GmbH - initial API and implementation
 *  	Ralph Soika - Software Developer
 *******************************************************************************/

package org.imixs.workflow.magento.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.magento.MagentoClient;
import org.imixs.workflow.magento.MagentoException;
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

public class MagentoRestClient implements MagentoClient {

	public final static String ERROR_MESSAGE = "ERROR_MESSAGE";
	public static final String ENTITY_TYPE = "ConfigMagento";

	private MagentoApi magentoApi = null;

	String magentoApiURL = null;
	String magentoBasisURL = null;

	String magentoConsumerKey = null;
	String magentoConsumerSecret = null;

	String magentoAccessKey = null;
	String magentoAccessSecret = null;
	Token accessToken = null;

	private static Logger logger = Logger.getLogger(MagentoRestClient.class
			.getName());

	/**
	 * Connects the Client to the magento REST API. The ItemCollection contains
	 * properties needed to establish the connection
	 */
	public void connect(ItemCollection magentoConfiguration) {

		// read data from config entity....
		if (magentoConfiguration != null) {
			magentoBasisURL = magentoConfiguration
					.getItemValueString("txtMagentoRestUriBasis");
			magentoApiURL = magentoConfiguration
					.getItemValueString("txtMagentoRestUriApi");
			magentoConsumerKey = magentoConfiguration
					.getItemValueString("txtMagentoOAuthConsumerKey");
			magentoConsumerSecret = magentoConfiguration
					.getItemValueString("txtMagentoOAuthConsumerSecret");
			magentoAccessKey = magentoConfiguration
					.getItemValueString("txtMagentoRestAccessKey");
			magentoAccessSecret = magentoConfiguration
					.getItemValueString("txtMagentoRestAccessSecret");

		}

		logger.fine("[MagentoPlugin] magentoApiKey='" + magentoConsumerKey
				+ "'");
		logger.fine("[MagentoPlugin] magentoApiURL='" + magentoApiURL + "'");
		logger.fine("[MagentoPlugin] magentoBasisURL='" + magentoBasisURL + "'");
		logger.fine("[MagentoPlugin] magentoTokenKey='" + magentoAccessKey
				+ "'");

		// create api
		magentoApi = new MagentoApi(magentoBasisURL);
		magentoApi.setAdminAPI(true);

		// Create a signed token....
		logger.fine("[MagentoPlugin] generate access token: "
				+ magentoAccessKey + " - " + magentoAccessSecret);
		if (magentoAccessKey != null && magentoAccessSecret != null) {
			accessToken = new Token(magentoAccessKey, magentoAccessSecret);
		}

	}

	/**
	 * Disconnects the client
	 */
	public void disconnect() {
		accessToken = null;
	}

	/**
	 * returns a OAuthService object...
	 * 
	 * @return
	 */
	public OAuthService getService() {
		if (logger.isLoggable( Level.FINE))
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

	public List<ItemCollection> getProducts() throws MagentoException {
		// Now let's go and ask for a protected resource!
		OAuthRequest request = new OAuthRequest(Verb.GET, magentoApiURL
				+ "/products");
		getService().signRequest(accessToken, request);

		Response response = request.send();

		return MagentoJsonParser.parseObjectList(response.getBody());
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
		
		
		
		// now we need to encode the SKU with special character check!
		try {
			sku=	URLEncoder.encode(sku, "UTF-8");
			sku=sku.replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		

		ItemCollection product = null;

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
			if (result.size() > 0) {
				product = result.get(0);
			}
		} catch (PluginException e) {
			logger.warning("[MagentoPlugin] getProductBySKU not found (" + sURL
					+ ") : " + e.getMessage());
			product = null;
		}

		return product;
	}

	/**
	 * returns a single itemCollection for a magento product entry
	 * 
	 * The method also lookups the customer addresses and adds a property
	 * 'addresses' with the collection of customers addresses
	 * 
	 * Rest URI: http://magentohost/api/rest/customers/:customer_id/addresses
	 * 
	 * @see: http://www.magentocommerce.com/api/rest/Resources/
	 *       resource_customer_addresses.html
	 * 
	 * 
	 * @param item_id
	 * @return
	 * @throws PluginException
	 */
	public ItemCollection getCustomerById(int id) {
		ItemCollection customer = null;
		String sURL = magentoApiURL + "/customers/" + id;
		// add filter
		// sURL += "?filter[1][attribute]=sku&filter[1][in]=" + id;

		logger.fine("[MagentoPlugin] getCustomerById : " + sURL);
		// Now let's go and ask for a protected resource!
		OAuthRequest request = new OAuthRequest(Verb.GET, sURL);
		getService().signRequest(accessToken, request);
		Response response = request.send();
		try {
			List<ItemCollection> result = MagentoJsonParser
					.parseObjectList(response.getBody());

			if (result.size() > 0) {
				customer = result.get(0);

				// lookup addresses....
				if (customer != null) {
					String entityID = customer.getItemValueString("entity_id");
					if (!entityID.isEmpty()) {
						sURL = magentoApiURL + "/customers/" + id
								+ "/addresses";
						logger.fine("[MagentoPlugin] getCustomerById addresses : "
								+ sURL);
						// Now let's go and ask for a protected resource!
						request = new OAuthRequest(Verb.GET, sURL);
						getService().signRequest(accessToken, request);
						response = request.send();
						result = MagentoJsonParser.parseObjectList(response
								.getBody());
						if (result.size() > 0) {
							customer.replaceItemValue("addresses", result);
						}
					}
				}
			}
		} catch (PluginException e) {
			logger.warning("[MagentoPlugin] getCustomerById not found (" + sURL
					+ ") : " + e.getMessage());
			customer = null;
		}

		return customer;
	}

	/**
	 * returns a single itemCollection for a magento order entity
	 * 
	 * @param item_id
	 * @return
	 * @throws PluginException
	 */
	public ItemCollection getOrderById(String id) {
		if (id == null || id.isEmpty())
			return null;

		ItemCollection order = null;

		String sURL = magentoApiURL + "/orders/" + id;
		// add filter
		// sURL += "?filter[1][attribute]=sku&filter[1][in]=" + id;

		logger.fine("[MagentoPlugin] getOrderById : " + sURL);
		// Now let's go and ask for a protected resource!
		OAuthRequest request = new OAuthRequest(Verb.GET, sURL);
		getService().signRequest(accessToken, request);
		Response response = request.send();
		List<ItemCollection> result = new ArrayList<ItemCollection>();
		try {
			result = MagentoJsonParser.parseObjectList(response.getBody());
			if (result.size() > 0) {
				order = result.get(0);
			}
		} catch (PluginException e) {
			logger.warning("[MagentoPlugin] getOrderById not found (" + sURL
					+ ") : " + e.getMessage());
			order = null;
		}

		return order;
	}

	/**
	 * This method implements a paging mechanism because the Rest API only
	 * returns a maximum of 100 entries per call!
	 */
	public List<ItemCollection> getOrders(String status)
			throws MagentoException {

		List<ItemCollection> resultList = new ArrayList<ItemCollection>();

		// we need to implement a paging here, because magento deliveres
		// only max of 100 entries.
		boolean hasMore = true;
		int page = 1;
		int limit = 100;
		String sLastEntityID = null;
		while (hasMore) {
			// build request url with paging info..
			String requestURL = magentoApiURL + "/orders?limit=" + limit
					+ "&page=" + page;

			if (status != null && !status.isEmpty()) {
				requestURL += "&filter[1][attribute]=status&filter[1][in]="
						+ status;
			}

			// Now let's go and ask for a protected resource!
			OAuthRequest request = new OAuthRequest(Verb.GET, requestURL);
			getService().signRequest(accessToken, request);
			Response response = request.send();
			List<ItemCollection> pageResult = MagentoJsonParser
					.parseObjectList(response.getBody());

			if (pageResult.size() == 0) {
				logger.fine("[MagentoRestClient] no more orders found.");
				break;
			} else {
				// test first entry to verify if this oder junk was already
				// read
				// before (magento delivers event if page is > max orders!)
				String sEntity_id = pageResult.get(0).getItemValueString(
						"entity_id");
				if (sEntity_id.equals(sLastEntityID)) {
					// max enties read! we can leave here...
					hasMore = false;
					logger.info("[MagentoRestClient] max orders read ");
					break;
				}
				sLastEntityID = sEntity_id;
			}
			logger.fine("[MagentoSchedulerSerivce] add page result....");
			for (ItemCollection aEntity : pageResult) {
				aEntity.replaceItemValue("order_id", aEntity.getItemValue("entity_id"));
				resultList.add(aEntity);
			}
			// continue with next page!
			page++;
		}
		return resultList;
	}

	@Override
	public void getAddOrderComment(String orderIncrementId, String status,
			String comment) throws MagentoException {
		logger.warning("[MagentoSOAPClient] method not implemented: getAddOrderComment");
		// TODO Auto-generated method stub
		// not implemented because this method is not supported by Magento Rest
		// API!
	}

}
