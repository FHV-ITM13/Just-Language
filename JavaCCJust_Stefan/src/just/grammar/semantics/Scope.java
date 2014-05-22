package just.grammar.semantics;

import just.grammar.semantics.Symbol.Kind;


public class Scope {
	public Scope outer; // no scope is null
	public int level;
	public int nrOfParams;
	public int nrOfLocals;
	public String name;

	public Symbol locals;

	public Scope(String name) {
		this.name = name;
	}

	public Scope(Scope outer, int level, String name) {
		this(name);
		this.outer = outer;
		this.level = level;
	}
	
	public void insert(Symbol symbol) {
		if(locals != null) {
			symbol.next = locals;
		}
		
		if(symbol.kind.equals(Kind.paramKind)) {
			++nrOfParams;
		}
		
		if(symbol.kind.equals(Kind.fieldKind)) {
			++nrOfLocals;
		}
		
		locals = symbol;
	}
}
