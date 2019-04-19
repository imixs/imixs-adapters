# Apache Kafka Adapter

This adapter module provides an Apache Kafka messaging service for Imixs-Workflow events.

## Workflow Message Autowire

With Imixs-Kafka you can easily send Workflow Messages automatically during the processing life-cycle. With the Autowire-Function new process instances are send into a Kafka Message Queue so that any consumer interested in workflow events can consume the message and react in various ways.

<img src="src/uml/kafka-adapter-prodcuer.png" />

The Adapter filters Workflow events by the Model Version number so you can control which kind of workflows are send into a message queue. 

# <img src="https://github.com/imixs/imixs-microservice/raw/master/small_h-trans.png">

To test the behavior of the Imxis Kafka Adapter, you can run [Imixs-Microservices](https://github.com/imixs/imixs-microservice) as a custom image. The project provides a setup to include the Imixs Kafka Adapter and create a custom build.

## How to create a custom Docker Image from Imixs-Microservcie

To create a custom Docker image of the Imixs-Microservice just checkout the project from [Github](https://github.com/imixs/imixs-microservice) and add the Imixs-Adapters-Kafka dependency into the maven pom.xml:

		<dependency>
			<groupId>org.imixs.workflow</groupId>
			<artifactId>imixs-adapters-kafka</artifactId>
			<version>${org.imixs.adapters.version}</version>
			<scope>compile</scope>
		</dependency>
		
The Imixs-Microservice project already includes this dependency in the pom.xml. You need to uncomment the dependency and than build the new Image


	$ cd ~/git/imixs-microservice
	$ mvn clean install -Pdocker-build
	
Now you have a local Docker image of the Imixs-Microservice including the Apache-Kafka adapter project.

Switch back into the imixs-adapter-kafka project and start the Imixs-Microservice Container with the docker-compose.yml file:

	$ cd ~/git/imixs-adapters/imixs-adapters-kafka/
	$ docker-compose up


This docker-compose.ymls file will start the following components in a Docker stack:

 * Imixs-Microservice including the Kafka Adapter and running in Wildfly Debug mode listening to port 8787 
 * Postgres DB for Imixs-Workflow
 * Apache Kafka single node cluster
 * Zookeeper 
 
Take note of the following setup within the docker-compose.yml file:

	....
	  kafka:
	    image: wurstmeister/kafka:latest
	    ports:
	      - target: 9094
	        published: 9094
	        protocol: tcp
	        mode: host
	    environment:
	      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
	      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: INSIDE:PLAINTEXT,OUTSIDE:PLAINTEXT
	      KAFKA_ADVERTISED_LISTENERS: INSIDE://:9092,OUTSIDE://localhost:9094
	      KAFKA_LISTENERS: INSIDE://:9092,OUTSIDE://:9094
	      KAFKA_INTER_BROKER_LISTENER_NAME: INSIDE


This defines port 9092 for the internal network communication with kafka (kafka:9092). The port number 9094 is for the outside communication and bound to the host name 'localhost'. This is for local dev test only. In production environment you can add the real host name here or use the following bash command to resolve the host name from the docker info: 

	 HOSTNAME_COMMAND: "docker info | grep ^Name: | cut -d' ' -f 2" 
	 
 
 

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
         	

###  Error while fetching metadata with correlation id 537 : {1.0.1=LEADER_NOT_AVAILABLE}

If you got a error message like this one:

	 Error while fetching metadata with correlation id 537 : {1.0.1=LEADER_NOT_AVAILABLE}


Then first shutdown your stack with the command:

	$ docker-compose down

and restart it again

	$ docker-compose up

