# Apache Kafka Adapter

This adapter module provides an Apache Kafka messaging service for Imixs-Workflow events.

See:

* https://dzone.com/articles/kafka-producer-and-consumer-example
* https://dzone.com/articles/writing-a-kafka-consumer-in-java



# <img src="https://github.com/imixs/imixs-microservice/raw/master/small_h-trans.png">

To test the behavior of the Imxis Kafka Adapter, you can run [Imixs-Microservices](https://github.com/imixs/imixs-microservice) as a custom image. The project provides a setup to include the Imixs Kafka Adapter and create a custom build.

## How to create a custom Docker Image from Imixs-Microservcie

To create a custom Docker image of the Imixs-Microservice just jecout the project from [Github](https://github.com/imixs/imixs-microservice) and add the Imixs-Adapters-Kafka dependency:

		<dependency>
			<groupId>org.imixs.workflow</groupId>
			<artifactId>imixs-adapters-kafka</artifactId>
			<version>${org.imixs.adapters.version}</version>
			<scope>compile</scope>
		</dependency>
		
The Imixs-Microservice project already includes this dependency in the pom.xml. You need to uncomment the dependency and than build the new Image


	$ cd ~/git/imixs-microservice
	$ mvn clean install -Pdocker-build
	
After that you can switch back into the imixs-adapter-kafka project and start the Imixs-Microservice Container with the docker-compose.yml file:

	$ cd ~/git/imixs-adapters/imixs-adapters-kafka/
	$ docker-compose up

This will start an instance of your new build Docker image of Imixs-Microservice including the Kafka Adater and also a local Kafak Server.


## Test the Kafka Adapter

First upload the demo model located under /src/model.ticket.bpmn


	curl --user admin:adminadmin --request POST -Tsrc/model/ticket.bpmn http://localhost:8080/api/model/bpmn

You can verify the availiblity of the model under the Web URI:

	http://localhost:8080/api/model

Now you can create a process instance which will trigger the Kafka Adapter:


	curl --user admin:adminadmin -H "Content-Type: application/json" -H 'Accept: application/json'  -d \
       '{"item":[ \
                 {"name":"type","value":{"@type":"xs:string","$":"workitem"}}, \
                 {"name":"$modelversion","value":{"@type":"xs:string","$":"1.0.1"}}, \
                 {"name":"$taskid","value":{"@type":"xs:int","$":"1000"}}, \
                 {"name":"$eventid","value":{"@type":"xs:int","$":"10"}}, \
                 {"name":"txtname","value":{"@type":"xs:string","$":"test-json"}}\
         ]}' \
         http://localhost:8080/api/workflow/workitem.json
         	
