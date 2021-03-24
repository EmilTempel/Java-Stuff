package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	public int x, y;

	World world;

	public Player(int x, int y, World world) {
		this.x = x;
		this.y = y;

		this.world = world;
	}

	public NextState Move(int action) {
		switch (action) {
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
		
		return new NextState(x,y);
	}

	public boolean isPossible(int action) {
		switch (action) {
		case 0:
			return true;
		case 1:
			if (y - 1 >= 0 && world.level[x][y - 1].type != Type.WALL) {
				return true;
			} else {
				return false;
			}
		case 2:
			if (y + 1 < world.l && world.level[x][y + 1].type != Type.WALL) {
				return true;
			} else {
				return false;
			}
		case 3:
			if (x - 1 >= 0 && world.level[x - 1][y].type != Type.WALL) {
				return true;
			} else {
				return false;
			}
		case 4:
			if (x + 1 < world.b && world.level[x + 1][y].type != Type.WALL) {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	public void paint(Graphics g) {
		g.setColor(new Color(0, 0, 255));
		g.fillRect(x * 100, y * 100, 100, 100);
	}

	public class NextState {
		public int x, y;
		public double reward;
		public boolean done;

		public NextState(int x, int y) {
			this.x = x;
			this.y = y;

			reward = world.level[x][y].reward;

			if (world.level[x][y].type == Type.GOAL) {
				done = true;
			} else {
				done = false;
			}
		}
	}
}
