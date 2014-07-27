package org.imixs.workflow.marty.test;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

import org.imixs.workflow.jee.util.PropertyService;
import org.mockito.Mockito;

/**
 * This class mocks the initial Context for a Marty JEE Plugin. 
 * The factory class can be used in JUnit Tests...
 * 
 * To activate this class add the following VM argument:
 *  
 * <code>
 * -Djava.naming.factory.initial=org.imixs.workflow.marty.test.MartyInitialContextFactory
 * </code>
 * 
 * @author rsoika
 *  
 */
public class MartyInitialContextFactory implements InitialContextFactory {
	public Context getInitialContext(Hashtable<?, ?> arg0)
			throws NamingException {

		PropertyService propertyService = Mockito.mock(PropertyService.class);
		Context context = Mockito.mock(Context.class);

		// mok property servcie
		// read configuration
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResource("imixs.properties").openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		when(propertyService.getProperties()).thenReturn(properties);

		Mockito.when(context.lookup("java:comp/env")).thenReturn(context);
		Mockito.when(context.lookup("ejb/PropertyService")).thenReturn(
				propertyService);
		return context;
	}

}
