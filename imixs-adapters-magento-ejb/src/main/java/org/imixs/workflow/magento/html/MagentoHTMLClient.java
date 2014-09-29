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
	private String basisURL=null;

	private String cookies = null;
	private String encoding = "UTF-8";

	private String loginFormKey = null;

	private final static Logger logger = Logger.getLogger(MagentoHTMLClient.class
			.getName());

	/**
	 * Creates a HTMLCLient instance with backend userid and password.
	 * 
	 * @param user
	 * @param password
	 */
	public MagentoHTMLClient(String user, String password,String basisURL) {
		super();
		this.user = user;
		this.password = password;
		this.basisURL=basisURL;
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
	 * This method get the content of a GET request
	 * 
	 * @param uri
	 *            - Rest Endpoint RUI
	 * 
	 * @return HTTPResult
	 */
	public String readPage(String uri) throws Exception {
		long l = System.currentTimeMillis();

		String result = readPage(basisURL+uri, basisURL+uri);
		logger.fine("[HTMLClient] read page in "
				+ (System.currentTimeMillis() - l) + " ms");
		return result;
	}

	private String readPage(String uri, String referer) throws Exception {
		String pageContent = null;
		URL obj = new URL(uri);

		logger.fine("[HTMLClient] Sending 'GET' request to URL : " + uri);
		HttpURLConnection urlConnection = (HttpURLConnection) obj
				.openConnection();

		// optional default is GET
		urlConnection.setRequestMethod("GET");

		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setAllowUserInteraction(false);
		urlConnection.setRequestProperty("User-Agent", USER_AGENT);
		urlConnection.setRequestProperty("Cache-Control", "max-age=0");
		urlConnection.addRequestProperty("Referer", uri);

		if (cookies != null) {
			urlConnection.setRequestProperty("Cookie", cookies);
		}
		// Authorization
		if (basicAuthUser != null) {
			urlConnection.setRequestProperty("Authorization",
					"Basic " + this.getAccessByUser());
		}

		// addCookies(urlConnection);

		int responseCode = urlConnection.getResponseCode();

		logger.fine("[HTMLClient] Response GET Code : " + responseCode);
		if (responseCode >= 200 && responseCode <= 299) {
			// read cookies
			// readCookies(urlConnection);
			pageContent = readResponse(urlConnection);

			if (isLoginPage(pageContent)) {
				parseFormKey(pageContent);
				pageContent = readPageWithLogin(uri);
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
	private String readPageWithLogin(String uri) throws Exception {
		// PrintWriter printWriter = null;
		logger.info("[HTMLClient] Login... 'POST' request to URL : " + uri);
		HttpURLConnection urlConnection = null;
		int responseCode = 500;
		try {
			String loginData = "form_key="
					+ URLEncoder.encode(loginFormKey, "UTF-8") + "&"
					+ "login%5Busername%5D=" + URLEncoder.encode(user, "UTF-8")
					+ "&" + "login%5Bpassword%5D="
					+ URLEncoder.encode(password, "UTF-8");

			// create a POST request......
			urlConnection = (HttpURLConnection) new URL(uri).openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			urlConnection.setRequestProperty("Content-Length",
					"" + Integer.toString(loginData.getBytes().length));
			urlConnection.setRequestProperty("Content-Language", "en-US");

			urlConnection.setUseCaches(false);
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);

			// we need to disable FollowRedirects !
			urlConnection.setInstanceFollowRedirects(false);
			HttpURLConnection.setFollowRedirects(false);

			// addCookies(urlConnection);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					urlConnection.getOutputStream());
			wr.writeBytes(loginData);
			wr.flush();
			wr.close();

			logger.finest("loginData=" + loginData);

			responseCode = urlConnection.getResponseCode();
			logger.finest("[HTMLClient] Response POST Code : " + responseCode);

			boolean redirect = false;
			if (responseCode != HttpURLConnection.HTTP_OK) {
				if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
						|| responseCode == HttpURLConnection.HTTP_MOVED_PERM
						|| responseCode == HttpURLConnection.HTTP_SEE_OTHER)
					redirect = true;
			}

			// if redirect then we now send a new request!
			if (redirect) {

				// get redirect url from "location" header field
				String newUrl = urlConnection.getHeaderField("Location");

				// get the cookie if need, for login
				cookies = urlConnection.getHeaderField("Set-Cookie");

				return readPage(newUrl, uri);				
			} else {
				// get the cookie if need, for login
				cookies = urlConnection.getHeaderField("Set-Cookie");
				// get content of result
				return readResponse(urlConnection);
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

		if (page.contains("form_key") && page.contains("login[username]")
				&& page.contains("login[password]")) {
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
	}

	/**
	 * Put the resonse string into the content property
	 * 
	 * @param urlConnection
	 * @throws IOException
	 */
	private String readResponse(URLConnection urlConnection) throws IOException {
		// get content of result
		logger.finest("[RestClient] readResponse....");
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
				in = new BufferedReader(new InputStreamReader(
						urlConnection.getInputStream(), sContentEncoding));
			else
				in = new BufferedReader(new InputStreamReader(
						urlConnection.getInputStream()));
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
