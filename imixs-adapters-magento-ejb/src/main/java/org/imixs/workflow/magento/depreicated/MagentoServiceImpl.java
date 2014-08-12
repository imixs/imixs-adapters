package org.imixs.workflow.magento.depreicated;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.imixs.workflow.magento.BasicAuthRequestTuner;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Request;
import org.scribe.model.RequestTuner;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.scribe.services.Base64Encoder;
import org.scribe.utils.MapUtils;


/**
 * overwrite implementation of OAuth10aServiceImpl 
 * to provide a way for additional headers.
 * 
 * 
 * @author rsoika
 *
 */
public class MagentoServiceImpl  implements OAuthService {

	
	  private static final String VERSION = "1.0";

	  private OAuthConfig config;
	  private DefaultApi10a api;

	  /**
	   * Default constructor
	   *
	   * @param api OAuth1.0a api information
	   * @param config OAuth 1.0a configuration param object
	   */
	  public MagentoServiceImpl(DefaultApi10a api, OAuthConfig config)
	  {
	    this.api = api;
	    this.config = config;
	  }

	  /**
	   * {@inheritDoc}
	   */
	  public Token getRequestToken(int timeout, TimeUnit unit)
	  {
	    return getRequestToken(new TimeoutTuner(timeout, unit));
	  }

	  public Token getRequestToken()
	  {
	    return getRequestToken(2, TimeUnit.SECONDS);
	  }

	  public Token getRequestToken(RequestTuner tuner)
	  {
	    config.log("obtaining request token from " + api.getRequestTokenEndpoint());
	    OAuthRequest request = new OAuthRequest(api.getRequestTokenVerb(), api.getRequestTokenEndpoint());

	    config.log("setting oauth_callback to " + config.getCallback());
	    request.addOAuthParameter(OAuthConstants.CALLBACK, config.getCallback());
	    addOAuthParams(request, OAuthConstants.EMPTY_TOKEN);
	    appendSignature(request);

	    config.log("sending request...");
	    

	    // need to add headers here manually....
	    // see: https://github.com/fernandezpablo85/scribe-java/issues/504
	    if (tuner instanceof BasicAuthRequestTuner) {
	    	
	    }
	    
	    Response response = request.send(tuner);
	 
	    
	    
	    String body = response.getBody();

	    config.log("response status code: " + response.getCode());
	    config.log("response body: " + body);
	    return api.getRequestTokenExtractor().extract(body);
	  }

	  private void addOAuthParams(OAuthRequest request, Token token)
	  {
	    request.addOAuthParameter(OAuthConstants.TIMESTAMP, api.getTimestampService().getTimestampInSeconds());
	    request.addOAuthParameter(OAuthConstants.NONCE, api.getTimestampService().getNonce());
	    request.addOAuthParameter(OAuthConstants.CONSUMER_KEY, config.getApiKey());
	    request.addOAuthParameter(OAuthConstants.SIGN_METHOD, api.getSignatureService().getSignatureMethod());
	    request.addOAuthParameter(OAuthConstants.VERSION, getVersion());
	    if(config.hasScope()) request.addOAuthParameter(OAuthConstants.SCOPE, config.getScope());
	    request.addOAuthParameter(OAuthConstants.SIGNATURE, getSignature(request, token));

	    config.log("appended additional OAuth parameters: " + MapUtils.toString(request.getOauthParameters()));
	  }

	  /**
	   * {@inheritDoc}
	   */
	  public Token getAccessToken(Token requestToken, Verifier verifier, int timeout, TimeUnit unit)
	  {
	    return getAccessToken(requestToken, verifier, new TimeoutTuner(timeout, unit));
	  }

	  public Token getAccessToken(Token requestToken, Verifier verifier)
	  {
	    return getAccessToken(requestToken, verifier, 2, TimeUnit.SECONDS);
	  }

	  public Token getAccessToken(Token requestToken, Verifier verifier, RequestTuner tuner)
	  {
	    config.log("obtaining access token from " + api.getAccessTokenEndpoint());
	    OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());
	    request.addOAuthParameter(OAuthConstants.TOKEN, requestToken.getToken());
	    request.addOAuthParameter(OAuthConstants.VERIFIER, verifier.getValue());

	    config.log("setting token to: " + requestToken + " and verifier to: " + verifier);
	    addOAuthParams(request, requestToken);
	    appendSignature(request);
	    
	    config.log("sending request...");
	    Response response = request.send(tuner);
	    String body = response.getBody();
	    
	    config.log("response status code: " + response.getCode());
	    config.log("response body: " + body);
	    return api.getAccessTokenExtractor().extract(body);
	  }

	  /**
	   * {@inheritDoc}
	   */
	  public void signRequest(Token token, OAuthRequest request)
	  {
	    config.log("signing request: " + request.getCompleteUrl());

	    // Do not append the token if empty. This is for two legged OAuth calls.
	    if (!token.isEmpty())
	    {
	      request.addOAuthParameter(OAuthConstants.TOKEN, token.getToken());
	    }
	    config.log("setting token to: " + token);
	    addOAuthParams(request, token);
	    appendSignature(request);
	  }

	  /**
	   * {@inheritDoc}
	   */
	  public String getVersion()
	  {
	    return VERSION;
	  }

	  /**
	   * {@inheritDoc}
	   */
	  public String getAuthorizationUrl(Token requestToken)
	  {
	    return api.getAuthorizationUrl(requestToken);
	  }

	  private String getSignature(OAuthRequest request, Token token)
	  {
	    config.log("generating signature...");
	    config.log("using base64 encoder: " + Base64Encoder.type());
	    String baseString = api.getBaseStringExtractor().extract(request);
	    String signature = api.getSignatureService().getSignature(baseString, config.getApiSecret(), token.getSecret());

	    config.log("base string is: " + baseString);
	    config.log("signature is: " + signature);
	    return signature;
	  }

	  private void appendSignature(OAuthRequest request)
	  {
	    switch (config.getSignatureType())
	    {
	      case Header:
	        config.log("using Http Header signature");

	        String oauthHeader = api.getHeaderExtractor().extract(request);
	        request.addHeader(OAuthConstants.HEADER, oauthHeader);
	        break;
	      case QueryString:
	        config.log("using Querystring signature");

	        for (Map.Entry<String, String> entry : request.getOauthParameters().entrySet())
	        {
	          request.addQuerystringParameter(entry.getKey(), entry.getValue());
	        }
	        break;
	    }
	  }

	  private static class TimeoutTuner extends RequestTuner
	  {
	    private final int duration;
	    private final TimeUnit unit;

	    public TimeoutTuner(int duration, TimeUnit unit)
	    {
	      this.duration = duration;
	      this.unit = unit;
	    }

	    @Override
	    public void tune(Request request)
	    {
	      request.setReadTimeout(duration, unit);
	    }
	  }
	}

