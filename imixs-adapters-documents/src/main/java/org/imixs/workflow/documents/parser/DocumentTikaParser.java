package org.imixs.workflow.documents.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 * The DocumentParser is used to parse the content for .pdf, .doc, .xls, .ppt or
 * .docx files.
 * 
 * The parser uses the Apache libraries POI and PDFBox.
 * 
 * @version 1.0
 * @author rsoika
 */
public class DocumentTikaParser {

	private static Logger logger = Logger.getLogger(DocumentTikaParser.class.getName());

	/**
	 * If the content is a .pdf, .doc, .xls, .ppt or .docx the content will be
	 * parsed and returned as a string.
	 * 
	 * @throws IOException
	 * @throws TikaException
	 * @throws SAXException
	 * 
	 */
	@SuppressWarnings("unused")
	public static String parse(String fileName, List<?> fileData) throws IOException, SAXException, TikaException {

		String result = null;

		String contentType = (String) fileData.get(0);
		byte[] data = (byte[]) fileData.get(1);

		if (data.length > 0) {
			logger.fine("parsing document content...");
			AutoDetectParser parser = new AutoDetectParser();
			BodyContentHandler handler = new BodyContentHandler();
			Metadata metadata = new Metadata();

			ByteArrayInputStream bis = new ByteArrayInputStream(data);

			parser.parse(bis, handler, metadata);

			return handler.toString();

		}
		return null;

	}

}