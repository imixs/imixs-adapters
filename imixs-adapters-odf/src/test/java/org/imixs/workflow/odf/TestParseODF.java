package org.imixs.workflow.odf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.incubator.search.TextNavigation;
import org.odftoolkit.odfdom.incubator.search.TextSelection;
import org.odftoolkit.odfdom.pkg.OdfElement;


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

				TextSelection item1 =  search1.next();

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
	 * 
	 */
	@Test
	public void testFindReplaceParagraph() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/test-document.odt");

			OdfTextDocument odt = (OdfTextDocument) OdfDocument.loadDocument(inputStream);
 
			TextNavigation textNav;
			int count=0;
			textNav = new TextNavigation("content|some", odt);
			while (textNav.hasNext()) {
				logger.info("..found match!");

				TextSelection selection = textNav.next();

				selection.replaceWith("Just a Example");
				OdfElement paragraph = selection.getContainerElement();
				String content=paragraph.getTextContent();

				Assert.assertTrue(content.contains("Just a Example"));

				
				count++;
			}

			// total count of paragraphs
			Assert.assertEquals(2,count);
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

	@Test
	public void testFindAndDeleteWords() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/test-document.odt");

			OdfTextDocument odt = (OdfTextDocument) OdfDocument.loadDocument(inputStream);

			TextNavigation textNav;
			int count=0;
			textNav = new TextNavigation("content|some", odt);
			while (textNav.hasNext()) {
				logger.info("..found match!");
				TextSelection selection = textNav.next();

				selection.cut();
				
				count++;
			}

			// total count of words
			Assert.assertEquals(2,count);
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


	@Test
	public void tesFindTextRegex() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/test-document.odt");

			OdfTextDocument odt = (OdfTextDocument) OdfDocument.loadDocument(inputStream);
			TextNavigation textNav;

			int count=0;
			textNav = new TextNavigation("content|some", odt);
			while (textNav.hasNext()) {
				logger.info("..found match!");
				count++;

				TextSelection selection =textNav.next();

				logger.info("...Position=" + selection.getIndex());
				logger.info("...Text=" + selection.getText());
				
				if ("some".equals(selection.getText())) {
					Assert.assertEquals(36, selection.getIndex());					
					selection.replaceWith("more");
				}

				if ("content".equals(selection.getText())) {
					Assert.assertEquals(41, selection.getIndex());					
					selection.replaceWith("text");
				}
			}

			// total count
			Assert.assertEquals(2,count);

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





	/**
	 * 
	 */
	@Test
	public void testFindReplaceSpreadSheet() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/test-document.ods");

			OdfSpreadsheetDocument ods = (OdfSpreadsheetDocument) OdfDocument.loadDocument(inputStream);
 
		
			OdfTable tbl = ods.getTableByName("Tabelle1");
			OdfTableCell cell = tbl.getCellByPosition("B3");
			//cell.setStringValue("8");
			cell.setDoubleValue(801.0);

			tbl.getCellByPosition("B5").setDoubleValue(806.0);
			tbl.getCellByPosition("D5").setDoubleValue(856.0);
			
			
			// write result back...
			  try (FileOutputStream output = new FileOutputStream("src/test/resources/output/test-document-output.ods")) {
				  
				  ods.save(output);
				  
		        } catch ( IOException e) {
		            e.printStackTrace();
		        }
			  
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}

