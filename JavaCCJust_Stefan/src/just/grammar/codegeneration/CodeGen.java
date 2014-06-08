package just.grammar.codegeneration;

import just.grammar.codegeneration.code.CodeLine;
import just.grammar.codegeneration.code.MethodCode;
import just.grammar.semantics.Symbol;
import just.grammar.semantics.Symbol.Kind;

public class CodeGen {
	public static CodeGen CodeGen = new CodeGen();
	
	private MethodCode currCode;
	
	private CodeGen() {
		
	}
	
	public void store(Descriptor desc) {
		switch (desc.kind) {
		case descConst:
			emit2(OpCode.LDC_W, desc.getAddress());
			break;
		case descArg:
			emit2(OpCode.ISTORE, desc.getAddress());
			break;
		case descLocal:
			emit2(OpCode.ISTORE, desc.getAddress());
			break;
		case descField:
			emit2(OpCode.PUTSTATIC, desc.getAddress());
			markFieldFixup(desc.symbol.spix);
			break;
		case descStack:
			break;
		}
		
		desc.kind = DescKind.descStack;
	}
	
	public void load(Descriptor desc) {
		switch (desc.kind) {
		case descConst:
			emit2(OpCode.LDC_W, desc.getAddress());
			break;
		case descArg:
			emit2(OpCode.ILOAD, desc.getAddress());
			break;
		case descLocal:
			emit2(OpCode.ILOAD, desc.getAddress());
			break;
		case descField:
			emit2(OpCode.GETSTATIC, desc.getAddress());
			markFieldFixup(desc.symbol.spix);
			break;
		case descStack:
			break;
		}
		
		desc.kind = DescKind.descStack;
	}
	
	public void call(Descriptor desc) {
		if(desc.symbol.kind == Kind.funcKind) {
			emit2(OpCode.INVOKESTATIC, desc.getAddress());
			markMethodFixup(desc.symbol.spix);
		}
	}
	
	public Descriptor newDescriptor(Symbol symbol) {
		return new Descriptor(symbol);
	}
	
	public void emit2(OpCode opCode, Object op) {
		currCode.addCodeLine(new CodeLine(opCode, op));
	}
	
	public void emit(OpCode opCode) {
		currCode.addCodeLine(new CodeLine(opCode));
	}

	public void markFieldFixup(int spix) {

	}

	private void markMethodFixup(int spix) {
		// TODO Auto-generated method stub
		
	}
	
	public MethodCode getCurrCode() {
		return currCode;
	}

	public void setCurrCode(MethodCode currCode) {
		this.currCode = currCode;
	}
}
