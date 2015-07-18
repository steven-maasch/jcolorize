package de.stevenmaasch.jcolorize.colorizer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.stevenmaasch.jcolorize.util.AnsiEscape;
import de.stevenmaasch.jcolorize.util.RegexPatternColorMapping;

public abstract class AbstractColorizer implements Colorizer {

	protected final Colorizer item;;

	protected final List<Pattern> patterns;

	public String colorize(String s) {
		String tmp = new String(item.colorize(s));
		for (Pattern pattern : patterns) {
			final AnsiEscape esacpe = RegexPatternColorMapping.getEscapeSequenceForPattern(pattern);
			final StringBuffer result = new StringBuffer();
			final Matcher matcher = pattern.matcher(tmp);
			while (matcher.find()) {
				matcher.appendReplacement(result, esacpe.getEscapeSequence() + "$0" + AnsiEscape.FG_DEFAULT.getEscapeSequence());
			}
			matcher.appendTail(result);
			tmp = result.toString();
		}
		return tmp;
	}

	protected AbstractColorizer(Colorizer item, Pattern... patterns) {
		this.item = item;
		this.patterns = new ArrayList<Pattern>(patterns.length);
		for (Pattern pattern : patterns) {
			this.patterns.add(pattern);
		}
	}

	public Colorizer getItem() {
		return item;
	}

	public final Iterable<Pattern> getPatterns() {
		return patterns;
	}

}
