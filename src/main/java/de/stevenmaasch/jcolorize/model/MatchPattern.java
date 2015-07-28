package de.stevenmaasch.jcolorize.model;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import de.stevenmaasch.jcolorize.util.Colorize;

public final class MatchPattern {

	private static final String[] phrasesGood = {
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
	
	private static final String[] phrasesBad = {
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
	
	private static final String[] phrasesError = {
		"fatal",
		"fail(?:ed)?",
		"invalid",
		"alarm",
		"critical",
		"bug",
		"error",
		"unexpected"
	};
	
	private static final String[] phrasesJava = {
		"persistence unit",
		"data source"
	};
	
	private static final String wordBoundary = "\\b";
	
	private static final String phrasesFormat = "(\\b(%s)\\b)";
	
	private static final Pattern reNoEscapeLookbehind = Pattern.compile("(?<!\\u001b)");
	
	@Colorize(AnsiEscape.FG_GREEN)
	public static final Pattern RE_PHRASES_GOOD =
			Pattern.compile(join(phrasesGood, "|", phrasesFormat), Pattern.CASE_INSENSITIVE);
	
	@Colorize(AnsiEscape.FG_YELLOW)
	public static final Pattern RE_PHRASES_BAD =
			Pattern.compile(join(phrasesBad, "|", phrasesFormat), Pattern.CASE_INSENSITIVE);
	
	@Colorize(AnsiEscape.FG_RED)
	public static final Pattern RE_PHRASES_ERROR =
			Pattern.compile(join(phrasesError, "|", phrasesFormat), Pattern.CASE_INSENSITIVE);
	
	@Colorize(AnsiEscape.FG_MAGENTA)
	public static final Pattern RE_PHRASES_JAVA =
			Pattern.compile(join(phrasesJava, "|", phrasesFormat), Pattern.CASE_INSENSITIVE);

	@Colorize(value = AnsiEscape.FG_CYAN, position = 10)
	public static final Pattern RE_BRACKETS = Pattern.compile(reNoEscapeLookbehind.pattern() + "[\\(\\)\\[\\]]");

	@Colorize(AnsiEscape.FG_CYAN)
	public static final Pattern RE_MAC_ADDR =
			Pattern.compile(wordBoundary + "([0-9A-F]{2}[:-]){5}([0-9A-F]{2})" + wordBoundary);

	@Colorize(AnsiEscape.FG_MAGENTA)
	public static final Pattern RE_IP4_ADDR_WITH_PORT =
			Pattern.compile(wordBoundary + "(\\d{1,3}\\.){3}\\d{1,3}(:\\d+)?" + wordBoundary);
	
	/**
	 * Machting example: 2015-07-14 00:01:05,702
	 */
	@Colorize(AnsiEscape.FG_BLUE)
	public static final Pattern RE_DATE_1 =
			Pattern.compile(wordBoundary +
					"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{1,3}" + wordBoundary);
	
	/**
	 * Machting example: Sat Jul 11 13:52:30 CEST 2015
	 */
	@Colorize(value = AnsiEscape.FG_BLUE)
	public static final Pattern RE_DATE_2 =
			Pattern.compile(wordBoundary +
					"\\b[a-zA-z]{3} [a-zA-z]{3} \\d{2} \\d{2}:\\d{2}:\\d{2} [a-zA-z]{1,5} \\d{4}\\b" +
					wordBoundary);

	@Colorize(AnsiEscape.FG_CYAN)
	public static final Pattern RE_LOGLEVEL_DEBUG =
			Pattern.compile(wordBoundary + "DEBUG" + wordBoundary);

	@Colorize(AnsiEscape.FG_YELLOW)
	public static final Pattern RE_LOGLEVEL_WARNING =
			Pattern.compile(wordBoundary + "WARN(ING)?" + wordBoundary);

	@Colorize(AnsiEscape.FG_RED)
	public static final Pattern RE_LOGLEVEL_ERROR =
			Pattern.compile(wordBoundary + "ERROR" + wordBoundary);

	@Colorize(AnsiEscape.FG_RED)
	public static final Pattern RE_LOGLEVEL_FATAL =
			Pattern.compile(wordBoundary + "FATAL" + wordBoundary);

	@Colorize(AnsiEscape.FG_BLUE)
	public static final Pattern RE_HTTP_METHOD =
			Pattern.compile(wordBoundary + "GET|POST|HEAD|PUT|DELETE|TRACE|OPTIONS|CONNECT" + wordBoundary);

	/**
	 * Matches a sequence of more than <u>4</u> same characters.
	 */
	@Colorize(AnsiEscape.FG_CYAN)
	public static final Pattern RE_SEQUENCE_OF_SAME_CHARACTERS = Pattern.compile("(.)\\1{4,}");

	private MatchPattern() { }
	
	private static String join(String[] array, String seperator, String format) {
		return String.format(format, StringUtils.join(array, seperator));
	}

}
