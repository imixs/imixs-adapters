package org.imixs.workflow.magento.soap;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.NamingException;

import junit.framework.Assert;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.jee.ejb.EntityService;
import org.imixs.workflow.jee.util.PropertyService;
import org.imixs.workflow.magento.MagentoClient;
import org.imixs.workflow.magento.MagentoClientFactory;
import org.imixs.workflow.magento.MagentoException;
import org.junit.Before;
import org.junit.Test;

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
public class TestMagentoSoapClient {
	MagentoClient magentoClient = null;
	EntityService entityService = null;
	PropertyService propertyService = null;
	Properties properties = null;

	Map<String, ItemCollection> database = new HashMap<String, ItemCollection>();

	@Before
	public void setup() throws PluginException, IOException, NamingException {

		// setup from properties file...
		properties = new Properties();
		properties.load(Thread.currentThread().getContextClassLoader()
				.getResource("imixs.properties").openStream());

		magentoClient =  MagentoClientFactory
				.createClient("org.imixs.workflow.magento.soap.MagentoSOAPClient");

		ItemCollection config = new ItemCollection();

		config.replaceItemValue("txtMagentoAccessKey",
				properties.getProperty("magento.soap.access-key"));

		config.replaceItemValue("txtMagentoAccessSecret",
				properties.getProperty("magento.soap.access-secret"));

		magentoClient.connect(config);

	}

	/**
	 * This Test checks the getProducts method...
	 * 
	 */
	@Test
	public void testGetProducts() {

		List<ItemCollection> result = null;
		try {
			result = magentoClient.getProducts();
		} catch (PluginException e) {

			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
		ItemCollection entity = result.get(0);

		Assert.assertTrue(entity.hasItem("sku"));
		Assert.assertTrue(entity.hasItem("product_id"));
		Assert.assertTrue(entity.hasItem("name"));
	}



	/**
	 * This Test checks the Magento Connection...
	 * http://localhost/magento/rest/api/products/1
	 * 
	 */
	@Test
	public void testGetProductBySKU() {

		ItemCollection result = null;

		try {
			result = magentoClient.getProductBySKU("100");
		} catch (MagentoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		// result = magentoPlugin.getStockitems();

		Assert.assertTrue(result.hasItem("entity_id"));
		Assert.assertFalse(result.getItemValueString("entity_id").isEmpty());
		Assert.assertEquals("100", result.getItemValueString("sku"));
		Assert.assertEquals("simple", result.getItemValueString("type_id"));

		Assert.assertEquals("Imixs Business Servicevertrag",
				result.getItemValueString("short_description"));

	}

	
	/**
	 * This Test adds a comment
	 * 
	 */
	@Test
	public void testAddComment() {

		
		try {
			magentoClient.getAddOrderComment("100000012","pending","junit test");
			
		} catch (MagentoException e) {
			e.printStackTrace();
			Assert.fail();
		}


	}

	
	
	/**
	 * This Test checks the Magento Connection...
	 * 
	 */
	@Test
	public void testGetPendingOrders() {

		List<ItemCollection> result = null;
		try {
			result = magentoClient.getOrders("pending", 0, 0);
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
