# Imixs WOPI

This adapter module provides a WOPI Host Implementation based on the [WOPI API](https://wopi.readthedocs.io/projects/wopirest/en/latest/). The adapter can be used to integrate WOPI Clients like LibreOffice Online to edit and view office Documents.

<img src="libreoffice-online-screen-001.png" width="700px"/>


## The Rest API

The WOPI Adapter module pvovices a Rest API with the following endpoints: 



| Method |URI                           | Description                               					   | 
|--------|------------------------------|------------------------------------------------------------------|
| GET    | /wopi/files/{name}           | returns a JSON object providing information about the file {name}. It will be called by LibreOffice Online to know what kind of document will be shown in the editor          |
| GET    | /wopi/files/{name}/contents  | endpoint providing the raw data of the file. The endpoint is called by LibreOffice Online to open the file.          |
| POST   | /wopi/files/{name}/contents  | endpoint called by LibreOffice Online when the user what save a file.    |



### Security

The /wopi/ Rest API endpoint must not be protected because LibreOffice has no mechanism to authenticate against a WOPI Host. You need to make sure the endpoint is not protected by the web.xml.

To validate user access the imixs-adapter-wopi module provides an JWT implementation to generate and to validate an access token. The endpoint uri to access the HOST looks like this:

	https://localhost:9980/{libreoffice-editor}.html?WOPISrc=http://wopi-app:8080/api/wopi/files/{your-file}?access_token={JWT} 


# Development

## Maven


The imixs-adapter-wopi module can be added into an application module. The module provides CDI and Rest API components. 

Add the following maven dependency into a parent project:


	<!-- POI Adapter -->
	<dependency>
		<groupId>org.imixs.workflow</groupId>
		<artifactId>imixs-adapters-wopi</artifactId>
		<version>${org.imixs.adapters.version}</version>
		<scope>provided</scope>
	</dependency>

**Note:** The WopiHostService needs manager access. So you need to tweak your deployment descriptors accordingly.


	