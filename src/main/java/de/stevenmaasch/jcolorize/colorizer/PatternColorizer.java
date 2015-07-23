package de.stevenmaasch.jcolorize.colorizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.Pair;

import de.stevenmaasch.jcolorize.util.AnsiEscape;
import de.stevenmaasch.jcolorize.util.ColorMapping;

public final class PatternColorizer {

	private final ColorMapping mapping;

	public String colorize(String s) {
		String tmp = s;
		for (Pair<Pattern, AnsiEscape> pair : mapping.getMapping()) {
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

	public PatternColorizer(ColorMapping mapping) {
		this.mapping = mapping;
	}
	
	public ColorMapping getColorMapping() {
		return mapping;
	}

}
