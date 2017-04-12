package org.imixs.workflow.ldap;

import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.imixs.workflow.ItemCollection;

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
	LDAPLookupService lookupService;

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
		if (lookupService.isEnabled()) {
			// test method name
			String sMethod = ctx.getMethod().getName();
			if ("lookupProfileById".equals(sMethod)) {
				logger.fine("intercept method=" + sMethod);

				// get id....
				Object[] params = ctx.getParameters();

				String sUserID = (String) params[0];
				logger.fine("userid=" + sUserID);

			
				ItemCollection ldapUser=lookupService.findUser(sUserID);
				
				if (ldapUser!=null) {
					logger.fine("updating profile with ldap attributes...");
					
					// print all
					Map<String, Object> items = (Map<String, Object>) ldapUser.getItemList();
					
					for (Map.Entry<String, Object> entry : items.entrySet()) {
					    String key = entry.getKey();
					    Object value = entry.getValue();
					    logger.fine(" ...... " + key +"=" + value);
					}
					
				} else {
					logger.warning("userid " + sUserID + " not found!");
				}
				
				
				
			}
		}

		return ctx.proceed();
	}

}