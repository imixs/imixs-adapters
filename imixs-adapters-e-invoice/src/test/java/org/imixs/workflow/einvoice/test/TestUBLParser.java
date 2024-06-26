package org.imixs.workflow.einvoice.test;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
		try {
			// Use the class loader to load the XML file as a resource
			ClassLoader classLoader = TestUBLParser.class.getClassLoader();
			xmlInputStream = classLoader.getResourceAsStream(PATH_XML);
			DocumentBuilder documentBuilder;

			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = documentBuilder.parse(xmlInputStream);

			Assert.assertNotNull(doc);

		} catch (Exception e) {
			Assert.fail();

		} finally {
			if (xmlInputStream != null)
				xmlInputStream.close();

		}

	}

}