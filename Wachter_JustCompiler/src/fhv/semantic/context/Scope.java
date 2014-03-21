package fhv.semantic.context;

import fhv.ParseException;
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

	public void insert(Symbol symbol) throws ParseException {
		if (this.lookup(symbol.spix) != null) {
			throw new ParseException("Duplicate definition '"
					+ Namelist.nameList.getNameOf(symbol.spix) + "' at level "
					+ this.level);
		}
		if (symbol.kind.equals(Symbol.Kind.parKind)) {
			this.numberOfParams += 1;
		} else if (symbol.kind.equals(Symbol.Kind.varKind)) {
			this.numberOfLocals += 1;
		}

		if (locals == null) {
			locals = symbol;
		} else {
			locals.insert(symbol, this.level);
		}
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
