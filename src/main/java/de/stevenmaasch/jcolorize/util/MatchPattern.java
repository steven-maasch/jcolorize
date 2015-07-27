package de.stevenmaasch.jcolorize.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public final class MatchPattern {

	private static final String[] PHRASES_GOOD = {
		"done",
		"complet(?:e[d]?|ing){1}",
		"start(?:ed|ing)?",
		"ready",
		"succeeded",
		"success(?:fully)?",
		"finish(?:ed)?",
		"configure[d]?",
		"enabl(?:e[d]?|ing){1}",
		"active",
		"found",
		"detected"
	};
	
	private static final String[] PHRASES_BAD = {
		"(?:can[ ]?)?not(?: available| supported| work(?:ing)?| found| enabled| started| completed)?",
		"but no",
		"can't",
		"empty",
		"null",
		"unable",
		"offline",
		"missing",
		"exit",
		"warning",
		"no result[s]?",
		"restart(?:ed|ing)?",
		"shut(?:ting)?(?:[ ]?down)?",
		"clos(?:e[d]?|ing){1}",
		"stop(?:ped|ping)?",
		"ignor(?:e[d]?|ing){1}",
		"block(?:ed|ing)?",
		"disable[d]?",
		"skip(?:ped|ping)?",
		"unreach(?:ed|able)?",
		"deny|denied",
		"terminat(?:e[d]?|ing){1}",
		"throw(?:ed|ing)?",
		"destroy(?:ed|ing)?",
		"unbound(?:ed)?",
		"unregistered",
		"exception",
		"todo"
	};
	
	private static final String[] PHRASES_ERROR = {
		"fatal",
		"fail(?:ed)?",
		"invalid",
		"alarm",
		"critical",
		"bug",
		"error",
		"unexpected"
	};
	
	private static final String[] PHRASES_JAVA = {
		"persistence unit",
		"data source",
		"jta"
	};
	
	private static final String RE_WORD_BOUNDARY = "\\b";
	
	private static final Pattern RE_NO_ESACPE_LOOKBEHIND = Pattern.compile("(?<!\\u001b)");
	
	private static final String PHRASES_FORMAT = "(\\b(%s)\\b)";
	
	@Colorize(AnsiEscape.FG_GREEN)
	public static final Pattern RE_PHRASES_GOOD =
			Pattern.compile(join(PHRASES_GOOD, "|", PHRASES_FORMAT), Pattern.CASE_INSENSITIVE);
	
	@Colorize(AnsiEscape.FG_YELLOW)
	public static final Pattern RE_PHRASES_BAD =
			Pattern.compile(join(PHRASES_BAD, "|", PHRASES_FORMAT), Pattern.CASE_INSENSITIVE);
	
	@Colorize(AnsiEscape.FG_RED)
	public static final Pattern RE_PHRASES_ERROR =
			Pattern.compile(join(PHRASES_ERROR, "|", PHRASES_FORMAT), Pattern.CASE_INSENSITIVE);
	
	@Colorize(AnsiEscape.FG_MAGENTA)
	public static final Pattern RE_PHRASES_JAVA =
			Pattern.compile(join(PHRASES_JAVA, "|", PHRASES_FORMAT), Pattern.CASE_INSENSITIVE);

	@Colorize(value = AnsiEscape.FG_CYAN, position = 10)
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
	
	/**
	 * Sat Jul 11 13:52:30 CEST 2015
	 * TODO: Merge with date iso
	 */
	@Colorize(value = AnsiEscape.FG_BLUE)
	public static final Pattern RE_DATE =
			Pattern.compile(RE_WORD_BOUNDARY +
					"[a-zA-z]{3} [a-zA-z]{3} \\d{2} \\d{2}:\\d{2}:\\d{2} [a-zA-z]{1,5} \\d{4}" +
					RE_WORD_BOUNDARY);

	// TODO: Merge with date iso
	@Colorize(value = AnsiEscape.FG_BLUE, enabled = false)
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
