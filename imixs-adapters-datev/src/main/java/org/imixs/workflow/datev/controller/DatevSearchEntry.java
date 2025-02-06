package org.imixs.workflow.datev.controller;

/**
 * Diese Kleine Hilfsklasse dient als DTO für die Übertragung eines
 * Sucheregenisses an das Frontend. Die Klasse besteht aus einem
 * Key, einer DisplayZeile für die Darstellung und einen JSON String der
 * beliebige Daten enthalten kann.
 * 
 * @author rsoika
 *
 */
public class DatevSearchEntry {

	private String key;
	private String display;
	private String data;

	public DatevSearchEntry(String key, String display, String data) {
		super();
		this.key = key;
		this.display = display;
		this.data = data;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
