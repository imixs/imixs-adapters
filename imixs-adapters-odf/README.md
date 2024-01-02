# Imixs ODF Adapter

This adapter module provides services to update the content of a ODF document. The implementation is based on
[LibreOffice ODF Toolkit](https://odftoolkit.org/). 

## ODF FindReplace Adapter

With the adapter class `org.imixs.workflow.odf.ODFDOMFindReplaceAdapter` it is possible to update the content of ODF documents (.odf).


### ODF Documents

The adapter can replace text fragments in a paragraph of a ODF document. The adapter uses the ODF Toolkit to find and replace all paragraphs matching a given Regular expression. The result document is saved in the current workitem.

The following example searches for an attachment with the name `Agreement-????.odf` and replaces the text fragments `#:company.name:#`, `#:company.country:#` and `#:contract_startdate:#` with the corresponding item values provided by the workitem.

```xml
<odf-update>
   <filename>Agreement-<itemvalue>numsequencenumber</itemvalue>.odf</filename>
   <replace>
      <key>#:company.name:#</key>
      <value><itemvalue>company.name</itemvalue></value>
   </replace>
   <replace>
      <key>#:company\.country:#</key>
      <value><itemvalue>company.country</itemvalue></value>
   </replace>
   <replace>
      <key>#:contract\.startdate:#</key>
      <value><itemvalue format="EEE, MMM d, yyyy">contract.start</itemvalue></value>
   </replace>
</odf-update>
```

### Regular Expressions for Document Name

You can also define the filename as a pattern including regular expressions. See the following example:

	<filename>.*<itemvalue>numsequencenumber</itemvalue>\.odf</filename>

This expression will match all files ending with the sequence number and the file extension '.odf'


### TextDocuments and Regular Expressions

The find replace feature of the ODFToolkit supports regular expressions to search for content. For example

```xml
<odf-update>
   ...
   <replace>
      <key>(Order)\w+</find>
      <value><itemvalue>order.number</itemvalue></value>
   </replace>
</odf-update>
```

will replace all words starting with 'Order' like 'OrderNumber' , or 'OrderName' with the current value of the item 'order.number'

### SpreadSheets

To update a SpreadSheet cell (.ods files) you can simply specify the cell position. See the following Example:

```xml 
<odf-update>
   <filename>order_<itemvalue>order.number</itemvalue>.ods</filename>
   <replace>
      <key>A5</key>
      <value><itemvalue>company.name</itemvalue></value>
   </replace>
   <replace>
      <key>B18</key>
      <value><itemvalue>total</itemvalue></value>
   </replace>
   <replace>
      <key>C18</key>
      <value><itemvalue format="dd.MM.yyyy">$lasteventdate</itemvalue></value>
   </replace>
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
	