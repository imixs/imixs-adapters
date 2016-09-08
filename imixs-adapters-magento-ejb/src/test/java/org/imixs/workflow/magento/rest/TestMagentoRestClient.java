package org.imixs.workflow.magento.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.naming.NamingException;

import junit.framework.Assert;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.PropertyService;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.magento.MagentoClientFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.scribe.model.Token;

/**
 * Dieser test testet die Magento Schnittstelle
 * 
 * 
 * Das MagentoPlugin wird hier über Mockito gemockt. Dazu ist es notwendig die
 * MockitioInitialContextFactory über folgendes VM Argument einzubinden!
 * 
 * 
 * <code>
 * -Djava.naming.factory.initial=org.imixs.workflow.magento.test.MockitioInitialContextFactory
 * </code>
 * 
 * 
 * 
 * @see: https://github.com/fernandezpablo85/scribe-java
 * 
 * 
 * 
 *       Set log level
 * 
 *       http://stackoverflow.com/questions/14235726/junit4-unit-tests-running-
 *       inside-eclipse-using-java-util-logging-cannot-see-l
 * 
 * 
 * 
 */
public class TestMagentoRestClient {
	MagentoApi magentoApi = null;
	MagentoRestClient magentoClient = null;
	PropertyService propertyService = null;
	Properties properties = null;

	Map<String, ItemCollection> database = new HashMap<String, ItemCollection>();

	@Before
	public void setup() throws PluginException, IOException, NamingException {

		// setup from properties file...
		properties = new Properties();
		properties.load(Thread.currentThread().getContextClassLoader()
				.getResource("imixs.properties").openStream());

		magentoClient = (MagentoRestClient) MagentoClientFactory
				.createClient("org.imixs.workflow.magento.rest.MagentoRestClient");

		ItemCollection config = new ItemCollection();

		config.replaceItemValue("txtMagentoRestUriBasis",
				properties.getProperty("magento.rest.uri-basis"));

		config.replaceItemValue("txtMagentoRestUriApi",
				properties.getProperty("magento.rest.uri-api"));
		config.replaceItemValue("txtMagentoOAuthConsumerKey",
				properties.getProperty("magento.oauth.consumer-key"));
		config.replaceItemValue("txtMagentoOAuthConsumerSecret",
				properties.getProperty("magento.oauth.consumer-secret"));
		config.replaceItemValue("txtMagentoRestAccessKey",
				properties.getProperty("magento.rest.access-key"));

		config.replaceItemValue("txtMagentoRestAccessSecret",
				properties.getProperty("magento.rest.access-secret"));

		magentoClient.connect(config);

	}

	/**
	 * This Test requests a new AccessToken from magento OAuht...
	 * 
	 * There for the test print outs the request url and waits for an input of
	 * the verifier finally it prints out the access token
	 * 
	 */
	@Test
	@Ignore
	public void testRequestNewToken() {

		Scanner in = new Scanner(System.in);

		Token requestToken = magentoClient.getRequestToken();
		String url = magentoClient.getAuthorizationUrl(requestToken);

		System.out
				.println("Open Browser Window and authorize the Imixs MagentoPlugin here:");
		System.out.println(url);

		System.out.println("And paste the verifier here");
		System.out.print(">>");

		Token accessToken = magentoClient.getAccessToken(requestToken,
				in.nextLine());

		Assert.assertNotNull(accessToken);

		System.out.println("Got the Access Token!");
		System.out.println("   key=" + accessToken.getToken());
		System.out.println("   secret=" + accessToken.getSecret());
		System.out.println();

		in.close();
	}

	/**
	 * This Test checks the Magento Connection...
	 * 
	 */
	@Test
	@Ignore
	public void testGetProducts() {

		List<ItemCollection> result = null;
		try {
			result = magentoClient.getProducts();
			// result = magentoPlugin.getStockitems();
		} catch (PluginException e) {

			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
		ItemCollection entity = result.get(0);
		Assert.assertTrue(entity.hasItem("entity_id"));
		Assert.assertEquals("simple", entity.getItemValueString("type_id"));

	}

	/**
	 * This Test checks the Magento Connection...
	 * http://localhost/magento/rest/api/products/1
	 * 
	 */
	@Test
	@Ignore
	public void testGetProductBySKU() {

		ItemCollection result = null;

	//	result = magentoClient.getProductBySKU("100");
		
		
		String sSKU="1000-4IH + 1200-11IHSP120cm90cm";
		String sEncodedSKU=	null;
		try {
			 sEncodedSKU=	URLEncoder.encode(sSKU, "UTF-8");
			 sEncodedSKU=sEncodedSKU.replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	//	sEncodedSKU="1000-4IH%20%2B%201200-11IHSP120cm90cm";
		
		result = magentoClient.getProductBySKU(sEncodedSKU);
		
		// result = magentoPlugin.getStockitems();

		Assert.assertTrue(result.hasItem("entity_id"));
		Assert.assertFalse(result.getItemValueString("entity_id").isEmpty());
		Assert.assertEquals("100", result.getItemValueString("sku"));
		Assert.assertEquals("simple", result.getItemValueString("type_id"));

		Assert.assertEquals("Imixs Business Servicevertrag",
				result.getItemValueString("short_description"));

	}

	/**
	 * This Test checks the Magento Connection...
	 * 
	 */
	@Test
	@Ignore
	public void testGetPendingOrders() { 
 
		List<ItemCollection> result = null;
		try {
			result = magentoClient.getOrders("pending");
		} catch (PluginException e) {

			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 4);

		ItemCollection entity = result.get(0);
		Assert.assertTrue(entity.hasItem("order_id"));
		Assert.assertEquals("pending", entity.getItemValueString("status"));

	}

}
