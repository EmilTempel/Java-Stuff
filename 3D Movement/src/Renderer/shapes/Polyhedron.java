package Renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Renderer.geometry.PointofView;

public class Polyhedron {

	Polygon3D[] polygons;

	public Polyhedron(Color color, Polygon3D... polygons) {
		this.polygons = new Polygon3D[polygons.length];
		for (int i = 0; i < polygons.length; i++) {
			Polygon3D p = polygons[i];
			this.polygons[i] = new Polygon3D(color, p.points);
		}
	}

	public Polyhedron(Polygon3D... polygons) {
		this.polygons = new Polygon3D[polygons.length];
		for (int i = 0; i < polygons.length; i++) {
			Polygon3D p = polygons[i];
			this.polygons[i] = new Polygon3D(p.color, p.points);
		}
	}

	public void rotate(boolean CW, int xdegrees, int ydegrees, int zdegrees) {
		for (Polygon3D p : polygons) {
			p.rotate(CW, xdegrees, ydegrees, zdegrees);
		}
	}

	public void sortPolygons(PointofView PoV) {
        List<Polygon3D> polygonlist = Arrays.asList(polygons);
		
		Collections.sort(polygonlist,new Comparator<Polygon3D>() {
			@Override
			public int compare(Polygon3D p1, Polygon3D p2) {
				return  p1.getAverageD(PoV) > p2.getAverageD(PoV)?-1:1;
			}
			
			
		});
		
		for(int i = 0; i < polygons.length; i++)
			polygons[i] = polygonlist.get(i);
	}

	public void render(Graphics g, PointofView PoV) {
		sortPolygons(PoV);
		
		for (Polygon3D p : polygons) {
			p.render(g, PoV);
		}
	}
}
