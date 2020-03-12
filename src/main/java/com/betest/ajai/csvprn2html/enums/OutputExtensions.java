package com.betest.ajai.csvprn2html.enums;

public enum OutputExtensions {

	HTML("html");
	
	private final String extension;

	private OutputExtensions(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}
}
