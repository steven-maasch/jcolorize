package de.stevenmaasch.jcolorize.util;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Mapping {
	
	private final Pattern pattern;
	
	private final AnsiEscape color;
	
	private final int position;

	protected Mapping(Pattern pattern, AnsiEscape color, int position) {
		this.pattern = Objects.requireNonNull(pattern);
		this.color = Objects.requireNonNull(color);
		this.position = position;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public AnsiEscape getColor() {
		return color;
	}

	public int getPosition() {
		return position;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mapping [pattern=");
		builder.append(pattern);
		builder.append(", color=");
		builder.append(color);
		builder.append(", position=");
		builder.append(position);
		builder.append("]");
		return builder.toString();
	}
	
}
