package org.imixs.workflow.documents;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.documents.parser.DocumentTikaParser;
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
public class TikaParserTest {
	private static Logger logger = Logger.getLogger(TikaParserTest.class.getName());

	@Before
	public void setup() {
	}

	@After
	public void teardown() {

	}

	/**
	 * Test parsing a doc file....
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

		String content = DocumentTikaParser.parse(fileName, testCol.getFile(fileName));

		Assert.assertNotNull(content);
		logger.info(content);
		Assert.assertTrue(content.contains("multi-level security concept"));

	}
	
	/**
	 * Test parsing a doc file....
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

		String content = DocumentTikaParser.parse(fileName, testCol.getFile(fileName));

		Assert.assertNotNull(content);
		logger.info(content);
		Assert.assertTrue(content.contains("multi-level security concept"));

	}
	
	/**
	 * Test parsing a open office file....
	 * 
	 * @throws Exception
	 */
	@Test
	public void parserTestodt() throws Exception {

		String fileName = "imixs-workflow.odt";

		InputStream inputStream = getClass().getResourceAsStream("/" + fileName);

		byte[] fileData = streamToByteArray(inputStream);

		ItemCollection testCol = new ItemCollection();
		testCol.addFile(fileData, fileName, "");

		String content = DocumentTikaParser.parse(fileName, testCol.getFile(fileName));

		Assert.assertNotNull(content);
		logger.info(content);
		Assert.assertTrue(content.contains("multi-level security concept"));

	}
	
	

	/**
	 * This is for testing the Tika core api without any wrapper classes.
	 * 
	 * @throws Exception
	 */
	@Test
	public void nativeTikaTest() throws Exception {

		String fileName = "imixs-workflow.doc";
		String content = null;

		AutoDetectParser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		try (InputStream stream = getClass().getResourceAsStream("/" + fileName)) {
			parser.parse(stream, handler, metadata);
			content = handler.toString();
		}

		Assert.assertNotNull(content);
		logger.info(content);
		Assert.assertTrue(content.contains("multi-level security concept"));

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
