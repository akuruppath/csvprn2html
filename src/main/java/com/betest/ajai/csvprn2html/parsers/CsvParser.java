package com.betest.ajai.csvprn2html.parsers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.betest.ajai.csvprn2html.enums.Headers;

public class CsvParser implements Parser {

	@Override
	public Collection<String[]> parse(File file) {
		try {
			String[] headerValues = Headers.getHeaderValues();
			CSVFormat format = CSVFormat.DEFAULT.withHeader(headerValues).withFirstRecordAsHeader();
			CSVParser parser = CSVParser.parse(file, StandardCharsets.ISO_8859_1, format);
			List<CSVRecord> records = parser.getRecords();
			List<String[]> chunks = new ArrayList<>();
			chunks.add(headerValues);
			
			if (records.size() > 0) {
				for (CSVRecord record : records) {
					chunks.add(getRecordData(record, headerValues));
				}
			}
			return chunks;
		} catch (IOException e) {
			throw new RuntimeException("Error encountered while parsing file : [" + file.getAbsolutePath() + "]", e);
		}

	}
	
	private String[] getRecordData(CSVRecord record, String[] headerValues) {
		return Arrays.stream(headerValues).map(record::get).toArray(String[]::new);
	}
}
