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
	public static final boolean DEBUG = Boolean.FALSE;

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

			String output = consumeInput(input);
			System.out.println(output);
		} else {
			String alert = String.format("Zadajte vstup pre regex '%s': ", regex);
			System.out.print(alert);
			Scanner s = new Scanner(System.in);
			String input = s.next().trim();

			String output = consumeInput(input);
			System.out.println(output);

			s.close();
		}
	}

	public static String consumeInput(String input) {
		if (App.DEBUG) {
			System.out.println(input);
		}

		int startingIndex = 0;
		try {
			q0(input, startingIndex);
			return App.SUCCESS;
		} catch (InvalidInputException e) {
			return App.FAILURE;
		}
	}

	public static void q0(String input, int characterIndex) throws InvalidInputException {
		if (App.DEBUG) {
			System.out.println(input + " " + characterIndex);
		}

		if (input.length() == 0) {
			throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
		}

		if (input.charAt(characterIndex) == 'a') {
			q1(input, ++characterIndex);
			return;
		}

		if (input.charAt(characterIndex) == 'b') {
			q2(input, ++characterIndex);
			return;
		}

		throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
	}

	public static void q1(String input, int characterIndex) throws InvalidInputException {
		if (App.DEBUG) {
			System.out.println(input + " " + characterIndex);
		}

		if (input.length() == 0) {
			throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
		}

		if (characterIndex >= input.length()) {
			if (input.charAt(characterIndex - 1) == 'a') {
				throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
			}
			return;
		}

		if (input.charAt(characterIndex) == 'a') {
			throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
		}

		if (input.charAt(characterIndex) == 'b') {
			q2(input, ++characterIndex);
			return;
		}

		throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
	}

	public static void q2(String input, int characterIndex) throws InvalidInputException {
		if (App.DEBUG) {
			System.out.println(input + " " + characterIndex);
		}

		if (input.length() == 0) {
			return;
		}

		if (characterIndex >= input.length()) {
			return;
		}

		if (input.charAt(characterIndex) == 'a') {
			q2(input, ++characterIndex);
			return;
		}

		if (input.charAt(characterIndex) == 'b') {
			q2(input, ++characterIndex);
			return;
		}

		throw new InvalidInputException(InvalidInputExceptionMessage.INVALID_INPUT);
	}
}