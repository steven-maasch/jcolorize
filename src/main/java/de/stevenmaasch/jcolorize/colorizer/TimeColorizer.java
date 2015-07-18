package de.stevenmaasch.jcolorize.colorizer;

import java.util.regex.Pattern;

import de.stevenmaasch.jcolorize.util.RegexPattern;

public class TimeColorizer extends AbstractColorizer {

	public static TimeColorizer getInstance(Colorizer item) {
		return new TimeColorizer(item, RegexPattern.TIME_ISO8601);
	}

	private TimeColorizer(Colorizer item, Pattern...patterns) {
		super(item, patterns);
	}

}
