package org.imixs.workflow.wopi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * This JUnit Helper Class is used to skip integration tests.
 * <p>
 * This class tests if a connection to the rest service end-point can be
 * established.
 * 
 * @author rsoika 
 * @version 1.0
 */
public class IntegrationTest {
 
    private String serviceEndpoint;
    private final static Logger logger = Logger.getLogger(IntegrationTest.class.getName());

    public IntegrationTest(String uri) {
        this.serviceEndpoint = uri;
    }

    /**
     * Test a http/https connection
     * <p>
     * We install a trust manager to accept insecure ssl certificates
     * 
     * @return
     */
    public boolean connected() {
        try {
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Now we can access an https URL without having the certificate in the
            // truststore
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(serviceEndpoint).openConnection();
            urlConnection.connect();
            return true;
        } catch (IOException | KeyManagementException | NoSuchAlgorithmException e) {
            logger.info("Integration Test skipped! " + e.getMessage());
            return false;
        }
    }

    /**
     * Helper class to Create a trust manager that does not validate certificate
     * chains
     **/
    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }
    } };
}
