package org.imixs.workflow.kafka;

import java.io.Serializable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.imixs.workflow.engine.ProcessingEvent;

/**
 * The ProducerService is a Kafka client that publishes workflow events to the
 * Kafka cluster.
 * <p>
 * The kafka producer is thread safe and sharing a single producer instance
 * across threads will generally be faster than having multiple instances. For
 * that reason we use a @Singleton with @ConcurrencyManagement
 * <p>
 * 
 * The producer consists of a pool of buffer space that holds records that
 * haven't yet been transmitted to the server as well as a background I/O thread
 * that is responsible for turning these records into requests and transmitting
 * them to the cluster. Failure to close the producer after use will leak these
 * resources.
 * 
 * @version 1.0
 * @author rsoika
 * 
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ProducerService implements Serializable {

	public static String KAFKA_BROKERS = "localhost:9092";
	public static Integer MESSAGE_COUNT = 1000;
	public static String CLIENT_ID = "client1";
	public static String TOPIC_NAME = "demo";
	public static String GROUP_ID_CONFIG = "consumerGroup1";
	public static Integer MAX_NO_MESSAGE_FOUND_COUNT = 100;
	public static String OFFSET_RESET_LATEST = "latest";
	public static String OFFSET_RESET_EARLIER = "earliest";
	public static Integer MAX_POLL_RECORDS = 1;

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(ProducerService.class.getName());

	Producer<Long, String> producer;

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

		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
		// CustomPartitioner.class.getName());

		producer = new KafkaProducer<>(props);

	}

	/**
	 * On each workflow process event a new message is generated.
	 */
	public void onProcess(@Observes ProcessingEvent documentEvent) {

		if (ProcessingEvent.AFTER_PROCESS == documentEvent.getEventType()) {

			String uid = documentEvent.getDocument().getUniqueID();
			ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(TOPIC_NAME,
					"processed workitem: " + uid);

			try {
				RecordMetadata metadata = producer.send(record).get();
				System.out.println("Record sent with key " + uid + " to partition " + metadata.partition()

						+ " with offset " + metadata.offset());
			}

			catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}

			catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}

		}

	}

}