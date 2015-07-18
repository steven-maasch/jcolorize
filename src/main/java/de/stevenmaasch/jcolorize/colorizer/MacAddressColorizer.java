package de.stevenmaasch.jcolorize.colorizer;

import java.util.regex.Pattern;

import de.stevenmaasch.jcolorize.util.RegexPattern;

public class MacAddressColorizer extends AbstractColorizer {

	public static MacAddressColorizer getInstance(Colorizer item) {
		return new MacAddressColorizer(item, RegexPattern.MAC_ADDR);
	}

	private MacAddressColorizer(Colorizer item, Pattern... patterns) {
		super(item, patterns);
	}

}
