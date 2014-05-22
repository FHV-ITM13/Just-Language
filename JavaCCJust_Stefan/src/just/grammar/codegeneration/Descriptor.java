package just.grammar.codegeneration;

import just.grammar.semantics.Symbol;
import just.grammar.semantics.Symbol.Kind;

public class Descriptor {
	public int address;
	public Symbol symbol;
	public Kind kind;
	public int spix;
	
	public Descriptor(Symbol symbol) {
		this.address = symbol.addr;
		this.symbol = symbol;
		this.kind = symbol.kind;
		this.spix = symbol.spix;
	}
}
