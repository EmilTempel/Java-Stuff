package Renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;

import Renderer.point.Point3D;

public class Cuboid{

	Polyhedron polyhedron;
	
	public Cuboid(Point3D M, int b, int l, int h) {
		polyhedron = new Polyhedron(getFaces(M,b,l,h));
	}
	
	private Polygon3D[] getFaces(Point3D M, int b, int l, int h) {
		Point3D p1 = new Point3D(M.x + b/2,M.y - l/2, M.z - h/2);
		Point3D p2 = new Point3D(M.x + b/2,M.y + l/2, M.z - h/2);
		Point3D p3 = new Point3D(M.x + b/2,M.y - l/2, M.z + h/2);
		Point3D p4 = new Point3D(M.x + b/2,M.y + l/2, M.z + h/2);
		Point3D p5 = new Point3D(M.x - b/2,M.y - l/2, M.z - h/2);
		Point3D p6 = new Point3D(M.x - b/2,M.y + l/2, M.z - h/2);
		Point3D p7 = new Point3D(M.x - b/2,M.y - l/2, M.z + h/2);
		Point3D p8 = new Point3D(M.x - b/2,M.y + l/2, M.z + h/2);
		
		Polygon3D[] faces = {
				new Polygon3D(Color.RED,p1,p2,p4,p3),
				new Polygon3D(Color.BLUE,p5,p6,p8,p7),
				new Polygon3D(Color.GREEN,p5,p1,p3,p7),
				new Polygon3D(Color.YELLOW,p5,p6,p2,p1),
				new Polygon3D(Color.WHITE,p6,p2,p4,p8),
				new Polygon3D(Color.PINK,p8,p4,p3,p7)
				};
		return faces;
	}
	
	public void rotate(boolean CW, int xdegrees, int ydegrees, int zdegrees) {
			polyhedron.rotate(CW,xdegrees,ydegrees,zdegrees);
			polyhedron.sortPolygons();
	}
	
	public void render(Graphics g) {
		polyhedron.render(g);
	}
}
