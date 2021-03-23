package Renderer.geometry;

public class Drehmatrix {
	double[] x;
	double[] y;
	double[] z;

	public Drehmatrix(double... komponenten) {
		x = new double[komponenten.length / 3];
		y = new double[komponenten.length / 3];
		z = new double[komponenten.length / 3];
		for (int i = 0; i < komponenten.length / 3; i++) {
			x[i] = komponenten[i];
			y[i] = komponenten[i + komponenten.length / 3];
			z[i] = komponenten[i + 2 * komponenten.length / 3];
		}
	}
	
	public Vektor Multiplikation(Vektor v) {
		Vektor d = new Vektor(0,0,0);
		d.x = v.x* x[0] + v.y * x[1] + v.z * x[2];
		d.y = v.x* y[0] + v.y * y[1] + v.z * y[2];
		d.z = v.x* z[0] + v.y * z[1] + v.z * z[2];
		return d;
	}
}
