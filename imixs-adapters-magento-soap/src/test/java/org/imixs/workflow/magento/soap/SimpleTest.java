/**
 *
 */
package org.imixs.workflow.magento.soap;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import junit.framework.Assert;

import org.imixs.workflow.magento.soap.client.Mage_Api_Model_Server_HandlerPortType;
import org.imixs.workflow.magento.soap.client.MagentoService;
import org.imixs.workflow.magento.soap.client.MagentoServiceLocator;
import org.junit.Before;
import org.junit.Test;

/**
 *  
 * 
 */
public class SimpleTest {

	String sessionId = null;
	Mage_Api_Model_Server_HandlerPortType stub = null;

	/**
	 * create session id
	 * @throws ServiceException 
	 * @throws RemoteException 
	 */
	@Before
	public void setup() throws ServiceException, RemoteException {
		// double d = Double.valueOf(args[1]).doubleValue();
		MagentoService service = new MagentoServiceLocator();
		stub = service.getMage_Api_Model_Server_HandlerPort();

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

	@Test
	public void testAddComment() throws java.lang.Exception {

		System.out.println("SessionID=" + sessionId);
		Assert.assertNotNull(sessionId);

		String orderIncrementId = "100000012";
		String status = "pending";
		String comment = "Hello World - Axis1Soap1";

		// $result = $client->call($session, 'sales_order.addComment',
		// array('orderIncrementId' => '200000004', 'status' => 'processing'));

		java.lang.Object[] args = new Object[4];
		args[0] = orderIncrementId;
		args[1] = status;
		args[2] = comment;
		args[3] = "false";

		stub.call(sessionId, "sales_order.addComment", args);

	}

	@Test
	public void testSalesOrderList() throws java.lang.Exception {

		System.out.println("SessionID=" + sessionId);
		Assert.assertNotNull(sessionId);

		java.lang.Object[] args = new Object[0];
		Object result = stub.call(sessionId, "sales_order.list", args);
		Assert.assertNotNull(result);
		System.out.println("SalesOrderListEntity=" + result);
	}

}
