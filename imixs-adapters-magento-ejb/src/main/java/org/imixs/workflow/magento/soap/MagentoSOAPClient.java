package org.imixs.workflow.magento.soap;

import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.rpc.ServiceException;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.magento.MagentoClient;
import org.imixs.workflow.magento.MagentoException;

public class MagentoSOAPClient implements MagentoClient {
	
	public final static String CONNECTION_FAILURE = "CONNECTION_FAILURE";
	private String magentoAccessKey = null;
	private String magentoAccessSecret = null;

	
	private String sessionId = null;
	private Mage_Api_Model_Server_HandlerPortType stub = null;
	
	private static Logger logger = Logger.getLogger(MagentoSOAPClient.class
			.getName());

	@Override
	public void connect(ItemCollection magentoConfiguration) throws MagentoException {
		magentoAccessKey = magentoConfiguration
				.getItemValueString("txtMagentoAccessKey");
		magentoAccessSecret = magentoConfiguration
				.getItemValueString("txtMagentoAccessSecret");

		
		MagentoSOAPService service = new MagentoSOAPServiceLocator();
		try {
			stub = service.getMage_Api_Model_Server_HandlerPort();
			sessionId = stub.login(magentoAccessKey, magentoAccessSecret);

			logger.fine("[MagentoSOAPClient] connected - sessionId=" + sessionId);
		} catch (ServiceException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "Connection failed: ", e);
		} catch (RemoteException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "Connection failed: ", e);
		}

	
		
	}
	
	@Override
	public void disconnect() {
		sessionId=null;
	}

	@Override
	public List<ItemCollection> getProducts() throws MagentoException {
		logger.warning("[MagentoSOAPClient] method not implemented: getProducts");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemCollection getCustomerById(String id) {
		logger.warning("[MagentoSOAPClient] method not implemented: getCustomerById");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemCollection getOrderById(String id) {
		logger.warning("[MagentoSOAPClient] method not implemented: getOrderById");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemCollection> getStockitems() throws MagentoException {
		logger.warning("[MagentoSOAPClient] method not implemented: getStockitems");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemCollection> getOrders(String status, int page, int limit) {
		logger.warning("[MagentoSOAPClient] method not implemented: getOrders");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemCollection getProductBySKU(String sku) {
		logger.warning("[MagentoSOAPClient] method not implemented: getProductBySKU");

		// TODO Auto-generated method stub
		return null;
	}

}
