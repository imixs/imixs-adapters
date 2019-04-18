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
 * This test verifies the IBAN regex
 * 
 * @author rsoika
 * 
 */
public class TestProducer {

	Producer<Long, String> producer;

	private static Logger logger = Logger.getLogger(TestProducer.class.getName());

	
	@Before
	public void setup() {
		Properties props = new Properties();

		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
		
		
		props.put(ProducerConfig.CLIENT_ID_CONFIG,"Imixs-Workflow1");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		

 
		// here we wait  maximum one second if the topic is present in the metadata (default is 60000)
		props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG,1000);
		// props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
		// CustomPartitioner.class.getName());
		producer = new KafkaProducer<>(props);
	}

	@After
	public void teardown() {
		producer.close();
	}

	/** 
	 * test the fieldlist of the first line of the file
	 */
	@Test 
	public void testSendMessages() {
		
		ProducerRecord<Long, String> record = new ProducerRecord<Long, String>("1.0.1", "some data test...");

		try {
			RecordMetadata metadata = producer.send(record).get();
			logger.info("...Imixs-Workflow Event sent to partition " + metadata.partition()
					+ " with offset " + metadata.offset());
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