package de.stevenmaasch.jcolorize.util;

public enum AnsiEscape {

	NULL_OBJECT(""),
	FG_BLACK("\u001b[30m"),
	FG_RED("\u001b[31m"),
	FG_GREEN("\u001b[32m"),
	FG_YELLOW("\u001b[33m"),
	FG_BLUE("\u001b[34m"),
	FG_MAGENTA("\u001b[35m"),
	FG_CYAN("\u001b[36m"),
	FG_WHITE("\u001b[37m"),
	FG_DEFAULT("\u001b[0m");


//    30 black
//    31 red
//    32 green
//    33 yellow
//    34 blue
//    35 magenta
//    36 cyan
//    37 white
//    40 black background
//    41 red background
//    42 green background
//    43 yellow background
//    44 blue background
//    45 magenta background
//    46 cyan background
//    47 white background
//    1 make bright (usually just bold)
//    21 stop bright (normalizes boldness)
//    4 underline
//    24 stop underline
//    0 clear all formatting


	private final String escapeSequence;

	private AnsiEscape(String escapeSequence) {
		this.escapeSequence = escapeSequence;
	}

	public String getEscapeSequence() {
		return escapeSequence;
	}

}
