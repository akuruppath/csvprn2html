package com.betest.ajai.csvprn2html.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HtmlGeneratorTest {

	private Path resourceDirectory;
	private String absolutePath;

	@BeforeEach
	void init() {
		resourceDirectory = Paths.get("src", "test", "resources");
		absolutePath = resourceDirectory.toFile().getAbsolutePath();
	}

	@Test
	void htmlGeneratorTest() throws IOException {
		File inputFile = new File(absolutePath + "/test.prn");
		HtmlGenerator generator = new HtmlGenerator(inputFile);

		String expectedHtmlString = "<table border = \"1\"><tr><th align =  \"left\">Name            </th><th align =  \"left\">Address               </th><th align =  \"left\">Postcode </th><th align =  \"left\">Phone         </th><th align =  \"right\">Credit Limit </th><th align =  \"left\">Birthday</th></tr><tr><td align =  \"left\">Johnson, John   </td><td align =  \"left\">Voorstraat 32         </td><td align =  \"left\">3122gg   </td><td align =  \"left\">020 3849381   </td><td align =  \"right\">     1000000 </td><td align =  \"left\">19870101</td></tr></table>";
		assertEquals(expectedHtmlString, generator.generateHtmlString());
	}
}
