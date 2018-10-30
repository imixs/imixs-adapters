package org.imixs.workflow.documents;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode;
import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;
import org.apache.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
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

	@Test
	public void simpleTestPdf() throws Exception {

		String fileName = "20160504_MX16124-000005_001-001_Muster.pdf";
		InputStream inputStream = getClass().getResourceAsStream("/" + fileName);
		PDDocument doc = new PDDocument();
		try {
			doc = PDDocument.load(inputStream);

			Assert.assertNotNull(doc);

			COSDocument cosDoc = doc.getDocument();

			List<COSObject> objects = cosDoc.getObjects();

			Assert.assertNotNull(objects);

			
			
//			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//			stripper.setSortByPosition(true);
//
//			PDFTextStripper tStripper = new PDFTextStripper();
//
//			String pdfFileInText = tStripper.getText(doc);
//				String lines[] = pdfFileInText.split("\\r?\\n");
//			for (String line : lines) {
//				System.out.println(line);
//			}
//			
//			
			
			
			 PDDocumentNameDictionary namesDictionary = new PDDocumentNameDictionary( doc.getDocumentCatalog());
	            PDEmbeddedFilesNameTreeNode efTree = namesDictionary.getEmbeddedFiles();
	            if (efTree != null) {
	                Map<String, PDComplexFileSpecification> names = efTree.getNames();
	                if (names != null) {
	                    extractFiles(names);
	                } else {
	                    List<PDNameTreeNode<PDComplexFileSpecification>> kids = efTree.getKids();
	                    for (PDNameTreeNode<PDComplexFileSpecification> node : kids) {
	                        names = node.getNames();
	                        extractFiles(names);
	                    }
	                }
	            }
			
			
			
			
			
			
			
			

		} finally {
			if (doc != null) {
				doc.close();
			}
		}

	}

	
	 private static void extractFiles(Map<String, PDComplexFileSpecification> names) throws IOException {
	        for (Map.Entry<String, PDComplexFileSpecification> entry : names.entrySet()) {
	            PDComplexFileSpecification fileSpec = entry.getValue();
	            String filename = fileSpec.getFile();
	            PDEmbeddedFile embeddedFile = getEmbeddedFile(fileSpec);
	            extractFile(filename, embeddedFile);
	        }
	    }
	 
	   private static void extractFile(String filename, PDEmbeddedFile embeddedFile) throws IOException {
	     //  String embeddedFilename = OUTPUT_DIR + filename;
	        File file = new File(filename);
	        System.out.println("Writing " + filename);
	        
	       
	        try (FileOutputStream fos = new FileOutputStream(file)) {
	            fos.write(embeddedFile.toByteArray());
	        }
	    }
	   
	 private static PDEmbeddedFile getEmbeddedFile(PDComplexFileSpecification fileSpec) {
	        PDEmbeddedFile embeddedFile = null;
	        if (fileSpec != null) {
	            embeddedFile = fileSpec.getEmbeddedFileUnicode();
	            if (embeddedFile == null) {
	                embeddedFile = fileSpec.getEmbeddedFileDos();
	            }
	            if (embeddedFile == null) {
	                embeddedFile = fileSpec.getEmbeddedFileMac();
	            }
	            if (embeddedFile == null) {
	                embeddedFile = fileSpec.getEmbeddedFileUnix();
	            }
	            if (embeddedFile == null) {
	                embeddedFile = fileSpec.getEmbeddedFile();
	            }
	        }
	        return embeddedFile;
	    }
}
