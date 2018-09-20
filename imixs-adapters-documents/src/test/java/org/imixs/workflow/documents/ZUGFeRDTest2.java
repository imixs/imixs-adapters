package org.imixs.workflow.documents;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.documents.parser.DocumentCoreParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.konik.PdfHandler;
import io.konik.zugferd.Invoice;

/**
 * This test class test the DMSParser with a ZUGFeRD invoice.
 * 
 * @author rsoika
 * 
 */
public class ZUGFeRDTest2 {
	private static Logger logger = Logger.getLogger(ZUGFeRDTest2.class.getName());

	@Before
	public void setup() {
	}

	@After
	public void teardown() {

	}

	/**
	 * Test parsing a pdf file....
	 * 
	 * @throws Exception
	 */
	@Test
	public void parserTestPdf() throws Exception {

		String fileName = "20160504_MX16124-000005_001-001_Muster.pdf";

		PdfHandler handler = new PdfHandler();
		InputStream inputZugferdPdfStream = getClass().getResourceAsStream("/" + fileName);
		Invoice invoice = handler.extractInvoice(inputZugferdPdfStream);

		Assert.assertNotNull(invoice);

	}

	private static byte[] streamToByteArray(InputStream ins) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] byteBuffer = new byte[1024];
		int len;
		while ((len = ins.read(byteBuffer)) > -1) {
			baos.write(byteBuffer, 0, len);
		}
		baos.flush();
		return baos.toByteArray();
	}
}
