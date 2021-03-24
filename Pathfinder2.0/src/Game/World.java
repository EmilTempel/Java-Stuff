package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Ai.Controller;

public class World {
	public int b, l;
	public Tile[][] level;
	public Controller controller;
	public Player[] player;
	public Input input;
	boolean showValues;

	public World() {
		BufferedImage maze = null;
		try {
			maze = ImageIO.read(World.class.getResource("/maze.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		b = maze.getWidth();
		l = maze.getHeight();
		level = new Tile[b][l];
		for (int x = 0; x < b; x++)
			for (int y = 0; y < l; y++) {
				level[x][y] = new Tile(Type.FLOOR, x, y);
				Color color = new Color(maze.getRGB(x, y));

				if (color.equals(new Color(0, 0, 0)))
					level[x][y] = new Tile(Type.WALL, x, y);
			}

		level[b - 1][0] = new Tile(Type.GOAL, b - 1, 0);
		level[b - 2][0] = new Tile(Type.GOAL, b - 2, 0);
		level[b - 1][1] = new Tile(Type.GOAL, b - 1, 1);
		level[b - 2][1] = new Tile(Type.GOAL, b - 2, 1);

		player = new Player[200];
		reset();

		controller = new Controller(this);

		input = new Input(this);

		showValues = false;
	}

	public void reset() {
		for (int i = 0; i < player.length; i++)
			player[i] = new Player(0, l - 1, this);
	}

	public void paint(Graphics g) {
		for (Tile[] T : level)
			for (Tile t : T)
				t.paint(g);

		for (Player p : player)
			p.paint(g);

	}
}
