# Imixs-Documents

The Imixs-Documents is a adapter project for collecting textual information from attached documents during the processing phase.
The extracted textual information can be added into a workitem for further processing. For example the textual OCR information 
from a PDF document can be stored into the item '_dms_'. In this way a fulltext search is supported. But also other information like embedded XML files can be process in various ways. 


## Document Parser Plugins

The Imixs-Document adapter supports two kind of parsers

 * DocumentCoreParser (supports a core set of office documents)
 
 * DocumentTikaParser (supports all kind of documents)
 
To activate the parsing process one of the following plugins can be configured into a BPMN model. 

	org.imixs.workflow.documents.DocumentCoreParserPlugin

	org.imixs.workflow.documents.DocumentTikaParserPlugin

See details about the different parsers below. 

### Searching Documents

Imixs-Documents provide the feature to search also for the content of parsed documents. To activate this feature, the item 'dms' must be included into the lucene fulltext index. You can add the attribute to the lucene configuration in the imixs.properties file:

	lucence.fulltextFieldList=.....,dms
	  


## PDF XML Plugin

The plugin class "org.imixs.workflow.documents.parser.PDFXMLPlugin can be used to extract a embedded file from a PDF document and convert the data into a Imixs Workitem. 

The plugin can be activated by the BPMN Model with the following result definition: 


	<item name="PDFXMLPlugin">
		<filename>*.xml</filename>
	    <report>myReport</report>
	</item>

The Item "PDFXMLPlugin" provides the following processing instructions for the PDFXMLPlugin:

 * filename - regular expression to select embedded files
 * report - imixs-report definition to convert the xml into a Imixs WorkItem. 


###  ZUGFeRD Support

ZUGFeRD is a standard for electronic invoices in PDF/A format. The invoice data is stored in a embedded XML file. With the XMLDocumentPlugin this data can be transformed and embedded into a WorkItem. The project includes examples how to transform a ZUGFeRD invoice into a Imixs WorkItem. 


# How to Install

To include the imixs-adapters-documents plugins the following jar file must be part of the EJB module:

	imixs-adapters-documents-${imixs.adapters.version}.jar
 
## The DocumentCoreParser

The DocumentCoreParser is based on the apache libraris '[apache-poi](http://poi.apache.org/)' and '[apache-pdfbox](pdfbox.apache.org)'. The parser supports the following file typs:
 
  * .pdf - PDF Documents
  * .doc - MS-Office doc files
  * .docx - MS-Office docx files
 
 
 
### Deployment

Add the following artifact versions into the master pom.xml

		<!-- Imixs-Adaters -->
		<org.imixs.adapters.version>1.5.2-SNAPSHOT</org.imixs.archive.version>
		<apache.poi.version>3.17</apache.poi.version>
		<apache.pdfbox.version>2.0.7</apache.pdfbox.version>
		
		  
To integrate the DocumetCoreParser the application must include the following dependencies:

	<dependency>
		<groupId>org.apache.poi</groupId>
		<artifactId>poi-scratchpad</artifactId>
		<version>${apache.poi.version}</version>
	</dependency>
	<dependency>
		<groupId>org.apache.pdfbox</groupId>
		<artifactId>pdfbox</artifactId>
		<version>${apache.pdfbox.version}</version>
	</dependency>

	

## The DocumentTikaParser

The DocumentTikaParser is based on the [apache tika project](https://tika.apache.org/). 
The Apache Tika™ toolkit detects and extracts metadata and text from over a thousand different file types (such as PPT, XLS, and PDF). All of these file types can be parsed through a single interface, making Tika useful for search engine indexing, content analysis. 


### Deployment



Add the following artifact versions into the master pom.xml

		<!-- Imixs-Adaters -->
		<org.imixs.adapters.version>1.5.2-SNAPSHOT</org.imixs.archive.version>
		<apache.tika.version>1.16</apache.tika.version>

  
To integrate the DocumetTikaParser the application must include the following dependencies:

	<dependency>
		<groupId>org.apache.tika</groupId>
		<artifactId>tika-core</artifactId>
		<version>${apache.tika.version}</version>
	</dependency>
	<dependency>
		<groupId>org.apache.tika</groupId>
		<artifactId>tika-parsers</artifactId>
		<version>${apache.tika.version}</version>
	</dependency>

**Notes for Wildfly:** 
As tika has a bunch of dependencies it is recommended to deploy tika as a module under wildfly.
	