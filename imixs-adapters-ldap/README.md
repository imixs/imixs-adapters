# LDAP Adapter

This adapter module provides a set of services and plug-ins to be used to lookup user meta data form a LDAP directory.
The Services and Plug-ins are connected to a LDAP service either directly or by using a JNDI connector (to be configured on the application server). 

The jndi name to connect to an LDAP server is:

    org.imixs.mail.directory


The configuration, to directly connect the services with a ldap server, is done via the imixs.properties file. See the following example:


	ldap.jndi-name=
	ldap.disable-jndi=true
	ldap.search-context=DC=.....
    # User lookup
	ldap.dn-search-filter=(uid=%u)
    # User attributes
	ldap.user-attributes=mail|txtemail,name|txtUserName
    # Group lookup
	ldap.group-search-filter=(member=%d)
	# Cache (1h, max 100 entries)
	ldap.cache-expires=3600000
	ldap.cache-size=100
	# host and credentials     
	java.naming.provider.url=ldap://localhost:389
	java.naming.security.principal=CN=bind_user,CN=users,DC=company,DC=de
	java.naming.security.credentials=password


### User Attributes
The property ldap.user-attrbutes provides a list of ldap attributes to be stored in the corresponding User Profile document. The attributes are comma separated. Optional a target-fieldname can be defined by | followed by the document item:

    ldap.user-attributes=uid,SN,CN,mail|txtEmail

This example will store the data of the attribute 'mail' into the item with the name 'txtEmail'.

## The LDAPLookupService

The EJB LDAPLookupService provides methods to fetch data from the LDAP service. This can be either user data or group information where a user is member of. 

### Caching

The LDAP Lookup service is using an internal cache. The cache size and cache duration is defined by the imixs.properties

    ldap.cache-expires=3600000
    ldap.cache-size=100

Note: A system-check form the web-front-end did discard the ProfileService cache, but not the ldapLookupService cache. This means to discard the ldap cache you can either wait for the ldap.cach-expires period or you can restart the application.
 



## Lookup LDAP Context
The ldap context can either be injected by a JNDI name or constructed manually based on a given configuration:

### JNDI Lookup
For the jndi lookup the 'ldap.disable-jndi' is set to 'false' or omitted. The ldap context is read form the JNDI name 'ldap.jndi-name'. See the following configuration example:

    ldap.disable-jndi=false
    ldap.jndi-name=mycontext


### Manually lookup

To connect directyl to the ldap service the following properties must be defined in the imixs.properties file:
 

	ldap.disable-jndi=true
	java.naming.factory.initial=com.sun.jndi.ldap.LdapCtxFactory
	java.naming.security.authentication=simple
	
	
Additional parameters can be set by defining property keys starting with 'java.naming'. See the following example:


	java.naming.provider.url=ldap://127.0.0.1:389
	java.naming.security.principal=user
	java.naming.security.credentials=password


### Microsoft Active Directory
To connect to a Microsoft Active Directory the follwoing additonal addributes can be set optional:

	java.naming.referral=follow
	java.naming.ldap.version=2

A userid lookup is typically made against the AD attribute 'samAccountName' using the following search phrase:

	(samAccountName=%u)


## The LDAPProfileEvent

The LdapLookupService sends the CDI Event 'org.imixs.workflow.ldap.LDAPProfileEvent'. This event can be observed by CDI classes to adapt the content of a fetched ldap user profile. 

See the following example:

	@Stateless
	public class CustomLDAPProfileAdapter {
		public void onEvent(@Observes LDAPProfileEvent event) {
			ItemCollection profile = event.getProfile();
			// set a custom profile attribute ....
			.....
			profile.replaceItemValue("txtusernameCustom", special_username);
			event.setProfile(profile);
		}
	}



## The Marty ProfileEvent

The Imixs-Marty ProfileService sends the CDI event 'ProfileEvent' during the creation and the lookup of a profile object. The event can be observed by a client service to enrich a profile with attributes from an LDAP object. 


	org.imixs.marty.ejb.ProfileEvent

The ProfileEvent defines the following event types:

 - ON_PROFILE_LOOKUP - send if a local lookup for a profile failed
 - ON_PROFILE_CREATE - send immediately before a profile object is created




## The LDAP Group Interceptor 

The interceptor class 

	org.imixs.workflow.ldap.LDAPGroupInterceptor

is used to provide the user access management with user groups provided by an external ldap directory. The interceptor class can be configured in the ejb-jar.xml deployment descriptor:


	...
	<assembly-descriptor>
		<!-- LDAPGroupInterceptor -->
		<interceptor-binding> 
		    <description>Intercepter to add ldap attributes into the profile context</description> 
		    <ejb-name>ProfileService</ejb-name> 
			<interceptor-class>org.imixs.workflow.ldap.LDAPGroupInterceptor</interceptor-class> 
		</interceptor-binding>
	</assembly-descriptor>
	...

The configuration to lookup a user is done by the imixs.properties.



  