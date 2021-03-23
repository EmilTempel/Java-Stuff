package gui;

import javax.swing.JFrame;

import logic.CircuitBoard;

public class Window extends JFrame{

	public Window(int width, int height, CircuitBoard cb) {
		setSize(width,height);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Input in = new Input(100,0.1,cb);
		
		addKeyListener(in);
		
		add(new Label(width,height, in));
	}
}
