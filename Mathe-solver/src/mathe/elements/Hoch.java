package mathe.elements;

import mathe.Element;
import mathe.Rechnung;

public class Hoch extends Operator{

	public Hoch(Rechnung e1, Rechnung e2) {
		super(e1, e2, "^");
	}

	public float calc() {
		return (float) Math.pow(e1.calc(), e2.calc());
	}
	
}
