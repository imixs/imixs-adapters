package org.imixs.workflow.datev.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * This test shows how to create a zip file
 * 
 * @author rsoika
 * 
 */
public class TestZipEntry {

	/**
	 * write a zip file. The trick is to call ZipOutputStrem.closeEntry() after a
	 * file was written.
	 */
	@Test
	public void testSimple() {

		try {
			// create ZipOutputStream to write to the zip file
			FileOutputStream fos = new FileOutputStream("src/test/resources/simple.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			// add a new Zip Entry to the ZipOutputStream
			ZipEntry ze = new ZipEntry("test1.txt");
			zos.putNextEntry(ze);
			zos.write("Hello World".getBytes());
			// Close the zip entry to write to zip file
			zos.closeEntry();

			// create a second entry...
			ze = new ZipEntry("test2.txt");
			zos.putNextEntry(ze);
			zos.write("Hello Java".getBytes());
			// Close the zip entry to write to zip file
			zos.closeEntry();

			// Close resources
			zos.close();

			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}