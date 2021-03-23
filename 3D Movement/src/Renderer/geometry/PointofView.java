package Renderer.geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import Renderer.Display;
import Renderer.shapes.Cuboid;

public class PointofView {
	public Vektor O;
	Ebene E;
	public double alpha, beta, theta;
	double d;

	int b = 10, l = 10;

	public boolean W, S, A, D;

	public PointofView(Vektor O) {
		this.O = O;
		alpha = Math.toRadians(0);
		beta = Math.toRadians(0);
		theta = Math.toRadians(45);
		d = (Display.WIDTH/2) / Math.tan(theta/2);
		System.out.println(d);
		Ebene();
		W = false;
		S = false;
		A = false;
		D = false;
	}
	
	
	public Point convert(Point3D p) {
		Vektor s = new Vektor(p.x, p.y, p.z);
		Vektor r = s.Differenz(O);
		Gerade g = new Gerade(s, r);

		Vektor S = E.Schnittpunkt(g);
//		System.out.println(S.x + "   " + S.y + "   " + S.z);
		Point P = convert2D(S);
//		System.out.println(P.x + "  " + P.y);
		return P;
	}

	public boolean checkS(Point3D p) {
		Vektor s = new Vektor(p.x, p.y, p.z);
		Vektor r = s.Differenz(O);
		Gerade g = new Gerade(O, r);
		return E.checkS(g);
	}

	public Point convert2D(Vektor S) {
		Vektor s = S;
		Drehmatrix Z = new Drehmatrix(Math.cos(alpha), Math.sin(alpha), 0, -Math.sin(alpha), Math.cos(alpha), 0, 0, 0,
				1);
		Vektor v = Z.Multiplikation(s);

		Drehmatrix Y = new Drehmatrix(Math.cos(beta), 0, -Math.sin(beta), 0, 1, 0, Math.sin(beta), 0, Math.cos(beta));
		v = Y.Multiplikation(v);
		Point p2D = new Point((int) v.y + Display.WIDTH / 2, (int) v.z * -1 + Display.HEIGHT / 2);
		return p2D;
	}

	public Vektor Stützvektor() {
		double x = d * Math.cos(alpha);
		double y = d * Math.sin(alpha);
		double z = 0;

//		System.out.println(x + "   " + y + "   " + z);
		Vektor D = new Vektor(x, y, z);
		Vektor s = D.Summe(O);
//		System.out.println(s.x + "   " + s.y + "   " + s.z);
		return s;
	}

	public Vektor Orthogonalvektor() {
		double x = d * Math.cos(alpha);
		double y = d * Math.sin(alpha);
		double z = 0;

//		System.out.println(x + "   " + y + "   " + z);
		Vektor D = new Vektor(x, y, z);
//		System.out.println(s.x + "   " + s.y + "   " + s.z);
		return D;
	}

	public void Ebene() {
		E = new Ebene(alpha, beta, d, O);
	}

	public void move(double speed) {
		if (W)
			moveforward(speed);

		if (S)
			moveback(speed);

		if (A)
			moveleft(speed);

		if (D)
			moveright(speed);
	}

	public void moveforward(double speed) {
		Vektor v = new Vektor(1, 0, 0).Multiplikation(speed);
		O = O.Summe(v);
		Ebene();
	}

	public void moveback(double speed) {
		Vektor v = new Vektor(-1, 0, 0).Multiplikation(speed);
		O = O.Summe(v);
		Ebene();
	}

	public void moveleft(double speed) {
		Vektor v = new Vektor(0, -1, 0).Multiplikation(speed);
		O = O.Summe(v);
		Ebene();
	}

	public void moveright(double speed) {
		Vektor v = new Vektor(0, 1, 0).Multiplikation(speed);
		O = O.Summe(v);
		Ebene();
	}

	public void rotateA(double alpha) {
		this.alpha += Math.toRadians(alpha);
		Ebene();
	}

	public void rotateB(double beta) {
		this.beta += Math.toRadians(beta);
		Ebene();
	}

	public void render(Graphics g,Cuboid[] cuboids) {
		int x = Display.WIDTH + 300 +(int)O.y;
		int y = 300 - (int) O.x;
		for(Cuboid W : cuboids) {
		Polygon p = new Polygon();
		p.addPoint(Display.WIDTH + 300 +(int)W.p3.y, 300 - (int)W.p3.x);
		p.addPoint(Display.WIDTH + 300 +(int)W.p4.y, 300 - (int)W.p4.x);
		p.addPoint(Display.WIDTH + 300 +(int)W.p8.y, 300 - (int)W.p8.x);
		p.addPoint(Display.WIDTH + 300 +(int)W.p7.y, 300 - (int)W.p7.x);
		
		g.setColor(Color.MAGENTA);
		g.fillPolygon(p);
		
		g.setColor(Color.BLUE);
		g.drawLine(x,y, Display.WIDTH + 300 +(int)W.p3.y, 300 - (int)W.p3.x);
		g.drawLine(x,y, Display.WIDTH + 300 +(int)W.p4.y, 300 - (int)W.p4.x);
		g.drawLine(x,y, Display.WIDTH + 300 +(int)W.p7.y, 300 - (int)W.p7.x);
		g.drawLine(x,y, Display.WIDTH + 300 +(int)W.p8.y, 300 - (int)W.p8.x);
		
		
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x - b / 2,y - l / 2, b, l);
		g.drawLine(x,y,Display.WIDTH + 300 + (int) E.s.y,300 - (int) E.s.x);
		
		double dist = Math.sqrt(d*d + Display.WIDTH/2*Display.WIDTH/2);
		
		Point AWL = new Point(x + (int)(dist*Math.cos(alpha-theta-Math.PI/2)), y + (int)(dist*Math.sin(alpha-theta-Math.PI/2)));
		Point AWR = new Point(x + (int)(dist*Math.cos(alpha+theta-Math.PI/2)), y + (int)(dist*Math.sin(alpha+theta-Math.PI/2
				)));
		g.setColor(Color.GREEN);
		g.drawLine(x, y, AWL.x, AWL.y);
		g.drawLine(x, y, AWR.x, AWR.y);
		g.setColor(Color.RED);
		g.drawLine(AWL.x,AWL.y,AWR.x,AWR.y);
		}
		
	}
}
