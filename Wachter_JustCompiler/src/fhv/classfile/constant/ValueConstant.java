package fhv.classfile.constant;

public class ValueConstant extends Constant {
	private String bytes;
	private String type;

	public ValueConstant(String bytes, String type) {
		this.bytes = bytes;
		this.type = type;
	}

	public String getBytes() {
		return bytes;
	}
	
	public String getType() {
		return type;
	}

	@Override
	public boolean isCompleted() {
		return true;
	}
}
