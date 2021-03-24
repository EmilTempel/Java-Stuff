package Game;

import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
	World world;
	public ArrayList<Snakepart> parts;

	public Snake(World world,int x, int y, int length) {
		this.world = world;
		parts = new ArrayList<Snakepart>();

		for (int i = 0; i <= length; i++) {
			parts.add(new Snakepart(x - i - 1, y));
		}
		System.out.println(parts.toString());

	}

	public boolean Move(int action) {
		boolean struck = false;

		for (int i = parts.size() - 1; i >= 0; i--) {
			if (i == 0) {
				parts.get(i).changeHeadDir(action);
			} else {
					parts.get(i).changeDir(parts.get(i - 1).dir);
				
			}
			
			parts.get(i).Move();
		}
		
		for(int i = 0; i< parts.size();i++)
			if(i != 0) {
				struck = checkifoccupied(parts.get(0).x,parts.get(0).y);
			}
		
		if(world.level[parts.get(0).x][parts.get(0).y].type == Type.WALL)
			struck = true;
		
		if(world.level[parts.get(0).x][parts.get(0).y].type == Type.FRUIT) {
			addNewPart();
			world.respawnFruit();
		}
		
		return struck;
	}
	
	public void addNewPart() {
		int x = parts.get(parts.size()-1).x;
		switch(parts.get(parts.size()-1).dir) {
		case 3:
			x++;
			break;
		case 4: 
			x--;
			break;
		}
		int y = parts.get(parts.size()-1).y;
		switch(parts.get(parts.size()-1).dir) {
		case 1:
			y++;
			break;
		case 2: 
			y--;
			break;
		}
		
		parts.add(new Snakepart(x,y));
	}
	
	public boolean checkifoccupied(int x,int y) {
		for(int i = 1; i < parts.size(); i++)
			if(parts.get(i).x == x && parts.get(i).y == y)
				return true;
		
		return false;
	}

	public void paint(Graphics g) {
		if (parts != null)
			for (int i = 0; i < parts.size(); i++)
				parts.get(i).paint(g);
	}
}
