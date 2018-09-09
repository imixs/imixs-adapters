<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="text" indent="no" encoding="ISO-8859-1" 
		omit-xml-declaration="yes" />
	<xsl:strip-space elements="*" />

	
	<xsl:template match="/">
	
	        
           Status:<xsl:value-of select="data/document/item[@name='_amount']/value" />
           IBAN:<xsl:value-of select="data/document/item[@name='_IBAN']/value" />
   </xsl:template>
</xsl:stylesheet>