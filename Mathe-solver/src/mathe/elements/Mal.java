package mathe.elements;

import mathe.Element;
import mathe.Rechnung;

public class Mal extends Operator{
	public Mal(Rechnung e1, Rechnung e2) {
		super(e1, e2, "*");
	}

	public float calc() {
		return e1.calc() * e2.calc();
	}
}
