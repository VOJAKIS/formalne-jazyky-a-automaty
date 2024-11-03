package sk.umb.fpv.valastan.formalne_jazyky.exception;

public class InvalidInputException extends Exception {
	public InvalidInputException(String message) {
		super(message);
	}

	public InvalidInputException(InvalidInputExceptionMessage message) {
		super(message.label);
	}
}
