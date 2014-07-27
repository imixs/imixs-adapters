package org.imixs.workflow.magento.test;

import java.util.Scanner;

import org.imixs.workflow.magento.MagentoApi;
import org.junit.Before;
import org.junit.Test;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * Dieser test testet die Magento Schnittstelle
 * 
 * @see: https://github.com/fernandezpablo85/scribe-java
 * 
 */
public class TestSimple {
	static final String MAGENTO_API_KEY = "9abde0f96ce9a388994d464f5dfa81b3";
	static final String MAGENTO_API_SECRET = "34aa69d7ee0782f9a6f04ac9cdb3d11e";
	static final String MAGENTO_REST_API_URL = "http://localhost/magento/api/rest";
	MagentoApi magentoApi = null;

	@Before
	public void setup() {

		magentoApi = new MagentoApi();
		magentoApi.setAdminAPI(true);
		magentoApi.setBaseURL("http://localhost/magento/index.php/");

	}

	/**
	 * This Test checks the Magento Consumer API
	 * 
	 */
	@Test
	public void testConsumerAPI() {
	
		magentoApi.setAdminAPI(false);
	
		OAuthService service = new ServiceBuilder().provider(magentoApi)
				.apiKey(MAGENTO_API_KEY).apiSecret(MAGENTO_API_SECRET).build();
	
		Scanner in = new Scanner(System.in);
	
		System.out.println("=== Mage v1.7.0.2 OAuth Workflow ===");
		System.out.println();
	
		// Obtain the Request Token
		System.out.println("Fetching the Request Token...");
		Token requestToken = service.getRequestToken();
		System.out.println("Got the Request Token!");
		System.out.println();
	
		System.out.println("Now go and authorize Scribe here:");
		System.out.println(service.getAuthorizationUrl(requestToken));
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		Verifier verifier = new Verifier(in.nextLine());
		System.out.println();
	
		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		Token accessToken = service.getAccessToken(requestToken, verifier);
		System.out.println("Got the Access Token!");
		System.out.println("(if your curious it looks like this: "
				+ accessToken + " )");
		System.out.println();
	
		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		OAuthRequest request = new OAuthRequest(Verb.GET, MAGENTO_REST_API_URL
				+ "/stockitems?type=rest");
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getBody());
	
		System.out.println();
		System.out
				.println("Thats it man! Go and build something awesome with Scribe! :)");
	}

	/**
	 * This Test checks the Magento Admin API
	 * 
	 */
	@Test
	public void testAdminAPI() {

		OAuthService service = new ServiceBuilder().provider(magentoApi)
				.apiKey(MAGENTO_API_KEY).apiSecret(MAGENTO_API_SECRET).build();

		Scanner in = new Scanner(System.in);

		System.out.println("=== Mage v1.7.0.2 OAuth Workflow ===");
		System.out.println();

		// Obtain the Request Token
		System.out.println("Fetching the Request Token...");
		Token requestToken = service.getRequestToken();
		System.out.println("Got the Request Token!");
		System.out.println();

		System.out.println("Now go and authorize Scribe here:");
		System.out.println(service.getAuthorizationUrl(requestToken));
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		Verifier verifier = new Verifier(in.nextLine());
		System.out.println();

		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		Token accessToken = service.getAccessToken(requestToken, verifier);
		System.out.println("Got the Access Token!");
		System.out.println("...if your curious it looks like this: ");
		System.out.println("   key="+accessToken.getToken());
		System.out.println("   secret="+accessToken.getSecret());
		System.out.println();

		
		
		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		OAuthRequest request = new OAuthRequest(Verb.GET, MAGENTO_REST_API_URL
				+ "/stockitems?type=rest");
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getBody());

		System.out.println();
		System.out
				.println("Thats it man! Go and build something awesome with Scribe! :)");
	}

	/**
	 * This Test tests a signed in admin token
	 * 
	 */
	@Test
	public void testSignedAdminAPI() {
	
		OAuthService service = new ServiceBuilder().provider(magentoApi)
				.apiKey(MAGENTO_API_KEY).apiSecret(MAGENTO_API_SECRET).build();
	
		Scanner in = new Scanner(System.in);
	
	
		// Create a signed token....
		Token accessToken = new Token("1c1180f7f4806874c6b36531de412099", "a5c0efbfb978380d500ba4cca04aa079");
	
			System.out.println("Creating a signed Access Token from known secret....");
		System.out.println("   key="+accessToken.getToken());
		System.out.println("   secret="+accessToken.getSecret());
		System.out.println();
	
		
		
		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		OAuthRequest request = new OAuthRequest(Verb.GET, MAGENTO_REST_API_URL
				+ "/stockitems?type=rest");
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getBody());
	
		System.out.println();
		System.out
				.println("Thats it man! Go and build something awesome with Scribe! :)");
	}









}
