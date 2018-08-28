# imixs-datev-adapter

The _imixs-datev-adapter_ is used to import datev data and assigen this data with a defined workflow model. So datev data can be processed in a custom way. 

The imxis-adapter-datev-web module can be added into a web module. The module provides CDI and servlets to control the datev scheduler service

The module contains also JSF pages to be used for frontends. 

Add the following maven dependency into a parent project:


	<!-- DATEV Adapter -->
	<dependency>
		<groupId>org.imixs.workflow</groupId>
		<artifactId>imixs-adapters-datev-ejb</artifactId>
		<version>${org.imixs.adapters.version}</version>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>org.imixs.workflow</groupId>
		<artifactId>imixs-adapters-datev-web</artifactId>
		<version>${org.imixs.adapters.version}</version>
	</dependency>

## Data Import

The DatevService EJB provides methods to read a datev file and import the data. 