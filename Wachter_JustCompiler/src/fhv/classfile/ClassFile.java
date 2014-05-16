package fhv.classfile;

import java.util.HashMap;

import fhv.classfile.constant.ClassConstant;
import fhv.classfile.constant.Constant;
import fhv.classfile.constant.UTF8Constant;

public class ClassFile {

	private HashMap<Integer, Constant> constantPool;

	private ClassConstant classConstant;

	public ClassFile() {
		this.constantPool = new HashMap<>();
	}

	public ClassConstant getClassConstant() {
		return this.classConstant;
	}

	public Constant getConstant(String name) {
		for (Constant constant : this.constantPool.values()) {
			if (constant instanceof UTF8Constant
					&& ((UTF8Constant) constant).getBytes().equals(name)) {
				return constant;
			}
		}
		return null;
	}

	public Constant getConstant(int index) {
		return this.constantPool.get(index);
	}

	public int addConstant(Constant constant) {
		constant.setIndex(this.constantPool.size() + 1);

		if (constant instanceof ClassConstant) {
			this.classConstant = (ClassConstant) constant;
		}

		this.constantPool.put(constant.getIndex(), constant);

		return constant.getIndex();
	}
}
