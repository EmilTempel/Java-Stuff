package Game;

import java.awt.Graphics;

import AI.Agent;

public class World {
	public int b,l;
	public Tile[][] level;
	public Snake player;
	public Agent agent;
	
	public World() {
		b = 30;
		l = 30;
		
		level = new Tile[b][l];
		for(int x = 0; x < b; x++)
			for(int y = 0; y < l; y++) {
				if(x == 0 || x == b-1 || y == 0 || y == l-1) {
					level[x][y] = new Tile(x,y,Type.WALL);
				}else {
					level[x][y] = new Tile(x,y,Type.FLOOR);
				}
				
			}
		respawnFruit();
		player = new Snake(this,30,20,10);
		agent = new Agent(this);
	}
	
	

	
	public void respawnFruit() {
		for(Tile[] T : level)
			for(Tile t : T) {
				if(t.type == Type.FRUIT)
					t.type = Type.FLOOR;
			}
		
		int randomx = (int) (Math.random()*(b-2))+1;
		int randomy = (int) (Math.random()*(l-2))+1;
		level[randomx][randomy] = new Tile(randomx,randomy,Type.FRUIT);
		
	}
	
	
	public void paint(Graphics g) {
		for(Tile[] T : level)
			for(Tile t : T)
				t.paint(g);
		
		agent.paint(g);
	}
}
