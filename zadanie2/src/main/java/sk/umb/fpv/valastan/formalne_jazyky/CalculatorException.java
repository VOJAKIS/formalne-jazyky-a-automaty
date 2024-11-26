package sk.umb.fpv.valastan.formalne_jazyky;

public class CalculatorException extends RuntimeException {
	public CalculatorException(String message) {
		super(message);
	}

	public CalculatorException(String message, Throwable throwable) {
		super(message, throwable);
	}
}