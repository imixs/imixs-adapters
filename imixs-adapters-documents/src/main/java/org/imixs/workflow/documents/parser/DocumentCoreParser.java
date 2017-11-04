package org.imixs.workflow.documents.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * The DocumentParser is used to parse the content for .pdf, .doc, .xls, .ppt or
 * .docx files.
 * 
 * The parser uses the Apache libraries POI and PDFBox.
 * 
 * @version 1.0
 * @author rsoika
 */
@Stateless
public class DocumentCoreParser {

	private static Logger logger = Logger.getLogger(DocumentCoreParser.class.getName());

	/**
	 * If the content is a .pdf, .doc, .xls, .ppt or .docx the content will be
	 * parsed and returned as a string.
	 * 
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("unused")
	public static String parse(String fileName, List<?> fileData) throws IOException {

		String result = null;

		String contentType = (String) fileData.get(0);
		byte[] data = (byte[]) fileData.get(1);

		if (data.length > 0) {

			// parse pdf file....
			if (fileName.toLowerCase().endsWith(".pdf")) {
				long l = System.currentTimeMillis();
				logger.fine("parsing pdf document '" + fileName + "'.....");
				result = parsePDF(fileName, data);
				logger.fine("parsing pdf document completed in " + (System.currentTimeMillis() - l) + "ms");
			}

			// pares ms-office file
			if (fileName.toLowerCase().endsWith(".doc") || fileName.toLowerCase().endsWith(".docx")) {
				long l = System.currentTimeMillis();
				logger.fine("parsing MS Office document '" + fileName + "'.....");
				result = parseMSDOC(fileName, data);
				logger.fine("parsing MS Office document completed in " + (System.currentTimeMillis() - l) + "ms");
			}
		}
		return result;
	}

	/**
	 * This method parses the text content of a pdf document.
	 * 
	 * @param fileName - name of pdf file
	 * @param pdfData - byte array of data of the pdf file 
	 * @throws IOException
	 *             If there is an error parsing the document.
	 */
	private static String parsePDF(String fileName, byte[] pdfData) throws IOException {
		PDFTextStripper stripper = null;
		RandomAccessRead source = new RandomAccessBuffer(pdfData);
		PDFParser parser = new PDFParser(source);
		parser.parse();
		PDDocument pdfDocument = parser.getPDDocument();

		try {
			// create a writer where to append the text content.
			StringWriter writer = new StringWriter();
			if (stripper == null) {
				stripper = new PDFTextStripper();
			}
			stripper.writeText(pdfDocument, writer);
			String contents = writer.getBuffer().toString();

			logger.fine("length of parsed file=" + contents.length());
			logger.fine(contents);

			return contents;

		} catch (InvalidPasswordException e) {
			// they didn't suppply a password and the default of "" was wrong.
			throw new IOException("Error: The document is encrypted and will not be indexed.", e);
		} finally {
			if (pdfDocument != null) {
				pdfDocument.close();
			}
		}
	}

	/**
	 * parse ms documents using the apache poi library.
	 * 
	 * 
	 * @param document
	 * @param pdfData
	 * @param fileName
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	private static String parseMSDOC(String fileName, byte[] pdfData) throws IOException {

		String contents = null;
		POIFSFileSystem fs = null;
		try {

			if (fileName.endsWith(".xls")) { // if the file is excel file
				ExcelExtractor ex = new ExcelExtractor(fs);
				contents = ex.getText(); // returns text of the excel file
			} else if (fileName.endsWith(".ppt")) { // if the file is power point file
				PowerPointExtractor extractor = new PowerPointExtractor(fs);
				contents = extractor.getText(); // returns text of the power point file

			} else if (fileName.endsWith(".doc")) {
				ByteArrayInputStream source = new ByteArrayInputStream(pdfData);
				// else for .doc file
				fs = new POIFSFileSystem(source);
				HWPFDocument doc = new HWPFDocument(fs);
				WordExtractor we = new WordExtractor(doc);
				contents = we.getText();// if the extension is .doc

				we.close();
			} else if (fileName.endsWith(".docx")) {
				ByteArrayInputStream source = new ByteArrayInputStream(pdfData);
				XWPFDocument doc = new XWPFDocument(source);
				XWPFWordExtractor we = new XWPFWordExtractor(doc);
				contents = we.getText();// if the extension is .doc

				we.close();
			}
		} catch (Exception e) {
			System.out.println("document file cant be indexed");
		}
		logger.fine("length of parsed file=" + contents.length());
		logger.fine(contents);

		return contents;
	}

}