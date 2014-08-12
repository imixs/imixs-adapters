package org.imixs.workflow.magento;

import org.imixs.workflow.util.Base64;
import org.scribe.model.Request;
import org.scribe.model.RequestTuner;

/**
 * This class extends the abstract class RequestTuner and adds a basic
 * authentication to the request haeder. This can be used in cases a OAuth
 * request needs a Basic Autentication.
 * 
 * <code>
 * 	Response response = request.send(new BasicAuthRequestTuner(user,password));
 * </code>
 * 
 * @author rsoika
 * 
 */
public class BasicAuthRequestTuner extends RequestTuner {

	private String user = null;
	private String password = null;

	/**
	 * Initializes a requestTuner with basic authentication information (user
	 * and password)
	 * 
	 * @param user
	 * @param password
	 */
	public BasicAuthRequestTuner(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	/**
	 * This method verifies if a user and password for basic authentication is
	 * provided.
	 */
	@Override
	public void tune(Request request) {

		if (user != null && !user.isEmpty() && password != null && !password.isEmpty()) {
			String sURLAccess = "";
			// UserID:Passwort
			String sUserCode = user + ":" + password;
			// base64 converting String
			char[] authcode = Base64.encode(sUserCode.getBytes());
			sURLAccess = String.valueOf(authcode);
			// add header -
			// Authorization: Basic aHR0cHdhdGNoOmY=
			request.addHeader("Authorization", "Basic " + sURLAccess);
		}

	}
	
}
