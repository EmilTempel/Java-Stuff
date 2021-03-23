package Renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Renderer.point.Point3D;
import Renderer.point.PointConverter;

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
	
	public double getAverageX() {
		double average = 0;
		for(Point3D p : points) {
			average += p.x;
		}
		return average/points.length;
	}
	
	public static Polygon3D[] sort(Polygon3D[] polygons) {
		List<Polygon3D> polygonlist = Arrays.asList(polygons);
		
		Collections.sort(polygonlist,new Comparator<Polygon3D>() {
			@Override
			public int compare(Polygon3D p1, Polygon3D p2) {
				return  p1.getAverageX() > p2.getAverageX()?1:-1;
			}
			
			
		});
		
		for(int i = 0; i < polygons.length; i++)
			polygons[i] = polygonlist.get(i);
		
		return polygons;
	}
	
	public void rotate(boolean CW, int xdegrees, int ydegrees, int zdegrees) {
		for(Point3D p : points) {
			p.rotate(CW,xdegrees,ydegrees,zdegrees);
		}
	}

	public void render(Graphics g) {
		Polygon poly = new Polygon();
		for(int i = 0; i < this.points.length; i++) {
			Point p = PointConverter.convert(this.points[i]);
			poly.addPoint(p.x, p.y);
		}
		g.setColor(this.color);
		g.fillPolygon(poly);
	}
}
