package org.imixs.workflow.documents;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.WorkflowMockEnvironment;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * This test class tests PDFXMLPlugin
 * 
 * @author rsoika
 * 
 */
public class PDFXMLPluginTest {
	private static Logger logger = Logger.getLogger(PDFXMLPluginTest.class.getName());

	PDFXMLPlugin plugin = null;
	ItemCollection event = null;

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
	 * Test extracting an embedded xml file from a PDF file using the pdfBox
	 * library.
	 * 
	 */
	@Test
	public void parserTestPdf() {
		ItemCollection workitem = null;
		try {
			event = workflowMockEnvironment.getModel().getEvent(100, 10);
			// Build a Document....
			workitem = new ItemCollection();
			// load the example pdf ..
			String fileName = "ZUGFeRD/20160504_MX16124-000005_001-001_Muster.pdf";
			InputStream inputStream = getClass().getResourceAsStream("/" + fileName);
			byte[] fileData = PDFXMLPlugin.streamToByteArray(inputStream);
			workitem.addFile(fileData, fileName, "");

			byte[] xmldata = PDFXMLPlugin.getXMLFile(workitem, ".pdf");

			Assert.assertNotNull(xmldata);
			
			// show first 100 characters from xml.....
			String xml=new String(xmldata);
			logger.info(xml.substring(0, 100) + "...");

		} catch (PluginException | ModelException | IOException e) {

			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(workitem);

	}

	/**
	 * Test the regex for pdf file names.
	 */
	@Test
	public void testRegex() {
		Assert.assertTrue(Pattern.compile(".pdf").matcher("sample.pdf").find());
		Assert.assertTrue(Pattern.compile(".[pP][dD][fF]").matcher("sample.PDF").find());
	}

}
