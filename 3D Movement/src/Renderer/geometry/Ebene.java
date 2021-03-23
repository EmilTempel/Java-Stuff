package Renderer.geometry;

import java.awt.Point;

public class Ebene {
	public Vektor s, r1, r2;
	
	public Vektor n;
	public double a,b,c,d;
	public double x0,y0,z0;

	Ebene(Vektor s, Vektor r1, Vektor r2) {
		this.s = s;
		this.r1 = r1;
		this.r2 = r2;
		Koordinatenform(Normalenvektor());
	}

	public Ebene(double alpha, double beta, double d, Vektor O) {
		Vektor v = new Vektor(d,0,0);
		Drehmatrix Z = new Drehmatrix(	Math.cos(alpha)	,-Math.sin(alpha)	,0,
										Math.sin(alpha)	,Math.cos(alpha) 	,0,
										0				,0				 	,1);
		v = Z.Multiplikation(v);
		
		Vektor u = new Vektor(Math.cos(alpha-Math.toRadians(90)),Math.sin(alpha-Math.toRadians(90)),0);
		u = u.Normieren();
		Drehmatrix U = new Drehmatrix(
										u.x*u.x*(1-Math.cos(beta)) + Math.cos(beta)  	, u.y*u.x*(1-Math.cos(beta)) - u.z*Math.sin(beta) 	, u.z*u.x*(1-Math.cos(beta)) + u.y*Math.sin(beta),
										u.x*u.y*(1-Math.cos(beta)) + u.z*Math.sin(beta)	, u.y*u.y*(1-Math.cos(beta)) + Math.cos(beta)		, u.z*u.y*(1-Math.cos(beta)) - u.x*Math.sin(beta),
										u.x*u.z*(1-Math.cos(beta)) - u.y*Math.sin(beta) , u.y*u.z*(1-Math.cos(beta)) + u.x*Math.sin(beta)	, u.z*u.z*(1-Math.cos(beta)) + Math.cos(beta)
				
				);
		v = U.Multiplikation(v);
		s = O.Summe(v);
		n = v;
		Koordinatenform(n);
		x0 = O.x;
		y0 = O.y;
		z0 = O.z;
					
	}

	public Vektor Normalenvektor() {
		Vektor n = r1.Kreuzprodukt(r2);
		return n;
	}

	public void Koordinatenform(Vektor n) {
		a = n.x;
		b = n.y;
		c = n.z;
		d = n.Skalarprodukt(s);
	}

	public Vektor Schnittpunkt(Gerade g) {
		Vektor s = g.s;
		Vektor r = g.r;
		
//		System.out.println(s.x + "   " + s.y + "   " + s.z);

		double psi = -1 *((a*s.x - a*x0 + b*s.y - b*y0 + c*s.z - c*z0 - d) / (a*r.x + b*r.y + c*r.z));
		
//		System.out.println(psi);
		
		Vektor S = s.Summe(r.Multiplikation(psi));
		return S;
	}
	
	public boolean checkS(Gerade g) {
		Vektor s = g.s;
		Vektor r = g.r;
		
//		System.out.println(s.x + "   " + s.y + "   " + s.z);

		double psi = -1 *((a*s.x - a*x0 + b*s.y - b*y0 + c*s.z - c*z0 - d) / (a*r.x + b*r.y + c*r.z));
//		System.out.println(psi);
		if(psi < 0) {
			return false;
		}else {
			return true;
		}
	}
}
