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



## JUnit Tests

The library contains a JUnit test class to show the general behavior of the QrGenerator class. For further information see also the [zxing QR-Library](https://github.com/zxing/zxing).