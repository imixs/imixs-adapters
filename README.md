# imixs-adapters

Connect Imixs-Workflow with services and software platforms!

The 'imixs-adapers' project contains different technologies to adapt services and software platforms into the imixs-workflow. The project is split into severals modules. Each module provides different connectors and technologies. 

Take a look into the separated sub projects or see the wiki pages for more information: https://github.com/imixs/imixs-adapters/wiki




## imixs-adapters-ldap

The [LDAP adapters](imixs-adapters-ldap-ejb) provide a set of services (EJB) to connect to an external LDAP server and extend the Imixs ProfileService with ldap attributes. The LDAP adapter can be configured as an incerceptor to extend the capabilities of imixs-marty. 

* [imixs-adapters-ldap-ejb](imixs-adapters-ldap-ejb)

## imixs-adapters-magento-ejb

The [Magento adapte](imixs-adapters-magento-ejb) provides a client API to connect  to the Ecommerce Softwareplattform Magento. 

* [imixs-adapters-magento-ejb](imixs-adapters-magento-ejb)


## imixs-adapters-datev

The DATEV adapter project is split into a back-end (EJB) and a front-end (JSF) component. The project supports services to import and export data with the DATEV system (DATEV is a finance software in Germany). 

* [imixs-adapters-datev-ejb](imixs-adapters-datev-ejb)
* [imixs-adapters-datev-web](imixs-adapters-datev-web)
