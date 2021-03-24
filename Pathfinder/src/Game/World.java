package Game;

import java.awt.Graphics;

import Ai.Agent;

public class World {
	public int b, l;
	public Tile[][] level;
	public Agent agent;
	public Player player;
	public Input input;
	boolean showValues;
	

	public World() {
		b = 10;
		l = 10;
		level = new Tile[b][l];
		for (int x = 0; x < b; x++)
			for (int y = 0; y < l; y++) {
				level[x][y] = new Tile(Type.FLOOR, x, y);
			}
		for(int i = 0; i < 20; i++) {
			int xRn = (int) (Math.random()*b);
			int yRn = (int) (Math.random()*l);
			if(level[xRn][yRn].type != Type.GOAL)
				level[xRn][yRn] = new Tile(Type.WALL,xRn,yRn);
		}
		
		level[b-1][0] = new Tile(Type.GOAL, b-1, 0);
		level[0][0] = new Tile(Type.GOAL, 0, 0);
		level[b-1][l-1] = new Tile(Type.GOAL, b-1, l-1);

		agent = new Agent(this);
		
		player = new Player(0, l-1,this);

		input = new Input(this);
		
		showValues = false;
	}
	
	public void reset() {
		player = new Player(0, l-1,this);
	}

	public void paint(Graphics g) {
		for (Tile[] T : level)
			for (Tile t : T)
				t.paint(g);
		
		player.paint(g);
		
		if(showValues)
		agent.paint(g);
	}
}
