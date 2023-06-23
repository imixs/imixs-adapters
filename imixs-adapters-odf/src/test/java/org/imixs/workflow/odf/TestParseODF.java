package org.imixs.workflow.odf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.incubator.search.TextNavigation;
import org.odftoolkit.odfdom.incubator.search.TextSelection;

/**
 * Simple test class just to evaluate the ODF library
 * 
 * @author rsoika
 * @version 1.0
 */
public class TestParseODF {
	private static Logger logger = Logger.getLogger(TestParseODF.class.getName());

	/**
	 * 
	 */
	@Test
	public void tesParseODF() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/test-document.odt");

			OdfTextDocument odt = (OdfTextDocument) OdfDocument.loadDocument(inputStream);

			TextNavigation search1;

			search1 = new TextNavigation("content|some", odt);
			while (search1.hasNext()) {
				logger.info("..found match!");

				TextSelection item1 = (TextSelection) search1.getSelection();

				logger.info("...Position=" + item1.getIndex());
				logger.info("...Text=" + item1.getText());
				
				if ("some".equals(item1.getText())) {
					Assert.assertEquals(36, item1.getIndex());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * Just a test for a matching problem....
	 */
	@Test
	public void tesMatcher() {
		 Pattern mPattern = Pattern.compile("\\[company\\.name\\]");
		Matcher matcher = mPattern.matcher("Imixs GmbH");

		int index=0;

		// // start from the end index of the selected item
		// if (matcher.find(index + selected.getText().length())) {
		// 	// here just consider \n\r\t occupy one char
		// 	nextIndex = matcher.start();
		// 	int eIndex = matcher.end();
		// 	mCurrentText = content.substring(nextIndex, eIndex);
		// }
	}
	/**
	 * 
	 */
	@Test
	@Ignore
	public void tesReplaceContentODF() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/test-document.odt");

			OdfTextDocument odt = (OdfTextDocument) OdfDocument.loadDocument(inputStream);

			TextNavigation search1;

			search1 = new TextNavigation("content|some", odt);
			while (search1.hasNext()) {
				logger.info("..found match!");

				TextSelection item1 = (TextSelection) search1.getSelection();

				logger.info("...Position=" + item1.getIndex());
				logger.info("...Text=" + item1.getText());
				
				if ("some".equals(item1.getText())) {
					Assert.assertEquals(36, item1.getIndex());
					
					item1.replaceWith("more");
				}
			}

			// write result back...
			  try (FileOutputStream output = new FileOutputStream("src/test/resources/output/test-document-output.odt")) {
				  
				  odt.save(output);
				  
		        } catch ( IOException e) {
		            e.printStackTrace();
		        }
			  
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
