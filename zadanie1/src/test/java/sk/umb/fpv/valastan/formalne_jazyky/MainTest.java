package sk.umb.fpv.valastan.formalne_jazyky;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class MainTest {
	@Test
	public void givenValidInputs_whenMain_thenPrintSuccess() {
		List<String> validInputs = Arrays.asList(
				"b",
				"ba",
				"bb",
				"bab",
				"bba",
				"ab",
				"ab",
				"aba",
				"abb");

		validInputs.forEach(input -> {
			if (Main.DEBUG) {
				System.out.println(String.format("input: %s", input));
			}
			assertEquals(Main.SUCCESS, Main.consumeInput(input));
		});
	}

	@Test
	public void givenInvalidInputs_whenMain_thenPrintFailure() {
		List<String> invalidInputs = Arrays.asList(
				"a",
				"aa",
				"aaa",
				"c");

		invalidInputs.forEach(input -> {
			if (Main.DEBUG) {
				System.out.println(String.format("input: %s", input));
			}
			assertEquals(Main.FAILURE, Main.consumeInput(input));
		});
	}
}