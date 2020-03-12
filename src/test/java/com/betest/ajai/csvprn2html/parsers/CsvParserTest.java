package com.betest.ajai.csvprn2html.parsers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CsvParserTest {

	private Path resourceDirectory;
	private String absolutePath;

	@BeforeEach
	void init() {
		resourceDirectory = Paths.get("src", "test", "resources");
		absolutePath = resourceDirectory.toFile().getAbsolutePath();
	}
	
	@Test
	void testParser() {
		Parser parser = new CsvParser();
		Collection<String[]> chunks = parser.parse(new File(absolutePath + "/test.csv"));
		assertEquals(2, chunks.size());
		List<String[]> chunkList = new ArrayList<>(chunks);
		String[] headerRow = new String[] { 
				"Name", 
				"Address", 
				"Postcode", 
				"Phone",
				"Credit Limit", 
				"Birthday" };
		assertArrayEquals(chunkList.get(0), headerRow);
		
		String[] columnRow = new String[] {
				"Johnson, John",
				"Voorstraat 32",
				"3122gg",
				"020 3849381",
				"10000",
				"01/01/1987"
		};
		assertArrayEquals(chunkList.get(1), columnRow);
		
	}
}
