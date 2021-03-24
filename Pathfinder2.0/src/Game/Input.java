package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Ai.Agent;

public class Input extends KeyAdapter {
	World world;

	public Input(World world) {
		this.world = world;
	}

	public void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_F3:
			if (world.showValues) {
				world.showValues = false;
			} else {
				world.showValues = true;
			}

			break;

		}
	}

}
