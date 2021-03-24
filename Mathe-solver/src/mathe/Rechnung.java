package mathe;

import mathe.elements.Const;
import mathe.elements.Operator;
import mathe.elements.Var;

public class Rechnung {

	Element e;

	public Rechnung(Element e) {
		this.e = e;
	}

	public Rechnung(String rechnung) {
		e = convert(rechnung);
	}

	public Element convert(String rechnung) {
		rechnung = rechnung.replace(" " ,"");
		if (!(rechnung.contains("+") || rechnung.contains("-") || rechnung.contains("*") || rechnung.contains("/") || rechnung.contains("^"))) {
			if (isNumber(rechnung)) {
				return new Const(Float.parseFloat(rechnung));
			} else {
				return new Var(rechnung);
			}
		} else {
			String operator = null;
			int index = 0;
			int c = 0;

			for (int i = 0; operator == null; i++) {
				switch (rechnung.charAt(i)) {
				case '(':
					c++;
					break;
				case ')':
					c--;
					break;

				case '+':
				case '-':
				case '*':
				case '/':
				case '^':
					if (c == 0) {
						operator = String.valueOf(rechnung.charAt(i));
						index = i;
						System.out.println(operator);
					}
					break;
				}
			}
			String[] next = new String[2];
			
			System.out.println(rechnung);
			for (int i = 0; i < 2; i++) {
				next[i] = rechnung.substring(i * (index + 1), index + i * (rechnung.length() - index));
				if(next[i].charAt(0) == '(') {
					next[i] = next[i].substring(1,next[i].length()-1);
					
				}
				System.out.println(i + "   " + next[i]);
			}
			
			return Operator.newOperator(new Rechnung(convert(next[0])), new Rechnung(convert(next[1])), operator);
		}

	}
	
	

	public boolean isNumber(String str) {
		try {
			Float.parseFloat(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public float calc() {
		return e.calc();
	}

	public String print() {
		return e.print();
	}

	public static void main(String[] args) {
		String rechnung = "(2*(6+9)) * x";
		Rechnung r = new Rechnung(rechnung);
		System.out.println(r.print() + " = " + r.calc());
		
	}
}
