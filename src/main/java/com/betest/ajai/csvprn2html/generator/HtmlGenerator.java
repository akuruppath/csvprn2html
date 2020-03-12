package com.betest.ajai.csvprn2html.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.FilenameUtils;

import com.betest.ajai.csvprn2html.enums.InputExtensions;
import com.betest.ajai.csvprn2html.enums.Headers;
import com.betest.ajai.csvprn2html.parsers.CsvParser;
import com.betest.ajai.csvprn2html.parsers.Parser;
import com.betest.ajai.csvprn2html.parsers.PrnParser;

public class HtmlGenerator {

	private final Parser parser;
	private final File inputFile;

	public HtmlGenerator(File inputFile) {
		this.inputFile = inputFile;
		this.parser = getParser(FilenameUtils.getExtension(inputFile.getName()));

	}

	public String generateHtmlString() {
		Collection<String[]> chunks = parser.parse(inputFile);
		return createTable(chunks);
	}

	private Parser getParser(String extension) {
		switch (InputExtensions.getExtension(extension)) {
		case CSV:
			return new CsvParser();
		case PRN:
			return new PrnParser();
		}
		throw new IllegalArgumentException("Unknown extension [" + extension + "] encountered.");
	}

	private String createTable(Collection<String[]> chunks) {
		StringBuilder builder = new StringBuilder();
		List<String[]> chunkList = new ArrayList<>(chunks);

		builder.append("<table border = \"1\">");
		builder.append("<tr>");
		builder.append(createHeaderColumn(chunkList.get(0)));
		builder.append("</tr>");

		chunkList.remove(0);

		for (String[] chunk : chunkList) {
			builder.append("<tr>");
			builder.append(createDataColumns(chunk));
			builder.append("</tr>");
		}
		builder.append("</table>");
		return builder.toString();
	}

	private String createHeaderColumn(String[] chunk) {
		StringBuilder builder = new StringBuilder();
		for (String data : chunk) {
			if (Objects.equals(data.trim(), Headers.CREDIT_LIMIT.getColumnHeader())) {
				builder.append("<th align =  \"right\">");
			} else {
				builder.append("<th align =  \"left\">");
			}
			builder.append(data);
			builder.append("</th>");
		}
		return builder.toString();
	}

	private String createDataColumns(String[] chunk) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < chunk.length; i++) {
			if (i == 4) {
				builder.append("<td align =  \"right\">");
			} else {
				builder.append("<td align =  \"left\">");
			}
			builder.append(chunk[i]);
			builder.append("</td>");
		}
		return builder.toString();
	}

}
