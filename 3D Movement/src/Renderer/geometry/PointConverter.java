package Renderer.geometry;

import java.awt.Point;

import Renderer.Display;

public class PointConverter {
	private static double scale = 1; 

	

	public static void rotateAxisX(Point3D p, boolean CW, int degrees) {
		double radius = Math.sqrt(p.y*p.y + p.z*p.z);
		double theta = Math.atan2(p.z, p.y);
		theta += 2*Math.PI/360*degrees*(CW?-1:1);
		p.y = radius * Math.cos(theta);
		p.z = radius * Math.sin(theta);
	}
	
	public static void rotateAxisY(Point3D p, boolean CW, int degrees) {
		double radius = Math.sqrt(p.x*p.x + p.z*p.z);
		double theta = Math.atan2(p.x, p.z);
		theta += 2*Math.PI/360*degrees*(CW?-1:1);
		p.x = radius * Math.sin(theta);
		p.z = radius * Math.cos(theta);
	}
	
	public static void rotateAxisZ(Point3D p, boolean CW, int degrees) {
		double radius = Math.sqrt(p.x*p.x + p.y*p.y);
		double theta = Math.atan2(p.x, p.y);
		theta += 2*Math.PI/360*degrees*(CW?-1:1);
		p.x = radius * Math.sin(theta);
		p.y = radius * Math.cos(theta);
	}
}
