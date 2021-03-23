package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CircuitBoard {
	public int width,height;
	
	public ArrayList<Component> components;

	public CircuitBoard(int width, int height) {
		this.width = width;
		this.height = height;
		
		components = new ArrayList<Component>();
	}
	
	public Component getComponent(int index) {
		return components.get(index);
	}
	
	public void addComponent(Component c) {
		components.add(c);
	}
	
	public int[] in(Point p) {
		int[] erg = new int[2];
		for(int i = 0; i < components.size(); i++) {
			int in = components.get(i).in(p);
			if(in!= -1) {
				erg[0] = i;
				erg[1] = in;
				return erg;
			}
		}
		return null;
	}
	
	public Component out(Point p) {
		for(Component c : components) {
			if(c.out(p)) {
				return c;
			}
		}
		return null;
	}
	
	public void turn(Point p) {
		for(int i = 0; i < components.size(); i++) {
			if(components.get(i).rect(p)) {
				components.get(i).turn();
				break;
			}
		}
	}
	
	public void delete(Point p) {
		for(int i = 0; i < components.size(); i++) {
			if(components.get(i).rect(p)) {
				components.remove(i);
				break;
			}
		}
	}
	
	public void connectLast() {
		components.get(components.size()-1).setInput(0, components.get(components.size()-2));;
		components.get(components.size()-1).setInput(1, components.get(components.size()-3));;
	}
	
	public void reset() {
		for(Component c : components) {
			c.calc = false;
		}
	}
	
	public BufferedImage asImage() {
		reset();
		for(Component c : components) {
				c.output();
		}
		
		BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
		
		Graphics g = img.createGraphics();
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);
		
		for(Component c : components) {
			c.paint(g);
		}
		
		
		return img;
	}
}
