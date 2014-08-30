/**
 *
 */
package org.imixs.workflow.magento;

import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.magento.rest.MagentoApi;
import org.junit.Before;
import org.junit.Test;

/**
 * test the client factory....
 * 
 */
public class FactoryTest {
	Properties properties=null;
	@Before
	public void setup() throws IOException {
		// setup from properties file...
		properties = new Properties();
		properties.load(Thread.currentThread().getContextClassLoader()
				.getResource("imixs.properties").openStream());

	}
	
	/**
	 * Test the MagentoClientFactory for the SOAP Client
	 */
	@Test
	public void testSOAPClient() throws java.lang.Exception {

		MagentoClient client = MagentoClientFactory
				.createClient("org.imixs.workflow.magento.soap.MagentoSOAPClient");

		ItemCollection config = new ItemCollection();
	
		
		config.replaceItemValue("txtMagentoAccessKey",
				properties.getProperty("magento.soap.access-key"));

		config.replaceItemValue("txtMagentoAccessSecret",
				properties.getProperty("magento.soap.access-secret"));

		
		client.connect(config);
		Assert.assertNotNull(client);

	}

	/**
	 * Test the MagentoClientFactory for the Rest Client
	 */
	@Test
	public void testRestClient() throws java.lang.Exception {

		MagentoClient client = MagentoClientFactory
				.createClient("org.imixs.workflow.magento.rest.MagentoRestClient");

		ItemCollection config = new ItemCollection();

		config.replaceItemValue("txtMagentoUriBasis",
				properties.getProperty("magento.rest.uri-basis"));

		config.replaceItemValue("txtMagentoUriApi",
				properties.getProperty("magento.rest.uri-api"));
		config.replaceItemValue("txtMagentoOAuthConsumerKey",
				properties.getProperty("magento.oauth.consumer-key"));
		config.replaceItemValue("txtMagentoOAuhtConsumerSecret",
				properties.getProperty("magento.oauth.consumer-secret"));
		config.replaceItemValue("txtMagentoAccessKey",
				properties.getProperty("magento.rest.access-key"));

		config.replaceItemValue("txtMagentoAccessSecret",
				properties.getProperty("magento.rest.access-secret"));

		client.connect(config);
		Assert.assertNotNull(client);

	}
}
