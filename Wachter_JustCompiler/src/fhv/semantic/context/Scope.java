package fhv.semantic.context;

import fhv.semantic.Symbol;

public class Scope {
	public Scope(Scope outer, Integer level) {
		super();
		this.outer = outer;
		this.level = level;

		numberOfLocals = numberOfParams = 0;

		locals = null;
	}

	public Scope outer;

	public Integer level;

	public Integer numberOfParams;

	public Integer numberOfLocals;

	public Symbol locals;

	public void insert(Symbol symbol) {
		if (symbol.type.equals(Symbol.Kind.parKind)) {
			this.numberOfParams += 1;
		} else if (symbol.type.equals(Symbol.Kind.varKind)) {
			this.numberOfLocals += 1;
		}

		locals.insert(symbol, this.level);
	}

	public Symbol lookup(Integer spix) {
		Symbol cur = this.locals;
		while (cur != null) {
			if (spix.equals(cur.spix)) {
				return cur;
			}
			cur = cur.next;
		}
		return null;
	}
}
