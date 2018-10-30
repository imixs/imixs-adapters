package org.imixs.workflow.documents;

import static org.mockito.Mockito.when;

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
import org.imixs.workflow.engine.WorkflowMockEnvironment;
import org.imixs.workflow.engine.plugins.SplitAndJoinPlugin;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.sun.xml.txw2.Document;

import io.konik.PdfHandler;
import io.konik.zugferd.Invoice;

/**
 * This test class tests PDFXMLPlugin
 * 
 * @author rsoika
 * 
 */
public class PDFXMLPluginTest {
	private static Logger logger = Logger.getLogger(PDFXMLPluginTest.class.getName());

	PDFXMLPlugin plugin=null;
	ItemCollection event=null;
	
	
	/**
	 * We use the provided test workflow model form the AbstractWorkflowServiceTest
	 * 
	 * @throws ModelException
	 */
	WorkflowMockEnvironment workflowMockEnvironment;

	
	
	
	@Before
	public void setup() throws PluginException, ModelException {
		
		workflowMockEnvironment = new WorkflowMockEnvironment();
		workflowMockEnvironment.setModelPath("/bpmn/TestZUGFeRD.bpmn");

		workflowMockEnvironment.setup();

		// mock abstract plugin class for the plitAndJoinPlugin
		plugin = Mockito.mock(PDFXMLPlugin.class, Mockito.CALLS_REAL_METHODS);
		when(plugin.getWorkflowService()).thenReturn(workflowMockEnvironment.getWorkflowService());
		
		
	//	when(plugin.getWorkflowService().evalWorkflowResult(Mockito.any(ItemCollection.class),Mockito.any(ItemCollection.class))).thenCallRealMethod();
		
		
		
		try {
			plugin.init(workflowMockEnvironment.getWorkflowContext());
		} catch (PluginException e) {

			e.printStackTrace();
		}

		
		
		 
		
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
		ItemCollection workitem=null;
		try {
			event = workflowMockEnvironment.getModel().getEvent(100, 10);

			//Build a Document....
			workitem=new ItemCollection();

			
			// load the exampl pdf ..
			String fileName = "ZUGFeRD/20160504_MX16124-000005_001-001_Muster.pdf";
			InputStream inputStream = getClass().getResourceAsStream("/" + fileName);
			byte[] fileData = streamToByteArray(inputStream);
			workitem.addFile(fileData, fileName, "");
			
			
			workitem=plugin.run(workitem, event);
		} catch (PluginException e) {

			e.printStackTrace();
			Assert.fail();
		}

		
		
		Assert.assertNotNull(workitem);

	}

	
	
	/* Helper method */
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
