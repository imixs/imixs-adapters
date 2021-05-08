package org.imixs.workflow.wopi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This test validates a running WOPI Clients discovery endpoints.
 * 
 * @author rsoika
 */
public class WopiDiscoveryTest {

    static String BASE_URL = "https://localhost:9980/hosting/discovery";
    WopiAccessHandler wopiAccessHandler = null;
    private IntegrationTest integrationTest = new IntegrationTest(BASE_URL);

    /**
     * The setup method deploys the ticket workflow into the running workflow
     * instance.
     * 
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {

        // Assumptions for integration tests
        org.junit.Assume.assumeTrue(integrationTest.connected());

        try {
            wopiAccessHandler = new WopiAccessHandler();
            wopiAccessHandler.parseDiscoveryURL(BASE_URL);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * Test some basic endpoints form a OpenLibre Online instance
     * 
     */
    @Test
    public void testEndpoints() {

        Assert.assertNotNull(wopiAccessHandler);

        Assert.assertNotNull(wopiAccessHandler.getClientEndpointByFilename("test.odt"));
        Assert.assertNotNull(wopiAccessHandler.getClientEndpointByMimeType("application/msword"));
    }

}