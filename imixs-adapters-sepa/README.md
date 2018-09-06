# imixs-sepa-adapter

The _imixs-sepa-adapter_ provides services to export workflow data into a sepa file.


## Model Based Configuration

The _imixs-sepa-adapter_ can be combined with different kind of workflow models. The SEPA export is configured by the SEPA Model

<img src="sepa-export.png" />

This model must at least define the following Events:


 * SEPA Export Finished = 100
 * SEPA Export Failed = 200
 
Other events can be defined based on the required business logic. 


## The SepaScheduler

The SEPA export is managed by the SeapScheduler which is an implementation of the interface _org.imxis.workflow.scheduler.Scheduler_.
The scheduler configuration obejct must at least prvide the following items:

 * _query = invoice selector (lucene query)
 * _gropby = optional group by definition to group selected invoices
 * _IBAN = default IBAN for the SEPA export file 
 * _BIC = default BIC for the SEPA export file
 
The SepaScheduler automatically select invoices based on the definition provided by the item _query. 
Selected invoices can be optional grouped by the definition of the item _groupby. 

Example:

	_query= (type:"workitem" AND $modelversion:"invoice-1.0.0")
	
	_groupBy= _mandator
	
This example configuration will select all invoices form the Model _invoice-1.0.0_. The selected invoices will than be grouped by the applied invoice item _mandator_. For each group a separate SEPA Export Workflow will be started.   
The item _groupBy can be used in various ways. For example if an invoice provide a mandator IBAN, than the invoices can be grouped by tis IBAN attribute. This allows the export of separate SEPA files for differnet mandators. 

