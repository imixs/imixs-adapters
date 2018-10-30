<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
	xmlns:rsm="urn:ferd:CrossIndustryDocument:invoice:1p0"
	xmlns:ram="urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:12"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:strip-space elements="*" />
	<xsl:output method="xml" indent="yes" encoding="UTF-8"
		standalone="yes" />


	<!-- This example template shows how to convert a ZUGFeRD xml file into 
		a Imixs XMLDocument -->

	<xsl:template match="/">


		<data xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xmlns:xs="http://www.w3.org/2001/XMLSchema">
			<document>


				<item name="test">
					<value xsi:type="xs:int">500</value>
				</item>


				<!-- generate head data -->
				<xsl:apply-templates
					select="/rsm:CrossIndustryDocument/rsm:HeaderExchangedDocument" />

				<xsl:apply-templates
					select="/rsm:CrossIndustryDocument/rsm:SpecifiedSupplyChainTradeTransaction/ram:ApplicableSupplyChainTradeAgreement" />


				<xsl:apply-templates
					select="/rsm:CrossIndustryDocument/rsm:SpecifiedSupplyChainTradeTransaction/ram:ApplicableSupplyChainTradeSettlement" />
			</document>
		</data>

	</xsl:template>



	<!-- HeaderExchangedDocument -->
	<xsl:template
		match="/rsm:CrossIndustryDocument/rsm:HeaderExchangedDocument">
		<item name="_zugferd_id">
			<value xsi:type="xs:string">
				<xsl:value-of select="ram:ID" />
			</value>
		</item>
		<item name="_zugferd_name">
			<value xsi:type="xs:string">
				<xsl:value-of select="ram:Name" />
			</value>
		</item>
		<item name="_zugferd_TypeCode">
			<value xsi:type="xs:string">
				<xsl:value-of select="ram:TypeCode" />
			</value>
		</item>
		<item name="_zugferd_IssueDateTime">
			<value xsi:type="xs:string">
				<xsl:value-of select="ram:IssueDateTime" />
			</value>
		</item>
	</xsl:template>



	<!-- SupplyChainTradeAgreement -->
	<xsl:template
		match="/rsm:CrossIndustryDocument/rsm:SpecifiedSupplyChainTradeTransaction/ram:ApplicableSupplyChainTradeAgreement">
		<item name="_zugferd_sellertradeParty_Name">
			<value xsi:type="xs:string">
				<xsl:value-of select="ram:SellerTradeParty/ram:Name" />
			</value>
		</item>
		<item name="_zugferd_BuyerTradeParty_Name">
			<value xsi:type="xs:string">
				<xsl:value-of select="ram:BuyerTradeParty/ram:Name" />
			</value>
		</item>
	</xsl:template>




	<!-- MonetarySummation -->
	<xsl:template
		match="/rsm:CrossIndustryDocument/rsm:SpecifiedSupplyChainTradeTransaction/ram:ApplicableSupplyChainTradeSettlement">


		<item name="_zugferd_InvoiceCurrencyCode">
			<value xsi:type="xs:string">
				<xsl:value-of select="ram:InvoiceCurrencyCode" />
			</value>
		</item>
		<item name="_zugferd_GrandTotalAmount">
			<value xsi:type="xs:string">
				<xsl:value-of
					select="ram:SpecifiedTradeSettlementMonetarySummation/ram:GrandTotalAmount" />
			</value>
		</item>

		<item name="_zugferd_IBAN">
			<value xsi:type="xs:string">
				<xsl:value-of
					select="ram:SpecifiedTradeSettlementPaymentMeans/ram:PayeePartyCreditorFinancialAccount/ram:IBANID" />
			</value>
		</item>
		<item name="_zugferd_BIC">
			<value xsi:type="xs:string">
				<xsl:value-of
					select="ram:SpecifiedTradeSettlementPaymentMeans/ram:PayeeSpecifiedCreditorFinancialInstitution/ram:BICID" />
			</value>
		</item>

	</xsl:template>

</xsl:stylesheet>