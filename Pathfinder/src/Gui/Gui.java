package Gui;

import Game.*; 

public class Gui {
	int b,l;
	
	Window window;
	Label label;
	
	
	public Gui(World world) {
		b = world.b*100;
		l = world.l*100;
		window = new Window(b+16,l+40);
		label = new Label(b,l,world);
		window.add(label);
		window.addKeyListener(world.input);
	}
}
