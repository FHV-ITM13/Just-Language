package fhv.classfile.constant;

public class ClassConstant extends Constant {
	private UTF8Constant nameIndex;

	public ClassConstant() {
	}

	public ClassConstant(UTF8Constant name) {
		this.nameIndex = name;
	}

	public UTF8Constant getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(UTF8Constant nameIndex) {
		this.nameIndex = nameIndex;
	}

	@Override
	public boolean isCompleted() {
		return nameIndex != null;
	}
}
