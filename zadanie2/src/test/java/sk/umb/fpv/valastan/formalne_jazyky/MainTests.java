package sk.umb.fpv.valastan.formalne_jazyky;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MainTests {
	@Test
	public void givenValidInputs_whenMain_thenSuccess() {
		List<InputAndOutput> inputsAndOutputs = Arrays.asList(
				// Addition
				new InputAndOutput("1+0", 1),
				new InputAndOutput("1+2", 3),
				new InputAndOutput("2+(-1)", 1),
				// Subtraction
				new InputAndOutput("1-0", 1),
				new InputAndOutput("1-2", -1),
				new InputAndOutput("5-4", 1),
				new InputAndOutput("5-(-4)", 9),
				// Multiplication
				new InputAndOutput("1*2", 2),
				new InputAndOutput("1*2*3", 6),
				new InputAndOutput("1*2*3*4", 24),
				new InputAndOutput("1*(-2)", -2),
				// Division
				new InputAndOutput("2/1", 2),
				new InputAndOutput("9/3", 3),
				new InputAndOutput("9/(-3)", -3),
				// Sum and multiplication
				new InputAndOutput("1+2*3", 7),
				new InputAndOutput("1*2+2", 4),
				new InputAndOutput("1*2+2*1", 4),
				new InputAndOutput("(1+2)*3", 9));

		inputsAndOutputs.forEach(inputAndOutput -> {
			System.out.println("\nInput           = " + inputAndOutput.getInput());
			System.out.println("Expected output = " + inputAndOutput.getOutput());

			Reader reader = new StringReader(inputAndOutput.getInput());
			Lexer lexer = new Lexer(reader);
			int actualResult = new Parser(lexer).statement();

			System.out.println("Actual output   = " + inputAndOutput.getOutput());
			assertEquals(inputAndOutput.getOutput(), actualResult);
		});
	}

	@Test
	public void givenValidInputs_whenMain_thenFailure() {
		List<InputAndOutput> inputsAndOutputs = Arrays.asList(
				// Calculator exception inbound
				new InputAndOutput("1+2*-+"),
				new InputAndOutput("1++2"),
				new InputAndOutput("1--2"),
				new InputAndOutput("1+-2"),
				new InputAndOutput("1-+2"),
				new InputAndOutput("1*-2"),
				new InputAndOutput("1/-2"));

		inputsAndOutputs.forEach(inputAndOutput -> {
			System.out.println("\nInput = " + inputAndOutput.getInput());
			System.out.println("Expecting failure");

			Reader reader = new StringReader(inputAndOutput.getInput());
			Lexer lexer = new Lexer(reader);

			assertThrows(CalculatorException.class, () -> new Parser(lexer).statement());
		});
	}
}