# imixs-adapters LDAP

The Imixs-Adapters LDAP subproject provides a set of services and plug-ins to be used to lookup user meta data form a LDAP directory.
The Services and Plug-ins are using a JNDI connector to be configured on the application server. 

The jndi name to connect to an LDAP server is

    org.imixs.mail.directory


The configuration is done in the imixs.properties files with the following examples:


    ldap.search-context=
    ldap.disable-jndi=
    ldap.jndi-name=
    # User lookup
    ldap.dn-search-filter=(uid=%u)
    # User attributes
    ldap.user-attributes=uid,SN,CN,mail
    # Group lookup
    ldap.group-search-filter=(member=%d)


## LDAP User Interceptor 

The interceptor class 

	org.imixs.workflow.ldap.LDAPUserInterceptor

is used to provide the ProfileService with optional attributes form an external ldap directory. The interceptor class can be configured in the ejb-jar.xml deployment descriptor:


	...
	<assembly-descriptor>
		<!-- LDAPGroupInterceptor -->
		<interceptor-binding> 
		    <description>Intercepter to add ldap attributes into the profile context</description> 
		    <ejb-name>ProfileService</ejb-name> 
			<interceptor-class>org.imixs.workflow.ldap.LDAPUserInterceptor</interceptor-class> 
		</interceptor-binding>
	</assembly-descriptor>
	...

The configuration to lookup a user is done by the imixs.properties.

## Lookup LDAP Context
The ldap context can either be injected ba JNDI name or constructed manually based on a given configuration:

### JNDI Lookup
For the jndi lookup the 'ldap.disable-jndi' is set to 'false' or omitted the ldap context is read form the JNDI name 'ldap.jndi-name'

    ldap.disable-jndi=false
    ldap.jndi-name=mycontext


### Manually lookup

	ldap.disable-jndi=true
	java.naming.factory.initial=com.sun.jndi.ldap.LdapCtxFactory
	java.naming.security.authentication=simple
	
	
Additional parameters can be set by defining property keys starting with 'java.naming'. See the following example:


	java.naming.provider.url=ldap://127.0.0.1:389
	java.naming.security.principal=user
	java.naming.security.credentials=password


### Microsoft Activte Direcotry
To connect to a Microsoft Active Directory the follwoing additonal addributes can be set optional:


	java.naming.referral=follow
	java.naming.ldap.version=2

A userid lookup is typically made against the AD attribute 'samAccountName' using the following search phrase:

	(samAccountName=?)



  