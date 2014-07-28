package org.imixs.workflow.magento.test;

import java.util.List;

import junit.framework.Assert;

import org.imixs.workflow.ItemCollection;
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

	@Test
	public void testProductList() {

		// error message
		List<ItemCollection> result=null;
		try {
			result = MagentoJsonParser
					.parseObjectList(PRODUCT_LIST);
		} catch (PluginException e) {
			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());

		ItemCollection entity = result.get(0);
		Assert.assertEquals("1", entity.getItemValueString("item_id"));
		Assert.assertEquals("1", entity.getItemValueString("product_id"));
		Assert.assertEquals("1", entity.getItemValueString("stock_id"));
		Assert.assertEquals(99.000, entity.getItemValueDouble("qty"));

		entity = result.get(1);
		Assert.assertEquals("2", entity.getItemValueString("item_id"));
		Assert.assertEquals("2", entity.getItemValueString("product_id"));
		Assert.assertEquals("1", entity.getItemValueString("stock_id"));
		Assert.assertEquals(100.000, entity.getItemValueDouble("qty"));
		
		
		// test unexpected error message
		try {
			result = MagentoJsonParser
					.parseObjectList(ERROR_MESSAGE);
			Assert.fail();
		} catch (PluginException e) {
			Assert.assertEquals("401", e.getErrorCode());
			Assert.assertEquals("oauth_problem=token_rejected", e.getMessage());
		}
	}

}
