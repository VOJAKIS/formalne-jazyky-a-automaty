package sk.umb.fpv.valastan.formalne_jazyky;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class InputAndOutput {
	@NonNull
	private String input;
	private int output;
}
