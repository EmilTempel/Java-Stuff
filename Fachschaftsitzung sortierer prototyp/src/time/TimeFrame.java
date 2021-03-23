package time;

public class TimeFrame {

	public Tag.Type type;
	public int time_slot,dauer;
	
	public TimeFrame(Tag.Type type, int time_slot, int dauer) {
		this.type = type;
		this.time_slot = time_slot;
		this.dauer = dauer;
		
	}
}
