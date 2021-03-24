package Game;

import java.awt.Color;

public enum Type{
	FLOOR(-1,new Color(188,172,144)),WALL(0,new Color(0,0,0)),FIRE(-10,new Color(255,0,0)),GOAL(10,new Color(0,255,0));
	
	double reward;
	Color color;
	
	Type(double reward, Color color){
		this.reward = reward;
		this.color = color;
	}
}
