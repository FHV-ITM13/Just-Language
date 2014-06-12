package just.grammar.semantics;

import just.grammar.semantics.Symbol.Kind;


public class Scope {
	public Scope outer; // no scope is null
	public int level;
	public int localAddress;
	public int nrOfParams;
	public int nrOfLocals;
	public String name;
	
	public Symbol locals;

	public Scope(String name) {
		this(null, 0, name);
	}

	public Scope(Scope outer, int level, String name) {
		this.name = name;
		this.outer = outer;
		this.level = level;
		localAddress = nrOfLocals = nrOfParams = 0;
	}
	
	public void insert(Symbol symbol) {
		if(locals != null) {
			symbol.next = locals;
		}
		
		if(symbol.kind.equals(Kind.parKind)) {
			++nrOfParams;
			symbol.addr = localAddress++;
		}
		
		if(symbol.kind.equals(Kind.varKind)) {
			++nrOfLocals;
			symbol.addr = localAddress++;
		}
		
		locals = symbol;
	}

	public Symbol findSymbol(Integer spix) {
		Symbol tempLocal = locals;
		
		while(tempLocal != null) {
			if(spix.equals(tempLocal.spix)) {
				return tempLocal;
			}
			
			tempLocal = tempLocal.next;
		}
		
		return null;
	}
}
