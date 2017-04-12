package org.imixs.workflow.ldap;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.imixs.workflow.engine.DocumentService;

/**
 * This Intercepter class provides a mechanism to compute the LDAP groups a user
 * belongs to. The Result is put into the EJB contextData which is read by the
 * DocumentSerivce EJB to grant access by dynamic user roles.
 * 
 * The interceptor can be enabled by the deployment descriptor of the
 * DocumentService. See the following example for a ejb-jar.xml configuration
 * 
 * <pre>
 * {@code
 * 
 * 	<assembly-descriptor>
		<!-- LDAPGroupInterceptor -->
		<interceptor-binding> 
		    <description>Intercepter to add project-role mapping into EJB Context Data</description> 
		    <ejb-name>DocumentService</ejb-name> 
			<interceptor-class>org.imixs.workflow.ldap.LDAPGroupInterceptor</interceptor-class> 
		</interceptor-binding>
	</assembly-descriptor>
 * }
 * 
 * 
 * @version 1.0
 * @author rsoika
 * 
 */

public class LDAPGroupInterceptor {

	@EJB
	LDAPLookupService lookupService;

	@Resource
	SessionContext ejbCtx;

	private static Logger logger = Logger.getLogger(LDAPGroupInterceptor.class.getName());

	/**
	 * The interceptor method injects the LDAP groups into the contextData map.
	 * The method only runs for the method calls 'findAllEntities', 'save' and
	 * 'load'
	 * 
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {

		// test if ldap lookup service is available
		if (lookupService.isEnabled()) {
			// test method name
			String sMethod = ctx.getMethod().getName();
			if ("getUserNameList".equals(sMethod)) {

				logger.finest("LDAPGroupInterceptor Method=" + sMethod);

				String sUserID = ejbCtx.getCallerPrincipal().getName();

				String[] sGroups = lookupService.findGroups(sUserID);

				ctx.getContextData().put(DocumentService.USER_GROUP_LIST, sGroups);

				if (logger.isLoggable(java.util.logging.Level.FINEST)) {
					String groupListe = "";
					for (String aGroup : sGroups)
						groupListe += "'" + aGroup + "' ";
					logger.finest("resolved UserGroups for '" + sUserID + "' = " + groupListe);
				}
			}
		}

		return ctx.proceed();
	}

}