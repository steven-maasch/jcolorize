package de.stevenmaasch.jcolorize.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.stevenmaasch.jcolorize.colorizer.PatternColorizer;
import de.stevenmaasch.jcolorize.util.ColorMapping;


public class JColorize {

	public static final String PROGRAM_NAME = "jcolorize";

	public static void main(String[] args) {

		BufferedReader inReader = null;
		if (args.length == 0) {
			inReader = new BufferedReader(new InputStreamReader(System.in));
		} else if (args.length == 1) {
			try {
				inReader = new BufferedReader(new FileReader(new File(args[0])));
			} catch (FileNotFoundException e) {
				printError(e.getMessage(), 1);
			}
		} else {
			usage(1);
		};

		final ColorMapping colorMapping = ColorMapping.getInstance();
		PatternColorizer colorizer = new PatternColorizer(colorMapping);
		try {
			String line;
			while ((line = inReader.readLine()) != null) {
				System.out.println(colorizer.colorize(line));
			}
			inReader.close();
		} catch (IOException e) {
			printError(e.getMessage(), 1);
		}
	}
	
	private static void usage(int status) {
		System.err.printf("Usage: %s [FILE]%n", PROGRAM_NAME);
		System.exit(status);
	}

	private static void printError(String msg, int status) {
		System.err.printf("%s: %s%n", PROGRAM_NAME, msg);
		System.exit(status);
	}

}
