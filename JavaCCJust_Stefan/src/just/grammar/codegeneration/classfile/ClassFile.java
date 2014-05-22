package just.grammar.codegeneration.classfile;
import java.util.HashMap;

import just.grammar.codegeneration.constant.ClassConstant;
import just.grammar.codegeneration.constant.Constant;
import just.grammar.codegeneration.constant.NameAndTypeConstant;
import just.grammar.codegeneration.constant.UTF8Constant;
import just.grammar.codegeneration.constant.ValueConstant;
import just.grammar.semantics.NameList;
import just.grammar.semantics.Symbol;

public class ClassFile {
	public static ClassFile ClassFile = new ClassFile();
	
	private HashMap<Integer, Constant> constants = new HashMap<>();
	
}
