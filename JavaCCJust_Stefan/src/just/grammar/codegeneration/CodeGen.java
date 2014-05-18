package just.grammar.codegeneration;

import just.grammar.semantics.Symbol;

public class CodeGen {

	public void load(Descriptor desc) {
		switch (desc.kind) {
		case descConst:
			emit2(OpCode.LDC_W, desc.address);
			break;
		case descArg:
			emit2(OpCode.ILOAD, desc.address);
			break;
		case descLocal:
			emit2(OpCode.ILOAD, desc.address);
			break;
		case descField:
			emit2(OpCode.GETSTATIC, desc.address);
			markFieldFixup(desc.symbol.spix);
			break;
		case descStack:
			break;
		}
		
		desc.kind = DescKind.descStack;
	}

	public Descriptor newDescriptor(Symbol symbol) {
		return new Descriptor(symbol);
	}
	
	public void emit2(OpCode opCode, int address) {

	}
	
	public void emit(OpCode opCode) {

	}

	public void markFieldFixup(int spix) {

	}
}
