package sk.umb.fpv.valastan.formalne_jazyky;

import java.util.Arrays;
import java.util.List;

public class Parser {
	private final Lexer lexer;
	private final List<Token> PROHOBITED_TOKENS_AFTER_SIGN = Arrays.asList(
			Token.PLUS,
			Token.MINUS,
			Token.MULTIPLICATION,
			Token.DIVISION);

	public Parser(Lexer lexer) {
		this.lexer = lexer;
	}

	public int statement() {
		return E();
	}

	private int E() {
		int value = F();

		while (true) {
			Token next = lexer.nextToken();
			if (Main.DEBUG) {
				System.out.println("expr next token=" + next);
			}
			switch (next) {
				case EXPONENTIATION:
					lexer.consume();
					throwIfNextTokenIsOneOf(PROHOBITED_TOKENS_AFTER_SIGN);
					value = (int) Math.pow(value, T());
					break;
				case PLUS:
					lexer.consume();
					throwIfNextTokenIsOneOf(PROHOBITED_TOKENS_AFTER_SIGN);
					value += F();
					break;
				case MINUS:
					lexer.consume();
					throwIfNextTokenIsOneOf(PROHOBITED_TOKENS_AFTER_SIGN);
					value -= F();
					break;
				default:
					return value;
			}
		}
	}

	private int F() {
		int value = T();

		while (true) {
			Token next = lexer.nextToken();
			if (Main.DEBUG) {
				System.out.println("mul next token=" + next);
			}
			switch (next) {
				case MULTIPLICATION:
					lexer.consume();
					throwIfNextTokenIsOneOf(PROHOBITED_TOKENS_AFTER_SIGN);
					value *= T();
					break;
				case DIVISION:
					lexer.consume();
					throwIfNextTokenIsOneOf(PROHOBITED_TOKENS_AFTER_SIGN);
					int temp = T();
					if (temp == 0) {
						throw new ArithmeticException("You cannot divide by zero (not in this case).");
					}
					value /= temp;
					break;
				default:
					return value;
			}
		}
	}

	private int T() {
		Token next = lexer.nextToken();
		if (Main.DEBUG) {
			System.out.println("term next token=" + next);
		}

		Integer timesNumber = 1;
		switch (next) {
			case PLUS:
				timesNumber = +1;
				lexer.consume();
				next = lexer.nextToken();
				break;
			case MINUS:
				timesNumber = -1;
				lexer.consume();
				next = lexer.nextToken();
				break;
			default:
				break;
		}

		switch (next) {
			case NUMBER:
				int temp = lexer.getValue();
				lexer.consume();
				while (lexer.nextToken().equals(Token.NUMBER)) {
					temp = temp * 10 + lexer.getValue();
					lexer.consume();
				}
				return temp *= timesNumber;
			case LEFT_PARENTHESES:
				lexer.consume();
				int result = E();
				match(Token.RIGHT_PARENTHESES);
				lexer.consume();
				return result;
			default:
				throw new CalculatorException("Neočakávaný token: " + next);
		}
	}

	private void match(Token expectedSymbol) {
		Token next = lexer.nextToken();
		if (Main.DEBUG) {
			System.out.println("match next token=" + next);
		}
		if (Main.DEBUG) {
			System.out.println("match expected token=" + expectedSymbol);
		}
		if (next != expectedSymbol) {
			throw new CalculatorException("Očakávaný symbol: " + expectedSymbol + ", ale našiel sa: " + next);
		}
	}

	private void throwIfNextTokenIsOneOf(List<Token> tokens) {
		tokens.forEach(token -> {
			if (lexer.nextToken().equals(token)) {
				throw new CalculatorException("Neočakávaný token: " + token);
			}
		});
	}
}