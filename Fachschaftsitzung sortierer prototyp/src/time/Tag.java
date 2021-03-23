package time;

public class Tag {

	public String name;
	public Type type;
	public int delta_t, abschnittzahl;
	public float start_time, end_time;
	public String[] abschnitte;

	public Tag(Type type, int delta_t, float start_time, float end_time) {
		this.name = type.toString();
		this.type = type;
		this.delta_t = delta_t;
		this.start_time = start_time;
		this.end_time = end_time;

		abschnittzahl = Math.round(((end_time - start_time) * 60) / delta_t);
		abschnitte = new String[abschnittzahl];
	}

	public String print() {
		String print = name + " : ";
		for (int i = 0; i < abschnittzahl; i++) {
			float time = start_time + ((float) delta_t / 60) * (float) i;
			String t = String.format("%.2f", time);
			print += t + " : " + abschnitte[i] + "\n";
		}
		return print;
	}

	public enum Type {
		Montag, Dienstag, Mittwoch, Donnerstag, Freitag;
	}
}
