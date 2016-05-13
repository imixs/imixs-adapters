package org.imixs.workflow.datev.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.imixs.workflow.datev.DatevService;
import org.imixs.workflow.exceptions.PluginException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Dieser Test testet den Inport einer datev datei
 * 
 * @author rsoika
 * 
 */
public class ImportTest {
 
	
	
	private DatevService datevService;

	@Before
	public void setup() throws PluginException {

		// mock EJBs and inject them into theworkflowService EJB
		datevService = Mockito.mock(DatevService.class);
		
		int i=1;
	}


	/**
	 * test the fieldlist of the first line of the file
	 */
	@Test
	public void testFieldList() { 
	 
		try {
			String filename="/DTVF_Deb_Stamm_20160511_101110.csv";
			
			URL url = this.getClass().getResource(filename);
			File testFile = new File(url.getFile());
	
			
			
			FileInputStream fis = new FileInputStream(testFile);
			DataInputStream in = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "ISO-8859-1"));

		
			// read the first line containing the field names
			String fieldnames = br.readLine();
			
			List<String> result = datevService.parseFieldList(fieldnames);

			br.close();
			Assert.assertNotNull(result);
		} catch (Exception e) {
	
			e.printStackTrace();
			Assert.fail();
		}
	
	}
	
	
	
	
/**
	
	 */
	@Test
	public void testPostXMLFileWithEmbeddedFile() { 
	
		try {
			
			URL url = this.getClass().getResource("/DTVF_Deb_Stamm_20160511_101110.csv");
			File testFile = new File(url.getFile());
	
			
			Assert.assertNotNull(testFile);
		} catch (Exception e) {
	
			e.printStackTrace();
			Assert.fail();
		}
	
	}

	

}