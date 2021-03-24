package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	public Type type;
	public int x,y;
	public double reward;
	
	public Tile(Type type,int x,int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		reward = type.reward;
	}
	
	public void paint(Graphics g) {
		g.setColor(type.color);
		g.fillRect(x*100, y*100, 100, 100);
		g.setColor(new Color(255,255,255));
		g.drawRect(x*100, y*100, 100, 100);
	}
}
