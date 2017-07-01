package org.imixs.workflow.archive.hadoop;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.imixs.workflow.services.rest.RestClient;
import org.imixs.workflow.xml.DocumentCollection;

public class HDFSClient {
	
	private final static Logger logger = Logger.getLogger(HDFSClient.class
			.getName());

	
	public int postCollection(String url, DocumentCollection aEntityCol) throws IOException {
		
		URL obj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn.setReadTimeout(5000);
		conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
		conn.addRequestProperty("User-Agent", "Mozilla");
		conn.addRequestProperty("Referer", "google.com");

		System.out.println("Request URL ... " + url);

		boolean redirect = false;

		// normally, 3xx is redirect
		int status = conn.getResponseCode();
		if (status != HttpURLConnection.HTTP_OK) {
			if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM
					|| status == HttpURLConnection.HTTP_SEE_OTHER)
				redirect = true;
		}

		logger.fine("Response Code ... " + status);

		
		return status;
	}
}
