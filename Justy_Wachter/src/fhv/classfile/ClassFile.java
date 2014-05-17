package fhv.classfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fhv.classfile.constant.ClassConstant;
import fhv.classfile.constant.Constant;
import fhv.classfile.constant.FieldRefConstant;
import fhv.classfile.constant.MethodRefConstant;
import fhv.classfile.constant.NameAndTypeConstant;
import fhv.classfile.constant.UTF8Constant;
import fhv.classfile.output.XmlWriter;
import fhv.symbol.Kind;
import fhv.symbol.MethodSymbol;
import fhv.symbol.Symbol;

public class ClassFile {
	private HashMap<Integer, Constant> constantPool;
	private ClassConstant classConstant;
	private UTF8Constant codeConstant;

	private List<Field> fields;

	private String magic = "classfile";
	private String minor = "0";
	private String major = "1";

	public ClassFile() {
		this.constantPool = new HashMap<>();
		this.fields = new ArrayList<>();

		this.codeConstant = (UTF8Constant) this.addConstant(new UTF8Constant(
				"Code"));
	}

	public HashMap<Integer, Constant> getConstantPool() {
		return constantPool;
	}

	public List<Field> getFields() {
		return fields;
	}

	public String getMagic() {
		return magic;
	}

	public String getMajor() {
		return major;
	}

	public String getMinor() {
		return minor;
	}

	public void write(String fileName) {
		new XmlWriter().write(fileName, this);
	}

	public ClassConstant getClassConstant() {
		return this.classConstant;
	}

	public UTF8Constant getConstant(String name) {
		for (Constant constant : this.constantPool.values()) {
			if (constant instanceof UTF8Constant
					&& ((UTF8Constant) constant).getBytes().equals(name)) {
				return (UTF8Constant) constant;
			}
		}
		return null;
	}

	public Constant getConstant(int index) {
		return this.constantPool.get(index);
	}

	public Constant addTypeConstant(Symbol s) {
		return this.addConstant(new UTF8Constant(s.getType().getShortName()));
	}

	private Constant addNameConstant(Symbol s) {
		return this.addConstant(new UTF8Constant(s.getName()));
	}

	public Constant addClassConstant(Symbol s) {
		Constant name = this.addNameConstant(s);

		return this.addConstant(new ClassConstant(name));
	}

	public Constant addVarConstant(Symbol s) {
		if (s.getKind().equals(Kind.fieldKind)) {
			Constant name = this.addNameConstant(s);
			Constant type = this.addTypeConstant(s);

			Constant constant = this.addConstant(new NameAndTypeConstant(name,
					type));

			constant = this.addConstant(new FieldRefConstant(
					this.classConstant, (NameAndTypeConstant) constant));

			this.fields.add(new Field((FieldRefConstant) constant));

			return constant;
		}
		// TODO generate addr and so (fields, param, ... s.kind)

		return null;
	}

	public Constant addMethodConstant(MethodSymbol s) {
		String typeString = String.format("(%s)%s", s.getParamTypes(), s
				.getType().getShortName());

		Constant name = this.addNameConstant(s);
		Constant type = this.addConstant(new UTF8Constant(typeString));

		Constant nameAndTypeConstant = this
				.addConstant(new NameAndTypeConstant(name, type));

		Constant constant = this.addConstant(new MethodRefConstant(
				this.classConstant, (NameAndTypeConstant) nameAndTypeConstant));

		// TODO init new method

		return constant;
	}

	private Constant addConstant(Constant constant) {
		constant.setIndex(this.constantPool.size() + 1);

		if (constant instanceof ClassConstant) {
			this.classConstant = (ClassConstant) constant;
		} else if (constant instanceof UTF8Constant) {
			UTF8Constant c = this.getConstant(((UTF8Constant) constant)
					.getBytes());
			if (c != null) {
				return c;
			}
		}

		this.constantPool.put(constant.getIndex(), constant);

		return constant;
	}
}
