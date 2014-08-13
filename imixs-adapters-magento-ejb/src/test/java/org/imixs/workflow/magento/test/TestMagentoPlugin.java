package org.imixs.workflow.magento.test;

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
import org.imixs.workflow.jee.ejb.WorkflowService;
import org.imixs.workflow.magento.MagentoApi;
import org.imixs.workflow.magento.MagentoPlugin;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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
 * Set log level
 * 
 * http://stackoverflow.com/questions/14235726/junit4-unit-tests-running-inside-eclipse-using-java-util-logging-cannot-see-l
 * 
 * 
 * 
 */
public class TestMagentoPlugin {
	MagentoApi magentoApi = null;
	MagentoPlugin magentoPlugin = null;
	ItemCollection documentActivity;
	ItemCollection documentContext;
	Properties properties = null;

	Map<String, ItemCollection> database = new HashMap<String, ItemCollection>();

	@Before
	public void setup() throws PluginException, IOException, NamingException {
		ItemCollection entity = null;

		// simulate process and space entities
		for (int i = 1; i < 6; i++) {
			entity = new ItemCollection();
			entity.replaceItemValue("type", "process");
			entity.replaceItemValue(EntityService.UNIQUEID, "P0000-0000" + i);
			entity.replaceItemValue("txtName", "Process " + i);
			database.put(entity.getItemValueString(EntityService.UNIQUEID),
					entity);
		}

		for (int i = 1; i < 6; i++) {
			entity = new ItemCollection();
			entity.replaceItemValue("type", "space");
			entity.replaceItemValue(EntityService.UNIQUEID, "S0000-0000" + i);
			entity.replaceItemValue("txtName", "Space " + i);
			database.put(entity.getItemValueString(EntityService.UNIQUEID),
					entity); 
		}

		for (int i = 1; i < 6; i++) {
			entity = new ItemCollection();
			entity.replaceItemValue("type", "workitem");
			entity.replaceItemValue(EntityService.UNIQUEID, "W0000-0000" + i);
			entity.replaceItemValue("txtName", "Workitem " + i);
			database.put(entity.getItemValueString(EntityService.UNIQUEID),
					entity);
		}

		for (int i = 1; i < 6; i++) {
			entity = new ItemCollection();
			entity.replaceItemValue(EntityService.UNIQUEID, "C0000-0000" + i);
			entity.replaceItemValue("txtName", "ChildWorkitem " + i);
			database.put(entity.getItemValueString(EntityService.UNIQUEID),
					entity);
		}

		// Mockito setup
		WorkflowService workflowContextMock = Mockito
				.mock(WorkflowService.class);
		when(workflowContextMock.getSessionContext()).thenReturn(null);

		EntityService entityService = Mockito.mock(EntityService.class);
		when(workflowContextMock.getEntityService()).thenReturn(entityService);

		// Simulate entityService.load()...
		when(entityService.load(Mockito.anyString())).thenAnswer(
				new Answer<ItemCollection>() {

					public ItemCollection answer(InvocationOnMock invocation)
							throws Throwable {
						Object[] args = invocation.getArguments();
						String id = (String) args[0];
						ItemCollection result = database.get(id);
						return result;
					}
				});

		documentActivity = new ItemCollection();
		documentContext = new ItemCollection();

		magentoPlugin = new MagentoPlugin();

		magentoPlugin.init(workflowContextMock);

	}

	/**
	 * This Test requests a new AccessToken from magento OAuht...
	 * 
	 * There for the test print outs the request url and waits for an input of
	 * the verifier finally it prints out the access token
	 *  
	 */
	@Test
	//@Ignore 
	public void testRequestNewToken() { 

		Scanner in = new Scanner(System.in);

		Token requestToken = magentoPlugin.getRequestToken();
		String url = magentoPlugin.getAuthorizationUrl(requestToken);

		System.out
				.println("Open Browser Window and authorize the Imixs MagentoPlugin here:");
		System.out.println(url);

		System.out.println("And paste the verifier here");
		System.out.print(">>");

		Token accessToken = magentoPlugin.getAccessToken(requestToken,
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
		
		List<ItemCollection> result=null;
		try {
			result = magentoPlugin.getStockitems();
		} catch (PluginException e) {
			
			e.printStackTrace();
			Assert.fail();
		}
	
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size()>0);
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
		
		List<ItemCollection> result=null;
		try {
			result = magentoPlugin.getProducts();
			//result = magentoPlugin.getStockitems();
		} catch (PluginException e) {
			
			e.printStackTrace();
			Assert.fail();
		}
 
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size()>0);
		ItemCollection entity = result.get(0);
		Assert.assertTrue(entity.hasItem("entity_id"));
		Assert.assertEquals("simple", entity.getItemValueString("type_id"));
		
	}
	

	/**
	 * This Test checks the Magento Connection...
	 *  
	 */
	@Test 
	public void testGetPendingOrders() {
		
		List<ItemCollection> result=null;
		try {
			result = magentoPlugin.getOrders("pending");
		} catch (PluginException e) {
			
			e.printStackTrace();
			Assert.fail();
		}
 
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size()>0);
		
		ItemCollection entity = result.get(0);
		Assert.assertTrue(entity.hasItem("entity_id"));	
		Assert.assertEquals("pending", entity.getItemValueString("status"));
		
		List<ItemCollection> addresses=entity.getItemValue("addresses");
		Assert.assertTrue(addresses.size()==2);
		ItemCollection address=addresses.get(0);
		Assert.assertEquals("Bayern", address.getItemValueString("region"));
		
		
	}

}
