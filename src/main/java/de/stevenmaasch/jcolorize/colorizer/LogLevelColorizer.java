package de.stevenmaasch.jcolorize.colorizer;

import java.util.regex.Pattern;

import de.stevenmaasch.jcolorize.util.RegexPattern;

public class LogLevelColorizer extends AbstractColorizer {

	public static LogLevelColorizer getInstance(Colorizer item) {
		final Pattern[] patterns = {
				RegexPattern.LOGLEVEL_WARNING,
				RegexPattern.LOGLEVEL_ERROR,
				RegexPattern.LOGLEVEL_FATAL
		};
		return new LogLevelColorizer(item, patterns);
	}

	private LogLevelColorizer(Colorizer item, Pattern... patterns) {
		super(item, patterns);
	}

}
