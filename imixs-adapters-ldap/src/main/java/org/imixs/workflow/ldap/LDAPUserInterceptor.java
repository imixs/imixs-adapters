package org.imixs.workflow.ldap;

import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.imixs.marty.ejb.ProfileService;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.DocumentService;
import org.imixs.workflow.exceptions.ModelException;
import org.imixs.workflow.exceptions.PluginException;

/**
 * This Intercepter class provides a mechanism to lookup the LDAP attributes of
 * a UserID. The interceptor is used to enhance the behavior of the marty
 * ProfileService EJB. The interceptor intercepts the method
 * 
 * <pre>
 * {@code
 *   ItemCollection lookupProfileById(String userid)
 * }
 * </pre>
 * 
 * of the service EJB
 * 
 * <pre>
 * {@code
 * org.imixs.marty.ejb.ProfileService
 * }
 * 
 * <pre>
 *
 * The interceptor can be enabled by the deployment descriptor of the
 * DocumentService. See the following example for a ejb-jar.xml configuration
 * 
 * {@code
 *  <interceptors>
 *   ....
 *      <interceptor>
			<interceptor-class>org.imixs.workflow.ldap.LDAPUserInterceptor</interceptor-class>
		</interceptor>

  ....
 * 	<assembly-descriptor>
		<!-- LDAPGroupInterceptor -->
		<interceptor-binding> 
		    <description>Intercepter to add ldap attributes into the profile context</description> 
		    <ejb-name>ProfileService</ejb-name> 
			<interceptor-class>org.imixs.workflow.ldap.LDAPUserInterceptor</interceptor-class> 
		</interceptor-binding>
	</assembly-descriptor>
 * }
 * 
 * 
 * @version 1.0
 * @author rsoika
 * 
 */

public class LDAPUserInterceptor {

	@EJB
	LDAPLookupService ldapLokupService;

	@EJB
	ProfileService profileService;

	@EJB
	DocumentService documentService;

	@Resource
	SessionContext ejbCtx;

	private static Logger logger = Logger.getLogger(LDAPUserInterceptor.class.getName());

	/**
	 * The interceptor method injects the LDAP groups into the contextData map.
	 * The method only runs for the method calls 'findAllEntities', 'save' and
	 * 'load'
	 * 
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {

		// test if ldap lookup service is available
		if (ldapLokupService.isEnabled()) {
			// test method name
			String sMethod = ctx.getMethod().getName();
			
			if ("lookupProfileById".equals(sMethod)) {
				logger.finest("......intercept method=" + sMethod);

				// get id....
				Object[] params = ctx.getParameters();

				String sUserID = (String) params[0];
				logger.finest("......userid=" + sUserID);

				ItemCollection profile = (ItemCollection) ctx.proceed();
				if (profile == null) {
					// create profile
					try {
						profile = profileService.createProfile(sUserID, "");
					} catch (RuntimeException | PluginException | ModelException e) {
						logger.severe("unable to create profile for userid '" + sUserID + "': " + e.getMessage());
					}
				}

				// update profile?
				if (profile != null) {
					// compare attributes....
					ItemCollection ldapUser = ldapLokupService.findUser(sUserID);
					boolean bUpdate = false;
					if (ldapUser != null) {
						logger.finest("......ldap entry found, verifing attributes...");

						// print all
						Map<String, Object> items = (Map<String, Object>) ldapUser.getItemList();

						for (Map.Entry<String, Object> entry : items.entrySet()) {
							String key = entry.getKey();
							Object value = entry.getValue();
							logger.finest(" ...... " + key + "=" + value);

							if (!profile.getItemValue(key).equals(ldapUser.getItemValue(key))) {
								profile.replaceItemValue(key, ldapUser.getItemValue(key));
								bUpdate = true;
							}
						}
						// save profile?
						if (bUpdate) {
							logger.info("Updating user profile '" + sUserID + "' with new ldap attributes....");
							profile = documentService.save(profile);
						}
					} else {
						logger.warning("userid " + sUserID + " not found!");
					}
				}

				return profile;
			}
		}

		// default behavior
		return ctx.proceed();
	}

}