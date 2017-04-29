# Imixs-QR

The Imixs-QR provides a QR-Code Rest API based on [zxing QR-Library](https://github.com/zxing/zxing).

The goal of this project is to provide a visual representation of a corporate business process. In combination with the Imixs-Workflow engine documents or assets can be easily linked with a business processes.

<img src="qr.png" />

## Installation
Imixs-QR is a Maven based JAX-RS rest service and can be build by the maven command:

    mvn clean install

The artifact file can be deployed into any Java EE Application server.

To request a qr-code for a key or url, the following GET request can be called:

http://localhost:8080/imixs-qr/code/[KEY]


<br /><br /><img src="small_h-trans.png" />


The Imixs-QR  provides a Docker Image to be used to run the service in a Docker container. 
The docker image is based on the docker image [imixs/wildfly](https://hub.docker.com/r/imixs/wildfly/).


## Run Imixs-Admin in a Docker Container
You can start the Imixs-Admin docker container with the command:

	docker run --name="imixs-qr" -d \
	 -p 8080:8080 \
	 imixs/imixs-qr
	 
Test the Service from your web browser

http://localhost:8080/imixs-qr/code/Imixs-Workflow
    

## Build Imixs-QR from sources

Alternatively you can build the imixs-admin client manually by sources

Imixs-QR is based on maven. To build the Java EE artifact run:

	mvn clean install

To build the docker image run

	docker build --tag=imixs/imixs-qr .



