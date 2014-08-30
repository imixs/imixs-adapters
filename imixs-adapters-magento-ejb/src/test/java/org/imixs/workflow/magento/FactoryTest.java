/**
 *
 */
package org.imixs.workflow.magento;

import junit.framework.Assert;

import org.imixs.workflow.ItemCollection;
import org.junit.Test;

/**
 *  test the client factory....
 * 
 */
public class FactoryTest {

	

	/**
	 * Test the reflection of Product
	 */
	@Test
	public void testSOAPClient() throws java.lang.Exception {

		MagentoClient client=MagentoClientFactory.createClient("org.imixs.workflow.magento.soap.MagentoSOAPClient");
		
		ItemCollection config=new ItemCollection();
		
		config.replaceItemValue("txtMagentoAccessKey", "admin");
		config.replaceItemValue("txtMagentoAccessSecret", "barer47");
		
		client.connect(config);
		Assert.assertNotNull(client);

	}
}
