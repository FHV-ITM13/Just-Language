package JustVM.constantPool;

import JustVM.Type;

public class Constant {
	private int id;
	private byte[] value;
	private Type type;
	private String name;
	
	public Constant(String name, int id, byte[] value, Type type) {
		this.id = id;
		this.value = value;
		this.type = type;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
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
