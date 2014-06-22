package JustVM.stack;

public class StackFrame {
	private int start;
	private int size;
	private int paramCount;
	private int localCount;
	private int sp;

	public StackFrame(int start, int size, int paramCount, int localCount, int sp) {
		this.start = start;
		this.size = size;
		this.paramCount = paramCount;
		this.localCount = localCount;
		this.sp = sp;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getParamCount() {
		return paramCount;
	}

	public void setParamCount(int paramCount) {
		this.paramCount = paramCount;
	}

	public int getLocalCount() {
		return localCount;
	}

	public void setLocalCount(int localCount) {
		this.localCount = localCount;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}
}
