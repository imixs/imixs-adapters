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
package org.imixs.workflow.magento.html;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Logger;

import org.imixs.workflow.util.Base64;

/**
 * This HTMLClient is used to connect into the Magento Backend. The client can
 * read any page to be parsed for content.
 * 
 * For example to receive a html page of a specific sales order content the
 * following deep link url pattern can be used.
 * 
 * <code>
 *  http://[MAGENTO_HOST]/index.php/admin/sales_order/view/order_id/[ORDER_ID]
 *  </code>
 * 
 * The reason for this client is the fact that some attributes of a customized
 * magento shop are not provided by the Magento Rest API and SOAP API. So this
 * client allows read the html backend data.
 * 
 * The method 'readPage() allows to read the content of a page. The method
 * expects the deep link uri, userid and password to login to the backend. The
 * HTMLClient caches a valid session.
 * 
 * 
 * @see http
 *      ://www.mkyong.com/java/java-httpurlconnection-follow-redirect-example/
 * 
 * @see http://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
 * 
 * @author Ralph Soika
 * @version 1.0
 */
public class MagentoHTMLClient {
	private final String USER_AGENT = "Mozilla/5.0";

	private String basicAuthUser = null;
	private String basicAuhtPassword = null;

	private String user = null;
	private String password = null;
	private String basisURL = null;

	private String cookies = null;
	private String encoding = "UTF-8";

	private String loginFormKey = null;

	private final static Logger logger = Logger.getLogger(MagentoHTMLClient.class.getName());

	/**
	 * Creates a HTMLCLient instance with backend userid and password.
	 * 
	 * @param user
	 * @param password
	 */
	public MagentoHTMLClient(String user, String password, String basisURL) {
		super();
		this.user = user;
		this.password = password;
		this.basisURL = basisURL;
	}

	/**
	 * This method sets additional credentials for a basic Authentification.
	 * This can be used if the magento backend is secured additional with a
	 * basic authentification
	 * 
	 * @param auser
	 * @param apw
	 */
	public void setBasicAuhtCredentials(String auser, String apw) {
		basicAuthUser = auser;
		basicAuhtPassword = apw;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String aEncoding) {
		encoding = aEncoding;
	}

	public String getUser() {
		return basicAuthUser;
	}

	public void setUser(String user) {
		this.basicAuthUser = user;
	}

	public String getPassword() {
		return basicAuhtPassword;
	}

	public void setPassword(String password) {
		this.basicAuhtPassword = password;
	}

	/**
	 * This method get the content of a GET request. If the response is a
	 * Magento login form the method will proceed a form base authentication.
	 * 
	 * @param uri
	 *            - Rest Endpoint URI
	 * 
	 * @return HTTPResult
	 */
	public String readPage(String uri) throws Exception {
		// allow login redirect
		return readPage(uri, true);
	}

	/**
	 * This method get the content of a GET request. If the response is a
	 * Magento login form the method will proceed a form base authentication.
	 * 
	 * @param uri
	 *            - Rest Endpoint URI
	 * 
	 * @param redirectWithLogin
	 *            - indicates if a form based login form in the response should
	 *            be processed
	 * 
	 * @return HTTPResult
	 */
	public String readPage(final String uri, final boolean redirectWithLogin) throws Exception {
		long lStart = System.currentTimeMillis();
		String result = readPageContent(uri, redirectWithLogin);
		// test response Time...
		long lEnd = System.currentTimeMillis();
		if (((lEnd - lStart) / 1000) > 2) {
			logger.warning("WARNING: MagentoHTMLClient readPage resonse time>2 seconds! - " + ((lEnd - lStart) / 1000)
					+ "seconds overall!");
		}

		logger.fine("[HTMLClient] read page in " + (System.currentTimeMillis() - lStart) + " ms");
		return result;
	}

	/**
	 * This method reads a page and verifies if the response is a Magento Login
	 * form. In this case the method starts a form-based login process.
	 * 
	 * With the boolean redirectWithLogin==false it is possible to avoid this
	 * login procedure.
	 * 
	 * @param uri
	 * @param referer
	 * @param redirectWithLogin
	 * @return
	 * @throws Exception
	 */
	private String readPageContent(final String uri, final boolean redirectWithLogin) throws Exception {
		String pageContent = null;
		URL urlObj = null;
		if (!uri.startsWith(basisURL)) {
			urlObj = new URL(basisURL + uri);
		} else {
			urlObj = new URL(uri);
		}

		logger.fine("[HTMLClient] Sending 'GET' request: " + urlObj.toString());
		HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();

		// optional default is GET
		urlConnection.setRequestMethod("GET");

		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setAllowUserInteraction(false);
		urlConnection.setRequestProperty("User-Agent", USER_AGENT);
		urlConnection.setRequestProperty("Cache-Control", "max-age=0");
		urlConnection.addRequestProperty("Referer", urlObj.toString());

		addCookies(urlConnection);

		// Authorization
		if (basicAuthUser != null) {
			logger.fine("[HTMLClient] set Basic Authorization...");
			urlConnection.setRequestProperty("Authorization", "Basic " + this.getAccessByUser());
		}

		int responseCode = urlConnection.getResponseCode();

		logger.fine("[HTMLClient] Response GET Code : " + responseCode);
		if (responseCode >= 200 && responseCode <= 299) {
			readCookies(urlConnection);

			pageContent = readResponse(urlConnection);
			// check if response is a login form
			if (isLoginPage(pageContent)) {
				logger.fine("[HTMLClient] response == Magento Login Page");
				// process a form-based login?
				if (redirectWithLogin) {
					parseFormKey(pageContent);
					pageContent = processFormBasedLogin(uri);
				} else {
					logger.warning("[HTMLClient] Response is Magento Login Page, but redirectWithLogin is disabled!");
				}
			}

		}

		return pageContent;
	}

	/**
	 * This method posts the login data and reads the content of returned page.
	 * We expect a HTTP Result 302 after login to be redirected to the content
	 * url.
	 * 
	 * If the responseCode =30x then the follow up request is send
	 * automatically!
	 * 
	 * @param uri
	 * @return page content or null if not readable
	 */
	private String processFormBasedLogin(final String requestUri) throws Exception {
		// PrintWriter printWriter = null;
		logger.info("[HTMLClient] processFormBasedLogin for URL : " + basisURL + requestUri);
		HttpURLConnection urlConnection = null;
		int responseCode = 500;
		try {
			String loginData = "form_key=" + URLEncoder.encode(loginFormKey, "UTF-8") + "&" + "login%5Busername%5D="
					+ URLEncoder.encode(user, "UTF-8") + "&" + "login%5Bpassword%5D="
					+ URLEncoder.encode(password, "UTF-8");

			// create a POST request......
			urlConnection = (HttpURLConnection) new URL(basisURL + requestUri).openConnection();

			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			urlConnection.setRequestProperty("Content-Length", "" + Integer.toString(loginData.getBytes().length));
			urlConnection.setRequestProperty("Content-Language", "en-US");

			urlConnection.setUseCaches(false);
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);

			// we need to disable FollowRedirects !
			urlConnection.setInstanceFollowRedirects(false);
			HttpURLConnection.setFollowRedirects(false);

			addCookies(urlConnection);

			// Send request
			DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
			wr.writeBytes(loginData);
			wr.flush();
			wr.close();

			logger.finest("loginData=" + loginData);

			responseCode = urlConnection.getResponseCode();
			logger.fine("[HTMLClient] Response POST Code : " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP 
					|| responseCode == HttpURLConnection.HTTP_MOVED_PERM
					|| responseCode == HttpURLConnection.HTTP_SEE_OTHER) {

				// get redirect url from "location" header field
				String newUrl = urlConnection.getHeaderField("Location");
				logger.fine("[HTMLClient] redirected to " + newUrl);
			}

			// if no bad request
			if (responseCode<HttpURLConnection.HTTP_BAD_REQUEST) {
				logger.fine("[HTMLClient] processFormBasedLogin OK - repeat request...");
				readCookies(urlConnection);

				// Issue #21
				// try to read again the uri with no login!
				return readPage(requestUri, false);
				// get content of result
				// return readResponse(urlConnection);
			} else {
				logger.fine("[HTMLClient] processFormBasedLogin FAILED ");
				return null;
			}

		} catch (Exception ioe) {
			// ioe.printStackTrace();
			throw ioe;
		} finally {
			// Release current connection

		}
	}

	/**
	 * This method verifies if a magento HTML page is the magento backend login
	 * page
	 * 
	 * @return
	 */
	boolean isLoginPage(String page) {
		boolean bLoginPage = false;

		if (page.contains("form_key") && page.contains("login[username]") && page.contains("login[password]")) {
			bLoginPage = true;
		}

		return bLoginPage;
	}

	/**
	 * This method parses a magento html page for a form_key used for the
	 * magento login page.
	 * 
	 * Expectd format:
	 * 
	 * <code>
	 *   <input name="form_key" type="hidden" value="Gxz3pkJZAkdCP5ou">
	 * </code>
	 * 
	 */
	void parseFormKey(String page) {
		int iPos = page.indexOf("name=\"form_key\"");
		int iStart = page.indexOf("value=\"", iPos + 1) + 7;
		int iEnd = page.indexOf("\"", iStart + 1);
		loginFormKey = page.substring(iStart, iEnd);
		logger.fine("[HTMLClient] loginFormKey=" + loginFormKey);
	}

	private void addCookies(URLConnection urlConnection) {
		if (cookies != null) {
			logger.fine("[HTMLClient] set cookies=" + cookies);
			urlConnection.setRequestProperty("Cookie", cookies);
		}
	}

	private void readCookies(URLConnection urlConnection) {
		String newCookies = urlConnection.getHeaderField("Set-Cookie");
		if (newCookies != null) {
			cookies = newCookies;
			logger.fine("[HTMLClient] read cookies=" + cookies);
		} else {
			logger.fine("[HTMLClient] no cookies read");
		}
	}

	/**
	 * Get the response
	 * 
	 * @param urlConnection
	 * @throws IOException
	 */
	private String readResponse(URLConnection urlConnection) throws IOException {
		// get content of result
		logger.fine("[RestClient] readResponse....");
		StringWriter writer = new StringWriter();
		BufferedReader in = null;
		try {
			// test if content encoding is provided
			String sContentEncoding = urlConnection.getContentEncoding();
			if (sContentEncoding == null || sContentEncoding.isEmpty()) {
				// no so lets see if the client has defined an encoding..
				if (encoding != null && !encoding.isEmpty())
					sContentEncoding = encoding;
			}

			// if an encoding is provided read stream with encoding.....
			if (sContentEncoding != null && !sContentEncoding.isEmpty())
				in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), sContentEncoding));
			else
				in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				logger.finest(inputLine);
				writer.write(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
		}

		return writer.toString();

	}

	/**
	 * add basic auth user for basic authentication
	 */
	private String getAccessByUser() {
		String sURLAccess = "";
		// UserID:Passwort
		String sUserCode = basicAuthUser + ":" + basicAuhtPassword;
		// String convertieren
		// sURLAccess = Base64.encodeBase64(sUserCode.getBytes()).toString();
		char[] authcode = Base64.encode(sUserCode.getBytes());

		sURLAccess = String.valueOf(authcode);
		return sURLAccess;
	}

}
