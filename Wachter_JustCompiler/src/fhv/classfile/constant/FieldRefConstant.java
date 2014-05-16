package fhv.classfile.constant;

public class FieldRefConstant extends Constant {
	private ClassConstant classIndex;

	private NameAndTypeConstant nameAndType;

	public FieldRefConstant(ClassConstant classIndex) {
		this.classIndex = classIndex;
	}

	public FieldRefConstant(ClassConstant classIndex,
			NameAndTypeConstant nameAndType) {
		this.classIndex = classIndex;
		this.nameAndType = nameAndType;
	}

	public ClassConstant getClassIndex() {
		return classIndex;
	}

	public void setNameAndType(NameAndTypeConstant nameAndType) {
		this.nameAndType = nameAndType;
	}

	public NameAndTypeConstant getNameAndType() {
		return nameAndType;
	}

	@Override
	public boolean isCompleted() {
		return nameAndType != null;
	}
}
