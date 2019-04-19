package org.imixs.workflow.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * Junit test producing a imixs kafka event
 * 
 * @author rsoika
 * 
 */
public class TestProducer {

	Producer<Long, String> producer;

	private static Logger logger = Logger.getLogger(TestProducer.class.getName());

	/**
	 * Setup the kafka producer class used to send messages
	 */
	@Before
	public void setup() {
		Properties props = new Properties();

		// bootstrap.servers
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");

		// client.id: An id string to pass to the server when making requests. The
		// purpose of this is to be able to track the source of requests beyond just
		// ip/port by allowing a logical application name to be included in server-side
		// request logging.
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "Imixs-Workflow1");
		//props.put(ConsumerConfig.GROUP_ID_CONFIG, "...");

		// max.block.ms: maximum ms to wait if the topic is no present in the metadata
		// (default is 60000)
		props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 1000);

		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
		// CustomPartitioner.class.getName());
		producer = new KafkaProducer<>(props);
	}

	@After
	public void teardown() {
		producer.close();
	}

	/**
	 * send a json test request
	 * 
	 */
	@Test
	public void testSendMessages() { 

		// JSON request:  
		String json_workitem = "{\"item\":[ "
				+ "             {\"name\":\"type\",\"value\":{\"@type\":\"xs:string\",\"$\":\"workitem\"}}, "
				+ "             {\"name\":\"$modelversion\",\"value\":{\"@type\":\"xs:string\",\"$\":\"kafka-ticket-1.0\"}}, "
				+ "             {\"name\":\"$taskid\",\"value\":{\"@type\":\"xs:int\",\"$\":\"1000\"}}, "
				+ "             {\"name\":\"$eventid\",\"value\":{\"@type\":\"xs:int\",\"$\":\"10\"}}, "
				+ "             {\"name\":\"txtname\",\"value\":{\"@type\":\"xs:string\",\"$\":\"test-json\"}}"
				+ "     ]}";

 
		// send a process instance in json format
		try {
			ProducerRecord<Long, String> record = new ProducerRecord<Long, String>("IN-1.0.1", json_workitem);
			RecordMetadata metadata = producer.send(record).get();
			logger.info("...Imixs-Workflow Event sent to partition " + metadata.partition() + " with offset "
					+ metadata.offset());
		}

		catch (ExecutionException e) {
			logger.info("Error in sending record: " + e.getMessage());
			Assert.fail(); 
		}

		catch (InterruptedException e) {
			System.out.println("Error in sending record: " + e.getMessage());
			Assert.fail();
		}

	}

}