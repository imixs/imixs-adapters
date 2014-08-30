package org.imixs.workflow.magento.rest;

import java.util.Scanner;

import org.imixs.workflow.magento.rest.MagentoApi;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * This test class implements some basic concepts how to use the scribe API. The
 * methods test simple rest api calls against a local magento shop installation.
 * 
 * 
 * @see: https://github.com/fernandezpablo85/scribe-java
 * 
 */
public class TestBasicScribeMagento {
//	static final String MAGENTO_API_KEY = "9abde0f96ce9a388994d464f5dfa81b3";
//	static final String MAGENTO_API_SECRET = "34aa69d7ee0782f9a6f04ac9cdb3d11e";
//	static final String MAGENTO_REST_API_URL = "http://localhost/magento/api/rest";
//	static final String MAGENTO_BASE_URL = "http://localhost/magento/index.php/";

	// toci
	 static final String MAGENTO_API_KEY = "77gqhzp35ka9euqqczn5sgr5s5ayeylx";
	 static final String MAGENTO_API_SECRET =  "mpfbw1ptlrejwwrx5z0o9yoe90rq2hda";
	 static final String MAGENTO_REST_API_URL = "http://toci01.imixs.com:11180/magento/api/rest";
	 static final String MAGENTO_BASE_URL = "http://toci01.imixs.com:11180/magento/index.php/";

	 
	 // ssg
//	 static final String MAGENTO_API_KEY = "8v4p1fj12k7t2u7zkjsvzm9vbh8umg2k";
//	 static final String MAGENTO_API_SECRET =  "bg0mbl4fl45ykxej8p95b61nu4hmsl4o";
//	 static final String MAGENTO_REST_API_URL = "http://staging.badsanitaer.de/api/rest";
//	 static final String MAGENTO_BASE_URL = "http://staging.badsanitaer.de/index.php/";

	 
	 
	 
	 
	MagentoApi magentoApi = null;

	@Before
	public void setup() {

		magentoApi = new MagentoApi(MAGENTO_BASE_URL);
		magentoApi.setAdminAPI(true);
		//magentoApi.setBaseURL(MAGENTO_BASE_URL);

		System.out.println("Base URL=" + magentoApi.getBaseURL());

	}

	/**
	 * This Test checks the Magento Consumer API
	 * 
	 */
	@SuppressWarnings("resource")
	@Test
	@Ignore
	public void testConsumerAPI() {

		magentoApi.setAdminAPI(false);

		OAuthService service = new ServiceBuilder().provider(magentoApi)
				.apiKey(MAGENTO_API_KEY).apiSecret(MAGENTO_API_SECRET).debug()
				.build();

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
	@SuppressWarnings("resource")
	@Test
	//@Ignore
	public void testAdminAPI() {

		OAuthService service = new ServiceBuilder().provider(magentoApi)
				.apiKey(MAGENTO_API_KEY).apiSecret(MAGENTO_API_SECRET).debug()
				.build();

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
		System.out.println("   key=" + accessToken.getToken());
		System.out.println("   secret=" + accessToken.getSecret());
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
	@SuppressWarnings({ "unused", "resource" })
	@Test
	@Ignore
	public void testSignedAdminAPI() {

		OAuthService service = new ServiceBuilder().provider(magentoApi)
				.apiKey(MAGENTO_API_KEY).apiSecret(MAGENTO_API_SECRET).debug()
				.build();

		Scanner in = new Scanner(System.in);

		// Create a signed token....
		Token accessToken = new Token("1c1180f7f4806874c6b36531de412099",
				"a5c0efbfb978380d500ba4cca04aa079");

		System.out
				.println("Creating a signed Access Token from known secret....");
		System.out.println("   key=" + accessToken.getToken());
		System.out.println("   secret=" + accessToken.getSecret());
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
