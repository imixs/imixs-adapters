# Imixs Prometheus Adapter

This adapter module provides a rest service endpoint exposing Imxis-Workflow metrics. 

The metric can be collected by the following Rest Service resource:

	http://...../api/metrics


This is an example how a Imixs-Workflow metric can look like:



	# HELP documents_total The total number of accessed documents.
	# TYPE documents_total counter
	documents_loaded_total{method="load"} 98
	documents_deleted_total{method="delete"} 3
	documents_saved_total{method="save"} 6
	# HELP workitems_processed_total The total number of workitems processeds.
	# TYPE workitems_processed_total counter
	workitems_processed_total{type="workitem",modelversion="protokoll-de-2.0.0",task="1100",event="10",workflowgroup="Protokoll",workflowstatus="Erstellung"}  1
	workitems_processed_total{type="workitem",modelversion="protokoll-de-2.0.0",task="1500",event="100",workflowgroup="Protokoll",workflowstatus="Freigegeben"}  2



# Development

## Maven


The imxis-prometheus-adapter module can be added into an applicaton module. The module provides CDI and EJB components. 

Add the following maven dependency into a parent project:


	<!-- Prometheus Adapter -->
	<dependency>
		<groupId>org.imixs.workflow</groupId>
		<artifactId>imixs-adapters-prometheus</artifactId>
		<version>${org.imixs.adapters.version}</version>
		<scope>provided</scope>
	</dependency>
	