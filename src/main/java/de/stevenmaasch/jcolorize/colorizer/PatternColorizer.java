package de.stevenmaasch.jcolorize.colorizer;

import java.util.regex.Matcher;

import de.stevenmaasch.jcolorize.util.AnsiEscape;
import de.stevenmaasch.jcolorize.util.ColorMapping;
import de.stevenmaasch.jcolorize.util.Mapping;

public final class PatternColorizer {

	private final ColorMapping colorMapping;

	public String colorize(String s) {
		String tmp = s;
		for (Mapping mapping : colorMapping.getMapping()) {
			final AnsiEscape esacpe = mapping.getColor();
			final StringBuffer result = new StringBuffer();
			final Matcher matcher = mapping.getPattern().matcher(tmp);
			while (matcher.find()) {
				matcher.appendReplacement(result, esacpe.getEscapeSequence() + "$0" + AnsiEscape.FG_DEFAULT.getEscapeSequence());
			}
			matcher.appendTail(result);
			tmp = result.toString();
		}
		return tmp;
	}

	public PatternColorizer(ColorMapping mapping) {
		this.colorMapping = mapping;
	}
	
	public ColorMapping getColorMapping() {
		return colorMapping;
	}

}
