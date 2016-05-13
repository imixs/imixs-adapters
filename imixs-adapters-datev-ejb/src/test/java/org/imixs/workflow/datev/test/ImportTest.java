package org.imixs.workflow.datev.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.datev.DatevService;
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
public class ImportTest {

	@Spy
	private DatevService datevService;

	@Before
	public void setup() throws PluginException {
		// initialize @Mock annotations....
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * test the fieldlist of the first line of the file
	 */
	@Test
	public void testFieldList() {

		try {
			String filename = "/DTVF_Deb_Stamm_20160511_101110.csv";

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
			Assert.assertTrue(result.size() > 3);

		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}

	}

	/**
	 * Test - read entity
	 */
	@Test
	public void testReadFirstEntity() {

		try {
			String filename = "/DTVF_Deb_Stamm_20160511_101110.csv";

			URL url = this.getClass().getResource(filename);
			File testFile = new File(url.getFile());

			FileInputStream fis = new FileInputStream(testFile);
			DataInputStream in = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "ISO-8859-1"));

			// ignore first line
			br.readLine();
			// read the first line containing the field names
			String fieldnames = br.readLine();

			List<String> fieldList = datevService.parseFieldList(fieldnames);
			Assert.assertNotNull(fieldList);

			// read first line
			ItemCollection entity = datevService.readEntity(br.readLine(), fieldList);
			Assert.assertNotNull(entity);
			Assert.assertEquals("22222", entity.getItemValueString("_datev_konto"));
			Assert.assertEquals("Muster GmbH 1", entity.getItemValueString("_datev_Name_(Adressattyp_Unternehmen)"));

			// read second line
			entity = datevService.readEntity(br.readLine(), fieldList);
			Assert.assertNotNull(entity);
			Assert.assertEquals("33333", entity.getItemValueString("_datev_konto"));
			Assert.assertEquals("Muster GmbH 2", entity.getItemValueString("_datev_Name_(Adressattyp_Unternehmen)"));

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

}