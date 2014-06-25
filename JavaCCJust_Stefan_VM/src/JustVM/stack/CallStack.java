package JustVM.stack;

public class CallStack {
	private int[] values; // represents the operand stack

	public CallStack(int size, int start) {
		values = new int[size];
	}

	public void put(int addr, int value) {
		values[addr] = value;
	}

	public int get(int addr) {
		return values[addr];
	}

	public void setValue(int addr, int value) {
		values[addr] = value;
	}

	public int getValue(int addr) {
		return values[addr];
	}

	public void printStack() {
		StringBuilder sb = new StringBuilder();
		sb.append("-----------------\n");

		for (int i = 0; i < values.length; i++) {
			sb.append(i).append(": ").append(values[i]).append("\n");
		}
		
		sb.append("-----------------\n");

		System.out.print(sb.toString());
	}
}
