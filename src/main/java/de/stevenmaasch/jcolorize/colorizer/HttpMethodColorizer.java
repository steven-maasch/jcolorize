package de.stevenmaasch.jcolorize.colorizer;

import java.util.regex.Pattern;

import de.stevenmaasch.jcolorize.util.RegexPattern;

public class HttpMethodColorizer extends AbstractColorizer {

	protected HttpMethodColorizer(Colorizer item, Pattern... patterns) {
		super(item, patterns);
	}

	public static HttpMethodColorizer getInstance(Colorizer item) {
		return new HttpMethodColorizer(item, RegexPattern.HTTP_METHOD);
	}

}
