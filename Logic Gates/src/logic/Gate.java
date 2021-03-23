package logic;

public enum Gate {
	
	AND(2),NAND(2),OR(2),XOR(2),NOR(2),NOT(1),SOURCE(0),LEVER(1),LAMP(1);

	int n;

	Gate(int n) {
		this.n = n;
	}

	public int output(int... input) {
		if (input.length != n) {
			return 0;
		} else {
			switch(this.toString()) {
			case "AND": return (input[0]+input[1] == 2)?1:0;
			case "NAND": return (!(input[0]+input[1] == 2))?1:0;
			case "OR": return (input[0]+input[1] >= 1)?1:0;
			case "XOR": return (input[0]+input[1] == 1)?1:0;
			case "NOR": return (input[0]+input[1] < 1)?1:0;
			case "NOT": return (input[0] == 0)?1:0;
			case "SOURCE": return 1;
			case "LEVER": return input[0];
			case "LAMP": return input[0];
			default: return 0;
			}
		}
	}
}
