package de.stevenmaasch.jcolorize.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegexPatternColorMapping {

	public static final Map<Pattern, AnsiEscape> mapping = new HashMap<Pattern, AnsiEscape>();

	static {
		mapping.put(RegexPattern.BRACKETS, AnsiEscape.FG_CYAN);
		mapping.put(RegexPattern.MAC_ADDR, AnsiEscape.FG_CYAN);
		mapping.put(RegexPattern.IP4_ADDR_WITH_PORT, AnsiEscape.FG_MAGENTA);
		mapping.put(RegexPattern.HTTP_METHOD, AnsiEscape.FG_CYAN);
		mapping.put(RegexPattern.DATE_ISO8601, AnsiEscape.FG_BLUE);
		mapping.put(RegexPattern.TIME_ISO8601, AnsiEscape.FG_BLUE);
		mapping.put(RegexPattern.LOGLEVEL_ERROR, AnsiEscape.FG_RED);
		mapping.put(RegexPattern.LOGLEVEL_FATAL, AnsiEscape.FG_RED);
		mapping.put(RegexPattern.LOGLEVEL_WARNING, AnsiEscape.FG_YELLOW);
		mapping.put(RegexPattern.LOGLEVEL_DEBUG, AnsiEscape.FG_CYAN);
	}

	public static AnsiEscape getEscapeSequenceForPattern(Pattern pattern) {
		AnsiEscape esacpeSequence = mapping.get(pattern);
		if (esacpeSequence != null) {
			return esacpeSequence;
		} else {
			return AnsiEscape.NULL_OBJECT;
		}
	}

}
