package de.stevenmaasch.jcolorize.colorizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import de.stevenmaasch.jcolorize.util.AnsiEscape;
import de.stevenmaasch.jcolorize.util.RegexPattern;

public final class PatternColorizer {

	private final Iterable<Pair<Pattern, AnsiEscape>> mappings;

	public String colorize(String s) {
		String tmp = new String(s);
		for (Pair<Pattern, AnsiEscape> pair : mappings) {
			final AnsiEscape esacpe = pair.getRight();
			final StringBuffer result = new StringBuffer();
			final Matcher matcher = pair.getKey().matcher(tmp);
			while (matcher.find()) {
				matcher.appendReplacement(result, esacpe.getEscapeSequence() + "$0" + AnsiEscape.FG_DEFAULT.getEscapeSequence());
			}
			matcher.appendTail(result);
			tmp = result.toString();
		}
		return tmp;
	}

	private PatternColorizer(Iterable<Pair<Pattern, AnsiEscape>> mappings) {
		this.mappings = mappings;
	}
	
	public static PatternColorizer getInstance() {
		final ArrayList<Pair<Pattern, AnsiEscape>> mappings = new ArrayList<Pair<Pattern, AnsiEscape>>();
		mappings.add(new ImmutablePair<Pattern, AnsiEscape>(RegexPattern.BRACKETS, AnsiEscape.FG_CYAN));
		mappings.add(new ImmutablePair<Pattern, AnsiEscape>(RegexPattern.MAC_ADDR, AnsiEscape.FG_CYAN));
		mappings.add(new ImmutablePair<Pattern, AnsiEscape>(RegexPattern.IP4_ADDR_WITH_PORT, AnsiEscape.FG_MAGENTA));
		mappings.add(new ImmutablePair<Pattern, AnsiEscape>(RegexPattern.HTTP_METHOD, AnsiEscape.FG_CYAN));
		mappings.add(new ImmutablePair<Pattern, AnsiEscape>(RegexPattern.DATE_ISO8601, AnsiEscape.FG_BLUE));
		mappings.add(new ImmutablePair<Pattern, AnsiEscape>(RegexPattern.TIME_ISO8601, AnsiEscape.FG_BLUE));
		mappings.add(new ImmutablePair<Pattern, AnsiEscape>(RegexPattern.LOGLEVEL_ERROR, AnsiEscape.FG_RED));
		mappings.add(new ImmutablePair<Pattern, AnsiEscape>(RegexPattern.LOGLEVEL_FATAL, AnsiEscape.FG_RED));
		mappings.add(new ImmutablePair<Pattern, AnsiEscape>(RegexPattern.LOGLEVEL_WARNING, AnsiEscape.FG_YELLOW));
		mappings.add(new ImmutablePair<Pattern, AnsiEscape>(RegexPattern.LOGLEVEL_DEBUG, AnsiEscape.FG_CYAN));
		return new PatternColorizer(mappings);
	}
	
	public Iterable<Pair<Pattern, AnsiEscape>> getMappings() {
		return mappings;
	}

}
