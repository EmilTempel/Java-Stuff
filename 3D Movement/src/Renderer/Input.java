package Renderer;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import Renderer.geometry.PointofView;

public class Input implements KeyListener, MouseMotionListener{
	PointofView player;
	int lastx;
	
	public Input(PointofView player) {
		this.player = player;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		double deltax = lastx - e.getX();
		player.rotateA(deltax/10000);
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("input");
		int keycode = e.getKeyCode();
		switch(keycode) {
		case KeyEvent.VK_W:
			player.W = true;
			break;
		case KeyEvent.VK_S:
			player.S = true;
			break;
		case KeyEvent.VK_A:
			player.A = true;
			break;
		case KeyEvent.VK_D:
			player.D = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch(keycode) {
		case KeyEvent.VK_W:
			player.W = false;
			break;
		case KeyEvent.VK_S:
			player.S = false;
			break;
		case KeyEvent.VK_A:
			player.A = false;
			break;
		case KeyEvent.VK_D:
			player.D = false;
			break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
