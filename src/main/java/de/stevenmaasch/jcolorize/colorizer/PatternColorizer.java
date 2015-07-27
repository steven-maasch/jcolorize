package de.stevenmaasch.jcolorize.colorizer;

import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.stevenmaasch.jcolorize.util.AnsiEscape;
import de.stevenmaasch.jcolorize.util.DefaultColorMapping;

public final class PatternColorizer {

	private final DefaultColorMapping mapping;

	public String colorize(String s) {
		String tmp = s;
		for (Entry<Pattern, AnsiEscape> entry : mapping.getMapping().entrySet()) {
			final AnsiEscape esacpe = entry.getValue();
			final StringBuffer result = new StringBuffer();
			final Matcher matcher = entry.getKey().matcher(tmp);
			while (matcher.find()) {
				matcher.appendReplacement(result, esacpe.getEscapeSequence() + "$0" + AnsiEscape.FG_DEFAULT.getEscapeSequence());
			}
			matcher.appendTail(result);
			tmp = result.toString();
		}
		return tmp;
	}

	public PatternColorizer(DefaultColorMapping mapping) {
		this.mapping = mapping;
	}
	
	public DefaultColorMapping getColorMapping() {
		return mapping;
	}

}
