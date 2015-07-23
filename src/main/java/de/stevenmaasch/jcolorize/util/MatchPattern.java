package de.stevenmaasch.jcolorize.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public final class MatchPattern {

	private static final String[] WORDS_GOOD = {
			"started", "activ", "ready", "online",
			"configured", "enable", "open",
			"complete", "done", "connected",
			"finish", "clean"
	};

	private static final String[] WORDS_BAD = {
			"warn", "restart", "exit", "stop", "shutting",
			"down", "close", "unreach", "can't", "cannot", "skip",
			"deny", "disable", "ignored", "missing", "blocking",
			"ignore", "unable", "offline", "terminat", "empty",
			"null", "exception", "throw"
	};

	private static final String[] WORDS_ERROR = {
			"error", "critical", "invalid", "fail", "alarm", "fatal"	
	};
	
	private static final String RE_WORD_BOUNDARY = "\\b";
	
	private static final Pattern RE_NO_ESACPE_LOOKBEHIND = Pattern.compile("(?<!\\u001b)");
	
	private static final String WORDS_FORMAT = "(\\w*(%s)\\w*)";
	
	/*
	 * 
	 */
	
	@Colorize(AnsiEscape.FG_GREEN)
	public static final Pattern RE_WORDS_GOOD =
			Pattern.compile(join(WORDS_GOOD, "|", WORDS_FORMAT), Pattern.CASE_INSENSITIVE);
	
	@Colorize(AnsiEscape.FG_YELLOW)
	public static final Pattern RE_WORDS_BAD =
			Pattern.compile(join(WORDS_BAD, "|", WORDS_FORMAT), Pattern.CASE_INSENSITIVE);
	
	@Colorize(AnsiEscape.FG_RED)
	public static final Pattern RE_WORDS_ERROR =
			Pattern.compile(join(WORDS_ERROR, "|", WORDS_FORMAT), Pattern.CASE_INSENSITIVE);

	@Colorize(AnsiEscape.FG_CYAN)
	public static final Pattern RE_BRACKETS = Pattern.compile(RE_NO_ESACPE_LOOKBEHIND.pattern() + "[\\(\\)\\[\\]]");

	@Colorize(AnsiEscape.FG_CYAN)
	public static final Pattern RE_MAC_ADDR =
			Pattern.compile(RE_WORD_BOUNDARY + "([0-9A-F]{2}[:-]){5}([0-9A-F]{2})" + RE_WORD_BOUNDARY);

	@Colorize(AnsiEscape.FG_MAGENTA)
	public static final Pattern RE_IP4_ADDR_WITH_PORT =
			Pattern.compile(RE_WORD_BOUNDARY + "(\\d{1,3}\\.){3}\\d{1,3}(:\\d+)?" + RE_WORD_BOUNDARY);

	@Colorize(AnsiEscape.FG_BLUE)
	public static final Pattern RE_DATE_ISO8601 =
			Pattern.compile(RE_WORD_BOUNDARY + "\\d{4}-\\d{2}-\\d{2}" + RE_WORD_BOUNDARY);

	@Colorize(AnsiEscape.FG_BLUE)
	public static final Pattern RE_TIME_ISO8601 =
			Pattern.compile(RE_WORD_BOUNDARY + "\\d{2}:\\d{2}:\\d{2}(,\\d{1,3})?" + RE_WORD_BOUNDARY);

	@Colorize(AnsiEscape.FG_CYAN)
	public static final Pattern RE_LOGLEVEL_DEBUG =
			Pattern.compile(RE_WORD_BOUNDARY + "DEBUG" + RE_WORD_BOUNDARY);

	@Colorize(AnsiEscape.FG_YELLOW)
	public static final Pattern RE_LOGLEVEL_WARNING =
			Pattern.compile(RE_WORD_BOUNDARY + "WARN(ING)?" + RE_WORD_BOUNDARY);

	@Colorize(AnsiEscape.FG_RED)
	public static final Pattern RE_LOGLEVEL_ERROR =
			Pattern.compile(RE_WORD_BOUNDARY + "ERROR" + RE_WORD_BOUNDARY);

	@Colorize(AnsiEscape.FG_RED)
	public static final Pattern RE_LOGLEVEL_FATAL =
			Pattern.compile(RE_WORD_BOUNDARY+ "FATAL" + RE_WORD_BOUNDARY);

	@Colorize(AnsiEscape.FG_BLUE)
	public static final Pattern RE_HTTP_METHOD =
			Pattern.compile(RE_WORD_BOUNDARY + "GET|POST|HEAD|PUT|DELETE|TRACE|OPTIONS|CONNECT" + RE_WORD_BOUNDARY);

	/**
	 * Matches a sequence of more than <u>4</u> same characters
	 */
	@Colorize(AnsiEscape.FG_CYAN)
	public static final Pattern RE_SEQUENCE_OF_SAME_CHARACTERS = Pattern.compile("(.)\\1{4,}");

	private MatchPattern() { }
	
	private static String join(String[] array, String seperator, String format) {
		return String.format(format, StringUtils.join(array, seperator));
	}

}
