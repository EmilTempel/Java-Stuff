package mathe.elements;

import mathe.Element;
import mathe.Rechnung;

public class Operator extends Element {

	Rechnung e1, e2;

	String zeichen;

	public Operator(Rechnung e1, Rechnung e2, String zeichen) {
		this.e1 = e1;
		this.e2 = e2;

		this.zeichen = zeichen;
	}

	public float calc() {
		return 0;
	}

	public String print() {
		return "(" + e1.print() + " " + zeichen + " " + e2.print() + ")";
	}

	public static Operator newOperator(Rechnung e1, Rechnung e2, String zeichen) {
		switch (zeichen) {
		case "+":
			return new Plus(e1, e2);
		case "-":
			return new Minus(e1, e2);
		case "*":
			return new Mal(e1, e2);
		case "/":
			return new Durch(e1, e2);
		case "^":
			return new Hoch(e1, e2);
		default:
			return new Plus(e1,e2);
		}

	}

	@Override
	public void kuerzen() {
		
		
	}
}
