package sk.umb.fpv.valastan.formalne_jazyky;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MainTests {
	@Test
	public void givenValidInputs_whenMain_thenPrintSuccess() {
		List<InputAndOutput> inputsAndOutputs = Arrays.asList(
				new InputAndOutput("1+2", 3),
				new InputAndOutput("1+2*3", 7),
				new InputAndOutput("(1+2)*3", 9));

		inputsAndOutputs.forEach(inputAndOutput -> {
			System.out.println("Input=  " + inputAndOutput.getInput());
			System.out.println("Output= " + inputAndOutput.getOutput());

			Reader reader = new StringReader(inputAndOutput.getInput());
			Lexer lexer = new Lexer(reader);
			int actualResult = new Parser(lexer).statement();
			assertEquals(actualResult, inputAndOutput.getOutput());
		});
	}
}