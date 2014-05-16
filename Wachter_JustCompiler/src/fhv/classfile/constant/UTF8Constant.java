package fhv.classfile.constant;

public class UTF8Constant extends Constant {

	private String bytes;

	public UTF8Constant(String bytes) {
		this.bytes = bytes;
	}
	
	public String getBytes() {
		return bytes;
	}

	@Override
	public boolean isCompleted() {
		return true;
	}
}
