package org.imixs.workflow.magento;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * This Class extends the DefaultApi 1.0a and provides the access endpoints for
 * magento. The Class supports the Consumer and the Admin view. There for the
 * class provided different oauth URL pattern.
 * 
 * The Base URL can be set using the constructor
 * 
 * @author rsoika
 * 
 */
public class MagentoApi extends DefaultApi10a {
	static final String URL_PATTERN_AUTHORIZATION_CONSUMER = "oauth/authorize";
	static final String URL_PATTERN_AUTHORIZATION_ADMIN = "admin/oauth_authorize";

	String baseURL = "http://localhost/magento/index.php/";
	boolean adminAPI = false;

	public String getBaseURL() {
		return baseURL;
	} 

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}


	public boolean isAdminAPI() {
		return adminAPI;
	}

	public void setAdminAPI(boolean adminAPI) {
		this.adminAPI = adminAPI;

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
