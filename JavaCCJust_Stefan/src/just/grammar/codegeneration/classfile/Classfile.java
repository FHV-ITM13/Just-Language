package just.grammar.codegeneration.classfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import just.grammar.codegeneration.classfile.constants.ClassConstant;
import just.grammar.codegeneration.classfile.constants.Constant;
import just.grammar.codegeneration.classfile.constants.FieldRefConstant;
import just.grammar.codegeneration.classfile.constants.MethodRefConstant;
import just.grammar.codegeneration.classfile.constants.NameTypeConstant;
import just.grammar.codegeneration.classfile.constants.UTF8Constant;
import just.grammar.codegeneration.output.XMLWriter;
import just.grammar.semantics.MethodSymbol;
import just.grammar.semantics.NameList;
import just.grammar.semantics.Symbol;
import just.grammar.semantics.Symbol.Kind;

public class Classfile {
	private List<Method> methods;
	private List<Field> fields;
	private HashMap<Integer, Constant> constants;
	private ClassConstant classConstant;
	
	private String magic = "justclassfile";
	private String majorVers = "1";
	private String minorVers = "0";
	
	public Classfile() {
		methods = new ArrayList<Method>();
		fields = new ArrayList<Field>();
		constants = new HashMap<Integer, Constant>();
	}

	public List<Method> getMethods() {
		return methods;
	}

	public List<Field> getFields() {
		return fields;
	}

	public HashMap<Integer, Constant> getConstants() {
		return constants;
	}

	public ClassConstant getClassConstant() {
		return classConstant;
	}
	
	public String getMagic() {
		return magic;
	}

	public String getMajorVers() {
		return majorVers;
	}

	public String getMinorVers() {
		return minorVers;
	}
	
	public Constant addConstant(Constant constant) {		
		constant.setIndex(this.constants.size() + 1);

		if (constant instanceof ClassConstant) {
			this.classConstant = (ClassConstant) constant;
		} else if (constant instanceof UTF8Constant) {
			Constant tempConst = this.getUTF8Constant(((UTF8Constant) constant).getBytes());
			if (tempConst != null) {
				return tempConst;
			}
		}
//		} else if (constant instanceof ValueConstant) {
//			ValueConstant tempVal = this.getValueConstant(((ValueConstant) constant).getBytes());
//			if (tempVal != null) {
//				return tempVal;
//			}
//		}

		this.constants.put(constant.getIndex(), constant);

		return constant;
	}
	
	public Constant addVarConstant(Symbol s) {
		//only global variables are important for constant pool
		//ignore Kind.varKind (local variables)
		if (s.kind == Kind.fieldKind) {
			//add var name to constant pool
			Constant name = this.addNameConstant(s.spix);
			//add type to constant pool
			Constant type = this.addTypeConstant(s);
			//add variable with name and type
			Constant constant = this.addConstant(new NameTypeConstant(name, type));

			constant = this.addConstant(new FieldRefConstant(classConstant, (NameTypeConstant) constant));

			this.fields.add(new Field((FieldRefConstant) constant));

			s.addr = constant.getIndex();

			return constant;
		}

		return null;
	}

	public Constant addClassConstant(Symbol s) {
		return addConstant(new ClassConstant(addNameConstant(s.spix)));
	}
	
	public Constant addMethodConstant(MethodSymbol s) {
		if(s.kind == Kind.funcKind) {
			String methodSignature = String.format("(%s)%s", s.getParamsAsString(), s.type.getName());
			
			//add method name and method signature to constant pool
			Constant constant = this.addNameTypeConstant(s.spix, methodSignature);
			//add method ref to constant pool
			constant = this.addConstant(new MethodRefConstant(classConstant, (NameTypeConstant) constant));

			//add to method to method pool
			this.methods.add(new Method((MethodRefConstant) constant));

			s.addr = constant.getIndex();

			return constant;
		}
		
		return null;
	}
	
//	String typeString = String.format("(%s)%s", s.getParamTypes(), s
//			.getType().getShortName());
//
//	Constant name = this.addNameConstant(s);
//	Constant type = this.addConstant(new UTF8Constant(typeString));
//
//	Constant nameAndTypeConstant = this
//			.addConstant(new NameAndTypeConstant(name, type));
//
//	MethodRefConstant constant = (MethodRefConstant) this
//			.addConstant(new MethodRefConstant(this.classConstant,
//					(NameAndTypeConstant) nameAndTypeConstant));
//
//	Method m = new Method(constant);
//	this.methods.add(m);
//	CodeAttribute codeAttr = new CodeAttribute(this.codeConstant, scope);
//	m.addAttribute(codeAttr);
//
//	constant.setMethod(m);
//	constant.setCode(codeAttr.getCode());
//	s.setAddress(constant.getIndex());
//
//	return constant;

	
	private Constant addNameTypeConstant(int nameSpix, String type) {
		Constant nameConst = this.addNameConstant(nameSpix);
		Constant typeConst = this.addConstant(new UTF8Constant(type));
		
		return addConstant(new NameTypeConstant(nameConst, typeConst));
	}

	public void write(String fileName) {
		XMLWriter.write(fileName, this);
	}
	
	private Constant addNameConstant(int spix) {
		return addConstant(new UTF8Constant(NameList.NameList.nameOf(spix)));
	}
	
	private Constant addTypeConstant(Symbol s) {
		return addConstant(new UTF8Constant(s.type.getName()));
	}
	
	private Constant getUTF8Constant(String bytes) {
		for (Constant constant : this.constants.values()) {
			if(constant instanceof UTF8Constant && ((UTF8Constant)constant).getBytes().equals(bytes)) {
				return constant;
			}
		}
		
		return null;
	}
}
