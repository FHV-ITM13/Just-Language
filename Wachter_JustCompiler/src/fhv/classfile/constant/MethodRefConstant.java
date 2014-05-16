package fhv.classfile.constant;

public class MethodRefConstant extends Constant {
	private ClassConstant classIndex;

	private NameAndTypeConstant nameAndType;

	public MethodRefConstant(ClassConstant classIndex) {
		this.classIndex = classIndex;
	}

	public MethodRefConstant(ClassConstant classIndex,
			NameAndTypeConstant nameAndType) {
		this.classIndex = classIndex;
		this.nameAndType = nameAndType;
	}

	public ClassConstant getClassIndex() {
		return classIndex;
	}

	public NameAndTypeConstant getNameAndType() {
		return nameAndType;
	}

	@Override
	public boolean isCompleted() {
		return nameAndType != null;
	}
}
