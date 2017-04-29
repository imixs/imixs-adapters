package org.imixs.workflow.qrcode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * Generator generates and reads QR Codes. The code is based on the library from
 * [zxing QR-Library](https://github.com/zxing/zxing).
 * 
 * 
 * 
 * @author rsoika
 *
 */
public class Generator {
	
	public static String IMAGE_FORMAT="PNG";

	 

	/**
	 * returns a byte array
	 * 
	 * @param qrCodeData
	 * @param charset
	 * @param hintMap
	 * @param qrCodeheight
	 * @param qrCodewidth
	 * @param out 
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void writeQRCodeToStream(String qrCodeData, String charset, Map<EncodeHintType, ?>  hintMap,
			int qrCodeheight, int qrCodewidth, OutputStream out) throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);

		MatrixToImageWriter.writeToStream(matrix, IMAGE_FORMAT, out);

	}

	public static String readQRCode(String filePath, String charset, Map<DecodeHintType, ?> hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(
				new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
		return qrCodeResult.getText();
	}
}
