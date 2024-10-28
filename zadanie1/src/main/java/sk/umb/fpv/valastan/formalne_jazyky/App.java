package sk.umb.fpv.valastan.formalne_jazyky;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

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

			String alert = String.format("Vstup zo súboru: '%s'", input);
			System.out.println(alert);

			consumeInput(input);
		} else {
			String alert = String.format("Zadajte vstup pre regex '%s': ", regex);
			System.out.print(alert);
			Scanner s = new Scanner(System.in);
			consumeInput(s.next().trim());
			s.close();
		}
	}

	public static String consumeInput(String input) {
		if (App.DEBUG) {
			System.out.println(input);
		}

		String finalString = q0(input);

		if (App.DEBUG) {
			System.out.println(finalString);
		}

		if (finalString != null && finalString.length() == 0) {
			System.out.println(App.SUCCESS);
			return App.SUCCESS;
		} else {
			System.out.println(App.FAILURE);
			return App.FAILURE;
		}
	}

	public static String q0(String input) {
		if (App.DEBUG) {
			System.out.println(input);
		}

		if (input.length() == 0)
			return null;
		if (input.charAt(0) == 'a') {
			return q1(input.substring(1));
		}
		if (input.charAt(0) == 'b') {
			return q2(input.substring(1));
		}
		return null;
	}

	public static String q1(String input) {
		if (App.DEBUG) {
			System.out.println(input);
		}

		if (input.length() == 0) {
			return null;
		}
		if (input.charAt(0) == 'a') {
			return null;
		}
		if (input.charAt(0) == 'b') {
			return q2(input.substring(1));
		}
		return null;
	}

	public static String q2(String input) {
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
		return null;
	}
}