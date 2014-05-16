package fhv.classfile.constant;

public class NameAndTypeConstant extends Constant {

	private UTF8Constant nameIndex;
	private UTF8Constant descriptorIndex;

	public NameAndTypeConstant(UTF8Constant nameIndex,
			UTF8Constant descriptorIndex) {
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}
	
	public UTF8Constant getDescriptorIndex() {
		return descriptorIndex;
	}

	public UTF8Constant getNameIndex() {
		return nameIndex;
	}

	@Override
	public boolean isCompleted() {
		return true;
	}
}
