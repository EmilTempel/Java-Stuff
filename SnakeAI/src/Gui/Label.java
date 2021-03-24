package Gui;

import java.awt.Graphics;

import javax.swing.JLabel;

import Game.World;
import Main.Start;

public class Label extends JLabel{
	World world;
	
	public Label(int b, int l,World world) {
		this.setSize(b, l);
		this.setVisible(true);
		
		this.world = world;
	}
	
	protected void paintComponent(Graphics g) {
		world.paint(g);
		
		repaint();
	}
}
