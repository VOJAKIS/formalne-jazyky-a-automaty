package sk.umb.fpv.valastan.formalne_jazyky;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Variable {
	private Character name;
	private Integer index;

	@Override
	public String toString() {
		return "zadajte " + name + "=";
	}
}