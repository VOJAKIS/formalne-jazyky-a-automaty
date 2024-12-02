package sk.umb.fpv.valastan.formalne_jazyky;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
	private int currentCharacterAsCharCode;

	private final Reader input;

	public Lexer(Reader reader) {
		this.input = reader;
		consume();
	}

	public Token nextToken() {
		switch (currentCharacterAsCharCode) {
			case '+':
				return Token.PLUS;
			case '-':
				return Token.MINUS;
			case '*':
				return Token.MULTIPLICATION;
			case '/':
				return Token.DIVISION;
			case '^':
				return Token.EXPONENTIATION;
			case '(':
				return Token.LEFT_PARENTHESES;
			case ')':
				return Token.RIGHT_PARENTHESES;
			case -1:
				return Token.END_OF_FILE;
			default:
				if (Character.isDigit(currentCharacterAsCharCode)) {
					return Token.NUMBER;
				}
				throw new CalculatorException("Neplatný znak: " + currentCharacterAsCharCode);
		}
	}

	public void consume() {
		try {
			currentCharacterAsCharCode = input.read();
		} catch (IOException e) {
			throw new CalculatorException("Chyba pri čítaní vstupu", e);
		}
	}

	public int getValue() {
		Character valueAsChar = (char) currentCharacterAsCharCode;
		String valueAsString = String.valueOf(valueAsChar);
		return Integer.valueOf(valueAsString);
	}
}