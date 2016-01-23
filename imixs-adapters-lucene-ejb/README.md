#Imixs-Lucene-Adapter
The Imixs-Lucene-Adapter project provides Services to update and query a Lucen index. 
The plug-in org.imixs.workflow.lucene.LucenPlugin can be used to synchronized a workitem during the processing phase. 

##Deployment

The imixs-adapter-lucene-ejb.jar can be deployed together with the imixs-workflow-engine in a singel EJB module. Using maven 
the configuration section of the maven-ejb-plugin can be used to add the jar into the class-path of the manifest entries: 

	<plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-ejb-plugin</artifactId>
		<version>2.3</version>
		<configuration>
			<ejbVersion>3.0</ejbVersion>
			<archive>
				<!-- add the EJB module imixs-workflow-jee-impl - !DO NOT format this line! -->
				<manifestEntries>
					<Class-Path>imixs-workflow-engine-${org.imixs.workflow.version}.jar imixs-workflow-core-${org.imixs.workflow.version}.jar imixs-adapters-lucene-ejb-${org.imixs.adapters.version}.jar</Class-Path>
				</manifestEntries>
			</archive>
		</configuration>
	</plugin>

In an EAR the imixs-adapter-lucene-ejb.jar can be listed as a jar module:

		...
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-ear-plugin</artifactId>
				<version>2.6</version>
				<configuration>
					<version>6</version>
					<defaultLibBundleDir>lib</defaultLibBundleDir>
					....
					<modules>
						....
						<!-- Imixs componentes -->
						<JarModule>
							<groupId>org.imixs.workflow</groupId>
							<artifactId> imixs-workflow-core</artifactId>
							<bundleDir>/</bundleDir>
						</JarModule>
						<JarModule>
							<groupId>org.imixs.workflow</groupId>
							<artifactId>imixs-workflow-engine </artifactId>
							<bundleDir>/</bundleDir>
						</JarModule>

						<JarModule>
							<groupId>org.imixs.workflow</groupId>
							<artifactId>imixs-adapters-lucene-ejb</artifactId>
							<bundleDir>/</bundleDir>
						</JarModule>

					</modules>
      .....
		</configuration>

##LucenePlugin
The LucenePlugin documentation is available here: http://www.imixs.org/doc/engine/plugins/luceneplugin.html 
