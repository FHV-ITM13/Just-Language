package fhv.symbol;

import fhv.ParseException;

public class SymbolTable {

	private Integer curLevel;
	private Scope curScope;
	private NameList nameList;

	public SymbolTable(NameList nameList) {
		this.curLevel = 0;
		this.curScope = new Scope(null, curLevel, nameList, "root");
		this.nameList = nameList;
	}

	public void enterScope(String identifier) {
		this.curLevel += 1;
		this.curScope = new Scope(this.curScope, this.curLevel, nameList, identifier);
	}

	public void leaveScope() {
		this.curLevel -= 1;
		this.curScope = this.curScope.getOuter();
	}

	public void insert(Symbol symbol) throws ParseException {
		this.curScope.insert(symbol);
	}

	public Symbol lookup(String name) throws ParseException {
		Scope cur = this.curScope;
		Integer id = this.nameList.lookup(name);
		ParseException inner = null;
		while (cur != null) {
			try {
				Symbol symbol = cur.lookup(id);
				if (symbol != null) {
					return symbol;
				}
			} catch (ParseException ex) {
				if (inner == null) {
					inner = ex;
				}
			}
			cur = cur.getOuter();
		}
		throw inner;
	}
}
