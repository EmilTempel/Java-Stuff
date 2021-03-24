package Main;

import Game.World;
import Gui.Gui;

public class Start {
	static World world;

	public static void main(String[] args) {
		Thread gamethread = new Thread() {
			public void run() {
				world = new World();
			}
		};
		gamethread.run();
		Thread guithread = new Thread() {
			public void run() {
				Gui gui = new Gui(world);
			}
		};
		guithread.run();

		world.controller.startLearning();
	}

}
