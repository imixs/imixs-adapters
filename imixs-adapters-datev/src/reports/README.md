# Overview

The DATEV adapter provides a standard  xsl file to translate the result of a invoice selection into the datev export format. 
The report is executed by the DatevSchedueler.

## Report
The datev report is based on the datev.xsl XSL Template. 

To upload the report run:

	$ curl --cookie "JSESSIONID=pUG7bsLfBHcmcPuzTSoaiLdHZTP_1AOvkPn-rRff.b5ef3717eaf7; path=/" --request POST -H "Content-Type: application/xml" -Tsepa.imixs-report http://localhost:8080/api/report

	
Replace the JSESSIONID with your current browser session id. 


 