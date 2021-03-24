package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Snakepart {
	public int x,y;
	int dir;
	
	public Snakepart(int x, int y) {
		this.x = x;
		this.y = y;
		dir = 4;
	}
	
	public void Move() {
		switch(dir) {
		case 1:
			y--;
			break;
		case 2:
			y++;
			break;
		case 3:
			x--;
			break;
		case 4:
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
		g.fillRect(x*25,y*25,25,25);
	}
}
