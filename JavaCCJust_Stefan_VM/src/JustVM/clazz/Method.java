package JustVM.clazz;

public class Method {
	private int index;
	private int startAddr;
	private int nrParams;
	private int nrLocals;
	private int stackSize;
	private int length;
	
	public Method() {}
	
	public Method(int index, int startAddr, int nrParams, int nrLocals, int stackSize, int length) {
		this.index = index;
		this.stackSize = stackSize;
		this.startAddr = startAddr;
		this.nrLocals = nrLocals;
		this.nrParams = nrParams;
		this.length = length;
	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getStartAddr() {
		return startAddr;
	}

	public void setStartAddr(int startAddr) {
		this.startAddr = startAddr;
	}

	public int getNrParams() {
		return nrParams;
	}

	public void setNrParams(int nrParams) {
		this.nrParams = nrParams;
	}

	public int getNrLocals() {
		return nrLocals;
	}

	public void setNrLocals(int nrLocals) {
		this.nrLocals = nrLocals;
	}

	public int getStackSize() {
		return stackSize;
	}

	public void setStackSize(int stackSize) {
		this.stackSize = stackSize;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
