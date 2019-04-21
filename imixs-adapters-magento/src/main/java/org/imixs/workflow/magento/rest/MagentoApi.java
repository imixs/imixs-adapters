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

import java.util.logging.Logger;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * This Class extends the DefaultApi 1.0a and provides the access endpoints for
 * magento. The Class supports the Consumer and the Admin view. There for the
 * class provided different oauth URL pattern.
 * 
 * The Base URL of the Magento Shop can be set using the constructor. (Default=
 * http://localhost/magento/index.php/)
 * 
 * * The Admin view can be configured by setting the property 'admin' to 'true'.
 * 
 * 
 * 
 * @author rsoika
 * 
 */
public class MagentoApi extends DefaultApi10a {
	static final String URL_PATTERN_AUTHORIZATION_CONSUMER = "oauth/authorize";
	static final String URL_PATTERN_AUTHORIZATION_ADMIN = "admin/oauth_authorize";

	String baseURL = "http://localhost/magento/index.php/";
	boolean adminAPI = false;

	private static Logger logger = Logger.getLogger(MagentoApi.class.getName());

	public MagentoApi() {
		super();
	}

	public MagentoApi(String baseURL) {
		super();
		this.baseURL = baseURL;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		logger.fine("[MagentoApi] set BaseURL='" + baseURL + "'");

		this.baseURL = baseURL;
	}

	public boolean isAdminAPI() {
		return adminAPI;
	}

	public void setAdminAPI(boolean adminAPI) {
		this.adminAPI = adminAPI;
		logger.fine("[MagentoApi] set adminAPI=" + adminAPI);
	}

	@Override
	public String getRequestTokenEndpoint() {
		return getBaseURL() + "oauth/initiate";
	}

	@Override
	public String getAccessTokenEndpoint() {
		return getBaseURL() + "oauth/token";
	}

	/**
	 * Depending on the isAdmin Flag the method returns the OAuth authorization
	 * url for the Admin login or the consumer login.
	 * 
	 * @param requestToken
	 * @return OAuth Authorization URL
	 */
	@Override
	public String getAuthorizationUrl(Token requestToken) {
		if (isAdminAPI()) {
			return getBaseURL() + URL_PATTERN_AUTHORIZATION_ADMIN
					+ "?oauth_token=" + requestToken.getToken();
		} else
			return getBaseURL() + URL_PATTERN_AUTHORIZATION_CONSUMER
					+ "?oauth_token=" + requestToken.getToken();
	}

}
