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

import com.betest.ajai.csvprn2html.parsers.PrnParser;

public class PrnParserTest {

	private Path resourceDirectory;
	private String absolutePath;

	@BeforeEach
	void init() {
		resourceDirectory = Paths.get("src", "test", "resources");
		absolutePath = resourceDirectory.toFile().getAbsolutePath();
	}

	@Test
	void testParser() {
		PrnParser parser = new PrnParser();
		Collection<String[]> chunks = parser.parse(new File(absolutePath + "/test.prn"));
		assertEquals(2, chunks.size());
		List<String[]> chunkList = new ArrayList<>(chunks);
		String[] headerRow = new String[] { 
				"Name            ", 
				"Address               ", 
				"Postcode ", 
				"Phone         ",
				"Credit Limit ", 
				"Birthday" };
		assertArrayEquals(chunkList.get(0), headerRow);
		
		String[] columnRow = new String[] {
				"Johnson, John   ",
				"Voorstraat 32         ",
				"3122gg   ",
				"020 3849381   ",
				"     1000000 ",
				"19870101"
		};
		assertArrayEquals(chunkList.get(1), columnRow);
		
	}
}
