package Main;

import java.awt.Graphics;

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
		
		Thread guithread = new Thread() {
			public void run() {
				Gui gui = new Gui(world);
			}
		};
		gamethread.run();
		guithread.run();
		
		world.agent.startLearning();
	}

}
