package sk.umb.fpv.valastan.formalne_jazyky;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Bc. Adam Valašťan
 */
public class Main {
	public static final Boolean DEBUG = false;

	private static String inputFromUser = "";
	private static List<Variable> variables = new ArrayList<>();

	public static void main(String[] args) {
		try {
			String alertForUserInput = String.format("Zadajte výraz na výpočet (napríklad 1+2): ");
			System.out.print(alertForUserInput);
			getInputFromUser();
			while (doesInputContainsVariables()) {
				String inputString = variables.toString();
				System.out.println("Výraz obsahuje premenné, zadajte hodnoty pre premenné oddelené medzerou: "
						+ inputString.substring(1, inputString.length() - 1));
				getInputForVariables();
			}

			Reader reader = new StringReader(inputFromUser);
			Lexer lexer = new Lexer(reader);
			int result = new Parser(lexer).statement();

			System.out.printf("\nResult = %d\n", result);
		} catch (Exception e) {
			throw new CalculatorException(e.getMessage());
		}
	}

	private static void getInputFromUser() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next().trim();
		inputFromUser = input;
		scanner.close();
	}

	private static void getInputForVariables() {
		Scanner scanner = new Scanner(System.in);
		String inputForVariables = scanner.nextLine();
		String[] variableValues = inputForVariables.trim().split(" ");

		if (variableValues.length != variables.size()) {
			System.out.println("Nezadali ste všetky premenné, skúste to znova");
			getInputForVariables();
		} else {
			StringBuilder newInput = new StringBuilder();
			int variableIndex = 0;
			for (int i = 0; i < inputFromUser.length(); i++) {
				char userInputCharacter = inputFromUser.charAt(i);
				if (Character.isAlphabetic(userInputCharacter)) {
					newInput.append(variableValues[variableIndex++]);
				} else {
					newInput.append(userInputCharacter);
				}
			}
			inputFromUser = newInput.toString();
			System.out.println("Výraz po doplnení premenných: " + inputFromUser);
		}
		scanner.close();
	}

	private static Boolean doesInputContainsVariables() {
		variables.clear();
		for (int i = 0; i < inputFromUser.length(); i++) {
			char currentCharacter = inputFromUser.charAt(i);
			if (Character.isAlphabetic(currentCharacter)) {
				variables.add(new Variable(currentCharacter, i));
			}
		}
		return !variables.isEmpty();
	}
}