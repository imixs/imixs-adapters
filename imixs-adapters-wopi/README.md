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


# Integration

The Imixs-WOPI Adapter provides a JavaScript library for an easy integration. To integrate your page can simply load the script and provide a div element to show the editor:


	<script type="text/javascript" src="/js/imixs-core.js"></script>
	<script type="text/javascript" src="/js/imixs-wopi.js"></script>
	<script type="text/javascript">
		// define imixs script name spaces
		var imixs = IMIXS.org.imixs.core;
		$(document).ready(function() {
			// init wopi viewer...
			imixsWopi.formID="workitem_form_body";
			imixsWopi.viewerID="libreoffice_viewer";
		});
	</script>
	
	....
	...........
	
	<!-- Script called when a file was updated -->
	<h:commandScript name="wopiControllerUpdateFile" action="#{wopiController.updateFile()}" onevent="someUI-UpdateMethod" />
	
	
	<!-- show no attachments from workitem -->
	<ui:param name="fileDataList" value="#{workflowController.workitem.fileData}"></ui:param>
	<ul>
		<ui:repeat value="#{fileDataList}" var="fileData">
			<li>#{fileData.name} -> <a href="javascript:void;" onclick="imixsWopi.openViewer('#{wopiController.getWopiAccessURLByFileName(workflowController.workitem.uniqueID,fileData.name)}');"> Edit</a></li>
		</ui:repeat>	
	</ul>
	
	<div id="libreoffice_viewer" style="display: none;">
		<!--the place where the editor is loaded.... -->
	</div>



## Reacting on Events

LibreOffice Online sends JavaScript events each time an update of the content is performed by the user. 
A javaScript can react on these events be registering a EventListner:


	/**
	 * Register a message listener
	 */
	$(document).ready(function() {
		//  Install the wopi message listener.
		// receive messages form libreoffice online
		window.addEventListener("message", receiveMessage, false);
	});
	
	// This function is invoked when the iframe posts a message back.
	function receiveMessage(event) {
		console.log('==== framed.doc.html receiveMessage: ' + event.data);
		... do something....
	}



## Updating the File Content

The  imixs-wopi.js reacts on the 'UI_Save' event and triggers WopiController method updateFile. This method fetches the new file content form the WopiAccessHandler file cache. 

The JSF commandScript can also trigger an additional javaScript method to update the DownloadSeciton.

	<h:commandScript name="wopiControllerUpdateFile" action="#{wopiController.updateFile()}" onevent="updateDownloadSection" />


# More...

See: https://people.gnome.org/~michael/data/2020-02-01-web-collaboration.pdf

Example HTML/IFrame : https://github.com/LibreOffice/online/blob/master/loleaflet/html/framed.doc.html


	