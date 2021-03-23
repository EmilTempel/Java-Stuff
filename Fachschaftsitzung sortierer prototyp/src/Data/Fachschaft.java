package Data;

import java.awt.Color;
import java.util.ArrayList;

import time.Stundenplan;
import time.Tag;
import time.TimeFrame;

public class Fachschaft {

	public Lehrer[] lehrer;
	public int groesse, dauer, mindest_teilnehmer;
	public Type type;
	public String name;

	public Fachschaft(Lehrer[] lehrer, int dauer, int mindest_teilnehmer, Type type) {
		this.lehrer = lehrer;
		this.groesse = lehrer.length;
		this.dauer = dauer;
		this.mindest_teilnehmer = mindest_teilnehmer;
		this.type = type;
		this.name = type.toString();
	}

	public void fillTimeFrame(TimeFrame tf) {
		for (int i = 0; i < lehrer.length; i++) {
			lehrer[i].plan.fillTimeFrame(tf, name);
		}
	}

	public TimeFrame[] find_all_Possibilities(Stundenplan plan) {
		TimeFrame[] time_frames = new TimeFrame[0];
		int counter = 0;
		boolean flag = true;
		while (time_frames.length == 0 && flag) {
			time_frames = find_Possibilities(plan, counter);
			counter++;
			if (counter >= mindest_teilnehmer) {
				flag = false;
			}
		}
		return time_frames;
	}

	public TimeFrame[] find_Possibilities(Stundenplan plan, int buffer) {
		ArrayList<TimeFrame> frames = new ArrayList<TimeFrame>();

		for (int i = 0; i < 5; i++) {
			Tag tag = plan.tage[i];

			for (int j = 0; j < tag.abschnittzahl - dauer; j++) {
				int counter = 0;

				for (int k = 0; k < dauer; k++) {
					int l_counter = 0;
					for (int l = 0; l < lehrer.length; l++) {
						if (lehrer[l].plan.tage[i].abschnitte[j + k] == null) {
							l_counter++;
						}
					}
					if (l_counter >= lehrer.length - buffer) {
						counter++;

					}
				}

				if (counter >= dauer) {
					frames.add(new TimeFrame(tag.type, j, dauer));
				}
			}
		}

		TimeFrame[] timeframes = new TimeFrame[frames.size()];
		for (int i = 0; i < frames.size(); i++) {
			timeframes[i] = frames.get(i);
		}

		return timeframes;
	}

	public enum Type {
		Biologie(new Color(0, 150, 0)), Chemie(new Color(100, 100, 100)), Deutsch(new Color(250, 250, 0)),
		Englisch(new Color(0, 255, 0)), Ethik(new Color(200, 200, 200)), Französisch(new Color(191, 112, 161)),
		Geographie(new Color(255, 0, 0)), Geschichte_Sozialkunde(new Color(127, 51, 0)),
		Informatik(new Color(200, 255, 200)), Italienisch(new Color(255, 203, 153)), Kunst(new Color(255, 127, 127)),
		Latein(new Color(200, 60, 0)), Mathematik(new Color(0, 0, 200)), Musik(new Color(70, 0, 70)),
		Natur_und_Technik(new Color(0, 160, 160)), Physik(new Color(218, 255, 127)), Religion(new Color(178, 0, 255)),
		Sport(new Color(0, 255, 255)), Wirtschaft_und_Recht(new Color(0, 0, 0));

		public Color color;

		Type(Color color) {
			this.color = color;
		}
	}
}
