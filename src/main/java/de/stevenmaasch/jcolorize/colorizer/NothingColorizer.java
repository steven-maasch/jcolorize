package de.stevenmaasch.jcolorize.colorizer;

public class NothingColorizer implements Colorizer {

	@Override
	public String colorize(String s) {
		return s;
	}

	public static NothingColorizer getInstance() {
		return new NothingColorizer();
	}

}
