imixs-adapters
==============

Connect Imixs-Workflow with services and software platforms 

The 'imixs-adapers' project contains different technolgies to adapt services and software platforms into the imixs-workflow. The project is split into severals modules. Each modul provides differnt connectors and technolgies. The common-adapers provide some general technologies to be used for defelopment. 


See wiki for more information: https://github.com/imixs/imixs-adapters/wiki



imixs-adapters-magento-ejb
==============

The moduel imixs-adapters-magento-ejb contains a client API to connect 
 to the Ecommerce Softwareplattform Magento. 
  
For testing the Magento Adapter you can take a look on the JUnit Tests provided
together with the imixs-adapters-magento-ejb module. 
To run the test create the property file "src/test/resources/imixs.properties" 
with the following minimal setup:


```
##############################
# Imixs Properties
##############################

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
magento.access-key=bst0xf3knba73.......li8wm4a
magento.access-secret=mhoifjombt.........aeurr04l

##############################
# Magento SOAP Service Configuration (replaces the rest api configuration)
##############################
#magento.soap.uri-api=http://localhost:8080/magento/index.php/api/index/index/
# access token...
#magento.access-key=your-useraccount
#magento.access-secret=your-password


```
