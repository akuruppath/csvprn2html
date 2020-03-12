package com.betest.ajai.csvprn2html.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum InputExtensions {

	CSV("csv"), PRN("prn");

	private static final Map<String, InputExtensions> EXTENSION_MAP = new HashMap<>();

	static {
		EXTENSION_MAP.put(CSV.getExtension(), CSV);
		EXTENSION_MAP.put(PRN.getExtension(), PRN);
	}

	private final String extension;

	private InputExtensions(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}

	public static String[] getExtensionValues() {
		return EnumSet.allOf(InputExtensions.class).stream().map(InputExtensions::getExtension).toArray(String[]::new);
	}

	public static InputExtensions getExtension(String extension) {
		if (!EXTENSION_MAP.containsKey(extension)) {
			throw new IllegalArgumentException("Extension : [" + extension + "] not supported.");
		}
		return EXTENSION_MAP.get(extension);
	}
}
