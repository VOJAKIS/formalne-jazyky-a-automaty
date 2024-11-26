package sk.umb.fpv.valastan.formalne_jazyky;

import java.io.Reader;
import java.io.StringReader;

/**
 * @author Bc. Adam Valašťan
 */
public class Main {
	public static void main(String[] args) {
		try {
			// System.out.println("Zadajte výraz pre kalkulačku: ");
			Reader reader = new StringReader("(1+2)*3");

			Lexer lexer = new Lexer(reader);
			int result = new Parser(lexer).statement();

			System.out.printf("\nResult = %d\n", result);
		} catch (Exception e) {
			throw new CalculatorException(e.getMessage());
		}
	}
}