# Imixs WOPI

This adapter module provides a WOPI Host Implementation based on the [WOPI API](https://wopi.readthedocs.io/projects/wopirest/en/latest/). The adapter can be used to integrate WOPI Clients like LibreOffice Online to edit and view office Documents.

<img src="libreoffice-online-screen-002.png" width="900px"/>


## The Rest API

The WOPI Adapter module provides a Rest API with the following endpoints. The endpoints are based on the WOPI API. 


| Method |URI                           | Description                               					   | 
|--------|------------------------------|------------------------------------------------------------------|
| GET    | /wopi/files/{name}           | returns a JSON object providing information about the file {name}. It will be called by LibreOffice Online to know what kind of document will be shown in the editor          |
| GET    | /wopi/files/{name}/contents  | endpoint providing the raw data of the file. The endpoint is called by LibreOffice Online to open the file.          |
| POST   | /wopi/files/{name}/contents  | endpoint called by LibreOffice Online when the user what save a file.    |





### Security

The WOPI API endpoints /wopi/ must not be protected because LibreOffice has no mechanism to authenticate against a WOPI Host. You need to make sure the endpoint /wopi/ is not protected by the web.xml.

To validate user access the imixs-adapter-wopi module provides an JWT implementation to generate and to validate an access token. The endpoint uri to access the HOST looks like this:

	https://localhost:9980/{libreoffice-editor}.html?WOPISrc=http://wopi-app:8080/api/wopi/files/{your-file}?access_token={JWT} 


# Integration

The Imixs-WOPI Adapter provides services and a JavaScript library for  a  tightly coupling with the Imixs Workflow Engine. The following section shows how to integrate the Imixs-WOPI Adapter into a application. A prerequisite is that an instance of a WOPI client (e.g. LibreOffice Online) is running. 

## Environment 

To setup the Imixs-WOPI Adapter the following environment variables must be set:


| Variable              | Description  							| Example |
| --------------------- |---------------------------------------|---------|
| WOPI_PUBLIC_ENDPOINT  | Public client endpoint to be called by the web application. This endpoint should be SSL encrypted |https://libreoffice.foo.com/loleaflet/6a844e4/loleaflet.html?
| WOPI_HOST_ENDPOINT    | Internal Wopi Host endpoint is called by the Wopi Client to fetch and store file data. This endpoint should not be public accessible | http://my-app:8080/api/wopi/
| WOPI_DISCOVERY_ENDPOINT | Optional public discovery endpoint used by the Wopi Host implementation to resolve the public wopi endpoint dynamically. This variable should only be set if no WOPI_PUBLIC_ENDPOINT was defined! | http://localhost:9980/hosting/discovery
| WOPI_FILE_EXTENSIONS   | Optional comma separated list of file extensions to be supported. |.odt,.doc,.docx,.ods,.xls,.xlsx,.ppt,.pptx|     
| WOPI_FILE_CACHE        | file path to cache wopi files temporarily on the wop host | default: /tmp/wopi/
| WOPI_POSTMESSAGEORIGIN | Optional postMessageOrigin  | e.g. http://application.foo.com:8080
     
The following example shows a setup for in a Docker Compose file running in a local dev environment:

	....
	  my-app:
	    image: imixs/imixs-office-workflow
	    environment:
	      ....
	      WOPI_PUBLIC_ENDPOINT: "http://localhost:9980/loleaflet/6a844e4/loleaflet.html?"
	      WOPI_HOST_ENDPOINT: "http://my-app:8080/api/wopi/"    
	    ....
	    ports:
	      - "8080:8080"
	....	      

In a productive environment, the WOPI_PUBLIC_ENDPOINT should be set to a SSL encrypted Internet domain name:

	....
	  my-app:
	    image: imixs/imixs-office-workflow
	    environment:
	      ....
	      WOPI_PUBLIC_ENDPOINT: "https://libreoffice.foo.com/loleaflet/6a844e4/loleaflet.html?"
	      WOPI_HOST_ENDPOINT: "http://my-app:8080/api/wopi/"    
	    ....
	    ports:
	      - "8080:8080"
	....	  

## Maven

The imixs-adapter-wopi module can be added into an Imixs Workflow application. The module provides CDI and Rest API components. 

Add the following maven dependency into a parent project:


	<!-- WOPI Adapter -->
	<dependency>
		<groupId>org.imixs.workflow</groupId>
		<artifactId>imixs-adapters-wopi</artifactId>
		<version>${org.imixs.adapters.version}</version>
		<scope>provided</scope>
	</dependency>

**Note:** The WopiHostService needs manager access. So you maybe need to tweak your deployment descriptors accordingly. Also make sure that the Rest API endpoint /api/wopi/ is not protected by JAAC. 


## The WopiController

To open the LibreOffice Online Editor you need a access url including the Wopi Host Endpoint and the access token. The CDI Bean WopiController provides a convenient method to generate such a URL:


	 <a href="javascript:void;"
	    onclick="imixsWopi.openViewer('wopi_canvas','#{wopiController.getWopiAccessURL(uniqueID,filename,userid,username)}');">
	    Edit</a>

In this example we are calling the JavaScript method to open the viewer component in a iframe. See details in the following section.

## JavaScript

The Imixs-WOPI Adapter provides a JavaScript library to open and control the Wopi Editor (Wopi Client).
The Integration of the Wopi Client into your application is done by a iframe. This necessary to isolate the editor form your surrounding application. 
To display the editor in a iframe the script library *imixs-wopi.js* provides a method imixsWopi.openViewer. The method expects a DIV element in your existing web page and the access URL to place the iframe with the editor. 



	<script type="text/javascript" src="/js/imixs-core.js"></script>
	<script type="text/javascript" src="/js/imixs-wopi.js"></script>
	<script>
		// open the wopi viewer
		function openWopiViewer(url) {
			// open viewer...		
			imixsWopi.openViewer('wopi_canvas', url);
			// define an optional save callback method
			//imixsWopi.saveCallback=closeWopiViewer;
		}
	</script>


	....
	...........
	<!-- LibreOffice Editor -->
	<div id="wopi_controlls">
		<button onclick="imixsWopi.save(); return false;">Update</button><button onclick="imixsWopi.closeViewer(); return false;">Close</button>
		<hr />
	</div>
	<div id="wopi_canvas" style="display: none;"></div>
	....

## UI Controls

The control of closing the editor or saving the content is in this concept part of your application. So in the example above the application shows two buttons to save the content and to close the editor. 

## Updating the File Content by Callback method

When a file was saved by LibreOffice Online, the data is posted to the WOPI Host endpoint '/wopi/files/{name}/contents'. The file content is not directly stored. It is cached into the local wopi file cache on the Wopi Host. An application can provide a saveCallback method to be triggered after a file was updated. 

	// define save callback when a file was updated....
	imixsWopi.saveCallback = uiSaveCallback;
	
	function uiSaveCallback(filename) {
		// you can do an ui update based on the filename
		// ....
		closeWopiViewer();
	}
	// close the wopi viewer
	function closeWopiViewer(confirmMessage) {
		// if document was modifed without save then ask the user....
		if (imixsWopi.isModified) {
			if (confirm(confirmMessage)) {
				imixsWopi.save(); return false;
			}
		}
		console.log("close ");
		$('#wopi_controlls').hide();
		imixsWopi.closeViewer();
		// show workflow form
		$('#imixs_workitem_form_id').show();
	}



## Reacting on PostMessage Events

LibreOffice Online sends JavaScript general events each time an update of the content is performed by the user. 
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

You can also send messages to the editor 

	imixsWopi.postMessage({
			"MessageId" : "Action_Save",
			"Values" : {
				"DontTerminateEdit" : true,
				"DontSaveIfUnmodified": false,
				"Notify" : true
			}
		});

Find more details about the Post Message in Collaboara [here](https://sdk.collaboraonline.com/docs/postmessage_api.html).

## Example Application

You can find a full demo integration in the 'wopi-host' branch of the [Imixs-Process-Manager project](https://github.com/imixs/imixs-process-manager/tree/wopi-host).


## Working with different domains

Because of a [bug in Collboara](https://github.com/CollaboraOnline/online/issues/2380) you can integrate collabora and imixs-workflow only if both services share the same toplevel domain (e.g. 'libreoffice.foo.com' and  'workflow.foo.com').

In addition you have to tweak some settings. In the following example we assume you have the domains:

	libreoffice.foo.com   - for LibreOffice Online/Collabora
	workflow.foo.com   - for Imixs-Office-Workflow


**1. The Docker Container Settings**

First in the deployment of the Collabora container you need to set the environment variable 'domain' pointing to your imixs workflow application:

        - name: domain
          value: workflow.foo.com 

The value can contain also a regular expression to add multiple host names.


**2. The 'frame_ancestors' in loolwsd.xml**

Next you need to set the tag 'frame_ancestors' in the loolwsd.xml file. You need to add all domain names from applications to be allowed to embed the editor.

	....
	<frame_ancestors>workflow.foo.com</frame_ancestors>
	
The [frame-ancestors](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Security-Policy/frame-ancestors) is an official security policy. You can separate multiple hosts by space and you can also use wildcards like in the following example:

	<frame_ancestors>*.foo.com</frame_ancestors>


Find more details [here](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Security-Policy/frame-ancestors).

**3. Allow wopi storage in loolwsd.xml**

In the section storage/wop of the loolwsd.xml file you also need to add the host names for your worklfow applications. 

	....
    <storage desc="Backend storage">
        <filesystem allow="false" />
        <wopi desc="Allow/deny wopi storage. Mutually exclusive with webdav." allow="true">
            <host desc="Regex pattern of hostname to allow or deny." allow="true">workflow.foo.com</host>
	....
	
Here you can use again regexepression.

**4. Using the WOPI_HOST_ENDPOINT in Kubernetes**

Running in Kubernetes you should not use internal host names from the internal Kubernetes Proxy Network. For the "WOPI\_HOST\_ENDPOINT" use the public Internet domain instead:

	...
    spec:
      containers:
      - name: imixs-documents
        image: imixs/imixs-documents:latest
        env:	
        ...
        - name: WOPI_HOST_ENDPOINT
          value: "https://workflow.foo.com/api/wopi/"
       ...
       
       
       
## The Wopi Template Adapter

With the adapter class *org.imixs.workflow.wopi.WopiTemplateAdapter* a office document template can be imported form the local filesystem or a textblock entity into a workitem.

The adapter can be configured by the workflow result:


	<wopi-template name="source-path">./invoice-template.odt/</wopi-template>
	<wopi-template name="target-name">invoice-2020.odt</wopi-template>

The tag 'source-path' specifies the location of the office template document in the servers local file system. 

The tag 'target-name' is the name of the file to be attached to the current workitem. The name can be computed by <itemvalue> tags. For example:

	<wopi-template name="target-name">invoice-<itemvalue>invoice.number</itemvalue>.odt</wopi-template>

### Loading Templates from a Textblock

A template can optionally be loaded from a Imixs-Office-Workflow textblock attachment.

	<wopi-template name="source-path"><textblock>invoice template</textblock></wopi-template>

In this case the adapter will load the first attachment from the textblock with the name 'inoice template'.

### Auto Load Editor

With the optional flag 

	<wopi-template name="auto-open">true</wopi-template>

The adapter class will set the item "wopi.auto.open". This flag can be used by a frontend implementation to automaitically open the Wopi Editor on load. This feature is implemented in Imixs-Office-Workflow.

The WopiController will automatically clean the flag before processing. 

### Kubernetes

In a Kubernetes environment the office templates can be provided in a ConfigMap object



      
       
## The Wopi Document Converter Adapter

With the adapter class *org.imixs.workflow.wopi.WopiDocumentConverterAdapter* a office document can be converted into PDF or other file formats.

The Adapter simply calls a build in RestAPI of Collabora to convert documents based on the [JODConverter](https://github.com/sbraconnier/jodconverter).  There is no need to implement the libraries in case a Collabora instance is up and running. In this case the rest API endpoint *'lool/convert-to/'* provides a convenience function. See details [here](https://www.collaboraoffice.com/de/document-conversion/)

Documents can be converted into different formats by calling the corresponding endpoint.

 - https://localhost:9980/lool/convert-to/pdf for pdf
 - https://localhost:9980/lool/convert-to/png for png

The rest service automatically detects the input document format. You can test the Rest API if you have a running instance of Collaboar with a curl command:

	curl -F "data=@test.txt" http://localhost:9980/lool/convert-to/pdf > out.pdf
	
### Configuration 

The adapter simply posts a given document to the service endpoint. The adapter can be configured by the BPMN event workflow result:

    <wopi-converter name="api-endpoint">http://localhost:9980/lool/convert-to/</wopi-converter>
    <wopi-converter name="filename">......</wopi-converter>
    <wopi-converter name="convert-to">pdf</wopi-converter>

The Collabora API endpoint must point to a collabora instance. The 'filename' is the file attached to the current workitem. The option 'convert-to' is optional and default value is 'pdf'


### Regular Expressions

You can also define the filename as a pattern including regulare expressins. See the following example:

	<wopi-converter name="filename">.*<itemvalue>numsequencenumber</itemvalue>\.docx</wopi-converter>

This expression will match all files ending with the sequence number and the file extension '.docx'


# Open Issues

## Hide Sidebar

To hide the default sidebar follow the discussion [here](https://forum.collaboraonline.com/t/postmessage-to-change-the-sidbar-status/394/6) and [Issue 113](https://github.com/imixs/imixs-adapters/issues/113).

