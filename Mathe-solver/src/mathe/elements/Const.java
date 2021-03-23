package mathe.elements;

import mathe.Element;

public class Const extends Element{

	float value;
	
	public Const(float value) {
		this.value = value;
	}

	public float calc() {
		return value;
	}
	
	public String print() {
		return String.valueOf(value);
	}
	
	public void kuerzen() {
		
	}
}
