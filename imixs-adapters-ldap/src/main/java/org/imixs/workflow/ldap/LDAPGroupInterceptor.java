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
	 * The interceptor method injects the LDAP groups into the contextData map. The
	 * method only runs for the method calls 'findAllEntities', 'save' and 'load'
	 * 
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {

		// To test the method name 'getUserNameList' is not working here, because in one
		// EJB context, the DocumentService can be called several times. But the
		// intercept method is only called once (the fist time). For that reason we fill
		// the Context in any case with the result of the findGroups() call and store
		// the result with the key DocumentService.USER_GROUP_LIST into the EJB
		// context. 
		//
		// So in all other cases we just need to check if the key already exists in the
		// current EJB context
		// See: issue #marty/178

		// test if ldap lookup service is available
		if (lookupService.isEnabled()) {
			String sMethod = ctx.getMethod().getName();
			String sUserID = ejbCtx.getCallerPrincipal().getName();
			// we can ignore anonymous here!!
			if ( sUserID == null || "anonymous".equals(sUserID)
					|| sUserID.isEmpty()) {
				return ctx.proceed();
			}
			
			// if we have not yet build a USER_GROUP_LIST lets start...
			if (!ctx.getContextData().containsKey(DocumentService.USER_GROUP_LIST)) {
				logger.finest("LDAPGroupInterceptor Method=" + sMethod);
				sUserID = ejbCtx.getCallerPrincipal().getName();
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