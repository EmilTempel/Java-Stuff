package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Component {

	protected Gate type;
	protected Component[] input;
	protected Rectangle[] rect_in;
	protected Rectangle rect_out, rect;
	

	protected String path;
	protected BufferedImage img;

	protected int x, y, l, b;
	
	protected boolean state;

	protected int output;

	protected boolean calc;

	public Component(Gate type, int x, int y) {
		this.type = type;
		this.input = new Component[type.n];

		this.path = "/" + type.toString() + ".png";
		this.img = asImage();

		
		this.l = img.getWidth();
		this.b = img.getHeight();
		this.x = x - l/2;
		this.y = y - b/2;
		
		this.state = true;
		
		this.rect_in = new Rectangle[type.n];
		for (int i = 0; i < type.n; i++) {
			rect_in[i] = new Rectangle(this.x, this.y + i * b / type.n, l / 2, b / type.n);
		}
		this.rect_out = new Rectangle(this.x + l / 2, this.y, l / 2, b);
		this.rect = new Rectangle(this.x,this.y,l,b);
		
		output = 0;

		calc = false;

	}

	public void setInput(int i, Component c) {
		if (i < input.length) {
			input[i] = c;
		}
	}

	public int[] input() {
		int[] in = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			in[i] = (input[i] != null) ? input[i].output() : 0;
		}
		return in;
	}

	public int output() {
		if (!calc) {
			if (input == null) {
				output = 0;
			} else {
				
				output = state?type.output(input()):0;
			}
			calc = true;
		}
		return output;
	}

	public int in(Point p) {
		for (int i = 0; i < rect_in.length; i++) {
			if (rect_in[i].contains(p)) {
				System.out.println("yes at: " + i);
				return i;
			}
		}
		return -1;
	}

	public boolean out(Point p) {
		return rect_out.contains(p);
	}
	
	public boolean rect(Point p) {
		return rect.contains(p);
	}
	
	public void turn() {
		state = state?false:true;
		
	}

	public static Gate randomGate() {
		return Gate.values()[(int) (Math.random() * Gate.values().length)];

	}

	public BufferedImage asImage() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public Point inputConnection(int i) {
		if (i < 0 || i > input.length) {
			return null;
		} else {
			int x_i = x;
			int y_i = y + (i + 1) * (b / (input.length+1));
			return new Point(x_i, y_i);
		}

	}

	public Point outputConnection() {
		int x_i = x + l;
		int y_i = y + (b / 2);
		return new Point(x_i, y_i);
	}

	public void paint(Graphics g) {
		g.drawImage(img, x, y, l, b, null);

		for (int i = 0; i < input.length; i++) {
			Component c = input[i];
			if (c != null) {
				g.setColor(new Color(255 * c.output, 255 * c.output, 0));
				Point c_o = c.outputConnection();
				Point this_i = this.inputConnection(i);

				g.drawLine(c_o.x, c_o.y, this_i.x, this_i.y);
			}
		}
	}
}
