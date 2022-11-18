# Imixs ODF Adapter

This adapter module provides services to update the content of a ODF document. The implementation is based on
[LibreOFfice ODF Toolkit](https://odftoolkit.org/). 



## ODF FindReplace Adapter

With the adapter class `org.imixs.workflow.odf.ODFDOMFindReplaceAdapter` it is possible to update the content of ODF documents (.odf).


### ODF Documents

The adapter can replace text fragments in a paragraph of a ODF document. The adapter uses the ODF Toolkit to find and replace all paragraphs matching a given Regular expression. The result document is saved in the current workitem.

The following example searches for an attachment with the name `Agreement-????.odf` and replaces the text fragments `[company.name]`, `[company.country]` and `[contract_startdate]` with the corresponding item values.

    <poi-update name=
       "filename">Agreement-<itemvalue>numsequencenumber</itemvalue>.docx</poi-update>
    <poi-update name="findreplace">
         <find>\[company\.name\]</find>
         <replace><itemvalue>company.name</itemvalue></replace>
    </poi-update>
    <poi-update name="findreplace">
         <find>\[company\.country\]</find>
         <replace><itemvalue>company.country</itemvalue></replace>
    </poi-update>
    <poi-update name="findreplace">
         <find>\[contract\.startdate\]</find>
         <replace><itemvalue format="EEE, MMM d, yyyy">contract.start</itemvalue></replace>
    </poi-update>


### Regular Expressions for Document Name

You can also define the filename as a pattern including regular expressions. See the following example:

	<poi-update name="filename">.*<itemvalue>numsequencenumber</itemvalue>\.odf</poi-update>

This expression will match all files ending with the sequence number and the file extension '.docx'



# Development

## Maven


The imixs-adapter-poi module can be added into an application module. The module provides CDI and Rest API components. 

Add the following maven dependency into a parent project:


	<!-- ODF Adapter -->
	<dependency>
		<groupId>org.imixs.workflow</groupId>
		<artifactId>imixs-adapters-odf</artifactId>
		<version>${org.imixs.adapters.version}</version>
		<scope>provided</scope>
	</dependency>
	