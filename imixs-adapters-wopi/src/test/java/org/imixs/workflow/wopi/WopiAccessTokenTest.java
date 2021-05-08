package org.imixs.workflow.wopi;

import org.imixs.jwt.JWTException;
import org.junit.Assert;
import org.junit.Test;

/**
 * This test validates a running WOPI Clients discovery endpoints.
 * 
 * @author rsoika
 */
public class WopiAccessTokenTest {

    WopiAccessHandler wopiAccessHandler = null;
  
    /**
     * Test some basic endpoints form a OpenLibre Online instance
     * 
     */
    @Test
    public void testToken() { 

        wopiAccessHandler = new WopiAccessHandler();
        wopiAccessHandler.init();
        try {
            String token=wopiAccessHandler.generateAccessToken();
            
            Assert.assertTrue(wopiAccessHandler.isValidAccessToken(token));
        } catch (JWTException e) {
            
            e.printStackTrace();
            Assert.fail();
        }
        
       
    }

}