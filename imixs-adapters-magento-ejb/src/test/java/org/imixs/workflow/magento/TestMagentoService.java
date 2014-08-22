package org.imixs.workflow.magento;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.naming.NamingException;

import junit.framework.Assert;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.jee.ejb.EntityService;
import org.imixs.workflow.jee.util.PropertyService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
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
public class TestMagentoService {
	MagentoApi magentoApi = null;
	MagentoService magentoService = null;
	EntityService entityService = null;
	PropertyService propertyService = null;
	Properties properties = null;

	Map<String, ItemCollection> database = new HashMap<String, ItemCollection>();

	@Before
	public void setup() throws PluginException, IOException, NamingException {
		ItemCollection entity = null;

		// Mockito setup
		entityService = Mockito.mock(EntityService.class);
		propertyService = Mockito.mock(PropertyService.class);

		// Simulate entityService.load()...
		properties = new Properties();
		properties.load(Thread.currentThread().getContextClassLoader()
				.getResource("imixs.properties").openStream());
		when(propertyService.getProperties()).thenReturn(properties);

		magentoService = Mockito.spy(new MagentoService());
		magentoService.propertyService = propertyService;

		Mockito.doReturn(null).when(magentoService).loadConfiguration();

		magentoService.init();

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

		Token requestToken = magentoService.getRequestToken();
		String url = magentoService.getAuthorizationUrl(requestToken);

		System.out
				.println("Open Browser Window and authorize the Imixs MagentoPlugin here:");
		System.out.println(url);

		System.out.println("And paste the verifier here");
		System.out.print(">>");

		Token accessToken = magentoService.getAccessToken(requestToken,
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
	public void testGetStockitems() {

		List<ItemCollection> result = null;
		try {
			result = magentoService.getStockitems();
		} catch (PluginException e) {

			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
		ItemCollection entity = result.get(0);

		Assert.assertTrue(entity.hasItem("item_id"));
		Assert.assertTrue(entity.hasItem("product_id"));
		Assert.assertTrue(entity.hasItem("stock_id"));
	}

	/**
	 * This Test checks the Magento Connection...
	 * 
	 */
	@Test
	public void testGetProducts() {

		List<ItemCollection> result = null;
		try {
			result = magentoService.getProducts();
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
	public void testGetProduct1() {

		ItemCollection result = null;

		result = magentoService.getProductBySKU("1");
		// result = magentoPlugin.getStockitems();

		Assert.assertTrue(result.hasItem("entity_id"));
		Assert.assertEquals("1", result.getItemValueString("entity_id"));
		Assert.assertEquals("simple", result.getItemValueString("type_id"));

		Assert.assertEquals("Imixs Business Servicevertrag",
				result.getItemValueString("short_description"));

	}

	/**
	 * This Test checks the Magento Connection...
	 * 
	 */
	@Test
	public void testGetPendingOrders() {

		List<ItemCollection> result = null;
		try {
			result = magentoService.getOrders("pending", 0, 0);
		} catch (PluginException e) {

			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(result); 
		Assert.assertTrue(result.size() > 4);

		ItemCollection entity = result.get(0);
		Assert.assertTrue(entity.hasItem("entity_id"));
		Assert.assertEquals("pending", entity.getItemValueString("status"));

		List<ItemCollection> addresses = entity.getItemValue("addresses");
		Assert.assertTrue(addresses.size() == 2);
		ItemCollection address = addresses.get(0);
		Assert.assertEquals("Alabama", address.getItemValueString("region"));

	}

}
