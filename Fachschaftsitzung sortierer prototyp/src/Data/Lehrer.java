package Data;

import time.Stundenplan;

public class Lehrer {
	
	public String name;
	public Stundenplan plan;
	
	public Lehrer(String name, Stundenplan plan) {
		this.name = name;
		this.plan = plan;
	}
	
	public Lehrer(String name) {
		this.name = name;
		this.plan = Stundenplan.randomlyfilled();
	}
}
