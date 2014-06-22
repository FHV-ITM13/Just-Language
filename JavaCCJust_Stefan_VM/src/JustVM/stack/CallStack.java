package JustVM.stack;

public class CallStack {
	private int[] values; //represents the operand stack
	private int nextIndex;
	
	public CallStack(int size) {
		values = new int[size];
		nextIndex = 0;
	}
	
	public void push(int value) {
		values[nextIndex++] = value;
	}
	
	public int pop() {
		return values[--nextIndex];
	}
	
	public void setValue(int addr, int value) {
		values[addr] = value;
	}
	
	public int getValue(int addr) {
		return values[addr];
	}
}



