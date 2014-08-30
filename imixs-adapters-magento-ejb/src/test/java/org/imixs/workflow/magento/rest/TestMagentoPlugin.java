package org.imixs.workflow.magento.rest;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.NamingException;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.jee.ejb.EntityService;
import org.imixs.workflow.jee.ejb.WorkflowService;
import org.imixs.workflow.magento.MagentoPlugin;
import org.imixs.workflow.magento.rest.MagentoApi;
import org.junit.Before;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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


}
