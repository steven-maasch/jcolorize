package de.stevenmaasch.jcolorize.colorizer;

import java.util.regex.Pattern;

import de.stevenmaasch.jcolorize.util.RegexPattern;

public class IpAddressColorizer extends AbstractColorizer {

	public static IpAddressColorizer getInstance(Colorizer item) {
		return new IpAddressColorizer(item, RegexPattern.IP4_ADDR_WITH_PORT);
	}

	protected IpAddressColorizer(Colorizer item, Pattern... patterns) {
		super(item, patterns);
	}


}
