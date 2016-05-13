imixs-datev-adapter
==============

The imxis-adapter-datev-cdi module can be added into a web module. The module provides CDI and servlets to control the datev scheduler service

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