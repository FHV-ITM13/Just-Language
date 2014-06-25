package JustVM.stack;

public class StackFrame {
	private int startAddr;
	private int maxSize;
	private int paramCount;
	private int localCount; //only needed for none objects
	private int sp;

	public StackFrame(int startAddr, int maxSize, int localCount, int paramCount) {
		this.startAddr = startAddr;
		this.maxSize = maxSize;
		this.paramCount = paramCount;
		this.localCount = localCount;
		this.sp = startAddr + paramCount + localCount;
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

	public int getLocalStartAddr() {
		return startAddr + 0; //params and locals start always at zero
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}
	
	public void increaseSp() {
		++sp;
	}
	
	public void decreaseSp() {
		--sp;
	}
}
