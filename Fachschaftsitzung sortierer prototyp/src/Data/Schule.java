package Data;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Algo.StundenplanGenerator;
import time.Stundenplan;

public class Schule {

	private int MAX = 0;

	public Lehrer[] lehrer;
	public Fachschaft[] fachschaften;
	public int delta_t;
	public float[] start_time, end_time;

	public Schule(int delta_t, float[] start_time, float[] end_time) {
		this.delta_t = delta_t;
		this.start_time = start_time;
		this.end_time = end_time;

		lehrer = new Lehrer[100];
		for (int i = 0; i < 100; i++) {
			lehrer[i] = new Lehrer("Lehrername " + i);
		}

		fachschaften = new Fachschaft[19];
		for (int i = 0; i < fachschaften.length; i++) {
			Lehrer[] l = new Lehrer[10];
			for (int j = 0; j < 10; j++) {
				l[j] = lehrer[i * 5 + j];
			}
			fachschaften[i] = new Fachschaft(l, (int) (Math.random()*4)+4, 4, Fachschaft.Type.values()[i]);
		}

		sortiereFachschaften(MAX);
	}

	public void sortiereFachschaften(int howto) {
		if (howto == MAX) {
			sortMAX();
		}
	}

	public void sortMAX() {
		
	}

	public int findMAX(int[] arg) {
		int max = 0;
		for (int i = 0; i < arg.length; i++) {
			if (arg[i] > arg[max]) {
				max = i;
			}
		}
		return max;
	}

	public static void main(String[] args) { 
		float[] s_t = { 15, 10, 11, 17, 12 };
		float[] e_t = { 16.5f, 20, 16, 19, 18 };
		Schule schule = new Schule(15, s_t, e_t);
		StundenplanGenerator gen = new StundenplanGenerator(schule);
		Stundenplan plan = gen.generate(0);
		System.out.println(plan.print());
		
		BufferedImage img = plan.toImage();
		JFrame frame = new JFrame("Plan");
		frame.setSize(img.getWidth(),img.getHeight()+34);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Graphics g = frame.getGraphics();
		g.drawImage(img, 0, 34, img.getWidth(), img.getHeight(),null);
	}
}
