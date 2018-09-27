# Overview

The sepa adapter provides a standard  SEPA xsl file to translate the result of a invoice selection into the sepa standard format. 
The report is executed by the SEPASchedueler.

## Report
The sepa report is based on the sepa.xsl XSL Template. 

To upload the report run:

	$ curl --cookie "JSESSIONID=pUG7bsLfBHcmcPuzTSoaiLdHZTP_1AOvkPn-rRff.b5ef3717eaf7; path=/" --request POST -H "Content-Type: application/xml" -Tsepa.imixs-report http://localhost:8080/api/report

	
Replace the JSESSIONID with your current browser session id. 


 