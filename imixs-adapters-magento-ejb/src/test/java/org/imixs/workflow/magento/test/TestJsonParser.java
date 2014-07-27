package org.imixs.workflow.magento.test;

import junit.framework.Assert;

import org.imixs.workflow.exceptions.PluginException;
import org.imixs.workflow.magento.MagentoJsonParser;
import org.junit.Test;

/**
 * This test class tests the JsonParser methods.
 * 
 * 
 */
public class TestJsonParser {

	final static String PRODUCT_LIST = "[{\"item_id\":\"1\",\"product_id\":\"1\",\"stock_id\":\"1\",\"qty\":\"99.0000\",\"low_stock_date\":null},{\"item_id\":\"2\",\"product_id\":\"2\",\"stock_id\":\"1\",\"qty\":\"100.0000\",\"low_stock_date\":null}]";
	final static String ERROR_MESSAGE = "{\"messages\":{\"error\":[{\"code\":401,\"message\":\"oauth_problem=token_rejected\"}]}}";

	/**
	 * This method test the parseError method.
	 * 
	 */
	@Test
	public void testJsonError() {

		// error message
		PluginException result = MagentoJsonParser.parseError(ERROR_MESSAGE);

		Assert.assertNotNull(result);
		Assert.assertEquals("401", result.getErrorCode());
		Assert.assertEquals("oauth_problem=token_rejected", result.getMessage());

		// test empty string
		result = MagentoJsonParser.parseError("");
		Assert.assertNull(result);

		// test non error string
		result = MagentoJsonParser.parseError(PRODUCT_LIST);
		Assert.assertNull(result);

	}

}
