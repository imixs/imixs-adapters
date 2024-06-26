package org.imixs.workflow.einvoice.test;

import java.io.IOException;
import java.io.InputStream;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.einvoice.UBLParser;
import org.imixs.workflow.exceptions.PluginException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 * This test verifies a datev import file
 * 
 * @author rsoika
 * 
 */
public class TestUBLParser {

	@Before
	public void setup() throws PluginException {

	}

	/**
	 * XSL Transformation for SEPA Standard file
	 * 
	 * @throws IOException
	 */
	@Test
	public void testDefault() throws IOException {
		String PATH_XML = "xml/XRechnung-3.0.1.xml";
		InputStream xmlInputStream = null;
		ItemCollection workitem = new ItemCollection();
		try {
			// Use the class loader to load the XML file as a resource
			ClassLoader classLoader = TestUBLParser.class.getClassLoader();
			xmlInputStream = classLoader.getResourceAsStream(PATH_XML);

			Document doc = UBLParser.parseInputStream(xmlInputStream);

			Assert.assertNotNull(doc);

			UBLParser.parseItems(doc, workitem);

			Assert.assertEquals("RE-2024-03", workitem.getItemValueString("invoice.number"));

		} catch (Exception e) {
			Assert.fail();

		} finally {
			if (xmlInputStream != null)
				xmlInputStream.close();

		}

	}

}