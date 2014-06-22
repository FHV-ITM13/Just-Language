package JustVM;

public enum Type {
	voidType(2), intType(4), boolType(2);
	
	Type(int length) {
		this.length = length;
	}
	
	private int length;
	
	public int getLength() {
		return length;
	}
}
