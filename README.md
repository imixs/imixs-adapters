# imixs-adapters
[![Java CI with Maven](https://github.com/imixs/imixs-adapters/actions/workflows/maven.yml/badge.svg)](https://github.com/imixs/imixs-adapters/actions/workflows/maven.yml)
[![Join a discussion](https://img.shields.io/badge/discuss-on%20github-4CB697)](https://github.com/imixs/imixs-workflow/discussions)
[![License](https://img.shields.io/badge/license-GPL-blue.svg)](https://github.com/imixs/imixs-adapters/blob/master/LICENSE)

Connect Imixs-Workflow with services and software platforms!

The 'imixs-adapers' project contains a set of technologies to adapt services and software platforms into the Imixs-Workflow engine. The project is split into severals modules. where each module provides a different connector technology. 

Take a look into the separated sub projects or see the [wiki pages](https://github.com/imixs/imixs-adapters/wiki) for more information.


## LDAP Adapter

The [LDAP adapters](imixs-adapters-ldap-ejb) provide a set of services (EJB) to connect the [Imixs-Marty](https://github.com/imixs/imixs-marty) project to an external LDAP server and extend the Imixs-Marty ProfileService with data provided by an ldap directory service. The LDAP adapter can be configured as an incerceptor to extend the capabilities of imixs-marty services. 

* [imixs-adapters-ldap-ejb](imixs-adapters-ldap-ejb)


## Documents Adapter

The [Imixs-Documents](imixs-adapters-documents) is a adapter project for collecting textual information from attached documents during the processing phase.
This extracted text information is added into the item '_dms_' which can be part of the lucene full-text-index. The DMS meta data is handled by the [Imixs-Archive project](https://github.com/imixs/imixs-archive).
 
* [imixs-adapters-documents](imixs-adapters-documents)

## Import Adapter

The [Import adapter](imixs-adapters-import) provides a generic document import service based on the Imixs Scheduler API

* [imixs-adapters-import](imixs-adapters-import)


## DATEV Adapter

The DATEV adapter project supports services to import and export data with the DATEV system (DATEV is a finance software in Germany).  The project is split into a back-end (EJB) and a front-end (JSF) component. 

* [imixs-adapters-datev](imixs-adapters-datev)


## Prometheus Adapter

The _imixs-prometheus-adapter_ provides a rest service endpoint exposing Imxis-Workflow metrics. 


## SEPA Adapter

The _imixs-adapters-sepa_ provides services to export workflow invoice data into a sepa file.



## Apache Kafka Adapter

This The _imixs-adapters-kafka_  provides an Apache Kafka messaging service for Imixs-Workflow events.