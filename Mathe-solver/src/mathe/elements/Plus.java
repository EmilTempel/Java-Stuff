package mathe.elements;

import mathe.Rechnung;

public class Plus extends Operator{

	

	public Plus(Rechnung e1, Rechnung e2) {
		super(e1, e2, "+");
	}

	public float calc() {
		return e1.calc() + e2.calc();
	}
	
}
