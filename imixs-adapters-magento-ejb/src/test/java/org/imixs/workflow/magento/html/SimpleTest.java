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
		loginURI = properties.getProperty("magento.html.uri-basis");
	}

	/**
	 * This test reads a sales order
	 */
	// @Ignore
	@Test
	public void testGetOrder() {
		MagentoHTMLClient restClient = new MagentoHTMLClient(userid, password,loginURI);

		try {

			String page = restClient.readPage("/sales_order/view/order_id/13");

			// String path = "/home/rsoika/Downloads/magento-test.html";
			// Files.write(Paths.get(path), page.getBytes(),
			// StandardOpenOption.CREATE);

			// expected not null
			Assert.assertNotNull(page);

			Assert.assertTrue(page.contains("Order"));
			Assert.assertTrue(page.contains("Gaby"));

			// test if second request need login
			page = restClient.readPage( "/sales_order/view/order_id/13");
			Assert.assertNotNull(page);

			Assert.assertTrue(page.contains("Order"));
			Assert.assertTrue(page.contains("Gaby"));

		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}

	}

	
}
