# Imixs Apache POI

This adapter module provides services to export the result of an Imixs-Report into a Excel file. The implementation is based on
[Apache POI](https://poi.apache.org/). 

We use XSSF to read and write an OOXML Excel file (XLSX). 

<img src="screen-01.png" width="700px"/>


## Imixs-Office-Workflow

The project [Imixs-Office-Worklfow](https://github.com/imixs/imixs-office-workflow) provides a interface to export Imixs-Reports with one of the following content types:

 - vnd.openxmlformats-officedocument. spreadsheetml.sheet 
 - application/vnd.ms-excel

The feature is automatically integrated into the UI

<img src="screen-02.png" width="700px"/>

## The Rest API

The POI Adapter module extends the [Imixs-Workflow Report Rest API](https://www.imixs.org/doc/restapi/reportservice.html) with an additional resource named 'poi'. 



| URI                                           | Description                               					   | 
|-----------------------------------------------|------------------------------------------------------------------|
| /poi/report/{name}.xlsx                       | generates an Excel file containing the result-set of a report definition                        |

The contentType and the processing instrcutions (XSL) of a  report definition will be ignored.




## POI FindReplace Adapter

With the adapter class *org.imixs.workflow.poi.POIFindReplaceAdapter* it is possible to update Word documents (.docx) or Excel documents (.xls, .xlsx).


### Word Documents

The adapter can replace text fragments in a paragraph of a MS Word document. The adapter uses the method XWPFRun.setText(String) and goes through the file until it finds the corresponding paragraph. The result document is saved in the current workitem.

The following example searches for an attachment with the name 'Agreement-????.docx' and replaces the text fragments *[company.name]*, *[company.country]* and *[contract.startdate]* with the corresponding item values.

    <poi-update name=
       "filename">Agreement-<itemvalue>numsequencenumber</itemvalue>.docx</poi-update>
    <poi-update name="findreplace">
         <find>[company.name]</find>
         <replace><itemvalue>company.name</itemvalue></replace>
    </poi-update>
    <poi-update name="findreplace">
         <find>[company.country]</find>
         <replace><itemvalue>company.country</itemvalue></replace>
    </poi-update>
    <poi-update name="findreplace">
         <find>[contract.startdate]</find>
         <replace><itemvalue format="EEE, MMM d, yyyy">contract.start</itemvalue></replace>
    </poi-update>


### Excel Documents

If you have a Excel Sheet than you can replace cell values be specifying the cell position or the cell name (cell reference):

	<poi-update name="filename">Invoice-<itemvalue>numsequencenumber</itemvalue>.xlsx</poi-update>
	<poi-update name="findreplace">
	       <find>A10</find>
	       <replace><itemvalue>company.name</itemvalue></replace>
	</poi-update>
	<poi-update name="findreplace">
	       <find>TOTAL_SUM</find>
	       <replace><itemvalue>invoice.total</itemvalue></replace>
	</poi-update>

The cell 'A10' is selected by row/column position, the cell 'TOTAL_SUM' by a cell reference which is a fixed name. 

### Regular Expressions for Document Name

You can also define the filename as a pattern including regular expressions. See the following example:

	<poi-update name="filename">.*<itemvalue>numsequencenumber</itemvalue>\.docx</poi-update>

This expression will match all files ending with the sequence number and the file extension '.docx'

## POI CopyContent Adapter

With the adapter class *org.imixs.workflow.poi.POICopyContentAdapter* it is possible to update the items of a workitem with cell values from Excel documents (.xls, .xlsx). The adapter can copy values form a cell. 

The following example searches for an attachment with the name 'Invoice-????.xlsx' and copies the cell F26  into the item 'company.name' and the cell with the name 'TOTAL_SUM' into the item 'invoice.total'


	<poi-copy name="filename">Invoice-<itemvalue>numsequencenumber</itemvalue>.xlsx</poi-copy>
	<poi-copy name="copy">
	    <find>F26</find>
	    <item>company.name</item>
	    <type>text</type>
	</poi-copy>
	<poi-copy name="copy">
	    <find>TOTAL_SUM</find>
	    <item>invoice.total</item>
	    <type>number</type>
	</poi-copy>     
	
A cell name can also be specified in extended expression:

	Sheet1!$F$26	

**Note:** To select a cell independent from newly inserted rows and columns you should name the cell and select the Cell by a named reference. 

### Value Types
	
Optional the value can be converted into one of the following object types:

 - date - Java Date object
 - number - Java Double 
 - text - String






# Development

## Maven


The imixs-adapter-poi module can be added into an application module. The module provides CDI and Rest API components. 

Add the following maven dependency into a parent project:


	<!-- POI Adapter -->
	<dependency>
		<groupId>org.imixs.workflow</groupId>
		<artifactId>imixs-adapters-poi</artifactId>
		<version>${org.imixs.adapters.version}</version>
		<scope>provided</scope>
	</dependency>
	