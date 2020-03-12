package com.betest.ajai.csvprn2html;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.betest.ajai.csvprn2html.enums.InputExtensions;

public class MainTest {

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
		if (file.exists() && file.isDirectory()) {
			FileUtils.deleteDirectory(file);
		}
	}
	
	@Test
    void readCsvFiles() {
		Collection<File> files = FileUtils.listFiles(new File(absolutePath), new String[]{ InputExtensions.CSV.getExtension()}, true);
		assertEquals(4, files.size());
    }
	
	@Test
	void readPrnFiles() {
		Collection<File> files = FileUtils.listFiles(new File(absolutePath), new String[]{ InputExtensions.PRN.getExtension()}, true);;
		assertEquals(3, files.size());	
	}

	@Test
	void testWithDirectory() throws IOException {
		File directory = new File(absolutePath + "/temp");
		directory.mkdirs();
		Main.main(new String[] { absolutePath, directory.getAbsolutePath() });
		File[] listFiles = directory.listFiles();
		assertNotNull(listFiles);
		assertFalse(listFiles.length <= 0);
		FileUtils.deleteDirectory(directory);
	}

	@Test
	void testWithEmptyInputLocation() {
		Assertions.assertThrows(IllegalStateException.class, () -> {
			Main.main(new String[] { "" });
		});
	}

	@Test
	void testWithNull() {
		Assertions.assertThrows(IllegalStateException.class, () -> {
			Main.main(null);
		});
	}

	@Test
	void testWithRegularFile() {
		Assertions.assertThrows(IllegalStateException.class, () -> {
			Main.main(new String[] { absolutePath + "/Workbook2.csv" });
		});
	}

	@Test
	void testWithNullOutputLocation() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Main.main(new String[] { absolutePath, null });
		});
	}
}
