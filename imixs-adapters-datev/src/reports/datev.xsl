<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
	
	<xsl:output method="text" indent="no" encoding="UTF-8"
		omit-xml-declaration="yes" />
	<xsl:strip-space elements="*" />
	<xsl:template match="/">

	<xsl:apply-templates
			select="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'DATEV-Export']" />
	

	<xsl:apply-templates
		select="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'Rechnungseingang']" />
	</xsl:template>



	<!-- This template builds datev header info -->
	<xsl:template
		match="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'DATEV-Export']">

		<xsl:variable name="date" select="item[@name='$modified']/value"/>
		<xsl:variable name="datevdate" select="concat(substring($date,0,5),substring($date,6,2),substring($date,9,2),substring($date,12,2),substring($date,15,2),substring($date,18,2),substring($date,21,3))"/>
	



		<!-- Header -->
<xsl:text>„EXTF“;510;21;“Buchungsstapel“;7;</xsl:text>
<xsl:value-of select="$datevdate"></xsl:value-of>
<xsl:text>;;“RE“;“ImixsWorkflow“;““;</xsl:text>
<xsl:value-of select="item[@name='_datev_consultantid']/value" /><xsl:text>;</xsl:text>
<xsl:value-of select="item[@name='_datev_clientid']/value" /><xsl:text>;</xsl:text>
<xsl:value-of select="concat(substring($date,0,5),'0101')"/><xsl:text>;</xsl:text>
<xsl:text>4;</xsl:text>
<xsl:value-of select="concat(substring($date,0,5),'0101')"/><xsl:text>;</xsl:text>
<xsl:value-of select="concat(substring($date,0,5),'1231')"/><xsl:text>;</xsl:text>
<xsl:text>“</xsl:text><xsl:value-of select="concat('Rechnungen ',substring($date,0,5),substring($date,6,2),substring($date,9,2))"/><xsl:text>“</xsl:text><xsl:text>;</xsl:text>
<xsl:text>““;1;0;0;;;;;;;;;““;““</xsl:text>

<xsl:text>&#xa;</xsl:text>

<xsl:text>Umsatz (ohne Soll-/Haben-Kennzeichen);Soll-/Haben-Kennzeichen;WKZ Umsatz;Kurs;Basisumsatz;WKZ Basisumsatz;</xsl:text>
<xsl:text>Konto;Gegenkonto(ohne BU-Schlüssel);BU-Schlüssel;Belegdatum;Belegfeld 1;Belegfeld 2;Skonto;</xsl:text>
<xsl:text>Buchungstext;Postensperre;Diverse Adressnummer;Geschäftspartnerbank;Sachverhalt;Zinssperre;Beleglink;</xsl:text>
<xsl:text>Beleginfo – Art 1;Beleginfo – Inhalt 1;</xsl:text>
<xsl:text>Beleginfo – Art 2;Beleginfo – Inhalt 2;</xsl:text>
<xsl:text>Beleginfo – Art 3;Beleginfo – Inhalt 3;</xsl:text>
<xsl:text>Beleginfo – Art 4;Beleginfo – Inhalt 4;</xsl:text>
<xsl:text>Beleginfo – Art 5;Beleginfo – Inhalt 5;</xsl:text>
<xsl:text>Beleginfo – Art 6;Beleginfo – Inhalt 6;</xsl:text>
<xsl:text>Beleginfo – Art 7;Beleginfo – Inhalt 7;</xsl:text>
<xsl:text>Beleginfo – Art 8;Beleginfo – Inhalt 8;</xsl:text>
<xsl:text>KOST1 – Kostenstelle;KOST2 – Kostenstelle;KOST-Menge;EU-Mitgliedstaat u. USt-IdNr.;EU-Steuersatz;Abw. Versteuerungsart;Sachverhalt L+L;Funktionsergänzung L+L;BU 49 Hauptfunktionstyp;BU 49 Hauptfunktionsnummer;BU 49
Funktionsergänzung;</xsl:text>
<xsl:text>Zusatzinformation – Art 1;Zusatzinformation – Inhalt 1;</xsl:text>
<xsl:text>Zusatzinformation – Art 2;Zusatzinformation – Inhalt 2;</xsl:text>
<xsl:text>Zusatzinformation – Art 3;Zusatzinformation – Inhalt 3;</xsl:text>
<xsl:text>Zusatzinformation – Art 4;Zusatzinformation – Inhalt 4;</xsl:text>
<xsl:text>Zusatzinformation – Art 5;Zusatzinformation – Inhalt 5;</xsl:text>
<xsl:text>Zusatzinformation – Art 6;Zusatzinformation – Inhalt 6;</xsl:text>
<xsl:text>Zusatzinformation – Art 7;Zusatzinformation – Inhalt 7;</xsl:text>
<xsl:text>Zusatzinformation – Art 8;Zusatzinformation – Inhalt 8;</xsl:text>
<xsl:text>Zusatzinformation – Art 9;Zusatzinformation – Inhalt 9;</xsl:text>
<xsl:text>Zusatzinformation – Art 10;Zusatzinformation – Inhalt 10;</xsl:text>
<xsl:text>Zusatzinformation – Art 11;Zusatzinformation – Inhalt 11;</xsl:text>
<xsl:text>Zusatzinformation – Art 12;Zusatzinformation – Inhalt 12;</xsl:text>
<xsl:text>Zusatzinformation – Art 13;Zusatzinformation – Inhalt 13;</xsl:text>
<xsl:text>Zusatzinformation – Art 14;Zusatzinformation – Inhalt 14;</xsl:text>
<xsl:text>Zusatzinformation – Art 15;Zusatzinformation – Inhalt 15;</xsl:text>
<xsl:text>Zusatzinformation – Art 16;Zusatzinformation – Inhalt 16;</xsl:text>
<xsl:text>Zusatzinformation – Art 17;Zusatzinformation – Inhalt 17;</xsl:text>
<xsl:text>Zusatzinformation – Art 18;Zusatzinformation – Inhalt 18;</xsl:text>
<xsl:text>Zusatzinformation – Art 19;Zusatzinformation – Inhalt 19;</xsl:text>
<xsl:text>Zusatzinformation – Art 20;Zusatzinformation – Inhalt 20;</xsl:text>
<xsl:text>Stück;Gewicht;Zahlweise;Forderungsart;Veranlagungsjahr;Zugeordnete Fälligkeit;Skontotyp;Auftragsnummer;Buchungstyp;USt-Schlüssel (Anzahlungen);EU-Mitgliedstaat (Anzahlungen);Sachverhalt L+L (Anzahlungen):</xsl:text>
<xsl:text>EU-Steuersatz (Anzahlungen);Erlöskonto (Anzahlungen);Herkunft-Kz;Leerfeld;KOST-Datum;SEPA-Mandatsreferenz;Skontosperre;Gesellschaftername;Beteiligtennummer;Identifikationsnummer;Zeichnernummer;Postensperre bis;Bezeichnung SoBil-Sachverhalt;Kennzeichen SoBil-Buchung;Festschreibung;Leistungsdatum;Datum Zuord. Steuerperiode</xsl:text>
		<xsl:text>&#xa;</xsl:text>
	</xsl:template>


	<!-- This template builds datev body info for each invoice -->
	<xsl:template
		match="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'Rechnungseingang']">
		
		<xsl:variable name="date" select="item[@name='$modified']/value"/>
		<xsl:variable name="datevdate" select="format-dateTime($date, '[D01][M01]')" />
			
		 <xsl:for-each select="item[@name='_childitems']/value">
		
			<xsl:variable name="betrag" select="replace(./item[@name='_amount']/value, '\.', ',')"/>

			<xsl:value-of select="$betrag" />
			<xsl:text>;S;;;;</xsl:text>
			<xsl:value-of select="./item[@name='_konto']/value" /><xsl:text>;</xsl:text>
			<xsl:value-of select="./item[@name='_konto']/value" /><xsl:text>;</xsl:text>
			<xsl:text>;</xsl:text>
			<xsl:value-of select="$datevdate"/><xsl:text>;</xsl:text>
			<xsl:text>;;;;http://;;;;;;;;;;;;;</xsl:text>
			<xsl:text>&#xa;</xsl:text>
			
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>