package just.grammar.codegeneration;

import just.grammar.semantics.Symbol;
import just.grammar.semantics.Symbol.Kind;

public class Descriptor {
	public int address;
	public Symbol symbol;
	public DescKind kind;
	
	public Descriptor(Symbol symbol) {
		this.address = -1; //TODO
		this.symbol = symbol;
		
		defineKind(symbol.kind);
	}

	private void defineKind(Kind syKind) {
		switch (syKind) {
		case constKind:
			this.kind = DescKind.descConst;
			break;
		case parKind:
			this.kind = DescKind.descArg;
			break;
		case varKind:
			this.kind = DescKind.descLocal;
			break;
		case fieldKind:
			this.kind = DescKind.descField;
			break;
		case funcKind:
		case undefKind:
		default:
			this.kind = DescKind.descStack;
			break;
		}
	}
	
}
