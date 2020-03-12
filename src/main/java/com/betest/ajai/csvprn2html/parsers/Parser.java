package com.betest.ajai.csvprn2html.parsers;

import java.io.File;
import java.util.Collection;

public interface Parser {

	public Collection<String[]> parse(File file);
}
