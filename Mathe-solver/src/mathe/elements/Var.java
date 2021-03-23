package mathe.elements;

import mathe.Element;

public class Var extends Element{

	String symbol;
	
	public Var(String symbol) {
		this.symbol = symbol;
	}
	
	public float calc() {
		return Float.NaN;
	}

	public String print() {
		return symbol;
	}

	
	public void kuerzen() {
		
	}

}
