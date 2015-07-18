package de.stevenmaasch.jcolorize.colorizer;

import java.util.regex.Pattern;

import de.stevenmaasch.jcolorize.util.RegexPattern;

public final class DateColorizer extends AbstractColorizer {

	public static DateColorizer getInstance(Colorizer item) {;
		final Pattern[] patterns = {
			RegexPattern.DATE_ISO8601
		};
		return new DateColorizer(item, patterns);
	}

	protected DateColorizer(Colorizer item, Pattern... patterns) {
		super(item, patterns);
	}

}
