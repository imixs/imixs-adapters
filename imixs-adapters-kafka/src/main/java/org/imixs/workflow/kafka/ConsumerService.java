package org.imixs.workflow.kafka;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

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
// @ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ConsumerService implements Serializable {

	private static final long TEN_SECONDS = 10 * 1000L;

	@Resource
	private TimerService timerService;

	public static String TOPIC_NAME = "IN-1.0.1"; // just an example
	public static String GROUP_ID_CONFIG = "consumerGroup1";
	public static Integer MAX_NO_MESSAGE_FOUND_COUNT = 100;
	public static String OFFSET_RESET_LATEST = "latest";
	public static String OFFSET_RESET_EARLIER = "earliest";
	public static Integer MAX_POLL_RECORDS = 1;

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ConsumerService.class.getName());

	Consumer<Long, String> consumer;
	Properties props = null;

	/**
	 * On startup we just initialize a new Timer running each 10 seconds to poll the
	 * message queue
	 * <p>
	 * 
	 * @see https://stackoverflow.com/questions/55640112/how-to-implement-a-permanent-background-process-with-ejbs
	 * 
	 */
	@PostConstruct
	void init() {
		TimerConfig config = new TimerConfig();
		config.setPersistent(false);
		timerService.createIntervalTimer(TEN_SECONDS, TEN_SECONDS, config);
	}

	@PreDestroy
	void close() {
		if (consumer != null) {
			consumer.close();
		}
	}

	/**
	 * Helper method initializes a new consumer if no consumer is available.
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
	void initalizeConsumer() {

		if (consumer == null) {
			logger.info("......initalize kafka consumer...");
			props = new Properties();
			props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
					ConfigService.getEnv(ConfigService.ENV_KAFKA_BROKERS, "kafka:9092"));
			props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
			props.put(ProducerConfig.CLIENT_ID_CONFIG,
					ConfigService.getEnv(ConfigService.ENV_KAFKA_CLIENTID, "Imixs-Workflow-1"));
			props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
			props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, MAX_POLL_RECORDS);
			props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
			props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OFFSET_RESET_EARLIER);

			// props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

			consumer = new KafkaConsumer<>(props);

			// here we need to subsribe the topics!
			logger.info("...register topic: " + TOPIC_NAME);
			consumer.subscribe(Collections.singletonList(TOPIC_NAME));
		}
	}

	/**
	 * Using EJB TimerService in current project for tasks like periodic data
	 * pruning, or back-end data synchronization. It allows not only single time
	 * execution, but also interval timers and timers with calendar based schedule.
	 */
	@Timeout
	private synchronized void onTimer() {

		// initalize consumer if not available
		initalizeConsumer();

		ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
		// time in milliseconds consumer will wait if no record is found at
		// broker.
		if (consumerRecords.count() > 0) {

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

	}

}