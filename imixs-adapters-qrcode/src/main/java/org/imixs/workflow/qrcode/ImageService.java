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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * The ImageService is a jax-rs web service to generate QR Codes. The code is based on
 * the library from [zxing QR-Library](https://github.com/zxing/zxing).
 * 
 * 
 * 
 * @author rsoika
 */
@RequestScoped
@Path("/qr-code")
public class ImageService {

	@Inject 
	ImageCache cache;

	private static Logger logger = Logger.getLogger(ImageService.class.getName());

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	@Path("/code/{key}")
	public Response getQR(@PathParam("key") String key) {
		byte[] content = null;

		// try to get conent form cache
		content = cache.getQrCode(key); 
		if (content == null) {
			try {
				String charset = "UTF-8"; // or "ISO-8859-1"
				Map hintMap = new HashMap();
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				Generator.writeQRCodeToStream(key, charset, hintMap, 200, 200, out);
				content = out.toByteArray();
				// cache content
				cache.putQrCode(key, content);
			} catch (WriterException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
