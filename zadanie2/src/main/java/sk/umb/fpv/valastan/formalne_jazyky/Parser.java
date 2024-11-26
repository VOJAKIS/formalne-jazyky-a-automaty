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
			switch (next) {
				case PLUS:
					value += mul();
					break;
				case MINUS:
					value -= mul();
					break;
				case NUMBER:
					return value;
				default:
					lexer.consume();
					return value;
			}
		}
	}

	private int mul() {
		int value = term();
		while (true) {
			Token next = lexer.nextToken();
			switch (next) {
				case MULTIPLICATION:
					value *= term();
					break;
				case DIVISION:
					value /= term();
					break;
				default:
					lexer.consume();
					return value;
			}
		}
	}

	private int term() {
		Token next = lexer.nextToken();
		switch (next) {
			case NUMBER:
				return lexer.getValue();
			case LEFT_PARENTHESES:
				int result = expr();
				match(Token.RIGHT_PARENTHESES);
				return result;
			default:
				throw new CalculatorException("Neočakávaný token: " + next);
		}
	}

	private void match(Token expectedSymbol) {
		Token next = lexer.nextToken();
		if (next != expectedSymbol) {
			throw new CalculatorException("Očakávaný symbol: " + expectedSymbol + ", ale našiel sa: " + next);
		}
	}
}