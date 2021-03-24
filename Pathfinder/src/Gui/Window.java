package Gui;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window(int b,int l) {
		this.setSize(b,l);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
