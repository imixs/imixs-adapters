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
            xsi:schemaLocation="urn:iso:std:iso:20022:tech:xsd:pain.008.001.08 pain.008.001.08.xsd">

            <CstmrCdtTrfInitn>

                <!-- generate header info -->
                <!-- compute count of invoices -->
                <xsl:variable name="count"
                    select="count(/data/document[starts-with(normalize-space(item[@name = '$modelversion']/value),'darlehen')]/item[@name='$uniqueid']/value)" />
                <!-- compute total amount -->
                <xsl:variable name="totalsum"
                    select="xs:decimal(sum(/data/document[starts-with(normalize-space(item[@name = '$modelversion']/value),'darlehen')]/item[@name='payment.in.rate']/value))" />
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
                    <CreDtTm>
                        <xsl:value-of
                            select="format-dateTime($now, '[Y0001]-[M01]-[D01]T[H01]:[m01]:[s01]')" />
                    </CreDtTm>
                    <NbOfTxs>
                        <xsl:value-of select="$count" />
                    </NbOfTxs>
                    <CtrlSum>
                        <!-- round to 2 digits -->
                        <xsl:value-of select="$total" />
                    </CtrlSum>
                    <InitgPty>
                        <Nm>
                            <xsl:value-of
                                select="$exportWorkitem/item[@name='cdtr.name']/value" />
                        </Nm>
                    </InitgPty>
                </GrpHdr>

                <PmtInf>
                    <PmtInfId>
                        <xsl:value-of
                            select="replace($exportWorkitem/item[@name='$uniqueid']/value, '-', '')" />
                        <xsl:text>-1</xsl:text>
                    </PmtInfId>
                    <PmtMtd>DD</PmtMtd>
                    <NbOfTxs>
                        <xsl:value-of select="$count" />
                    </NbOfTxs>
                    <CtrlSum>
                        <!-- round to 2 digits -->
                        <xsl:value-of select="$total" />
                    </CtrlSum>
                    <PmtTpInf>
                        <SvcLvl>
                            <Cd>SEPA</Cd>
                        </SvcLvl>
                        <LclInstrm>
                            <Cd>CORE</Cd>
                        </LclInstrm>
                        <SeqTp>FRST</SeqTp>
                    </PmtTpInf>
                    <ReqdColltnDt>
                        <xsl:value-of
                            select="format-dateTime($now, '[Y0001]-[M01]-[D01]')" />
                    </ReqdColltnDt>

                    <Cdtr>
                        <Nm>
                            <xsl:value-of
                                select="$exportWorkitem/item[@name='cdtr.name']/value" />
                        </Nm>
                    </Cdtr>
                    <CdtrAcct>
                        <Id>
                            <IBAN>
                                <xsl:value-of
                                    select="replace($exportWorkitem/item[@name='cdtr.iban']/value, ' ', '')" />
                            </IBAN>
                        </Id>
                    </CdtrAcct>
                    <CdtrAgt>
                        <FinInstnId>
                            <BIC>
                                <xsl:value-of
                                    select="replace($exportWorkitem/item[@name='cdtr.bic']/value, ' ', '')" />
                            </BIC>
                        </FinInstnId>
                    </CdtrAgt>
                    <ChrgBr>SLEV</ChrgBr>
                    <!-- generate CdtTrfTxInf for each darlehen -->
                    <xsl:apply-templates
                        select="/data/document[starts-with(normalize-space(item[@name = '$modelversion']/value),'darlehen')]" />
                </PmtInf>
            </CstmrCdtTrfInitn>
        </Document>
    </xsl:template>


    <!-- This template builds sepa header info -->
    <xsl:template
        match="/data/document[normalize-space(item[@name = '$workflowgroup']/value) = 'SEPA-Export']">
        <!-- not in use -->
    </xsl:template>


    <!-- This template builds sepa payment info for each darlehen -->
    <xsl:template
        match="/data/document[starts-with(normalize-space(item[@name = '$modelversion']/value),'darlehen')]">


        <DrctDbtTxInf xmlns="urn:iso:std:iso:20022:tech:xsd:pain.008.001.08">
            <PmtId>
                <EndToEndId>NOTPROVIDED</EndToEndId>
            </PmtId>
            <InstdAmt>
                <xsl:attribute name="Ccy">EUR</xsl:attribute>
                <xsl:value-of
                    select="item[@name='payment.in.rate']/value" />
            </InstdAmt>


            <DrctDbtTx>
                <MndtRltdInf>
                    <MndtId>Other-Mandate-Id</MndtId>
                    <DtOfSgntr>2023-12-01</DtOfSgntr>
                    <AmdmntInd>false</AmdmntInd>
                </MndtRltdInf>
            </DrctDbtTx>
            <DbtrAgt>
                <FinInstnId>
                    <BICFI>
                        <xsl:value-of select="replace(item[@name='dbtr.bic']/value, ' ', '')" />
                    </BICFI>
                </FinInstnId>
            </DbtrAgt>
            <Dbtr>
                <Nm>
                    <xsl:value-of select="substring(item[@name='$workflowsummary']/value, 1, 70)" />
                </Nm>
            </Dbtr>
            <DbtrAcct>
                <Id>
                    <IBAN>
                        <xsl:value-of select="replace(item[@name='dbtr.iban']/value, ' ', '')" />
                    </IBAN>
                </Id>
            </DbtrAcct>

            <RmtInf>
                <Ustrd>
                    <xsl:value-of select="substring(item[@name='$workflowsummary']/value, 1, 140)" />
                </Ustrd>
            </RmtInf>

        </DrctDbtTxInf>

    </xsl:template>

</xsl:stylesheet>