package com.betest.ajai.csvprn2html.enums;

import java.util.EnumSet;

public enum Headers {

	NAME("Name"), ADDRESS("Address"), POST_CODE("Postcode"), PHONE("Phone"), CREDIT_LIMIT("Credit Limit"),
	BIRTHDAY("Birthday");

	private final String columnHeader;

	private Headers(String columnHeader) {
		this.columnHeader = columnHeader;
	}

	public String getColumnHeader() {
		return columnHeader;
	}

	public static String[] getHeaderValues() {
		return EnumSet.allOf(Headers.class).stream().map(Headers::getColumnHeader).toArray(String[]::new);
	}

}
