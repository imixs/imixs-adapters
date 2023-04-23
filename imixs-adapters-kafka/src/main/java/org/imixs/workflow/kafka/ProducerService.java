package org.imixs.workflow.kafka;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;
import javax.enterprise.event.Observes;
import jakarta.xml.bind.JAXBException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.engine.ProcessingEvent;
import org.imixs.workflow.exceptions.AdapterException;
import org.imixs.workflow.exceptions.ProcessingErrorException;
import org.imixs.workflow.xml.XMLDocumentAdapter;

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

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ProducerService.class.getName());

	Producer<Long, String> producer;

	/**
	 * This method creates a Kafka producer with some properties during
	 * initalization.
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
		logger.info("...init KafkaProducer...");
		Properties props = new Properties();

		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				ConfigService.getEnv(ConfigService.ENV_KAFKA_BROKERS, "kafka:9092"));
		props.put(ProducerConfig.CLIENT_ID_CONFIG,
				ConfigService.getEnv(ConfigService.ENV_KAFKA_CLIENTID, "Imixs-Workflow-1"));
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
		// CustomPartitioner.class.getName());
		producer = new KafkaProducer<>(props);
	}

	/**
	 * Autowire:
	 * <p>
	 * On each workflow process event a new message is generated if the workflow
	 * model version matches the setup.
	 */
	public void onProcess(@Observes ProcessingEvent documentEvent) {

		if (ProcessingEvent.AFTER_PROCESS == documentEvent.getEventType()) {

			// test autowire / model version
			String modelPattern = ConfigService.getEnv(ConfigService.ENV_KAFKA_AUTOWIRE, null);
			if (modelPattern != null && !modelPattern.isEmpty()) {
				String modelVersion = documentEvent.getDocument().getModelVersion();

				Pattern regexPattern = Pattern.compile(modelPattern);
				if (!regexPattern.matcher(modelVersion).find()) {
					// no match
					return;
				}
			}

			logger.info("...consuming ProcssingEvent (model:" + modelPattern + ") -> send new kafka event...");
			try {
				sendWorkitem(documentEvent.getDocument()); 
			} catch (AdapterException e) {
				// convert Adapter Exception into runtime Exception!
				throw new ProcessingErrorException(e.getErrorContext() , e.getErrorCode(), e.getMessage(),e);
			}
		}

	}

	/**
	 * This method sends a kafka message based on a given workitem.
	 * <p>
	 * The topic ofi the message is the model version
	 * <p>
	 * The value is a serialized version of the workitem.
	 * 
	 * @param workitem
	 * @throws AdapterException 
	 */
	public void sendWorkitem(ItemCollection workitem) throws AdapterException {
		String uid = workitem.getUniqueID();
		// we use the model version as the topic name

		String topic = workitem.getModelVersion();
		// String value = workitem.getWorkflowGroup() + ":" + uid;

		try {
			byte[] value = XMLDocumentAdapter.writeItemCollection(workitem);

			ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(topic, new String(value));

			RecordMetadata metadata = producer.send(record).get();
			logger.info("...Imixs-Workflow Event sent with key " + uid + " to partition " + metadata.partition()
					+ " with offset " + metadata.offset());
		}

		catch (ExecutionException e) {
			throw new AdapterException(ProducerService.class.getSimpleName(), "EXECUTION-EXCEPTION",
					e.getMessage(), e);
		} catch (InterruptedException e) {
			throw new AdapterException(ProducerService.class.getSimpleName(), "INTERUPTED-EXCEPTION",
					e.getMessage(), e);
		} catch (JAXBException e) {
			throw new AdapterException(ProducerService.class.getSimpleName(), "JAXB-EXCEPTION", e.getMessage(),
					e);
		} catch (IOException e) {
			throw new AdapterException(ProducerService.class.getSimpleName(), "IO-EXCEPTION", e.getMessage(),
					e);
		}

	}

}