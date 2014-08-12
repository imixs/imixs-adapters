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
import java.util.List;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.Plugin;
import org.imixs.workflow.WorkflowContext;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.jee.util.PropertyService;
import org.imixs.workflow.plugins.jee.AbstractPlugin;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * This plugin provides methods to interact with a magento instance through the
 * magento rest api.
 * 
 * @author rsoika
 * 
 */
public class MagentoPlugin extends AbstractPlugin {

	public final static String PROPERTYSERVICE_NOT_BOUND = "PROPERTYSERVICE_NOT_BOUND";
	public final static String ERROR_MESSAGE = "ERROR_MESSAGE";

	ItemCollection documentContext;

	private MagentoApi magentoApi = null;

	String magentoApiURL = null;
	String magentoBasisURL = null;

	String magentoApiKey = null;
	String magentoApiSecret = null;

	String magentoTokenKey = null;
	String magentoTokenSecret = null;

	String basicAuthUser = null;
	String basicAuthPassword = null;

	boolean debugMode = false;

	// Basic Authentication
	String user = null;
	String password = null;

	OAuthService service = null;
	Token accessToken = null;
	private PropertyService propertyService = null;

	private static Logger logger = Logger.getLogger(MagentoPlugin.class
			.getName());

	/**
	 * 
	 */
	@Override
	public void init(WorkflowContext actx) throws PluginException {
		super.init(actx);

		try {
			// lookup PropertyService
			InitialContext ictx = new InitialContext();
			Context ctx = (Context) ictx.lookup("java:comp/env");
			String jndiName = "ejb/PropertyService";
			propertyService = (PropertyService) ctx.lookup(jndiName);
		} catch (NamingException e) {
			throw new PluginException(MagentoPlugin.class.getSimpleName(),
					PROPERTYSERVICE_NOT_BOUND, "PropertyService not bound", e);
		}

		// read configuration....
		magentoBasisURL = propertyService.getProperties().getProperty(
				"magento.uri-basis");
		logger.fine("[MagentoPlugin] magentoBasisURL='"+magentoBasisURL+"'");
		
		magentoApiURL = propertyService.getProperties().getProperty(
				"magento.uri-api");
		logger.fine("[MagentoPlugin] magentoApiURL='"+magentoApiURL+"'");
		
		
		magentoApiKey = propertyService.getProperties().getProperty(
				"magento.token.api-key");
		logger.fine("[MagentoPlugin] magentoApiKey='"+magentoApiKey+"'");
		
		
		magentoApiSecret = propertyService.getProperties().getProperty(
				"magento.token.api-secret");
		magentoTokenKey = propertyService.getProperties().getProperty(
				"magento.token.access-key");
		logger.fine("[MagentoPlugin] magentoTokenKey='"+magentoTokenKey+"'");
		
		
		magentoTokenSecret = propertyService.getProperties().getProperty(
				"magento.token.access-secret");

		basicAuthUser = propertyService.getProperties().getProperty(
				"magento.basicAuth.user");
		basicAuthPassword = propertyService.getProperties().getProperty(
				"magento.basicAuth.password");

		if ("true".equals(propertyService.getProperties().getProperty(
				"magento.debug")))
			debugMode = true;
		
		
		// create api
		magentoApi = new MagentoApi();
		magentoApi.setAdminAPI(true);
		magentoApi.setBaseURL(magentoBasisURL);

		// add basic auth if provided
		this.addBasicAuthCredentials(basicAuthUser, basicAuthPassword);

		// Create a signed token....
		logger.fine("[MagentoPlugin] generate access token: " + magentoTokenKey
				+ " - " + magentoTokenSecret);
		accessToken = new Token(magentoTokenKey, magentoTokenSecret);

	}

	/**
	 *
	 */
	@Override
	public int run(ItemCollection adocumentContext,
			ItemCollection documentActivity) throws PluginException {

		return Plugin.PLUGIN_OK;
	}

	@Override
	public void close(int arg0) throws PluginException {

		// no op

	}

	/**
	 * returns a OAuthService object...
	 * 
	 * @return
	 */
	public OAuthService getService() {
		if (service == null) {
			if (debugMode)
				service = new ServiceBuilder().provider(magentoApi)
						.apiKey(magentoApiKey).apiSecret(magentoApiSecret)
						.debug().build();
			else
				service = new ServiceBuilder().provider(magentoApi)
						.apiKey(magentoApiKey).apiSecret(magentoApiSecret)
						.build();
		}
		return service;
	}

	/**
	 * This method adds a user credentials for a basic authentication. This is
	 * needed if the complete service is additional protected with basic
	 * authentication
	 * 
	 * @param aUser
	 * @param aPassword
	 */
	public void addBasicAuthCredentials(String aUser, String aPassword) {
		this.user = aUser;
		this.password = aPassword;
	}

	/**
	 * returns a new request token...
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

		Response response = request.send(new BasicAuthRequestTuner(user,
				password));

		List<ItemCollection> result = new ArrayList<ItemCollection>();
		result = MagentoJsonParser.parseObjectList(response.getBody());

		return result;
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

	public List<ItemCollection> getOrders(String status) throws PluginException {

		String requestURL = magentoApiURL + "/orders";

		if (status != null && !status.isEmpty()) {
			requestURL += "?filter[1][attribute]=status&filter[1][in]="
					+ status;
		}

		// Now let's go and ask for a protected resource!
		OAuthRequest request = new OAuthRequest(Verb.GET, requestURL);

		// addBasicAuthHeader(request);

		getService().signRequest(accessToken, request);
		Response response = request.send();

		List<ItemCollection> result = new ArrayList<ItemCollection>();
		result = MagentoJsonParser.parseObjectList(response.getBody());

		return result;
	}

}
