package org.imixs.workflow.kafka;

import java.io.Serializable;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * 
 * 
 * @version 1.0
 * @author rsoika
 * 
 */
@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ConsumerService implements Serializable {

	public static String KAFKA_BROKERS = "localhost:9092";
	public static Integer MESSAGE_COUNT = 1000;
	public static String CLIENT_ID = "client1";
	
	
	public static String TOPIC_NAME = "1.0.1"; // just an example
	public static String GROUP_ID_CONFIG = "consumerGroup1";
	public static Integer MAX_NO_MESSAGE_FOUND_COUNT = 100;
	public static String OFFSET_RESET_LATEST = "latest";
	public static String OFFSET_RESET_EARLIER = "earliest";
	public static Integer MAX_POLL_RECORDS = 1;

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(ConsumerService.class.getName());


	Consumer<Long, String> consumer;

	/**
	 * The above snippet creates a Kafka producer with some properties.
	 * <p>
	 * BOOTSTRAP_SERVERS_CONFIG: The Kafka broker's address. If Kafka is running in
	 * a cluster then you can provide comma (,) seperated addresses. For
	 * example:localhost:9091,localhost:9092
	 * <p>
	 * CLIENT_ID_CONFIG: Id of the producer so that the broker can determine the
	 * source of the request.
	 * <p>
	 * KEY_SERIALIZER_CLASS_CONFIG: The class that will be used to serialize the key
	 * object. In our example, our key is Long, so we can use the LongSerializer
	 * class to serialize the key. If in your use case you are using some other
	 * object as the key then you can create your custom serializer class by
	 * implementing the Serializer interface of Kafka and overriding the serialize
	 * method.
	 * <p>
	 * VALUE_SERIALIZER_CLASS_CONFIG: The class that will be used to serialize the
	 * value object. In our example, our value is String, so we can use the
	 * StringSerializer class to serialize the key. If your value is some other
	 * object then you create your custom serializer class.
	 * 
	 */
	@PostConstruct
	void init() {

		Properties props = new Properties();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
		
		props.put(ProducerConfig.CLIENT_ID_CONFIG, ConfigService.getEnv(ConfigService.ENV_KAFKA_CLIENTID, "Imixs-Workflow-1"));
		
		
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, MAX_POLL_RECORDS);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OFFSET_RESET_EARLIER);

		consumer = new KafkaConsumer<>(props);
		
		
		// here we need to subsribe the topics!
//		logger.info("...register topic: " + TOPIC_NAME);
//		consumer.subscribe(Collections.singletonList(TOPIC_NAME));
//		runConsumer();
	}

	void runConsumer() {

		int noMessageFound = 0;

		while (true) {

			ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
			// 1000 is the time in milliseconds consumer will wait if no record is found at
			// broker.
			if (consumerRecords.count() == 0) {
				noMessageFound++;
				if (noMessageFound > MAX_NO_MESSAGE_FOUND_COUNT)
					// If no message found count is reached to threshold exit loop.
					break;
				else
					continue;
			}

			// print each record.
			consumerRecords.forEach(record -> {
				System.out.println("Record Key " + record.key());
				System.out.println("Record value " + record.value());
				System.out.println("Record partition " + record.partition());
				System.out.println("Record offset " + record.offset());
			});

			// commits the offset of record to broker.
			consumer.commitAsync();
		}

		consumer.close();
	}

}