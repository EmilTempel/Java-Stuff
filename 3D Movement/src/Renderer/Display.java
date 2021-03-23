package Renderer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Renderer.geometry.Point3D;
import Renderer.geometry.PointofView;
import Renderer.geometry.Vektor;
import Renderer.shapes.Cuboid;
import Renderer.shapes.Polyhedron;

public class Display extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private JFrame frame;
	private static final String title = "3D-Movement";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static boolean running = false;

	private Cuboid[] cuboids;
	private PointofView PoV;
	private Input input;

	public Display() {
		frame = new JFrame();

		frame.setSize(WIDTH+600, HEIGHT);

	}

	public static void main(String[] args) {
		Display display = new Display();

		display.frame.add(display);
		display.frame.setTitle(title);
		display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.frame.setLocationRelativeTo(null);
		display.frame.setResizable(false);
		display.frame.setVisible(true);
		display.frame.pack();

		display.start();
	}

	private void start() {
		running = true;
		this.thread = new Thread(this, "Display");
		this.thread.start();
	}

	private void stop() {
		running = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void init() {
		PoV = new PointofView(new Vektor(0, 0, 0));
		input = new Input(PoV);
		this.addKeyListener(input);
		this.addMouseMotionListener(input);
		cuboids = new Cuboid[2];
		cuboids[0] = new Cuboid(new Point3D(200, 200, 0), 100, 100, 100);
		cuboids[1] = new Cuboid(new Point3D(200, -200, 0), 100, 100, 100);
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60;
		double delta = 0;
		int frames = 0;

		init();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = System.nanoTime();
			while (delta >= 1) {
				update();

				delta = 0;
			}

			render();

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + "  |  " + frames + "fps");
				frames = 0;
			}
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.CYAN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		

		for(Cuboid c : cuboids) {
			c.render(g, PoV);
		}
		
		g.setColor(Color.WHITE);
		g.fillRect(800, 0, 600, 600);
		
		
		PoV.render(g,cuboids);

		g.dispose();
		bs.show();
	}

	public void update() {
		PoV.move(4);
		PoV.rotateA(0);
		PoV.rotateB(0);
//		System.out.println(PoV.O.x +"   " + PoV.O.y + "     " + PoV.O.z);
//		System.out.println(PoV.alpha + "   " + PoV.beta);
	}

}
