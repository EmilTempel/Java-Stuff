package Algo;

import time.Stundenplan;
import time.TimeFrame;

public class Bewerter {
	
	Stundenplan plan;
	
	int kompaktheits_faktor = 10;
	int verteilungs_faktor = 2;
	
	
	public Bewerter(Stundenplan plan) {
		this.plan = plan;
	}
	
	public int bewerte() {
		
		
		
		return 0;
	}
	
	public TimeFrame bestChoice(TimeFrame[] tf) {
		int[] scores = bewerte(tf);
		
		int index = 0;
		for(int i = 0; i < tf.length; i++) {
			if(scores[i] < scores[index]) {
				index = i;
			}
		}
		
		return tf[index];
	}
	
	public int[] bewerte(TimeFrame[] tf) {
		int[] output = new int[tf.length];
		for(int i = 0; i < tf.length; i++) {
			output[i] = bewerte(tf[i]);
		}
		return output;
	}
	
	public int bewerte(TimeFrame timeframe) {
		int bewertung = 0;
		
		bewertung += kompaktheits_faktor * distanz(timeframe);
		
		return bewertung;
	}
	
	public int distanz(TimeFrame timeframe) {
		for(int i = timeframe.time_slot - 1; i >= 0; i--) {
			if(plan.tage[timeframe.type.ordinal()].abschnitte[i] != null) {
				return timeframe.time_slot - i;
			}
		}
		
		return timeframe.time_slot;
	}
	
	
	
}
