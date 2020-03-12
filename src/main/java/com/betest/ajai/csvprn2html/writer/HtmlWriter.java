package com.betest.ajai.csvprn2html.writer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.betest.ajai.csvprn2html.enums.OutputExtensions;

public class HtmlWriter {

	private final String location;
	private final String htmlString;
	private final String name;
	private final String template;

	public HtmlWriter(String htmlString, String name, String template, String outputLocation) {
		this.location = StringUtils.isBlank(outputLocation) ? System.getProperty("user.dir") : outputLocation;
		this.htmlString = htmlString;
		this.name = name;
		this.template = template == null ? getTemplateFilePath() : template;
	}

	public void write() {
		File newFile = new File(generateName(location, name, OutputExtensions.HTML.getExtension()));
		try {
			String finalHtmlString = getFinalHtmlString(htmlString);
			FileUtils.write(newFile, finalHtmlString, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(
					"Encountered an error while writing html data to file : [" + newFile.getAbsolutePath() + "]", e);
		}
	}

	private String getFinalHtmlString(String htmlTableString) {
		File templateFile = new File(template);
		String htmlString;
		try {
			htmlString = FileUtils.readFileToString(templateFile, StandardCharsets.UTF_8);
			htmlString = htmlString.replace("$title", "Generated on : " + new Date());
			htmlString = htmlString.replace("$body", htmlTableString);
			return htmlString;
		} catch (IOException e) {
			throw new RuntimeException(
					"Encountered an error while reading the templateFile [" + templateFile.getAbsolutePath() + "]", e);
		}
	}

	private String getTemplateFilePath() {
		Path resourceDirectory = Paths.get("src", "main", "resources", "templates");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		return absolutePath + "/template.html";
	}

	private static String generateName(String location, String fileName, String extension) {
		return location + "/" + FilenameUtils.removeExtension(fileName) + "_" + FilenameUtils.getExtension(fileName)
				+ "_" + Instant.now() + "." + extension;
	}

}
