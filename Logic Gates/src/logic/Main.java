package logic;

import gui.Window;

public class Main {

	public static void main(String[] args) {
		CircuitBoard cb = new CircuitBoard(1200,1000);
		Window w = new Window(1200,1000,cb);
	}
}
