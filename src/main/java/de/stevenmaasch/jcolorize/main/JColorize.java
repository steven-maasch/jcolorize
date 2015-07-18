package de.stevenmaasch.jcolorize.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import de.stevenmaasch.jcolorize.colorizer.BracketColorizer;
import de.stevenmaasch.jcolorize.colorizer.Colorizer;
import de.stevenmaasch.jcolorize.colorizer.DateColorizer;
import de.stevenmaasch.jcolorize.colorizer.HttpMethodColorizer;
import de.stevenmaasch.jcolorize.colorizer.IpAddressColorizer;
import de.stevenmaasch.jcolorize.colorizer.LogLevelColorizer;
import de.stevenmaasch.jcolorize.colorizer.MacAddressColorizer;
import de.stevenmaasch.jcolorize.colorizer.NothingColorizer;
import de.stevenmaasch.jcolorize.colorizer.TimeColorizer;


public class JColorize {

	public static final String PROGRAM_NAME = "jcolorize";

	public static void main(String[] args) {

		final CommandLineParser parser = new DefaultParser();

		final Options options = new Options();
		options.addOption(null, "help", false, "print this message and quit");
		options.addOption(null, "version", false, "print version information and quit");
		options.addOption(Option.builder().argName("FILE")..build());

		try {
			final CommandLine line = parser.parse(options, args);
			line.h


		}
		catch( ParseException exp ) {
			printError(exp.getMessage(), 1);
		}


















		BufferedReader inReader = null;

		if (args.length == 1) {
			inReader = new BufferedReader(new InputStreamReader(System.in));
		} else if (args.length == 2) {
			try {
				inReader = new BufferedReader(new FileReader(new File(args[2])));
			} catch (FileNotFoundException e) {
				printError(e.getMessage(), 1);
			}
		} else {
			usage(1);
		};

		try {
			String line;
			while ((line = inReader.readLine()) != null) {
				Colorizer colorizer = NothingColorizer.getInstance();
				colorizer = BracketColorizer.getInstance(colorizer);
				colorizer = DateColorizer.getInstance(colorizer);
				colorizer = TimeColorizer.getInstance(colorizer);
				colorizer = LogLevelColorizer.getInstance(colorizer);
				colorizer = MacAddressColorizer.getInstance(colorizer);
				colorizer = HttpMethodColorizer.getInstance(colorizer);
				colorizer = IpAddressColorizer.getInstance(colorizer);
				final String colorizedLine = colorizer.colorize(line);
				System.out.println(colorizedLine);
			}
			inReader.close();
		} catch (IOException e) {
			printError(e.getMessage(), 1);
		}
	}

	private static void printError(String msg, int status) {
		System.err.println(msg);
		System.exit(status);
	}

}
