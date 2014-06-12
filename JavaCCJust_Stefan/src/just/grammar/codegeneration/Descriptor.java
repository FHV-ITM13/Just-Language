package just.grammar.codegeneration;

import just.grammar.semantics.Symbol;
import just.grammar.semantics.Symbol.Kind;

public class Descriptor {
	public Symbol symbol;
	public DescKind kind;
	
	public Descriptor(Symbol symbol) {
		this.symbol = symbol;
		
		defineKind(symbol.kind);
	}
	
	public int getAddress() {
		return symbol.addr;
	}

	public Symbol getSymbol() {
		return symbol;
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
	
	@Override
	public String toString() {
		return String.valueOf(getAddress());
	}
}
