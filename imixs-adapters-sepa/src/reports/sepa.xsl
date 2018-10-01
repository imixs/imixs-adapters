<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:strip-space elements="*" />
	<xsl:output method="xml" indent="yes" encoding="UTF-8" standalone="yes" />

	<xsl:template match="/">

		<Document
			xmlns="urn:iso:std:iso:20022:tech:xsd:pain.001.003.03"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="urn:iso:std:iso:20022:tech:xsd:pain.001.003.03 pain.001.003.03.xsd">
			<CstmrCdtTrfInitn>

				<!-- generate header info -->
				<!-- compute count of inoices -->
				<xsl:variable name="count"
					select="count(/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'Rechnungseingang']/item[@name='$uniqueid']/value)" />
				<!-- comput total amount -->
				<xsl:variable name="total"
					select="sum(/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'Rechnungseingang']/item[@name='_amount_brutto']/value)" />

				<!-- shotcut for the sepa export document -->
				<xsl:variable name="exportWorkitem"
					select="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'SEPA-Export']" />


				<GrpHdr>
					<MsgId>
						<xsl:value-of
							select="$exportWorkitem/item[@name='$uniqueid']/value" />
					</MsgId>
					<CreDtTm>2018-08-20T17:34:47</CreDtTm>
					<NbOfTxs>
						<xsl:value-of select="$count" />
					</NbOfTxs>
					<CtrlSum>
						<xsl:value-of select="$total" />
					</CtrlSum>
					<InitgPty>
						<Nm>
							<xsl:value-of
								select="$exportWorkitem/item[@name='_subject']/value" />
						</Nm>
					</InitgPty>
				</GrpHdr>

				<PmtInf>
					<PmtInfId>
						<xsl:value-of
							select="$exportWorkitem/item[@name='$uniqueid']/value" /><xsl:text>-1</xsl:text>
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
					<ReqdExctnDt>1999-01-01</ReqdExctnDt>
					<Dbtr>
						<Nm>
							<xsl:value-of
								select="$exportWorkitem/item[@name='_dbtr_name']/value" />
						</Nm>
					</Dbtr>
					<DbtrAcct>
						<Id>
							<IBAN>
								<xsl:value-of
									select="$exportWorkitem/item[@name='_dbtr_iban']/value" />
							</IBAN>
						</Id>
					</DbtrAcct>
					<DbtrAgt>
						<FinInstnId>
							<BIC>
								<xsl:value-of
									select="$exportWorkitem/item[@name='_dbtr_bic']/value" />
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


		<CdtTrfTxInf>
			<PmtId>
				<EndToEndId>NOTPROVIDED</EndToEndId>
			</PmtId>
			<Amt>
				<InstdAmt>
					<xsl:attribute name="Ccy"><xsl:value-of
						select="item[@name='_currency']/value" /></xsl:attribute>
					<xsl:value-of
						select="item[@name='_amount_brutto']/value" />
				</InstdAmt>
			</Amt>
			<CdtrAgt>
				<FinInstnId>
					<BIC>
						<xsl:value-of select="item[@name='_cdtr_bic']/value" />
					</BIC>
				</FinInstnId>
			</CdtrAgt>
			<Cdtr>
				<Nm>
					<xsl:value-of select="item[@name='_cdtr_name']/value" />
				</Nm>
			</Cdtr>
			<CdtrAcct>
				<Id>
					<IBAN>
						<xsl:value-of select="item[@name='_cdtr_iban']/value" />
					</IBAN>
				</Id>
			</CdtrAcct>
			<RmtInf>
				<Ustrd>
					<xsl:value-of select="item[@name='_subject']/value" />
				</Ustrd>
			</RmtInf>
		</CdtTrfTxInf>





	</xsl:template>


</xsl:stylesheet>