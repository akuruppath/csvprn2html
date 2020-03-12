package com.betest.ajai.csvprn2html;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.betest.ajai.csvprn2html.enums.InputExtensions;
import com.betest.ajai.csvprn2html.generator.HtmlGenerator;
import com.betest.ajai.csvprn2html.writer.HtmlWriter;

public class Main {

	public static void main(String[] args) {

		if (args == null || args.length <= 0) {
			throw new IllegalStateException("Please specify a valid input path.");
		}

		if (args.length == 1) {
			throw new IllegalStateException("Please specify a valid output path.");
		}

		String inputPath = args[0];
		String outputPath = args[1];

		isValidPath(inputPath);
		isValidPath(outputPath);

		System.out.println("InputPath : [" + inputPath + "] and OutputPath :[" + outputPath + "]");
		
		Collection<File> csvFiles = FileUtils.listFiles(new File(inputPath), new String[] {InputExtensions.CSV.getExtension()},true);
		
		Collection<File> prnFiles = FileUtils.listFiles(new File(inputPath), new String[] {InputExtensions.PRN.getExtension()},true);


		int csvFileCount = csvFiles.size();
		int prnFileCount = prnFiles.size();
		
		if (csvFileCount <= 0 && prnFileCount <= 0) {
			System.out.println("No *.csv or *.prn files found in the specified input directory.");
			System.exit(0);
		}

		System.out.println("Found [" + csvFileCount + "] CSV and [" + prnFileCount + "] PRN files.");
		
		
		for (File file : prnFiles) {
			HtmlGenerator generator = new HtmlGenerator(file);
			String generatedHtml = generator.generateHtmlString();
			HtmlWriter writer = new HtmlWriter(generatedHtml, file.getName(), null, outputPath);
			writer.write();
		}

		for (File file : csvFiles) {
			HtmlGenerator generator = new HtmlGenerator(file);
			String generatedHtml = generator.generateHtmlString();
			HtmlWriter writer = new HtmlWriter(generatedHtml, file.getName(), null, outputPath);
			writer.write();
		}

		System.out.println("Finished generating html files.");

	}

	private static void isValidPath(String path) {
		if (StringUtils.isBlank(path)) {
			throw new IllegalArgumentException("Specified path should not be blank.");
		}
		if (!Files.isDirectory(Paths.get(path))) {
			throw new IllegalArgumentException(
					"Specified path [" + path + "] is not a directory. Expected a directory.");
		}
	}

}
