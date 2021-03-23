package mathe;

public class Mathe {
	
	public static int ggT(int a, int b) {
		while(a != b) {
			if(a > b) {
				a = a - b;
			}else {
				b = b - a;
			}
		}
		return a;
	}
	
	public static void main(String[] args) {
		System.out.println(ggT(44,12));
	}
}
