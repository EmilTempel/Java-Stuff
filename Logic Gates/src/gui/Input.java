package gui;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import logic.CircuitBoard;
import logic.Component;
import logic.Gate;

public class Input implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

	int x, y, coef;
	double percent, min_percent;
	CircuitBoard cb;
	Gate type;

	int[] in;
	Component out;

	public Input(int coef, double min_percent, CircuitBoard cb) {
		this.x = 0;
		this.y = 0;
		this.coef = coef;
		this.percent = 1;
		this.min_percent = min_percent;
		this.cb = cb;
		type = Gate.values()[0];
	}

	public void connect() {
		Component c = cb.getComponent(in[0]);
		c.setInput(in[1], out);
		in = null;
		out = null;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
		switch (e.getButton()) {
		case 1:
			if (cb.in(p) != null) {
				in = cb.in(p);
				if (out != null) {
					connect();
				}
			} else if (cb.out(p) != null) {
				out = cb.out(p);
				if (in != null) {
					connect();
				}

			} else {
				cb.addComponent(new Component(type, e.getX(), e.getY()));
			}
			break;
		case 2:
			cb.turn(p);
			
			break;
		case 3:
			cb.delete(p);
			break;
		}

	}

	public void keyPressed(KeyEvent e) {
		System.out.println("YES");
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			if (e.isControlDown()) {
				type = Gate.NAND;
			} else {
				type = Gate.AND;
			}
			break;
		case KeyEvent.VK_O:
			if (e.isAltDown()) {
				type = Gate.XOR;
			} else if (e.isControlDown()) {
				type = Gate.NOR;
			} else {
				type = Gate.OR;
			}
			break;
		case KeyEvent.VK_N:
			type = Gate.NOT;
			break;
		case KeyEvent.VK_S:
			type = Gate.SOURCE;
			break;
		case KeyEvent.VK_L:
			type = Gate.LEVER;
			break;
		}

	}

	public BufferedImage asImage() {
		BufferedImage cb_img = cb.asImage();
		int w = (int) (cb_img.getWidth() * percent);
		int h = (int) (cb_img.getHeight() * percent);

		BufferedImage img = cb_img.getSubimage(x, y, w, h);
		return img;
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
