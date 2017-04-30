package org.imixs.workflow.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Test class for QRGenerator. The generation of the qr code is based on the
 * library from [zxing QR-Library](https://github.com/zxing/zxing).
 * 
 * 
 * @author rsoika
 * @version 1.0
 */
public class QRTest {

	@Before
	public void setup() {
	}

	/**
	 * This is basic test to generate and read a qr-code. 
	 * 
	 * A qr-code image is generated and written to a file.
	 * 
	 * Next the file is read and the containing code is verified.
	 * 
	 * @throws WriterException
	 * @throws IOException
	 * @throws NotFoundException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testWriteRead() throws WriterException, IOException, NotFoundException {

		String qrCodeData = "Imixs-Workflow";
		String filePath = "src/test/resources/qr.png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		QrGenerator.writeQRCodeToStream(qrCodeData, charset, hintMap, 200, 200, out);
		File file = new File(filePath);
		FileOutputStream fop = new FileOutputStream(file);
		
		byte[] bytes = out.toByteArray();
		Assert.assertNotNull(bytes);
		Assert.assertTrue(bytes.length>10);
		fop.write(bytes);
		fop.flush();
		fop.close();

		// .writeQRCodeToFile(qrCodeData, filePath, charset, hintMap, 200, 200);
		System.out.println("QR Code image created successfully!");

		String verfiedCode= QrGenerator.readQRCode(filePath, charset, hintMap);
		Assert.assertNotNull(verfiedCode);
		Assert.assertEquals("Imixs-Workflow", verfiedCode);
		System.out.println("Data read from QR Code: " +verfiedCode);

		System.out.println("OK");

	}

}
