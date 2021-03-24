package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Snakepart {
	World world;
	public int x,y;
	boolean head;
	int dir;
	
	public Snakepart(World world,int x, int y,boolean head) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.head = head;
		dir = 4;
	}
	
	public void Move() {
		switch(dir) {
		case 1:
			if(y -1  >= 0)
			y--;
			break;
		case 2:
			if(y +1 < world.l)
			y++;
			break;
		case 3:
			if(x -1  >= 0)
			x--;
			break;
		case 4:
			if(x +1 < world.b)
			x++;
			break;
		}
	}
	
	public void changeHeadDir(int action) {
		if(action == 0 || (dir == 1 && action == 2) || (dir == 2 && action == 1)|| (dir == 3 && action == 4)|| (dir == 4 && action == 3)) {
			
			
		}else {
			dir = action;
		}
			
	}
	
	
	public void changeDir(int action) {
		if(action != 0)
			dir = action;
	}
	
	public void paint(Graphics g) {
		g.setColor(new Color(0,255,0));
		
		if(head)
			g.setColor(new Color(0,0,255));
		
		g.fillRect(x*25,y*25,25,25);
	}
}
