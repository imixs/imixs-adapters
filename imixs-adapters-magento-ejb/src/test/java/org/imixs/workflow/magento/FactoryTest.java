/**
 *
 */
package org.imixs.workflow.magento;

import java.util.Properties;

import junit.framework.Assert;

import org.imixs.workflow.ItemCollection;
import org.junit.Test;

/**
 * test the client factory....
 * 
 */
public class FactoryTest {

	/**
	 * Test the MagentoClientFactory for the SOAP Client
	 */
	@Test
	public void testSOAPClient() throws java.lang.Exception {

		MagentoClient client = MagentoClientFactory
				.createClient("org.imixs.workflow.magento.soap.MagentoSOAPClient");

		ItemCollection config = new ItemCollection();

		config.replaceItemValue("txtMagentoAccessKey", "admin");
		config.replaceItemValue("txtMagentoAccessSecret", "barer47");

		client.connect(config);
		Assert.assertNotNull(client);

	}

	/**
	 * Test the MagentoClientFactory for the Rest Client
	 */
	@Test
	public void testRestClient() throws java.lang.Exception {

		MagentoClient client = MagentoClientFactory
				.createClient("org.imixs.workflow.magento.soap.MagentoSOAPClient");

		// setup from properties file...
		Properties properties = new Properties();
		properties.load(Thread.currentThread().getContextClassLoader()
				.getResource("imixs.properties").openStream());

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
				properties.getProperty("magento.access-key"));

		config.replaceItemValue("txtMagentoAccessSecret",
				properties.getProperty("magento.access-secret"));

		client.connect(config);
		Assert.assertNotNull(client);

	}
}
