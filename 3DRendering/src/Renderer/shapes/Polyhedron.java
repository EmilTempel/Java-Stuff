package Renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

	public void sortPolygons() {
		Polygon3D.sort(polygons);
	}

	public void render(Graphics g) {
		for (Polygon3D p : polygons) {
			p.render(g);
		}
	}
}
