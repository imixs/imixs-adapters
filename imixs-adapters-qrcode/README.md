# Imixs-QR

The Imixs-QR provides a QR-Code Rest API based on [zxing QR-Library](https://github.com/zxing/zxing).

The goal of this project is to provide a visual representation of a corporate business process. In combination with the Imixs-Workflow engine documents or assets can be easily linked with a business processes.

<img src="qr.png" />

## Rest API
Imixs-QR library contains a jax-rs rest service:

    org.imixs.workflow.qrcode.QrService


This service provides one Rest API resource with a query parameter containing the qr-code :


	/qr-code?[QR-CODE]


The qr-code have to be URL encoded.

| Method        | URL                 | Description  |
| ------------- |---------------------| -----|
| /qr-code?key  | /qr-code?key=sample | Generates the QR-Code image for the key 'sample' |


## Deployment

To add the QR-Code Adapter into your Web Project just add the following maven dependency

		<dependency>
		    <groupId>org.imixs.workflow</groupId>
		    <artifactId>imixs-adapters-qrcode</artifactId>
		    <version>2.2.4</version>
		    <scope>compile</scope>
		</dependency>

To place the QR-Code image into a web page you can use the following JSF snippet:

			<!-- QR-Code if property 'qr-code.url.prafix' is filled -->
			<ui:param name="qrprafix"
				value="#{propertyController.getProperty('qr-code.url.prafix')}"></ui:param>
			<h:panelGroup layout="block" styleClass="col-2"
				rendered="#{(!userController.mobileUser) and (!empty qrprafix)}">
				<a href="#" onclick="printQRCode();"><h:graphicImage
						rendered="#{!empty qrprafix}" styleClass="imixsQrCode"
						url="/api/qr-code?size=130&amp;key=#{qrprafix}#{workflowController.workitem.uniqueID}"></h:graphicImage></a>
			</h:panelGroup>

In this snippet we ask for the optional property 'qr-code.url.prafix' to render a valid link.

The JavaScript method 	printQRCode() opens a popup window with the QR-Code to be printed. 		


	printQRCode=function () {
		fenster = window
				.open(
						imixsOfficeMain.contextPath+"/pages/workitems/qrcode_print.jsf?id="+imixsOfficeMain.workitem_uid,
						"message.print",
						"width=760,height=300,status=no,scrollbars=no,resizable=yes");
		fenster.focus();
	};



## JUnit Tests

The library contains a JUnit test class to show the general behavior of the QrGenerator class. For further information see also the [zxing QR-Library](https://github.com/zxing/zxing).