package Renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import Renderer.Display;
import Renderer.geometry.Point3D;
import Renderer.geometry.PointofView;
import Renderer.geometry.Vektor;

public class Polygon3D {

	Point3D[] points;
	Color color;

	public Polygon3D(Color color, Point3D... points) {
		this.color = color;
		this.points = new Point3D[points.length];
		for (int i = 0; i < points.length; i++) {
			Point3D p = points[i];
			this.points[i] = new Point3D(p.x, p.y, p.z);
		}
	}

	public Polygon3D(Point3D... points) {
		this.color = Color.WHITE;
		this.points = new Point3D[points.length];
		for (int i = 0; i < points.length; i++) {
			Point3D p = points[i];
			this.points[i] = new Point3D(p.x, p.y, p.z);
		}
	}

	public double getAverageD(PointofView PoV) {
		double average = 0;
		for (Point3D p : points) {
			Vektor D = new Vektor(p.x, p.y, p.z).Differenz(PoV.O);
			average += D.Vektorbetrag();
		}
		return average / points.length;
	}

	public void rotate(boolean CW, int xdegrees, int ydegrees, int zdegrees) {
		for (Point3D p : points) {
			p.rotate(CW, xdegrees, ydegrees, zdegrees);
		}
	}

	public void render(Graphics g, PointofView PoV) {
		Polygon poly = new Polygon();
		boolean visible = true;
		for (int i = 0; i < this.points.length; i++) {
			Point p = PoV.convert(this.points[i]);
			poly.addPoint(p.x, p.y);
			visible = PoV.checkS(this.points[i]);
			
		}
		if (visible) {
			g.setColor(this.color);
			g.fillPolygon(poly);
			g.setColor(Color.BLACK);
			g.drawPolygon(poly);
		}
	}
}
