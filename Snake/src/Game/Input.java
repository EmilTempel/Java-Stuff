package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {
	World world;
	int action;

	public Input(World world) {
		this.world = world;
		action = 0;
	}

	public void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		action = 0;
		switch (keyCode) {
		case KeyEvent.VK_W:
			action = 1;
			break;
		case KeyEvent.VK_S:
			action = 2;
			break;
		case KeyEvent.VK_A:
			action = 3;
			break;
		case KeyEvent.VK_D:
			action = 4;
			break;

		}
		

	}

}
