package com.betest.ajai.csvprn2html.parsers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.betest.ajai.csvprn2html.enums.Headers;

public class PrnParser implements Parser {

	@Override
	public Collection<String[]> parse(File file) {
		Optional<String> header = getHeader(file);
		if (!header.isPresent()) {
			throw new IllegalStateException("Header not found for file : [" + file.getAbsolutePath() + "]");
		}
		int[] startIndices = getHeaderStartIndices(header.get());
		List<String[]> chunks = new ArrayList<>();
		try (Stream<String> lines = Files.lines(file.toPath(), StandardCharsets.ISO_8859_1)) {
			lines.forEach(line -> {
				String[] columnChunks = getColumnChunks(startIndices, line);
				chunks.add(columnChunks);
			});

		} catch (IOException e) {
			throw new RuntimeException(
					"Encountered an error while attempting to read file: [" + file.getAbsolutePath() + "]", e);
		}
		return chunks;

	}

	private String[] getColumnChunks(int[] indices, String line) {
		List<String> chunks = new ArrayList<>();
		int start = 0;
		for (int length : indices) {
			String chunk = line.substring(start, length);
			if (StringUtils.isBlank(chunk)) {
				continue;
			}
			chunks.add(chunk);
			start = length;
		}
		chunks.add(line.substring(start, line.length()));
		return chunks.toArray(new String[0]);

	}

	private Optional<String> getHeader(File file) {
		try (Stream<String> lines = Files.lines(file.toPath(), StandardCharsets.ISO_8859_1)) {
			Optional<String> header = lines.findFirst();
			return header.isPresent() ? header : Optional.empty();

		} catch (IOException e) {
			throw new RuntimeException(
					"Encountered an error while attempting to read file: [" + file.getAbsolutePath() + "]", e);
		}
	}

	private int[] getHeaderStartIndices(String header) {
		return new int[] { header.indexOf(Headers.NAME.getColumnHeader()),
				header.indexOf(Headers.ADDRESS.getColumnHeader()), header.indexOf(Headers.POST_CODE.getColumnHeader()),
				header.indexOf(Headers.PHONE.getColumnHeader()), header.indexOf(Headers.CREDIT_LIMIT.getColumnHeader()),
				header.indexOf(Headers.BIRTHDAY.getColumnHeader()) };
	}
}
