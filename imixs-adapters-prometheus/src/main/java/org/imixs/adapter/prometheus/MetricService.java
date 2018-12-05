package org.imixs.adapter.prometheus;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.imixs.workflow.WorkflowKernel;
import org.imixs.workflow.engine.DocumentEvent;
import org.imixs.workflow.engine.ProcessingEvent;
import org.imixs.workflow.exceptions.AccessDeniedException;

/**
 * The Imixs MetricSerivce is a monitoring resource for Prometheus.
 * 
 * A metric is generated when a Imixs ProcessingEvent or Imixs DocumentEvent is
 * fired. The service exports metrics in prometheus text format.
 * 
 * See:
 * 
 * https://prometheus.io/docs/instrumenting/exposition_formats/
 * https://www.oreilly.com/library/view/prometheus-up/9781492034131/ch04.html
 * 
 * To avoid dependencies, we implement the prometheus exposition text format you
 * ourself.
 * 
 * Mainly the adaper proivdes counter metrics about processed workitems. A
 * coutner will always increase. To extract the values in prometheus use the
 * rate fuction - Exmaple:
 * 
 * rate(http_requests_total[5m])
 * 
 * See: https://www.robustperception.io/how-does-a-prometheus-counter-work
 * 
 * 
 * 
 * General architecture is inspired by:
 * 
 * See:
 * 
 * http://www.adam-bien.com/roller/abien/entry/singleton_the_simplest_possible_jmx
 * http://www.adam-bien.com/roller/abien/entry/monitoring_java_ee_appservers_with
 * http://www.adam-bien.com/roller/abien/entry/java_ee_6_observer_with
 * 
 * 
 * 
 * @author rsoika
 * @version 1.0
 */
@Singleton
@Startup
@Path("metrics")
public class MetricService {

	private ConcurrentHashMap<String, AtomicLong> metricTotalProcessing = new ConcurrentHashMap<String, AtomicLong>();
	private ConcurrentHashMap<String, AtomicLong> metricTotalDocuments = new ConcurrentHashMap<String, AtomicLong>();

	private static Logger logger = Logger.getLogger(MetricService.class.getName());

	@PostConstruct
	public void init() {
		// init metrics map
		metricTotalProcessing = new ConcurrentHashMap<String, AtomicLong>();
		metricTotalDocuments = new ConcurrentHashMap<String, AtomicLong>();
	}

	/**
	 * Get resource to export metrics in Prometheus text format
	 * 
	 * Example:
	 * 
	 * documents_total{method="load"} 66
	 * workitems_processed_total{type="workitem",modelversion="auftrag-1.0.0",task="7000",event="10",workflowgroup="Auftrag",workflowstatus="Neuanlage"}
	 * 1
	 * 
	 */
	@GET
	@Produces({ "text/plain; version=0.0.4" })
	public Response getMetrics() {

		StreamingOutput stream = new StreamingOutput() {
			@Override
			public void write(OutputStream os) throws IOException, WebApplicationException {
				Writer writer = new BufferedWriter(new OutputStreamWriter(os));
				// Timestamps in the exposition format should generally be avoided

				// write documents metrics
				writer.write("# HELP documents_total The total number of accessed documents." + "\n");
				writer.write("# TYPE documents_total counter" + "\n");
				// iterate over all collected metrics....

				Iterator<Map.Entry<String, AtomicLong>> it = metricTotalDocuments.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, AtomicLong> metric = it.next();
					writer.write(metric.getKey() + " " + metric.getValue().get() + "\n");
				}

				// write processing metrics
				writer.write("# HELP workitems_processed_total The total number of workitems processeds." + "\n");
				writer.write("# TYPE workitems_processed_total counter" + "\n");
				// iterate over all collected metrics....
				it = metricTotalProcessing.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, AtomicLong> metric = it.next();
					writer.write(metric.getKey() + " " + metric.getValue().get() + "\n");
				}
				// note: The last line must end with a line feed character. Empty lines are
				// ignored.
				writer.flush();
			}
		};

		return Response.ok(stream).build();
	}

	/**
	 * ProcessingEvent listener to generate a metric.
	 * 
	 * @param processingEvent
	 * @throws AccessDeniedException
	 */
	public void onProcessingEvent(@Observes ProcessingEvent processingEvent) throws AccessDeniedException {
		if (processingEvent == null)
			return;

		long l = System.currentTimeMillis();

		if (ProcessingEvent.AFTER_PROCESS == processingEvent.getEventType()) {
			String metric = buildProcessingMetric(processingEvent);
			AtomicLong counter = metricTotalProcessing.get(metric);
			if (counter == null) {
				counter = new AtomicLong();
			}
			// write metric
			counter.incrementAndGet();
			metricTotalProcessing.put(metric, counter);
			logger.fine("...metric " + metric + " collected in " + (System.currentTimeMillis() - l) + "ms");
		}
	}

	/**
	 * DocumentEvent listener to generate a metric.
	 * 
	 * @param documentEvent
	 * @throws AccessDeniedException
	 */
	public void onDocumentEvent(@Observes DocumentEvent documentEvent) throws AccessDeniedException {
		if (documentEvent == null)
			return;

		long l = System.currentTimeMillis();

		String metric = buildDocumentMetric(documentEvent);
		if (metric == null) {
			return;
		}
		AtomicLong counter = metricTotalDocuments.get(metric);
		if (counter == null) {
			counter = new AtomicLong();
		}
		// write metric
		counter.incrementAndGet();
		metricTotalDocuments.put(metric, counter);
		logger.fine("...metric " + metric + " collected in " + (System.currentTimeMillis() - l) + "ms");

	}

	/**
	 * This method builds a prometheus metric from a DocumentEvent object containing
	 * the lables save, load delete
	 * 
	 * @return
	 */
	private static String buildDocumentMetric(DocumentEvent event) {
		if (DocumentEvent.ON_DOCUMENT_SAVE == event.getEventType()) {
			return "documents_total{method=\"save\"}";
		}

		if (DocumentEvent.ON_DOCUMENT_LOAD == event.getEventType()) {
			return "documents_total{method=\"load\"}";
		}

		if (DocumentEvent.ON_DOCUMENT_DELETE == event.getEventType()) {
			return "documents_total{method=\"delete\"}";
		}

		return null;

	}

	/**
	 * This method builds a prometheus metric from a ProcessingEvent object
	 * containing the lables task, event, type, workflowgroup, worklowstatus,
	 * modelversion
	 * 
	 * @return
	 */
	private static String buildProcessingMetric(ProcessingEvent event) {
		String metric = "workitems_processed_total";

		// add lables
		metric += "{";
		metric += "type=\"" + event.getDocument().getType() + "\",";
		metric += "modelversion=\"" + event.getDocument().getModelVersion() + "\",";
		metric += "task=\"" + event.getDocument().getTaskID() + "\",";
		metric += "event=\"" + event.getDocument().getItemValueInteger("$lastevent") + "\",";
		metric += "workflowgroup=\"" + event.getDocument().getItemValueString(WorkflowKernel.WORKFLOWGROUP) + "\",";
		metric += "workflowstatus=\"" + event.getDocument().getItemValueString(WorkflowKernel.WORKFLOWSTATUS) + "\"";
		metric += "} ";

		return metric;

	}
}
