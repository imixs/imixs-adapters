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

/**
 * This test class test the DMSParser.
 * 
 * @author rsoika
 * 
 */
public class CoreParserTest {
	private static Logger logger = Logger.getLogger(CoreParserTest.class.getName());

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

		String fileName = "imixs-workflow.pdf";

		InputStream inputStream = getClass().getResourceAsStream("/" + fileName);

		byte[] fileData = streamToByteArray(inputStream);

		ItemCollection testCol = new ItemCollection();
		testCol.addFile(fileData, fileName, "");

		String content = DocumentCoreParser.parse(fileName, testCol.getFile(fileName));

		Assert.assertNotNull(content);

		Assert.assertTrue(content.contains("multi-level security concept"));
		logger.info(content);
	}
	
	/**
	 * Test parsing a MS doc file....
	 * 
	 * @throws Exception
	 */
	@Test
	public void parserTestDoc() throws Exception {

		String fileName = "imixs-workflow.doc";

		InputStream inputStream = getClass().getResourceAsStream("/" + fileName);

		byte[] fileData = streamToByteArray(inputStream);

		ItemCollection testCol = new ItemCollection();
		testCol.addFile(fileData, fileName, "");

		String content = DocumentCoreParser.parse(fileName, testCol.getFile(fileName));

		Assert.assertNotNull(content);

		Assert.assertTrue(content.contains("multi-level security concept"));
		logger.info(content);
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
