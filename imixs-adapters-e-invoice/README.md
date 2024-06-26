# eInvoicing Adapter

The Imixs eInvoicing Adapter provides methods to parse and produce eInvoice files according to the [EN 16931 compliance](https://ec.europa.eu/digital-building-blocks/sites/display/DIGITAL/EN+16931+compliance).

## Field Mapping

In the default configuration the adapter supports the following items:


| Item                         | xPath                                          | Example                                                            |
|--------------------------------- |------------------------------------------------------|------------------------------------------------------------------- | 
| invoice.number                | Disable JNDI lookup                                    | false                                                               |
| invoice.total              | Search Context                                         | DC=intern,DC=ib-vassen,DC=de                                        |


