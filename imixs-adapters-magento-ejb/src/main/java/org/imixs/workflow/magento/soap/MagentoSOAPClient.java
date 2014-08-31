package org.imixs.workflow.magento.soap;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
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
	public void connect(ItemCollection magentoConfiguration)
			throws MagentoException {
		magentoAccessKey = magentoConfiguration
				.getItemValueString("txtMagentoAccessKey");
		magentoAccessSecret = magentoConfiguration
				.getItemValueString("txtMagentoAccessSecret");

		MagentoSOAPService service = new MagentoSOAPServiceLocator();
		try {
			stub = service.getMage_Api_Model_Server_HandlerPort();
			sessionId = stub.login(magentoAccessKey, magentoAccessSecret);

			logger.fine("[MagentoSOAPClient] connected - sessionId="
					+ sessionId);
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
		sessionId = null;
	}

	@Override
	public void getAddOrderComment(String orderIncrementId, String status, String comment)
			throws MagentoException {
		
		logger.fine("[MagentoSOAPClient] getAddOrderComment - sessionId="
				+ sessionId);

		
		java.lang.Object[] args=new Object[4];
		args[0]=orderIncrementId;
		args[1]=status;
		args[2]=comment;
		args[3]="false";
		
		try {
			stub.call(sessionId, "sales_order.addComment", args);
		} catch (RemoteException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "getAddOrderComment failed: ", e);
		}
		
		
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ItemCollection> getProducts() throws MagentoException {
		logger.fine("[MagentoSOAPClient] getStockItems - sessionId="
				+ sessionId);

		List<ItemCollection> result = new ArrayList<ItemCollection>();

		java.lang.Object[] args = new String[0];
		Map[] responseObject;
		try {
			responseObject = (Map[]) stub.call(sessionId,
					"catalog_product.list", args);

			if (responseObject != null) {

				for (Map entity : responseObject) {
					ItemCollection itemCol = convertToItemCollection(entity);
					if (itemCol != null) {
						result.add(itemCol);
					}
				}

			}
		} catch (RemoteException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "getStockitems failed: ", e);
		}

		return result;
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
	public List<ItemCollection> getOrders(String status, int page, int limit) {
		logger.warning("[MagentoSOAPClient] method not implemented: getOrders");
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ItemCollection getProductBySKU(String sku) throws MagentoException {
		Set<String> attributes=new HashSet<String>();
		attributes.add("product_id");
		
		Object[] arguments = new Object[] { sku+" ", null, attributes };
		
		Map responseObject;
		try {
			Object irgendwas =  stub.call(sessionId,
					"catalog_product.info", arguments);

//			if (responseObject != null) {
//				ItemCollection itemCol = convertToItemCollection(responseObject);
//				return itemCol;
//			}
		} catch (RemoteException e) {
			throw new MagentoException(MagentoSOAPClient.class.getSimpleName(),
					CONNECTION_FAILURE, "getProductBySKU failed: ", e);
		}
		return null;
	}

	/**
	 * This method converts the elements of a Map into a ItemCollection. The
	 * method takes care about ArrayList and String[] Arrays and converts them
	 * into a Vector.
	 * 
	 * If one element is an embedded Map then this will result in embedded
	 * ItemCollection
	 * 
	 * @param entity
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ItemCollection convertToItemCollection(Map entity) {

		ItemCollection result = new ItemCollection();

		Iterator iter = entity.keySet().iterator();
		while (iter.hasNext()) {
			String itemName = (String) iter.next();
			Object itemValue = entity.get(itemName);

			// print object types....
			logger.fine("Entry " + itemName + " is a "
					+ itemValue.getClass().getName());

			if (itemValue instanceof Object[]) {
				logger.fine("value type is Array");
				Vector<?> v = new Vector();
				v.copyInto((Object[]) itemValue);
				result.replaceItemValue(itemName, v);
			} else {
				if (itemValue instanceof ArrayList) {
					logger.fine("value type is ArrayList");
					
					ArrayList arrayList=(ArrayList) itemValue;
					// test if first element of the list is a Map 
					// then call recursiv
					if (arrayList.size()>0 && arrayList.get(0) instanceof Map) {
						List<Map> mapList=arrayList;
						Vector vItemColList=new Vector();
						for (Map embeddedEntity: mapList) {
							vItemColList.add(convertToItemCollection(embeddedEntity));
						}
						result.replaceItemValue(itemName, vItemColList);
					} else {
						result.replaceItemValue(itemName, itemValue);
					}
					
				} else {
					// default - should be a basic object (String)
					result.replaceItemValue(itemName, itemValue);
					logger.fine(" Value="+itemValue);
				}
			}
			

		}

		return result;

	}
}
