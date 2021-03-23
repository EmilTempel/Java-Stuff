package Algo;

import Data.Schule;
import time.Stundenplan;
import time.TimeFrame;

public class StundenplanGenerator {
	
	Schule schule;
	
	public StundenplanGenerator(Schule schule) {
		this.schule = schule;
	}
	
	public Stundenplan generate(int seed) {
		Stundenplan plan = new Stundenplan(schule.delta_t, schule.start_time, schule.end_time);
		
		for(int i = 0; i < schule.fachschaften.length; i++) {
			TimeFrame[] timeframes = schule.fachschaften[i].find_all_Possibilities(plan);
			
			Bewerter bewerter = new Bewerter(plan);
			TimeFrame bestchoice = bewerter.bestChoice(timeframes);
			plan.fillTimeFrame(bestchoice, schule.fachschaften[i].name);
			schule.fachschaften[i].fillTimeFrame(bestchoice);
		}
		
		return plan;
	}
	
}
