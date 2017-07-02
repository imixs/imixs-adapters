package org.imixs.workflow.archive.hadoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.apache.hadoop.security.authentication.client.AuthenticatedURL;
import org.apache.hadoop.security.authentication.client.AuthenticationException;
import org.apache.hadoop.security.authentication.client.Authenticator;
import org.apache.hadoop.security.authentication.client.AuthenticatedURL.Token;
import org.imixs.workflow.services.rest.RestClient;
import org.imixs.workflow.xml.DocumentCollection;

/**
 * This client class can be used to access the Hadoop WebHDFS Rest API. The
 * client provides methods to write and read data streams. The client is used by
 * the Imixs-Archie HadoopService class to store and restore Imixs Document
 * Collections.
 * 
 * The client reads configuration settings form the property file
 * 'imixs-hadoop.properties'. The properie file provides the following
 * properties
 * <ul>
 * <li>hadoop.hdfs.baseURL</li>
 * <li>hadoop.hdfs.defaultPrincipal</li>
 * </ul>
 * 
 * In the first version the client supports only the pseudo authentication.
 * Kerberos authentication will be implemented in version 2.0
 * 
 * 
 * 
 * @author rsoika
 * @version 1.0
 */
public class HDFSClient {

	public final static String BASE_URL = "hadoop.hdfs.baseURL";
	public final static String DEFUALT_PRINCIPAL = "hadoop.hdfs.defaultPrincipal";
	private final static Logger logger = Logger.getLogger(HDFSClient.class.getName());

	private String principal = null;
	private String hadoopBaseUrl = null;
	private Properties properties = null;

	/**
	 * Constructor for pseudo authentication. The method expects only a
	 * pricipal, all other properties are read from the imixs-hadoop.properties
	 * file
	 * 
	 * @param httpfsUrl
	 * @param principal
	 */
	public HDFSClient(String principal) {

		init();

		this.principal = principal;
		if (principal == null || principal.isEmpty()) {
			principal = properties.getProperty(DEFUALT_PRINCIPAL, "root");
			logger.warning("principal not set, using default principal 'root'");
		}

	}

	/**
	 * The init method read the imixs-hadoop.properties file and set the default values
	 */
	void init() {
		loadProperties();
		hadoopBaseUrl = properties.getProperty(BASE_URL, "http://localhost:50070/webhdfs/v1");
		// if url ends with / cut the last char
		if (hadoopBaseUrl.endsWith("/")) {
			hadoopBaseUrl = hadoopBaseUrl.substring(0, hadoopBaseUrl.length() - 1);
		}

	}

	/**
	 * This method transfers the data of an input stream to the hadoop file
	 * system by using the <b>CREATE</b> method from the WebHDFS API.
	 * 
	 * To create a new file, 2 requests are needed:
	 * 
	 * 1.) request the datanode post URL
	 * 
	 * curl -i -X PUT -T test.txt
	 * "http://localhost:50070/webhdfs/v1/test.txt?user.name=root&op=CREATE"
	 * 
	 * 2.) transfere the file
	 * 
	 * curl -i -X PUT -T /home/rsoika/Downloads/test.txt
	 * "http://my-hadoop-cluster.local:50075/webhdfs/v1/test.txt?op=CREATE&user.name=root&namenoderpcaddress=localhost:8020&createflag=&createparent=true&overwrite=false"
	 * 
	 * 
	 * 
	 * @param path
	 * @param is
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws JAXBException
	 * @throws AuthenticationException
	 */
	public String putData(String path, DocumentCollection aEntityCol)
			throws MalformedURLException, IOException, JAXBException {
		String encoding = "UTF-8";
		String resp = null;

		String redirectUrl = null;

		if (!path.startsWith("/")) {
			path = "/" + path;
		}

		String url = hadoopBaseUrl + path + "?user.name=" + principal + "&op=CREATE";
		URL obj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

		conn.setRequestMethod("PUT");
		conn.setInstanceFollowRedirects(false);
		conn.connect();
		logger.info("Location:" + conn.getHeaderField("Location"));
		resp = getJSONResult(conn, true);
		if (conn.getResponseCode() == 307) {
			redirectUrl = conn.getHeaderField("Location");
			conn.disconnect();

			// open connection on new location...
			conn = (HttpURLConnection) new URL(redirectUrl).openConnection();

			conn.setRequestMethod("PUT");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/octet-stream");

			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(DocumentCollection.class);
			Marshaller m = context.createMarshaller();
			m.marshal(aEntityCol, writer);

			// compute length
			conn.setRequestProperty("Content-Length", "" + Integer.valueOf(writer.toString().getBytes().length));

			PrintWriter printWriter = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), encoding)));

			printWriter.write(writer.toString());
			printWriter.close();
			String sHTTPResponse = conn.getHeaderField(0);

			resp = getJSONResult(conn, false);
			conn.disconnect();
		}

		return resp;
	}

	@Deprecated
	public static synchronized Token generateToken(String srvUrl, String princ, String passwd) {
		AuthenticatedURL.Token newToken = new AuthenticatedURL.Token();
		Authenticator authenticator = new PseudoAuthenticator2(princ);
		try {

			String spec = MessageFormat.format("/webhdfs/v1/?op=GETHOMEDIRECTORY&user.name={0}", princ);
			HttpURLConnection conn = new AuthenticatedURL(authenticator).openConnection(new URL(new URL(srvUrl), spec),
					newToken);

			conn.connect();

			conn.disconnect();

		} catch (Exception ex) {
			logger.severe(ex.getMessage());
			logger.severe("[" + princ + ":" + passwd + "]@" + srvUrl);
			// WARN
			// throws MalformedURLException, IOException,
			// AuthenticationException, InterruptedException
		}

		return newToken;
	}

	/**
	 * Returns the connection result in JSON
	 * 
	 * @param conn
	 * @param input
	 * @return
	 * @throws IOException
	 */
	private static String getJSONResult(HttpURLConnection conn, boolean input) throws IOException {
		StringBuffer sb = new StringBuffer();
		if (input) {
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			is.close();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", conn.getResponseCode());
		result.put("mesg", conn.getResponseMessage());
		result.put("type", conn.getContentType());
		result.put("data", sb);
		//
		// Convert a Map into JSON string.
		//
		return HadoopUtil.getJSON(result);

	}

	/**
	 * loads a imixs-hadoop.property file
	 * 
	 * (located at current threads classpath)
	 * 
	 */
	private void loadProperties() {
		properties = new Properties();
		try {
			properties
					.load(Thread.currentThread().getContextClassLoader().getResource("imixs.properties").openStream());
		} catch (Exception e) {
			logger.severe("PropertyService unable to find imixs-hadoop.properties in current classpath");
			e.printStackTrace();
			properties = new Properties();
		}

	}

}
