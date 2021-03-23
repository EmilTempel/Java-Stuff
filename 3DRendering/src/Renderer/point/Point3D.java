package Renderer.point;

public class Point3D {

	public double x;
	public double y;
	public double z;

	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void rotate(boolean CW, int xdegrees, int ydegrees, int zdegrees) {
		PointConverter.rotateAxisX(this, CW, xdegrees);
		PointConverter.rotateAxisY(this, CW, ydegrees);
		PointConverter.rotateAxisZ(this, CW, zdegrees);
	}
}
