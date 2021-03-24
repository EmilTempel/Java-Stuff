package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	public int x, y;
	public Type type;
	public double reward;

	public Tile(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
		reward = type.reward;
	}

	public void paint(Graphics g) {
		g.setColor(type.color);
		g.fillRect(x * 25, y * 25, 25, 25);
		g.setColor(new Color(0,0,0));
		g.drawRect(x*25,y*25,25,25);
	}
}
