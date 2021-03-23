package Renderer.geometry;

public class Vektor {

	public double x;
	public double y;
	public double z;

	public Vektor(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;

	}

	public double Vektorbetrag() {
		double betrag = Math.sqrt(x * x + y * y + z * z);
		return betrag;
	}
	
	public Vektor Normieren() {
		Vektor n = Division(Vektorbetrag());
		return n;
	}
	
	public Vektor Summe(Vektor V) {
		double x = this.x + V.x;
		double y = this.y + V.y;
		double z = this.z + V.z;
		Vektor s = new Vektor(x,y,z);
		return s;
	}
	
	public Vektor Differenz(Vektor V) {
		double x = this.x - V.x;
		double y = this.y - V.y;
		double z = this.z - V.z;
		Vektor d = new Vektor(x,y,z);
		return d;
	}
	
	public Vektor Multiplikation(double skalar) {
		double x = this.x* skalar;
		double y = this.y* skalar;
		double z = this.z* skalar;
		Vektor m = new Vektor(x,y,z);
		return m;
	}
	
	public Vektor Division(double skalar) {
		double x = this.x* skalar;
		double y = this.y* skalar;
		double z = this.z* skalar;
		Vektor m = new Vektor(x,y,z);
		return m;
	}

	public double Skalarprodukt(Vektor V) {
		double s = x * V.x + y * V.y + z * V.z;
		return s;
	}

	public Vektor Kreuzprodukt(Vektor V) {
		double x = this.y * V.z - this.z * V.y;
		double y = this.z * V.x - this.x * V.z;
		double z = this.x * V.y - this.y * V.x;
		Vektor p = new Vektor(x, y, z);
		return p;
	}

}
