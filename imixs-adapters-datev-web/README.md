# imixs-datev-adapter

The _imixs-datev-adapter_ is used to import DATEV data into the Imixs-Workflow System.
Datev data objects can be imported as simple entities or can be  assigned to a specific workflow model. Therefore the adapter provides two different import services:


* **DatevImportService** - imports simple data objects from a DATEV import File
* **DatevWorkflowService** - imports data objects from a DATEV import File and assigns the object with a workflow model.


Withe the _DatevWorkflowService_ DATEV data can be processed in a custom way. 

The imxis-adapter-datev-web module can be added into a web module. The module provides CDI and servlets to control the datev scheduler service

The module contains also JSF pages to be used for frontends. 

Add the following maven dependency into a parent project:


	<!-- DATEV Adapter -->
	<dependency>
		<groupId>org.imixs.workflow</groupId>
		<artifactId>imixs-adapters-datev</artifactId>
		<version>${org.imixs.adapters.version}</version>
		<scope>provided</scope>
	</dependency>
	
## DataImportService

The DatevImportService EJB provides methods to read a datev file and import the data. 


	// test if supported CSV file?
	if (file.getName().endsWith(".csv")) {
		ByteArrayInputStream input = new ByteArrayInputStream(file.getContent());
		String result = datevImportService.importData(input, "ISO-8859-1");
		getImportData().replaceItemValue("log", result);
	} else {
		throw new PluginException(this.getClass().getName(), DatevImportService.IMPORT_ERROR,
				"File Format not supported: " + file.getName());
	}
