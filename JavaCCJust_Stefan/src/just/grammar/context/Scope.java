package just.grammar.context;

import just.grammar.semantics.Symbol;
import just.grammar.semantics.Symbol.Kind;

public class Scope {
	public Scope outer; // no scope is null
	public int level;
	public int nrOfParams;
	public int nrOfLocals;

	public Symbol locals;

	public Scope() {
	}

	public Scope(Scope outer, int level) {
		this.outer = outer;
		this.level = level;
	}
	
	public void insert(Symbol symbol) {
		if(locals != null) {
			symbol.next = locals;
		}
		
		if(symbol.kind.equals(Kind.parKind)) {
			++nrOfParams;
		}
		
		if(symbol.kind.equals(Kind.varKind)) {
			++nrOfLocals;
		}
		
		locals = symbol;
	}
}
