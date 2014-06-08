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
import just.grammar.semantics.Scope;
import just.grammar.semantics.Symbol;
import just.grammar.semantics.Symbol.Kind;

public class Classfile {
	private List<Method> methods;
	private List<Field> fields;
	private HashMap<Integer, Constant> constants;
	
	private ClassConstant classConstant;
	private UTF8Constant codeConstant;
	
	private String magic = "justclassfile";
	private String majorVers = "1";
	private String minorVers = "0";
	
	public Classfile() {
		methods = new ArrayList<Method>();
		fields = new ArrayList<Field>();
		constants = new HashMap<Integer, Constant>();
		codeConstant = (UTF8Constant)addConstant(new UTF8Constant("Code"));
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

		this.constants.put(constant.getIndex(), constant);

		return constant;
	}
	
	public Constant addVarConstant(Symbol symbol) {
		//only global variables are important for constant pool
		//ignore Kind.varKind (local variables)
		if (symbol.kind == Kind.fieldKind) {
			//add var name to constant pool
			Constant name = this.addNameConstant(symbol.spix);
			//add type to constant pool
			Constant type = this.addTypeConstant(symbol);
			//add variable with name and type
			Constant constant = this.addConstant(new NameTypeConstant(name, type));

			constant = this.addConstant(new FieldRefConstant(classConstant, (NameTypeConstant) constant));

			this.fields.add(new Field((FieldRefConstant) constant));

			symbol.addr = constant.getIndex();

			return constant;
		}

		return null;
	}

	public Constant addClassConstant(Symbol symbol) {
		return addConstant(new ClassConstant(addNameConstant(symbol.spix)));
	}
	
	public Constant addMethodConstant(MethodSymbol method, Scope scope) {
		if(method.kind == Kind.funcKind) {
			String methodSignature = String.format("(%s)%s", method.getParamsAsString(), method.type.getName());
			
			//add method name and method signature to constant pool
			Constant constant = this.addNameTypeConstant(method.spix, methodSignature);
			//add method ref to constant pool
			constant = this.addConstant(new MethodRefConstant(classConstant, (NameTypeConstant) constant));

			//add to method to method pool
			this.methods.add(new Method((MethodRefConstant) constant, new Attribute(codeConstant, scope)));
			
			//add ref to method code
			((MethodRefConstant)constant).setMethodCode(methods.get(methods.size() -1 ).getAttribute().getMethodCode());
			
			method.addr = constant.getIndex();

			return constant;
		}
		
		return null;
	}
	
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
	
	private Constant addTypeConstant(Symbol symbol) {
		return addConstant(new UTF8Constant(symbol.type.getName()));
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
