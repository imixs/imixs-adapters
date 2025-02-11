<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

	<xsl:output method="text" indent="no" byte-order-mark="yes" encoding="UTF-8"
		omit-xml-declaration="yes" />
	<xsl:strip-space elements="*" />
	<xsl:template match="/">

		<xsl:apply-templates
			select="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'DATEV-Export']" />
	

	<xsl:apply-templates
			select="/data/document[normalize-space(item[@name = '$workflowgroup']/value) != 'DATEV-Export']" />
	</xsl:template>


	<!-- This template builds datev header info -->
	<xsl:template
		match="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'DATEV-Export']">

		<xsl:variable name="date" select="item[@name='$modified']/value" />
		<xsl:variable
			name="stapelstart" select="item[@name='datev.stapelzeitraum.start']/value" />
		<xsl:variable
			name="stapelende" select="item[@name='datev.stapelzeitraum.ende']/value" />
		<xsl:variable
			name="fiscalstart" select="item[@name='datev.fiscal_start']/value" />


		<!-- Header -->
<xsl:text>"EXTF";510;21;"Buchungsstapel";7;</xsl:text>
<xsl:value-of
			select="format-dateTime($date, '[Y0001][M01][D01][H01][m01][s01][f001]')"></xsl:value-of>
<xsl:text>;;"RE";"ImixsWorkflow";"";</xsl:text>
<xsl:value-of
			select="item[@name='datev.consultant.id']/value" /><xsl:text>;</xsl:text>
<xsl:value-of
			select="item[@name='datev.client.id']/value" /><xsl:text>;</xsl:text>
<xsl:value-of
			select="$fiscalstart"></xsl:value-of><xsl:text>;</xsl:text>
		<!-- Sachkontennumernlaenge mandantenabhaengig - defautl = 6 -->
<xsl:choose>
			<xsl:when test="string-length(./item[@name='datev.sachkontennummernlaenge']/value) > 0">
				<xsl:value-of select="item[@name='datev.sachkontennummernlaenge']/value" /><xsl:text>;</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>6;</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
<xsl:value-of
			select="format-dateTime($stapelstart, '[Y0001][M01][D01]')" /><xsl:text>;</xsl:text>
<xsl:value-of
			select="format-dateTime($stapelende, '[Y0001][M01][D01]')" /><xsl:text>;</xsl:text>
<xsl:text>"</xsl:text><xsl:value-of
			select="concat('Rechnungen ',format-dateTime($date, '[D01][M01]'))" /><xsl:text>"</xsl:text><xsl:text>;</xsl:text>
<xsl:text>"";1;0;0;;;;;;;;;"";""</xsl:text>

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
<xsl:text>KOST1 – Kostenstelle;KOST2 – Kostenstelle;KOST-Menge;EU-Mitgliedstaat u. USt-IdNr.;EU-Steuersatz;Abw. Versteuerungsart;Sachverhalt L+L;Funktionsergänzung L+L;BU 49 Hauptfunktionstyp;BU 49 Hauptfunktionsnummer;BU 49 Funktionsergänzung;</xsl:text>
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


	<!-- This template builds datev body info for each invoice 
	     wir nehmen jedes workitem das kein DATEV-Export ist - also invoices oder Data Export items (IW24
	repimport) -->
	<xsl:template
		match="/data/document[normalize-space(item[@name = '$workflowgroup']/value) != 'DATEV-Export']">

		<xsl:variable
			name="belegdatum" select="item[@name='datev.belegdatum']/value" />  
		<xsl:variable
			name="belegfeld1" select="item[@name='datev.belegfeld1']/value" />
		<xsl:variable
			name="gegenkonto" select="item[@name='datev.gegenkonto']/value" />
		<xsl:variable
			name="buchungstext" select="substring(item[@name='$workflowsummary']/value,1,59)" />
		<xsl:variable
			name="uniqueid" select="item[@name='$uniqueid']/value" />
		<xsl:for-each
			select="item[@name='datev.booking.list']/value">
			<xsl:variable name="betrag"
				select="replace(./item[@name='datev.betrag']/value, '\.', ',')" />
			<xsl:variable
				name="abs_betrag" select="replace($betrag, '-', '')" />
			<xsl:value-of
				select="$abs_betrag" /><xsl:text>;</xsl:text>
			<!-- S / H  -->
			<xsl:if
				test="not(starts-with($betrag, '-'))">
				<xsl:text>S;</xsl:text>
			</xsl:if>
			<xsl:if test="(starts-with($betrag, '-'))">
				<xsl:text>H;</xsl:text>
			</xsl:if>
			<xsl:text>;;;;</xsl:text>
			<xsl:value-of
				select="./item[@name='datev.konto']/value" /><xsl:text>;</xsl:text>
			<xsl:value-of
				select="$gegenkonto" /><xsl:text>;</xsl:text>
			<xsl:value-of
				select="./item[@name='datev.buschluessel']/value" /><xsl:text>;</xsl:text>
			<xsl:value-of
				select="format-dateTime($belegdatum, '[D01][M01]')" /><xsl:text>;</xsl:text>
			<xsl:value-of
				select="$belegfeld1" /><xsl:text>;</xsl:text>
			<xsl:text>;;</xsl:text>
			<xsl:value-of
				select="$buchungstext" /><xsl:text>;</xsl:text>
			<xsl:text>;;;;;</xsl:text>

			<!-- Beleglink -->		
			<xsl:text>BEDI "</xsl:text><xsl:value-of
				select="$uniqueid" /><xsl:text>";</xsl:text>	 				
			<xsl:text>;;;;;;;;;;;;;;;;</xsl:text>
			<xsl:value-of
				select="./item[@name='datev.kostenstelle1']/value" /><xsl:text>;</xsl:text>
			<xsl:value-of
				select="./item[@name='datev.kostenstelle2']/value" /><xsl:text>;</xsl:text>
			<xsl:text>;;;;;;;;;;;;;;</xsl:text>
			<xsl:text>&#xa;</xsl:text>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>