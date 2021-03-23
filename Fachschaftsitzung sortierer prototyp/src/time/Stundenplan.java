package time;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Data.Fachschaft;

public class Stundenplan {

	private int spalten_breite = 200;
	private int zeilen_höhe = 20;

	public int delta_t;
	float[] start_times, end_times;
	float min_start_time, max_end_time;
	public Tag[] tage;

	public Stundenplan(int delta_t, float[] start_times, float[] end_times) {
		this.delta_t = delta_t;
		this.start_times = start_times;
		this.end_times = end_times;
		min_start_time = min_start_time();
		max_end_time = max_end_time();
		tage = new Tag[5];
		for (int i = 0; i < 5; i++) {
			tage[i] = new Tag(Tag.Type.values()[i], delta_t, start_times[i], end_times[i]);
		}

	}

	public float min_start_time() {
		float min = start_times[0];
		for (int i = 0; i < start_times.length; i++) {
			if (start_times[i] < min) {
				min = start_times[i];
			}
		}
		return min;
	}
	
	public float max_end_time() {
		float max = 0;
		for (int i = 0; i < end_times.length; i++) {
			if (end_times[i] > max) {
				max = end_times[i];
			}
		}
		return max;
	}

	public void fillTimeFrame(TimeFrame tf, String name) {
		for (int i = 0; i < tf.dauer; i++) {
			boolean plus = true;
			if (this.tage[tf.type.ordinal()].abschnitte[tf.time_slot + i] == null) {
				this.tage[tf.type.ordinal()].abschnitte[tf.time_slot + i] = "";
				plus = false;
			}
			this.tage[tf.type.ordinal()].abschnitte[tf.time_slot + i] += (plus ? "," : "") + name;
		}

	}

	public String print() {
		String print = "\n";
		for (int i = 0; i < 5; i++) {
			print += "\n" + tage[i].print();
		}
		return print;
	}

	public BufferedImage toImage() {
		BufferedImage img = new BufferedImage(6 * spalten_breite, findeMaxAbschnittzahl() * zeilen_höhe,
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = img.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());

		for (int i = 0; i < 5; i++) {

			for (int j = 0; j < tage[i].abschnittzahl; j++) {
				String abschnitt = tage[i].abschnitte[j];
				String[] f;

				if (abschnitt == null) {
					f = new String[1];
				} else {
					if (abschnitt.contains(",")) {
						f = abschnitt.split(",");
					} else {
						f = new String[1];
						f[0] = abschnitt;
					}
				}
				for (int k = 0; k < f.length; k++) {
					if (f[k] == null || f[k] == "geht nich") {
						if (f[k] == null) {
							g.setColor(Color.WHITE);
						} else {
							g.setColor(Color.RED);
						}
					} else {
						Fachschaft.Type typ = Fachschaft.Type.valueOf(f[k]);
						g.setColor(typ.color);
					}
					g.fillRect((i + 1) * spalten_breite + spalten_breite / f.length * k,
							((int) ((tage[i].start_time - min_start_time) *60)/delta_t) * zeilen_höhe
									+ (j + 1) * zeilen_höhe,
							spalten_breite / f.length, zeilen_höhe);
					g.setColor(Color.BLACK);
					g.drawRect((i + 1) * spalten_breite + spalten_breite / f.length * k,
							((int) ((tage[i].start_time - min_start_time) *60)/delta_t) * zeilen_höhe
									+ (j + 1) * zeilen_höhe,
							spalten_breite / f.length, zeilen_höhe);
				}
			}
			g.setFont(new Font("Arial", Font.BOLD, 24));
			g.drawString(tage[i].name, (i + 1) * spalten_breite, 25);
		}
		
		System.out.println(max_end_time);
		System.out.println(min_start_time);
		for(int i = 0; i < ((max_end_time - min_start_time)*60)/delta_t; i++) {
			float time = min_start_time + (float)(i*delta_t)/60;
			g.setFont(new Font("Arial", Font.BOLD, 10));
			g.drawString(time(time), 20, (i)*20);
		}
		return img;
	}
	
	public String time(float t) {
		int stunden = (int) t;
		int minuten = (int)((t - (float)stunden)*60);
		return stunden + ":" + minuten;
	}

	public int findeMaxAbschnittzahl() {
		int max = (int) (((max_end_time - min_start_time) * 60)/delta_t);
		return max;
	}

	public static Stundenplan randomlyfilled() {
		float[] s_t = { 15, 10, 11, 17, 12 };
		float[] e_t = { 16.5f, 20, 16, 19, 18 };
		Stundenplan p = new Stundenplan(15, s_t, e_t);
		for (int i = 0; i < 5; i++) {
			Tag tag = p.tage[i];
			for (int j = 0; j < tag.abschnittzahl; j++) {
				if (Math.random() > 0.1) {
					tag.abschnitte[j] = null;
				} else {
					tag.abschnitte[j] = "geht nich";
				}
			}
		}
		return p;
	}

}
