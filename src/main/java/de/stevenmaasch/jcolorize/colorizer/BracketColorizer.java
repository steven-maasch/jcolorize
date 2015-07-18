package de.stevenmaasch.jcolorize.colorizer;

import java.util.regex.Pattern;

import de.stevenmaasch.jcolorize.util.RegexPattern;

public class BracketColorizer extends AbstractColorizer {

	public static BracketColorizer getInstance(Colorizer item) {
		return new BracketColorizer(item, RegexPattern.BRACKETS);
	}

	protected BracketColorizer(Colorizer item, Pattern... patterns) {
		super(item, patterns);
	}

}
