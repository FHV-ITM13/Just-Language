package fhv.classfile.constant;

public abstract class Constant {
	private int index;

	public Constant() {
		// TODO Auto-generated constructor stub
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public abstract boolean isCompleted();
}
