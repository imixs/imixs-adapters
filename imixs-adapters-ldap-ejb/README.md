# imixs-adapters LDAP

The Imixs-Adapters LDAP subproject contains a set of Plug-Ins to be used to lookup user meta data form a LDAP directory.
The Plugins are using a JNDI connector to be configured on the application server. 

The jndi name to connect to an LDAP server is

    org.imixs.mail.directory


## Microsoft Activte Direcotry
To connect to a Microsoft Active Directory the plug-in class

    org.imixs.workflow.ldap.ADMailPlugin

can be used. This class lookup the user principal provided by the 'Java Authentication and Authorization Service' (JAA) standard mapped to the AD attribute 'samAccountName' using the following search phrase:

	(samAccountName=?)

To adapt the plug-in for different environments or configuration you can overwrite the plug-in class.  