package org.imixs.workflow.datev.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.datev.services.DatevImportService;
import org.imixs.workflow.datev.services.DatevWorkflowService;
import org.imixs.workflow.exceptions.PluginException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 * This test verifies a datev import file
 * 
 * @author rsoika
 * 
 */
public class TestDatevImportService {

	@Spy
	private DatevImportService datevService;

	@Before
	public void setup() throws PluginException {
		// initialize @Mock annotations....
		MockitoAnnotations.initMocks(this);
	}

	
	
	
	
	/**
	 * test the fieldlist of the first line of the file
	 */
	@Test
	public void testSKBeschrift() {

		try {
			String filename = "/DTVF_SKBeschrift_20180917_165234.csv";

			URL url = this.getClass().getResource(filename);
			File testFile = new File(url.getFile());

			FileInputStream fis = new FileInputStream(testFile);
			DataInputStream in = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "ISO-8859-1"));

			// ignore first line
			br.readLine();
			// read the first line containing the field names
			String fieldnames = br.readLine();

			List<String> result = datevService.parseFieldList(fieldnames);

			br.close();
			Assert.assertNotNull(result);
			Assert.assertTrue(result.size() >= 3);

		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}

	}

	
}