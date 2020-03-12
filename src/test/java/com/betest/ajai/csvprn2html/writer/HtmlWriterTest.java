package com.betest.ajai.csvprn2html.writer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.betest.ajai.csvprn2html.writer.HtmlWriter;

public class HtmlWriterTest {

	private Path resourceDirectory;
	private String absolutePath;
	private String outputLocation;
	

	@BeforeEach
	void init() throws IOException {
		resourceDirectory = Paths.get("src", "test", "resources");
		absolutePath = resourceDirectory.toFile().getAbsolutePath();
		outputLocation = absolutePath + "/generated-html";
	}
	
	@AfterEach
	void breakDown() throws IOException {
		File file = new File(outputLocation);
		if(file.exists() && file.isDirectory()) {
			FileUtils.deleteDirectory(file);
		}
	}

	@Test
	void htmlWriterTest() throws IOException {
		String expectedHtmlString = "<table border = \"1\"><tr><th align =  \"left\">Name            </th><th align =  \"left\">Address               </th><th align =  \"left\">Postcode </th><th align =  \"left\">Phone         </th><th align =  \"right\">Credit Limit </th><th align =  \"left\">Birthday</th></tr><tr><td align =  \"left\">Johnson, John   </td><td align =  \"left\">Voorstraat 32         </td><td align =  \"left\">3122gg   </td><td align =  \"left\">020 3849381   </td><td align =  \"right\">     1000000 </td><td align =  \"left\">19870101</td></tr></table></body>";
		HtmlWriter writer = new HtmlWriter(expectedHtmlString, "generated", null, outputLocation);
		writer.write();

		File generatedFiles = new File(outputLocation + "/");
		File[] listFiles = generatedFiles.listFiles();
		assertNotNull(listFiles);
		String htmlString = FileUtils.readFileToString(listFiles[0], StandardCharsets.UTF_8);
		assertTrue(htmlString.contains(expectedHtmlString));
	}
}
