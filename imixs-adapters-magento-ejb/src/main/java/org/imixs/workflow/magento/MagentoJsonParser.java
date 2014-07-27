package org.imixs.workflow.magento;

import java.io.StringReader;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

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
		boolean hasMessages = false;
		
		if (json==null) 
			return null;
		
		json=json.trim();
		if (json.isEmpty())
			return null;
		
		// test error message string...
		if (json.indexOf("\"messages\":{\"error\":")==-1)
			return null;

		// parse the error message
		JsonParser parser = Json.createParser(new StringReader(json));

		if (parser.hasNext()) {

			while (parser.hasNext()) {

				Event eventKey = parser.next();

				if (eventKey == Event.KEY_NAME) {

					String name = parser.getString();
					if ("messages".equals(name)) {
						hasMessages = true;
						logger.fine("MagentoJsonParser: messages found");
					}

					// test for error object
					if (hasMessages && "error".equals(name)) {

						try {
							logger.fine("MagentoJsonParser: error found");

							// now parse the error object....

							// array....
							@SuppressWarnings("unused")
							Event errorKey = parser.next();
							errorKey = parser.next();
							errorKey = parser.next();

							// code..
							errorKey = parser.next();

							long code = parser.getLong();

							// message..
							errorKey = parser.next();
							errorKey = parser.next();

							String message = parser.getString();

							result = new PluginException(
									MagentoPlugin.ERROR_MESSAGE, "" + code,
									message);
							break;
						} catch (Exception parsingException) {
							logger.fine("MagentoJsonParser: error parsing error message!");

						}
					}

				}
			}
		}

		return result;
	}

}
