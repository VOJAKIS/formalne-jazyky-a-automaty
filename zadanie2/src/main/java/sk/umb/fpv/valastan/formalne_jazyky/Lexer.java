package sk.umb.fpv.valastan.formalne_jazyky;

import java.io.Reader;

public class Lexer {
	private final Reader input;

	public Lexer(Reader reader) {
		this.input = reader;
	}

	public Token nextToken() {
		// TODO: implement me
		return Token.I_LOVE_FORMAL_LANGUAGES;
	}

	private void consume() {
		// TODO: implement me
	}
}