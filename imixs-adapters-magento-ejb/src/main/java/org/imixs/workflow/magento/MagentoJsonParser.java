package org.imixs.workflow.magento;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;

/**
 * This class parses the Magento json structures and transforms objects into
 * instances of Imixs ItemCollection
 * 
 * @version 1.0
 * @author rsoika
 * 
 */
public class MagentoJsonParser {
	private final static Logger logger = Logger
			.getLogger(MagentoJsonParser.class.getName());

	/**
	 * This method parses a Magento json string for a error message and returns
	 * a new instance of a imixs PluginException containing the error code and
	 * the error message. If the json string is no error message the method
	 * returns null.
	 * 
	 * Example JSON String:
	 * 
	 * <code>
	 *       {"messages":{"error":[{"code":401,"message":"oauth_problem=token_rejected"}]}}
	 * </code>
	 * 
	 * @param json
	 *            - the message string
	 * @return an PluginException or null if no error message contained.
	 * 
	 */
	public static PluginException parseError(String json) {

		PluginException result = null;
		if (json == null)
			return null;

		json = json.trim();
		if (json.isEmpty())
			return null;

		// test error message string...
		if (json.indexOf("\"messages\":{\"error\":") == -1)
			return null;

		// parse the error message
		JsonParser parser = Json.createParser(new StringReader(json));

		Event event = null;
		// Advance to "messages" key
		while (parser.hasNext()) {
			event = parser.next();
			if (event == Event.KEY_NAME
					&& "messages".equals(parser.getString())) {
				event = parser.next();
				break;
			}
		}

		// parse message object....
		long code = -1;
		String message = null;
		while (event != Event.END_OBJECT) {
			if (event == Event.KEY_NAME && "code".equals(parser.getString())) {
				// code..
				event = parser.next();
				code = parser.getLong();
			}

			if (event == Event.KEY_NAME && "message".equals(parser.getString())) {
				// message..
				event = parser.next();
				message = parser.getString();
			}

			// error message found?
			if (code > -1 && message != null) {
				result = new PluginException(MagentoPlugin.ERROR_MESSAGE, ""
						+ code, message);
				logger.fine("[MagentoJsonParser] found error message: " + code
						+ " - " + message);
				break;
			}

			event = parser.next();
		}

		return result;
	}

	/**
	 * This method parses a Magento json string containing a List of objects.
	 * Each object can contain several items. An object will be transfromed into
	 * a ItemCollection. The Result will be stored into a list of ItemColleciton
	 * objects.
	 * 
	 * 
	 * Example JSON String:
	 * 
	 * <code>
	 *    [ {"item_id":"1","product_id":"1","stock_id":"1","qty":"99.0000","low_stock_date":null},
	 *      {"item_id":"2","product_id":"2","stock_id":"1","qty":"100.0000","low_stock_date":null}
	 *    ]
	 * </code>
	 * 
	 * @param json
	 *            - the object string
	 * @return an List<ItemCollection> containing the objects. Can be empty. Can
	 *         not be null.
	 * @throws PluginException
	 * 
	 */
	public static List<ItemCollection> parseObjectList(String json)
			throws PluginException {

		List<ItemCollection> result = new ArrayList<ItemCollection>();
		if (json == null)
			return result;

		json = json.trim();
		if (json.isEmpty())
			return result;

		// test error message...
		PluginException pluginException = parseError(json);
		if (pluginException != null) {
			logger.severe("[MagentoParser] error parsing ObjectList!");
			throw pluginException;
		}

		// parse the data string
		JsonParser parser = Json.createParser(new StringReader(json));

		Event event = null;
		// Advance to "messages" key
		while (parser.hasNext()) {
			event = parser.next();
			// object start...

			if (event == Event.START_OBJECT) {

				// parse the item collection...
				ItemCollection entity = parseItemCollection(parser);

				// add itemCollection into result
				if (entity != null) {
					result.add(entity);
					entity = null;
				}

			}

		}

		return result;
	}

	/**
	 * This method pareses a item collection part. The expected format is:
	 * 
	 * <code>
	 *  {
        "item_id":"1",
        "product_id":"1",
        "stock_id":"1",
        ....
        }
	 * </code>
	 * 
	 * an item can contain embedded items. The method then makes a recursive
	 * call and embeds a new itemcolleciton into the current
	 * 
	 * <code>
	 * ....
	 *  "tax_name":null,
        "tax_rate":null,
        "addresses":[
            {
                "region":"Bayern",
                "postcode":"34535",
                ...
             },
       "tax_rate":null,
        }]
	 * </code>
	 * 
	 * 
	 * Matento also returns lists with an index number before each item. The
	 * format than looks like this:
	 * 
	 * <code>
	 *  {"1":{
        "entity_id":"1",
        "attribute_set_id":"4",
        "type_id":"simple",
        ...
        },
        "2":{
        ....
	 * </code>
	 * 
	 * @param parser
	 * @return
	 */
	private static ItemCollection parseItemCollection(JsonParser parser) {
		Event event = null;

		ItemCollection entity = new ItemCollection();
		// object contents
		while (event != Event.END_OBJECT) {

			// check if empty arry (from recursive call)
			if (event == Event.END_ARRAY) {
				return null;
			}
			
			
			if (event == Event.KEY_NAME) {
				Object itemValue = null;
				String itemName = parser.getString();
				// value..
				event = parser.next();

				switch (event) {
				
				
				case START_ARRAY: {
					itemValue = new ArrayList<ItemCollection>();
					while (event != Event.END_ARRAY) {
						ItemCollection embeddedItemCollection=parseItemCollection(parser);
						if (embeddedItemCollection!=null) {
							((List<ItemCollection>)itemValue).add(embeddedItemCollection);
						} else {
							// empty array!
							break;
						}
						event = parser.next();
					}
					break;
				}
				
				
				case START_OBJECT: {
					// embedded itemCollection
				//	itemValue =parseItemCollection(parser);
				}
				

				case VALUE_FALSE: {
					itemValue = false;
					break;
				}
				case VALUE_TRUE: {
					itemValue = true;
					break;
				}
				case VALUE_NULL: {
					itemValue = null;
					break;
				}
				case VALUE_NUMBER: {
					if (parser.isIntegralNumber()) {
						itemValue = new Integer(parser.getInt());
					} else {
						BigDecimal b = parser.getBigDecimal();
						itemValue = new Double(b.doubleValue());
					}
					break;
				}
				case VALUE_STRING: {
					itemValue = parser.getString();
					break;
				}

				default: {
				}

				}

				if (itemValue != null) {
					entity.replaceItemValue(itemName, itemValue);
				}

			}

			event = parser.next();
		}

		return entity;
	}

}
