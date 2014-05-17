package fhv.code;

import fhv.classfile.ClassFile;
import fhv.classfile.constant.Constant;
import fhv.code.fixup.Fixup;
import fhv.semantic.NameList;
import fhv.semantic.Symbol;

public class CodeGenerator {
	private Code currentCode;
	
	private ClassFile classFile;

	public CodeGenerator(Code currentCode, ClassFile classFile) {
		this.currentCode = currentCode;
		this.classFile = classFile;
	}

	public void load(Symbol symbol) {
		Symbol.Kind kind = symbol.kind;
		if (kind.equals(Symbol.Kind.constKind)) {
			Constant constant = classFile.getConstant(NameList.nameList.getNameOf(symbol.spix));
			this.emit2(OptCode.LDC_W, constant.getIndex());
		}
	}

	public void emit(OptCode opt) {
		Fixup[] fixups = new Fixup[0];
		CodeLine line = new CodeLine(opt, fixups);
		currentCode.addLine(line);
	}

	public void emit2(OptCode opt, Object operator) {
	}

	public void emit3() {
	}
}
