package Game;

import java.awt.Color;

public enum Type {

	FLOOR(0,new Color(255,255,255)), WALL(-10,new Color(0,0,0)), FRUIT(10,new Color(255,0,0));
	
	public double reward;
	public int code;
	Color color;

	Type(double reward, Color color) {
		this.reward = reward;
		code = this.ordinal();
		this.color = color;
	}
}
