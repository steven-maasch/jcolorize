package de.stevenmaasch.jcolorize.util;

import java.util.regex.Pattern;

public final class RegexPattern {

	private static final Pattern NO_ESACPE_LOOKBEHIND = Pattern.compile("(?<!\\u001b)");
	
	private static final String WORD_BOUNDARY = "\\b";

	public static final Pattern BRACKETS = Pattern.compile(NO_ESACPE_LOOKBEHIND.pattern() + "[\\(\\)\\[\\]]");

	public static final Pattern MAC_ADDR =
			Pattern.compile(WORD_BOUNDARY + "([0-9A-F]{2}[:-]){5}([0-9A-F]{2})" + WORD_BOUNDARY);

	public static final Pattern IP4_ADDR_WITH_PORT =
			Pattern.compile(WORD_BOUNDARY + "(\\d{1,3}\\.){3}\\d{1,3}(:\\d+)?" + WORD_BOUNDARY);

	public static final Pattern DATE_ISO8601 =
			Pattern.compile(WORD_BOUNDARY + "\\d{4}-\\d{2}-\\d{2}" + WORD_BOUNDARY);

	public static final Pattern TIME_ISO8601 =
			Pattern.compile(WORD_BOUNDARY + "\\d{2}:\\d{2}:\\d{2}(,\\d{1,3})?" + WORD_BOUNDARY);

	public static final Pattern LOGLEVEL_DEBUG =
			Pattern.compile(WORD_BOUNDARY + "DEBUG" + WORD_BOUNDARY);

	public static final Pattern LOGLEVEL_WARNING =
			Pattern.compile(WORD_BOUNDARY + "WARN(ING)?" + WORD_BOUNDARY);

	public static final Pattern LOGLEVEL_ERROR =
			Pattern.compile(WORD_BOUNDARY + "ERROR" + WORD_BOUNDARY);

	public static final Pattern LOGLEVEL_FATAL =
			Pattern.compile(WORD_BOUNDARY+ "FATAL" + WORD_BOUNDARY);

	public static final Pattern HTTP_METHOD =
			Pattern.compile(WORD_BOUNDARY + "GET|POST|HEAD|PUT|DELETE|TRACE|OPTIONS|CONNECT" + WORD_BOUNDARY);
	
	/**
	 * Matches a sequence of more than <u>3</u> same characters
	 */
	public static final Pattern SEQUENCE_OF_SAME_CHARACTERS = Pattern.compile("(.)\\1{2,}");
	
	private RegexPattern() { }

}
