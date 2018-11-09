<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
	<xsl:strip-space elements="*" />
	<xsl:output method="xml" indent="yes" encoding="UTF-8"
		standalone="yes" />

	<xsl:template match="/">

		<xsl:variable name="date"
			select="/data/document/item[@name='$modified']/value" />
		<xsl:variable name="subject"
			select="/data/document/item[@name='_subject']/value" />
		<xsl:variable name="currency"
			select="/data/document/item[@name='_currency']/value" />
		<xsl:variable name="invoiceid"
			select="/data/document/item[@name='_invoicenumber']/value" />
		<xsl:variable name="gegenkonto"
			select="/data/document/item[@name='_kreditor_konto']/value" />

		<LedgerImport
			xmlns="http://xml.datev.de/bedi/tps/ledger/v040"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://xml.datev.de/bedi/tps/ledger/v040 Belegverwaltung_online_ledger_import_v040.xsd"
			version="4.0" generator_info="Imixs Office Workflow"
			generating_system="Imixs Workflow"
			xml_data="Kopie nur zur Verbuchung berechtigt nicht zum Vorsteuerabzug">

			<consolidate>
				<xsl:attribute name="consolidatedAmount"><xsl:value-of
					select="/data/document/item[@name='_amount_brutto']/value" /></xsl:attribute>
				<xsl:attribute name="consolidatedDate"><xsl:value-of
					select="format-dateTime($date, '[Y0001]-[M01]-[D01]')" /></xsl:attribute>
				<xsl:attribute name="consolidatedInvoiceId"><xsl:value-of
					select="$invoiceid" /></xsl:attribute>
				<xsl:attribute name="consolidatedCurrencyCode"><xsl:value-of
					select="$currency" /></xsl:attribute>



				<xsl:for-each
					select="/data/document/item[@name='_childitems']/value">

					<accountsPayableLedger>
						<date>
							<xsl:value-of
								select="format-dateTime($date, '[Y0001]-[M01]-[D01]')" />
						</date>
						<amount>
							<xsl:value-of select="./item[@name='_amount']/value" />
						</amount>
						<accountNo>
							<xsl:value-of select="./item[@name='_konto']/value" />
						</accountNo>
						<buCode>
							<xsl:value-of select="./item[@name='_tax']/value" />
						</buCode>
						<information>
							<xsl:value-of select="$subject" />
						</information>
						<currencyCode>
							<xsl:value-of select="$currency" />
						</currencyCode>
						<invoiceId>
							<xsl:value-of select="$invoiceid" />
						</invoiceId>
						<bpAccountNo>
							<xsl:value-of select="$gegenkonto" />
						</bpAccountNo>
					</accountsPayableLedger>
				</xsl:for-each>

			</consolidate>


		</LedgerImport>

	</xsl:template>
</xsl:stylesheet>