package sk.umb.fpv.valastan.formalne_jazyky.exception;

public enum InvalidInputExceptionMessage {
	INVALID_INPUT("Input is invalid");

	public final String label;

	private InvalidInputExceptionMessage(String label) {
		this.label = label;
	}
}
