# Imixs ODF Adapter

This adapter module provides services to update the content of a ODF document. The implementation is based on
[LibreOffice ODF Toolkit](https://odftoolkit.org/). 

## ODF FindReplace Adapter

With the adapter class `org.imixs.workflow.odf.ODFDOMFindReplaceAdapter` it is possible to update the content of ODF documents (.odf).


### ODF Documents

The adapter can replace text fragments in a paragraph of a ODF document. The adapter uses the ODF Toolkit to find and replace all paragraphs matching a given Regular expression. The result document is saved in the current workitem.

The following example searches for an attachment with the name `Agreement-????.odf` and replaces the text fragments `#:company.name:#`, `#:company.country:#` and `#:contract_startdate:#` with the corresponding item values provided by the workitem.

```xml
<odf-update name=
     "filename">Agreement-<itemvalue>numsequencenumber</itemvalue>.odf</odf-update>
<odf-update name="findreplace">
     <find>#:company.name:#</find>
     <replace><itemvalue>company.name</itemvalue></replace>
</odf-update>
<odf-update name="findreplace">
     <find>#:company\.country:#</find>
     <replace><itemvalue>company.country</itemvalue></replace>
</odf-update>
<odf-update name="findreplace">
     <find>#:contract\.startdate:#</find>
     <replace><itemvalue format="EEE, MMM d, yyyy">contract.start</itemvalue></replace>
</odf-update>
```

### Regular Expressions for Document Name

You can also define the filename as a pattern including regular expressions. See the following example:

	<odf-update name="filename">.*<itemvalue>numsequencenumber</itemvalue>\.odf</odf-update>

This expression will match all files ending with the sequence number and the file extension '.odf'


### TextDocuments and Regular Expressions

The find replace feature of the ODFToolkit supports regular expressions to search for content. For example

```xml
<odf-update name="findreplace">
   <find>(Order)\w+</find>
   <replace><itemvalue>order.number</itemvalue></replace>
</odf-update>
```

will replace all words starting with 'Order' like 'OrderNumber' , or 'OrderName' with the current value of the item 'order.number'

### SpreadSheets

To update a SpreadSheet cell (.ods files) you can simply specify the cell position. See the following Example:

```xml 
<odf-update name="filename">order_<itemvalue>order.number</itemvalue>.ods</odf-update>
<odf-update name="findreplace">
   <find>A5</find>
   <replace><itemvalue>company.name</itemvalue></replace>
</odf-update>
<odf-update name="findreplace">
   <find>B18</find>
   <replace><itemvalue>total</itemvalue></replace>
</odf-update>
<odf-update name="findreplace">
   <find>C18</find>
   <replace><itemvalue format="dd.MM.yyyy">$lasteventdate</itemvalue></replace>
</odf-update>

```

This example updates a text, a date and a number value. 

### Formulas

The Imixs-Adapter-ODF Project is based on the [ODF Toolkit](https://odftoolkit.org/) which is a lightweight Java library to create, read and update the data of ODF documents. Unlike other approaches, which rely on runtime manipulation of heavy-weight editors via an automation interface, you can only update the content of the (xml)document. But you have no access to any application interface like LibreOffice. 

So there is no API to recompute cell values based on a formula. But you can calculate the results of your changes manually and update the corresponding cell. The ODFToolkit keeps your original formulas intact. In this way changes, made later in an editor like LibreOffice or Collabora, are automatically recalculated by your origin formulas.


# Development

## Maven


The imixs-adapter-odf module can be added into an application module. The module provides CDI and Rest API components. 

Add the following maven dependency into a parent project:


	<!-- ODF Adapter -->
	<dependency>
		<groupId>org.imixs.workflow</groupId>
		<artifactId>imixs-adapters-odf</artifactId>
		<version>${org.imixs.adapters.version}</version>
		<scope>provided</scope>
	</dependency>
	