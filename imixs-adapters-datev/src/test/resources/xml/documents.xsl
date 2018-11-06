<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
	<xsl:strip-space elements="*" />
	<xsl:output method="xml" indent="yes" encoding="UTF-8"
		standalone="yes" />

	<xsl:template match="/">

		<xsl:variable name="now" select="current-dateTime()" />

		<archive xmlns="http://xml.datev.de/bedi/tps/document/v04.0"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://xml.datev.de/bedi/tps/document/v04.0 Document_v040.xsd"
			version="4.0" generatingSystem="DATEV-Musterdaten">
			<header>
				<date>
					<xsl:value-of
						select="format-dateTime($now, '[Y0001]-[M01]-[D01]T[H01]:[m01]:[s01]')" />
				</date>
				<description>Belegsatzdaten Eingangsrechnung</description>
				<clientName>Muster GmbH</clientName>
			</header>
			<content>

				<xsl:for-each select="/data/document">

					<xsl:variable name="filename"
						select="./item[@name='$file']/value/item/@name" />
					<xsl:variable name="date"
						select="./item[@name='$modified']/value" />
						

					<document>
						<extension>
							<xsl:attribute name="xsi:type">accountsPayableLedger</xsl:attribute>
							<xsl:attribute name="datafile">
							<xsl:value-of
								select="./item[@name='$uniqueid']/value" /><xsl:text>.xml</xsl:text>
								</xsl:attribute>
							<property>
								<xsl:attribute name="value"><xsl:value-of select="format-dateTime($date, '[Y0001]-[M01]')"/></xsl:attribute>
								<xsl:attribute name="key">1</xsl:attribute>
							</property>
							<property>
								<xsl:attribute name="value">Eingangsrechnungen</xsl:attribute>
								<xsl:attribute name="key">3</xsl:attribute>
							</property>
						</extension>
						<extension>
							<xsl:attribute name="xsi:type">File</xsl:attribute>
							<xsl:attribute name="name"><xsl:value-of
								select="$filename" /></xsl:attribute>
						</extension>
					</document>



				</xsl:for-each>


			</content>
		</archive>
	</xsl:template>
</xsl:stylesheet>