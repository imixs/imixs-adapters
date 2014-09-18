/**
 *
 */
package org.imixs.workflow.magento.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.NamingException;

import junit.framework.Assert;

import org.imixs.workflow.exceptions.PluginException;
import org.junit.Before;
import org.junit.Test;

/**
 *  
 * 
 */
public class SimpleTest {
	Properties properties = null;

	String userid = null;
	String password = null;
	String loginURI = null;
	CookieManager cookies = null;

	private final static Logger logger = Logger.getLogger(SimpleTest.class
			.getName());

	@Before
	public void setup() throws PluginException, IOException, NamingException {

		// setup from properties file...
		properties = new Properties();
		properties.load(Thread.currentThread().getContextClassLoader()
				.getResource("imixs.properties").openStream());

		password = properties.getProperty("magento.html.access-secret");
		userid = properties.getProperty("magento.html.access-key");
		loginURI = properties.getProperty("magento.html.uri-login");
	}

	/**
	 * This test reads a sales order
	 */
	// @Ignore
	@Test
	public void testGetOrder() {
		HTMLClient restClient = new HTMLClient(userid, password);

		try {

			String page = restClient.readPage(loginURI
					+ "/sales_order/view/order_id/13");

//			String path = "/home/rsoika/Downloads/magento-test.html";
//			Files.write(Paths.get(path), page.getBytes(),
//					StandardOpenOption.CREATE);

			// expected not null
			Assert.assertNotNull(page);

			Assert.assertTrue(page.contains("Order"));
			Assert.assertTrue(page.contains("Gaby"));

			// test if second request need login
			page = restClient.readPage(loginURI
					+ "/sales_order/view/order_id/13");
			Assert.assertNotNull(page);

			Assert.assertTrue(page.contains("Order"));
			Assert.assertTrue(page.contains("Gaby"));

		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}

	}

	/**
	 * This test verifies the backend login
	 */
	// @Ignore
	@Test
	public void testLoginPage() {
		CopyOfHTMLClient restClient = new CopyOfHTMLClient();

		// form_key:pRiUJ3wRzrXynv5v
		// login[username]:admin
		// login[password]:barer47

		try {

			int httpResult = restClient.get(loginURI
					+ "/sales_order/view/order_id/13", null);

			String page = restClient.getContent();

			String path = "/home/rsoika/Downloads/magento-test.html";
			Files.write(Paths.get(path), page.getBytes(),
					StandardOpenOption.CREATE);

			// expected result 200
			Assert.assertEquals(200, httpResult);

			// check form
			Assert.assertTrue(page.contains("form_key"));

			Assert.assertTrue(page.contains("login[username]"));
			Assert.assertTrue(page.contains("login[password]"));

			// pars the form key
			// <input name="form_key" type="hidden" value="Gxz3pkJZAkdCP5ou">
			int iPos = page.indexOf("name=\"form_key\"");

			String sTest = page.substring(iPos);

			int iStart = page.indexOf("value=\"", iPos + 1) + 7;
			int iEnd = page.indexOf("\"", iStart + 1);

			String formkey = page.substring(iStart, iEnd);

			// now post the right form data.....
			httpResult = restClient.postLogin(formkey, userid, password,
					loginURI + "/sales_order/view/order_id/13");

			page = restClient.getContent();

			path = "/home/rsoika/Downloads/magento-test-login.html";
			Files.write(Paths.get(path), page.getBytes(),
					StandardOpenOption.CREATE);

			cookies = restClient.getCookieManager();

			Assert.assertNotNull(cookies);

			// test get order
			// index.php/admin/sales_order/view/order_id/13
			// restClient.setCookieManager(cookies);
			httpResult = restClient.get(loginURI
					+ "/sales_order/view/order_id/13", null);

			page = restClient.getContent();

			path = "/home/rsoika/Downloads/magento-test-order.html";
			Files.write(Paths.get(path), page.getBytes(),
					StandardOpenOption.CREATE);

			// expected result 200
			Assert.assertEquals(200, httpResult);

			Assert.assertTrue(page.contains("Order"));

		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}

	}

	@Test
	public void testRedirect() {

		try {

			String url = "http://toci01.imixs.com:11180/magento/index.php/admin/sales_order/view/order_id/13";

			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(5000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Referer", "localhost");

			System.out.println("Request URL ... " + url);

			boolean redirect = false;

			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
						|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
					redirect = true;
			}

			System.out.println("Response Code ... " + status);

			if (redirect) {

				// get redirect url from "location" header field
				String newUrl = conn.getHeaderField("Location");

				// get the cookie if need, for login
				String cookies = conn.getHeaderField("Set-Cookie");

				// open the new connnection again
				conn = (HttpURLConnection) new URL(newUrl).openConnection();
				conn.setRequestProperty("Cookie", cookies);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				conn.addRequestProperty("User-Agent", "Mozilla");
				conn.addRequestProperty("Referer", "google.com");

				System.out.println("Redirect to URL : " + newUrl);

			}

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String inputLine;
			StringBuffer html = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				html.append(inputLine);
			}
			in.close();

			System.out.println("URL Content... \n" + html.toString());
			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
