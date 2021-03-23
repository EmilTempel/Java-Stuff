package Renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import Renderer.Display;
import Renderer.geometry.Point3D;
import Renderer.geometry.PointofView;

public class Cuboid {

	Polyhedron polyhedron;
	public Point3D p1,p2,p3,p4,p5,p6,p7,p8;

	public Cuboid(Point3D M, int b, int l, int h) {
		polyhedron = new Polyhedron(getFaces(M, b, l, h));
	}

	private Polygon3D[] getFaces(Point3D M, int b, int l, int h) {
		p1 = new Point3D(M.x - b / 2, M.y - l / 2, M.z + h / 2);
		p2 = new Point3D(M.x - b / 2, M.y + l / 2, M.z + h / 2);
		p3 = new Point3D(M.x - b / 2, M.y + l / 2, M.z - h / 2);
		p4 = new Point3D(M.x - b / 2, M.y - l / 2, M.z - h / 2);
		p5 = new Point3D(M.x + b / 2, M.y - l / 2, M.z + h / 2);
		p6 = new Point3D(M.x + b / 2, M.y + l / 2, M.z + h / 2);
		p7 = new Point3D(M.x + b / 2, M.y + l / 2, M.z - h / 2);
		p8 = new Point3D(M.x + b / 2, M.y - l / 2, M.z - h / 2);

		Polygon3D[] faces = {
				new Polygon3D(Color.RED, p1, p2, p3), 
				new Polygon3D(Color.RED, p1, p4, p3),
				
				new Polygon3D(Color.GREEN, p5, p6, p7),
				new Polygon3D(Color.GREEN, p5, p8, p7),
				
				new Polygon3D(Color.YELLOW, p5, p1, p4),
				new Polygon3D(Color.YELLOW, p5, p8, p4),
				
				new Polygon3D(Color.WHITE, p2, p6, p7),
				new Polygon3D(Color.WHITE, p2, p3, p7),
				
				new Polygon3D(Color.PINK, p5, p6, p2),
				new Polygon3D(Color.PINK, p5, p1, p2),
				
				new Polygon3D(Color.BLUE, p8, p7, p3),
				new Polygon3D(Color.BLUE, p8, p4, p3) 
				};
		return faces;
	}

	private Polygon3D[] getPixels(Point3D M, int b, int l, int h) {
		List<Polygon3D> polygonList = new ArrayList<Polygon3D>();
		for (int i = 0; i < b; i+= 10) {
			for (int j = 0; j < l; j+= 10) {
				Polygon3D poly1 = new Polygon3D(Color.BLACK,
												new Point3D(M.x - b / 2 + i		, M.y - l / 2 + j	  , M.z - h / 2),
												new Point3D(M.x - b / 2 + i + 10, M.y - l / 2 + j	  , M.z - h / 2),
												new Point3D(M.x - b / 2 + i + 10, M.y - l / 2 + j + 10, M.z - h / 2),
												new Point3D(M.x - b / 2 + i		, M.y - l / 2 + j + 10, M.z - h / 2));
				
				Polygon3D poly2 = new Polygon3D(Color.GREEN,
												new Point3D(M.x - b / 2 + i		, M.y - l / 2 + j	  , M.z + h / 2),
												new Point3D(M.x - b / 2 + i + 10, M.y - l / 2 + j	  , M.z + h / 2),
												new Point3D(M.x - b / 2 + i + 10, M.y - l / 2 + j + 10, M.z + h / 2),
												new Point3D(M.x - b / 2 + i		, M.y - l / 2 + j + 10, M.z + h / 2));
				polygonList.add(poly1);
				polygonList.add(poly2);
			}

		}
		
		Polygon3D[] polygons = new Polygon3D[polygonList.size()];
		
		for(int i = 0; i < polygonList.size(); i++) {
			polygons[i] = polygonList.get(i);
		}
		
		return polygons;
	}

	public void rotate(boolean CW, int xdegrees, int ydegrees, int zdegrees) {
		polyhedron.rotate(CW, xdegrees, ydegrees, zdegrees);
	}

	public void render(Graphics g, PointofView PoV) {
		polyhedron.render(g, PoV);
	}
}
