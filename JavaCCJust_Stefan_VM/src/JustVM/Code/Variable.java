package JustVM.Code;

public class Variable {
	private Type type;
	private String name;
	private Byte[] bytes;

	public Variable(String name, Type type, Byte[] bytes) {
		this.name = name;
		this.type = type;
		this.bytes = bytes;
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

	public Byte[] getBytes() {
		return bytes;
	}

	public void setBytes(Byte[] bytes) {
		this.bytes = bytes;
	}
}
