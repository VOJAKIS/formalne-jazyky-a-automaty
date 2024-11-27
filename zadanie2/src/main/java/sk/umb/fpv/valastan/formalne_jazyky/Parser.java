package sk.umb.fpv.valastan.formalne_jazyky;

public class Parser {
	private final Lexer lexer;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
	}

	public int statement() {
		return expr();
	}

	private int expr() {
		int value = mul();

		while (true) {
			Token next = lexer.nextToken();
			if (Main.DEBUG) {
				System.out.println("expr next token=" + next);
			}
			switch (next) {
				case PLUS:
					lexer.consume();
					value += mul();
					break;
				case MINUS:
					lexer.consume();
					value -= mul();
					break;
				default:
					return value;
			}
		}
	}

	private int mul() {
		int value = term();

		while (true) {
			Token next = lexer.nextToken();
			if (Main.DEBUG) {
				System.out.println("mul next token=" + next);
			}
			switch (next) {
				case MULTIPLICATION:
					lexer.consume();
					value *= term();
					break;
				case DIVISION:
					lexer.consume();
					int temp = term();
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

	private int term() {
		Token next = lexer.nextToken();
		if (Main.DEBUG) {
			System.out.println("term next token=" + next);
		}
		switch (next) {
			case NUMBER:
				int temp = lexer.getValue();
				lexer.consume();
				return temp;
			case LEFT_PARENTHESES:
				lexer.consume();
				int result = expr();
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
}