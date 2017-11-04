# Imixs-Documents

The Imixs-Documents is a adapter project for collecting textual information from attached documents during the processing phase.
This extracted text information is added into the item '_dms_' wich can be part of the lucene full-text-index. The DMS meta data is handled by the DMSPlugin.


## Parsers
 
The Imixs-Document adapter supports two kind of parsers

 * DocumentCoreParser
 
 * DocumentTikaParser
 
### The DocumentCoreParser

The DocumentCoreParser is based on the apache libraris '[apache-poi](http://poi.apache.org/)' and '[apache-pdfbox](pdfbox.apache.org)'. The parser supports the following file typs:
 
  * .pdf - PDF Documents
  * .doc - MS-Office doc files
  * .docx - MS-Office docx files
  
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

	

### The DocumentTikaParser

The DocumentTikaParser is based on the [apache tika project](https://tika.apache.org/). 
The Apache Tika™ toolkit detects and extracts metadata and text from over a thousand different file types (such as PPT, XLS, and PDF). All of these file types can be parsed through a single interface, making Tika useful for search engine indexing, content analysis. 

  
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
