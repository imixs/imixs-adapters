/**
 *
 */
package org.imixs.workflow.magento.soap;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.imixs.workflow.magento.soap.generated.Mage_Api_Model_Server_V2_HandlerPortType;
import org.imixs.workflow.magento.soap.generated.MagentoService;
import org.imixs.workflow.magento.soap.generated.MagentoServiceLocator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import junit.framework.Assert;

/**
 *  
 * 
 */
public class SimpleTest {

	String sessionId = null;
	Mage_Api_Model_Server_V2_HandlerPortType stub = null;

	/**
	 * create session id
	 * @throws ServiceException 
	 * @throws RemoteException 
	 */
	@Before
	public void setup() throws ServiceException, RemoteException {
		// double d = Double.valueOf(args[1]).doubleValue();
		MagentoService service = new MagentoServiceLocator();
		stub = service.getMage_Api_Model_Server_V2_HandlerPort();

		sessionId = stub.login("admin", "");

	}

	/**
	 * Test the reflection of Product
	 */
	@Test
	public void testlogin() throws java.lang.Exception {

		System.out.println("SessionID=" + sessionId);
		Assert.assertNotNull(sessionId);

	}

	@Ignore
	@Test
	public void testAddComment() throws java.lang.Exception {

		System.out.println("SessionID=" + sessionId);
		Assert.assertNotNull(sessionId);

		String orderIncrementId = "100000012";
		String status = "pending";
		String comment = "Hello World - Axis1Soap1";

		

		stub.salesOrderAddComment(sessionId, orderIncrementId, status,comment,"false");

	}

	@Ignore
	@Test
	public void testSalesOrderList() throws java.lang.Exception {

		System.out.println("SessionID=" + sessionId);
		Assert.assertNotNull(sessionId);

		java.lang.Object[] args = new Object[0];
		Object result = stub.salesOrderList(sessionId,null);
		Assert.assertNotNull(result);
		System.out.println("SalesOrderListEntity=" + result);
	}

}
