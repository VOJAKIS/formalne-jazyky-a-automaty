package sk.umb.fpv.valastan.formalne_jazyky;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
	private int current;
	// private int value;

	private final Reader input;

	public Lexer(Reader reader) {
		this.input = reader;
		consume();
	}

	public Token nextToken() {
		System.out.println((char) current);

		switch (current) {
			case '+':
				consume();
				return Token.PLUS;
			case '-':
				consume();
				return Token.MINUS;
			case '*':
				consume();
				return Token.MULTIPLICATION;
			case '/':
				consume();
				return Token.DIVISION;
			case '(':
				consume();
				return Token.LEFT_PARENTHESES;
			case ')':
				consume();
				return Token.RIGHT_PARENTHESES;
			case -1:
				return Token.END_OF_FILE;
			default:
				if (Character.isDigit(current)) {
					return Token.NUMBER;
				}
				throw new CalculatorException("Neplatný znak: " + current);
		}
	}

	public void consume() {
		try {
			current = input.read();
		} catch (IOException e) {
			throw new CalculatorException("Chyba pri čítaní vstupu", e);
		}
	}

	public int getValue() {
		Character valueAsChar = (char) current;
		String valueAsString = String.valueOf(valueAsChar);
		return Integer.valueOf(valueAsString);
	}
}