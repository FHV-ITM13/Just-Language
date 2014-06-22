package JustVM.stack;

public class StackFrame {
	private int startAddr;
	private int maxSize;
	private int paramCount;
	private int sp;
	private int currSize;
	private int localCount; //only needed for none objects

	public StackFrame(int startAddr, int maxSize, int localCount, int paramCount) {
		this.startAddr = startAddr;
		this.maxSize = maxSize;
		this.paramCount = paramCount;
		this.localCount = localCount;
		this.sp = startAddr;
		this.currSize = 0;
	}

	public int getStart() {
		return startAddr;
	}

	public void setStart(int start) {
		this.startAddr = start;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int size) {
		this.maxSize = size;
	}

	public int getParameterStartAddr() {
		return 0; //are the first entries on the stack
	}

	public int getLocalStartAddr() {
		return paramCount + localCount + 1; //local vars start after the parameters
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}
	
	public void increaseSp() {
		++sp;
		++currSize;
	}
	
	public void decreaseSp() {
		--sp;
		--currSize;
	}
	
	public int getCurrSize() {
		return currSize;
	}
}
