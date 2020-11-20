<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	version="2.0">
	<xsl:strip-space elements="*" />
	<xsl:output method="xml" indent="yes" encoding="UTF-8" standalone="yes" />

	<xsl:template match="/">
	
		<xsl:variable name="now" select="current-dateTime()" />
	

		<Document
			xmlns="urn:iso:std:iso:20022:tech:xsd:pain.001.003.03"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="urn:iso:std:iso:20022:tech:xsd:pain.001.003.03 pain.001.003.03.xsd">
			<CstmrCdtTrfInitn>

				<!-- generate header info -->
				<!-- compute count of invoices -->
				<xsl:variable name="count"
					select="count(/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'Rechnungseingang']/item[@name='$uniqueid']/value)" />
				<!-- compute total amount -->
				<xsl:variable name="totalsum"
					select="xs:decimal(sum(/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'Rechnungseingang']/item[@name='invoice.total']/value))" />
				<!-- round to 2 digits -->
				<xsl:variable name="total"
					select="xs:decimal(round-half-to-even($totalsum, 2))" />

				<!-- shortcut for the sepa export document -->
				<xsl:variable name="exportWorkitem"
					select="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'SEPA-Export']" />

				<GrpHdr>
					<MsgId>
						<xsl:value-of
							select="replace($exportWorkitem/item[@name='$uniqueid']/value, '-', '')" />
					</MsgId>
					<CreDtTm><xsl:value-of
						select="format-dateTime($now, '[Y0001]-[M01]-[D01]T[H01]:[m01]:[s01]')" /></CreDtTm>
					<NbOfTxs>
						<xsl:value-of select="$count" />
					</NbOfTxs>
					<CtrlSum>
						<xsl:value-of select="$total" />
					</CtrlSum>
					<InitgPty>
						<Nm>
							<xsl:value-of
								select="$exportWorkitem/item[@name='dbtr.name']/value" />
						</Nm>
					</InitgPty>
				</GrpHdr>

				<PmtInf>
					<PmtInfId>
						<xsl:value-of
							select="replace($exportWorkitem/item[@name='$uniqueid']/value, '-', '')" /><xsl:text>-1</xsl:text>
					</PmtInfId>
					<PmtMtd>TRF</PmtMtd>
					<NbOfTxs>
						<xsl:value-of select="$count" />
					</NbOfTxs>
					<CtrlSum>
						<xsl:value-of select="$total" />
					</CtrlSum>
					<PmtTpInf>
						<SvcLvl>
							<Cd>SEPA</Cd>
						</SvcLvl>
					</PmtTpInf>
					<ReqdExctnDt><xsl:value-of
						select="format-dateTime($now, '[Y0001]-[M01]-[D01]')" /></ReqdExctnDt>
					<Dbtr>
						<Nm>
							<xsl:value-of
								select="$exportWorkitem/item[@name='dbtr.name']/value" />
						</Nm>
					</Dbtr>
					<DbtrAcct>
						<Id>
							<IBAN>
								<xsl:value-of
									select="replace($exportWorkitem/item[@name='dbtr.iban']/value, ' ', '')" />
							</IBAN>
						</Id>
					</DbtrAcct>
					<DbtrAgt>
						<FinInstnId>
							<BIC>
								<xsl:value-of
									select="$exportWorkitem/item[@name='dbtr.bic']/value" />
							</BIC>
						</FinInstnId>
					</DbtrAgt>
					<ChrgBr>SLEV</ChrgBr>



					<!-- generate CdtTrfTxInf for each invoice -->
					<xsl:apply-templates
						select="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'Rechnungseingang']" />



				</PmtInf>
			</CstmrCdtTrfInitn>
		</Document>
	</xsl:template>




	<!-- This template builds sepa header info -->
	<xsl:template
		match="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'SEPA-Export']">
		<!-- not in use -->
	</xsl:template>




	<!-- This template builds sepa payment info for each invoice -->
	<xsl:template
		match="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'Rechnungseingang']">


		<CdtTrfTxInf xmlns="urn:iso:std:iso:20022:tech:xsd:pain.001.003.03">
			<PmtId>
				<EndToEndId>NOTPROVIDED</EndToEndId>
			</PmtId>
			<Amt>
				<InstdAmt>
					<xsl:attribute name="Ccy"><xsl:value-of
						select="item[@name='invoice.currency']/value" /></xsl:attribute>
					<xsl:value-of
						select="item[@name='invoice.total']/value" />
				</InstdAmt>
			</Amt>
			<CdtrAgt>
				<FinInstnId>
					<BIC>
						<xsl:value-of select="item[@name='cdtr.bic']/value" />
					</BIC>
				</FinInstnId>
			</CdtrAgt>
			<Cdtr>
				<Nm>
					<xsl:value-of select="item[@name='cdtr.name']/value" />
				</Nm>
			</Cdtr>
			<CdtrAcct>
				<Id>
					<IBAN>
						<xsl:value-of select="replace(item[@name='cdtr.iban']/value, ' ', '')" />
					</IBAN>
				</Id>
			</CdtrAcct>
			<RmtInf>
				<Ustrd>
					<xsl:value-of select="item[@name='$workflowsummary']/value" />
				</Ustrd>
			</RmtInf>
		</CdtTrfTxInf>





	</xsl:template>


</xsl:stylesheet>