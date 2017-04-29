#Docker

To run the project during development in debug mode you can use the following run command:


	docker run --name="imixs-qr" -it \
	   -p 8080:8080 -p 9990:9990 -p 8787:8787 \
	   -e WILDFLY_PASS="adminadmin" -e DEBUG="true" \
	   -v ~/git/imixs-qr/src/docker/.deployments:/opt/wildfly/standalone/deployments/ \
	   imixs/imixs-qr
	 
    