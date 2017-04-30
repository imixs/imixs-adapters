package org.imixs.workflow.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * The ImageService is a jax-rs web service to generate QR Codes. The code is
 * based on the library from [zxing QR-Library](https://github.com/zxing/zxing).
 * 
 * 
 * 
 * @author rsoika
 */
@RequestScoped
@Path("/qr-code")
public class QrService {

	@Inject
	QrCache cache;
	final static int DEFAULT_SIZE = 200;

	private static Logger logger = Logger.getLogger(QrService.class.getName());

	/**
	 * Returns the QR-Code image. The key is a url encoded query param.
	 * 
	 * The query param size is optional and defines the image size
	 * 
	 * @param key
	 *            - string with the qr-code
	 * @param size
	 *            - integer, size in pixel
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	@Path("/")
	public Response getQR(@QueryParam("key") String key, @QueryParam("size") int size) {
		byte[] content = null;

		if (size <= 0) {
			size = DEFAULT_SIZE;
		}
		// try to get conent form cache
		content = cache.getQrCode(key);
		if (content == null) {
			try {
				String charset = "UTF-8"; // or "ISO-8859-1"
				Map hintMap = new HashMap();
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

				hintMap.put(EncodeHintType.MARGIN, 0);

				ByteArrayOutputStream out = new ByteArrayOutputStream();
				QrGenerator.writeQRCodeToStream(key, charset, hintMap, size, size, out);
				content = out.toByteArray();
				// cache content
				cache.putQrCode(key, content);
			} catch (WriterException | IOException e) {
				logger.severe("getQR failed: " + e.getMessage());
			}

		} else {
			logger.fine("got qr-code '" + key + "' from cache");
		}

		if (content != null) {
			ResponseBuilder response = Response.ok(content, "image/png");
			// define filename form the hash code
			response.header("Content-Disposition", "filename=" + content.hashCode() + ".png");
			return response.build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
