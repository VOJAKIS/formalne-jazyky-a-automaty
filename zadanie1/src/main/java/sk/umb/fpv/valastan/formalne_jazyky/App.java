package sk.umb.fpv.valastan.formalne_jazyky;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import sk.umb.fpv.valastan.formalne_jazyky.exception.InvalidInputException;
import sk.umb.fpv.valastan.formalne_jazyky.exception.InvalidInputExceptionMessage;

/**
 * @author Adam Valašťan
 * 
 * 
 *         RegEx: [a]b{a|b}
 * 
 *         q0,a -> q1
 *         q0,b -> q2
 * 
 *         q1,a -> ⊥
 *         q1,b -> q2
 * 
 *         q2,a -> q2
 *         q2,b -> q2
 *         q2 -> finished
 */
public class App {
	public static final boolean DEBUG = false;

	public static final String SUCCESS = "A";
	public static final String FAILURE = "N";

	public static void main(String[] args) throws IOException {
		String regex = "[a]b{a|b}";
		if (args != null && args.length != 0 && args[0] != null) {
			if (App.DEBUG) {
				System.out.println(args);
			}

			String filename = args[0];
			String content = Files.readString(Paths.get(filename));
			String input = content.replace("\n", "").replace("\r", "");

			String template = "Vstup zo súboru: '%s'%s";
			String alert;
			if (input.length() >= 100) {
				alert = String.format(template, input.substring(0, 99), " (bolo vypísaných len prvých 100 znakov)");
			} else {
				alert = String.format(template, input, "");
			}
			System.out.println(alert);

			try {
				consumeInput(input);
				System.out.println(App.SUCCESS);
			} catch (InvalidInputException e) {
				System.out.println(App.FAILURE);
			}
		} else {
			String alert = String.format("Zadajte vstup pre regex '%s': ", regex);
			System.out.print(alert);
			Scanner s = new Scanner(System.in);
			String input = s.next().trim();

			try {
				consumeInput(input);
				System.out.println(App.SUCCESS);
			} catch (InvalidInputException e) {
				System.out.println(App.FAILURE);
			}

			s.close();
		}
	}

	public static void consumeInput(String input) throws InvalidInputException {
		if (App.DEBUG) {
			System.out.println(input);
		}

		q0(input);
	}

	public static String q0(String input) throws InvalidInputException {
		if (App.DEBUG) {
			System.out.println(input);
		}

		if (input.length() == 0)
			throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
		if (input.charAt(0) == 'a') {
			return q1(input.substring(1));
		}
		if (input.charAt(0) == 'b') {
			return q2(input.substring(1));
		}
		throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
	}

	public static String q1(String input) throws InvalidInputException {
		if (App.DEBUG) {
			System.out.println(input);
		}

		if (input.length() == 0) {
			throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
		}
		if (input.charAt(0) == 'a') {
			throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
		}
		if (input.charAt(0) == 'b') {
			return q2(input.substring(1));
		}
		throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
	}

	public static String q2(String input) throws InvalidInputException {
		if (App.DEBUG) {
			System.out.println(input);
		}

		if (input.length() == 0) {
			return input;
		}
		if (input.charAt(0) == 'a') {
			return q2(input.substring(1));
		}
		if (input.charAt(0) == 'b') {
			return q2(input.substring(1));
		}
		throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
	}
}