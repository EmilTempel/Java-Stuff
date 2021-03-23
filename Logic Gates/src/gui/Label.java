package gui;

import java.awt.Graphics;

import javax.swing.JLabel;

import logic.CircuitBoard;

public class Label extends JLabel {
	CircuitBoard cb;
	Input in;

	public Label(int width, int height, Input in) {
		setSize(width, height);
		setVisible(true);


		this.in = in;

		addMouseListener(in);
		addMouseWheelListener(in);
		addMouseMotionListener(in);
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(in.asImage(), 0, 0, getWidth(), getHeight(), null);

		repaint();
	}
}
