package fhv.symbol;

import fhv.ParseException;

public class Scope {
	private Scope outer;
	private Integer level;
	private Integer numberOfParams;
	private Integer numberOfLocals;
	private String identifier;
	private Symbol locals;
	private NameList nameList;
	
	public Scope(Scope outer, Integer level, NameList nameList, String identifier) {
		super();
		this.outer = outer;
		this.level = level;
		this.identifier = identifier;
		this.nameList = nameList;

		numberOfLocals = numberOfParams = 0;
	}
	
	public Scope getOuter() {
		return outer;
	}

	public void insert(Symbol symbol) throws ParseException {
		if (this.hasSymbol(symbol.getId())) {
			throw new ParseException("Duplicate definition '"
					+ symbol.getName() + "' at level "
					+ this.level + " (in Scope " + this.identifier + ")");
		}
		
		if (symbol.getKind().equals(Kind.paramKind)) {
			this.numberOfParams += 1;
		} else if (symbol.getKind().equals(Kind.varKind)) {
			this.numberOfLocals += 1;
		}

		if (locals == null) {
			locals = symbol;
		} else {
			locals.insert(symbol, this.level);
		}
	}

	private boolean hasSymbol(Integer id) {
		Symbol cur = this.locals;
		while (cur != null) {
			if (id.equals(cur.getId())) {
				return true;
			}
			cur = cur.getNext();
		}
		return false;
	}

	public Symbol lookup(Integer id) throws ParseException {
		Symbol cur = this.locals;
		while (cur != null) {
			if (id.equals(cur.getId())) {
				return cur;
			}
			cur = cur.getNext();
		}
		throw new ParseException("Name not defined '"
				+ this.nameList.getNameOf(id) + "' at level "
				+ this.level + " (in Scope '" + this.identifier + "')");
	}
}
