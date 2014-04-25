package fhv.semantic.context;

import fhv.ParseException;
import fhv.semantic.NameList;
import fhv.semantic.Symbol;

public class Scope {
	public Scope(Scope outer, Integer level, String identifier) {
		super();
		this.outer = outer;
		this.level = level;
		this.identifier = identifier;

		numberOfLocals = numberOfParams = 0;
		locals = null;
	}

	public Scope outer;

	public Integer level;

	public Integer numberOfParams;

	public Integer numberOfLocals;

	public Symbol locals;

	public String identifier;

	public void insert(Symbol symbol) throws ParseException {
		if (this.hasSpix(symbol.spix)) {
			throw new ParseException("Duplicate definition '"
					+ NameList.nameList.getNameOf(symbol.spix) + "' at level "
					+ this.level + " (in Scope " + this.identifier + ")");
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

	private boolean hasSpix(Integer spix) {
		Symbol cur = this.locals;
		while (cur != null) {
			if (spix.equals(cur.spix)) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	public Symbol lookup(Integer spix) throws ParseException {
		Symbol cur = this.locals;
		while (cur != null) {
			if (spix.equals(cur.spix)) {
				return cur;
			}
			cur = cur.next;
		}
		throw new ParseException("Name not defined '"
				+ NameList.nameList.getNameOf(spix) + "' at level "
				+ this.level + " (in Scope '" + this.identifier + "')");
	}
}
