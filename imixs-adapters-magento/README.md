# Magento Adapter

The Imixs-Magento-Adapter is a connector module providing a set of classes to connect to the Ecommerce Softwareplattform Magento. Magento provides two different WebService Interfaces. The Magento Rest API for a simple Rest Service based on a the OAuth 1.0.a protocol and the Magento SOAP API for a SOAP interface.

See the [wiki pages](https://github.com/imixs/imixs-adapters/wiki) for more details.

## Testing 
For testing the Magento Adapter you can take a look on the JUnit Tests provided together with the imixs-adapters-magento-ejb module. 
To run the test create the property file "src/test/resources/imixs.properties" 
with the following minimal setup:



	
	##############################
	# Magento REST Service Configuration 
	##############################
	# basi urls
	magento.rest.uri-basis=http://localhost:8080/magento/index.php/
	magento.rest.uri-api=http://localhost:8080/magento/api/rest
	# api token 
	magento.oauth.consumer-key=e2dv81c4t......mpw63mc77zk
	magento.oauth.consumer-secret=w2y4........wtb8ldfz1cekb
	# access token... (use testRequestNewToken to generate a new one)
	magento.rest.access-key=bst0xf3knba73.......li8wm4a
	magento.rest.access-secret=mhoifjombt.........aeurr04l
	
	##############################
	# Magento SOAP Service Configuration (replaces the rest api configuration)
	##############################
	#magento.soap.uri-api=http://localhost:8080/magento/index.php/api/index/index/
	# access token...
	#magento.soap.access-key=your-useraccount
	#magento.soap.access-secret=your-password

