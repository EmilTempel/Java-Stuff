package Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

public class Snake {
	World world;
	public ArrayList<Snakepart> parts;

	public Snake(World world, int x, int y, int length) {
		this.world = world;
		parts = new ArrayList<Snakepart>();

		for (int i = 0; i <= length; i++) {
			parts.add(new Snakepart(world, x - i - 1, y, false));
		}
		parts.get(0).head = true;

	}

	public NextState Move(int action) {
		boolean done = false;
		double reward = 0;

		for (int i = parts.size() - 1; i >= 0; i--) {
			if (i == 0) {
				parts.get(i).changeHeadDir(action);
			} else {
				parts.get(i).changeDir(parts.get(i - 1).dir);

			}

			parts.get(i).Move();
		}

		for (int i = 0; i < parts.size(); i++)
			if (i != 0) {
				if (checkifoccupied(parts.get(0).x, parts.get(0).y)) {
					done = true;
					reward = -1000000;
				}
			}

		if (world.level[parts.get(0).x][parts.get(0).y].type == Type.FLOOR) {
			reward += Type.FLOOR.reward;
		}
		
		if (world.level[parts.get(0).x][parts.get(0).y].type == Type.WALL) {
			done = true;
			reward += Type.WALL.reward;
		}

		if (world.level[parts.get(0).x][parts.get(0).y].type == Type.FRUIT) {
			addNewPart();
			reward += Type.FRUIT.reward;
			world.respawnFruit();
		}

		return new NextState(parts.get(0).x, parts.get(0).y, reward, done);
	}

	public void addNewPart() {
		int x = parts.get(parts.size() - 1).x;
		switch (parts.get(parts.size() - 1).dir) {
		case 3:
			x++;
			break;
		case 4:
			x--;
			break;
		}
		int y = parts.get(parts.size() - 1).y;
		switch (parts.get(parts.size() - 1).dir) {
		case 1:
			y++;
			break;
		case 2:
			y--;
			break;
		}

		parts.add(new Snakepart(world, x, y, false));
	}

	public boolean checkifoccupied(int x, int y) {
		for (int i = 1; i < parts.size(); i++)
			if (i != 0)
				if (parts.get(i).x == x && parts.get(i).y == y)
					return true;

		return false;
	}

	public int[][] getState() {
		int head_x = parts.get(0).x;
		int head_y = parts.get(0).y;
		int[][] state = new int[11][11];

		for (int x = -5; x <= 5; x++)
			for (int y = -5; y <= 5; y++) {
				if (head_x + x < 0 || head_x + x > world.b - 1 || head_y + y < 0 || head_y + y > world.l - 1
						|| checkifoccupied(head_x + x, head_y + y)) {
					state[x + 5][y + 5] = 1;
				} else {
					state[x + 5][y + 5] = world.level[head_x + x][head_y + y].type.code;
				}
			}
		return state;
	}

	public void paint(Graphics g) {

		if (parts != null)
			for (int i = 0; i < parts.size(); i++)
				parts.get(i).paint(g);
	}

	public class NextState {
		public int x, y;
		public int[][] state;
		public double reward;
		public boolean done;

		public NextState(int x, int y, double reward, boolean done) {
			this.x = x;
			this.y = y;
			this.done = done;
			state = getState();
			this.reward = reward;
		}
	}
}
