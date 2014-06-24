package JustVM.constantPool;

import JustVM.type.Type;

public class Constant {
	private int addr;
	private int value; //TODO should be byte[]
	private Type type;
	private String name;
	
	public Constant(String name, int addr, int value, Type type) {
		this.addr = addr;
		this.value = value;
		this.type = type;
		this.name = name;
	}

	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
