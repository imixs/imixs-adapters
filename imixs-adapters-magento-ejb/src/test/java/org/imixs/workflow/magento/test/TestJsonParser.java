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
	final static String ORDER_LIST="{\"1\":{\"entity_id\":\"1\",\"status\":\"pending\",\"coupon_code\":null,\"shipping_description\":\"Flat Rate - Fixed\",\"customer_id\":null,\"base_discount_amount\":\"0.0000\",\"base_grand_total\":\"205.0000\",\"base_shipping_amount\":\"5.0000\",\"base_shipping_tax_amount\":\"0.0000\",\"base_subtotal\":\"200.0000\",\"base_tax_amount\":\"0.0000\",\"base_total_paid\":null,\"base_total_refunded\":null,\"discount_amount\":\"0.0000\",\"grand_total\":\"205.0000\",\"shipping_amount\":\"5.0000\",\"shipping_tax_amount\":\"0.0000\",\"store_to_order_rate\":\"1.0000\",\"subtotal\":\"200.0000\",\"tax_amount\":\"0.0000\",\"total_paid\":null,\"total_refunded\":null,\"base_shipping_discount_amount\":\"0.0000\",\"base_subtotal_incl_tax\":\"200.0000\",\"base_total_due\":null,\"shipping_discount_amount\":\"0.0000\",\"subtotal_incl_tax\":\"200.0000\",\"total_due\":null,\"increment_id\":\"100000001\",\"base_currency_code\":\"EUR\",\"discount_description\":null,\"remote_ip\":\"::1\",\"store_currency_code\":\"EUR\",\"store_name\":\"Main Website\nMain Website Store\nDefault Store View\",\"created_at\":\"2014-07-23 19:56:32\",\"shipping_incl_tax\":\"5.0000\",\"payment_method\":\"checkmo\",\"gift_message_from\":null,\"gift_message_to\":null,\"gift_message_body\":null,\"tax_name\":null,\"tax_rate\":null,\"addresses\":[{\"region\":\"Bayern\",\"postcode\":\"34535\",\"lastname\":\"Soika\",\"street\":\"Ag.\",\"city\":\"asdf\",\"email\":\"ralph.soika@imixs.com\",\"telephone\":\"23423\",\"country_id\":\"DE\",\"firstname\":\"Ralph\",\"address_type\":\"billing\",\"prefix\":null,\"middlename\":null,\"suffix\":null,\"company\":\"Imixs\"},{\"region\":\"Bayern\",\"postcode\":\"34535\",\"lastname\":\"Soika\",\"street\":\"Ag.\",\"city\":\"asdf\",\"email\":\"ralph.soika@imixs.com\",\"telephone\":\"23423\",\"country_id\":\"DE\",\"firstname\":\"Ralph\",\"address_type\":\"shipping\",\"prefix\":null,\"middlename\":null,\"suffix\":null,\"company\":\"Imixs\"}],\"order_items\":[{\"item_id\":\"1\",\"parent_item_id\":null,\"sku\":\"2\",\"name\":\"Enterprise Servicevertrag\",\"qty_canceled\":\"0.0000\",\"qty_invoiced\":\"0.0000\",\"qty_ordered\":\"1.0000\",\"qty_refunded\":\"0.0000\",\"qty_shipped\":\"0.0000\",\"price\":\"200.0000\",\"base_price\":\"200.0000\",\"original_price\":\"200.0000\",\"base_original_price\":\"200.0000\",\"tax_percent\":\"0.0000\",\"tax_amount\":\"0.0000\",\"base_tax_amount\":\"0.0000\",\"discount_amount\":\"0.0000\",\"base_discount_amount\":\"0.0000\",\"row_total\":\"200.0000\",\"base_row_total\":\"200.0000\",\"price_incl_tax\":\"200.0000\",\"base_price_incl_tax\":\"200.0000\",\"row_total_incl_tax\":\"200.0000\",\"base_row_total_incl_tax\":\"200.0000\"}],\"order_comments\":[{\"is_customer_notified\":\"1\",\"is_visible_on_front\":\"0\",\"comment\":null,\"status\":\"pending\",\"created_at\":\"2014-07-23 19:56:32\"}]}}";
	
	
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
