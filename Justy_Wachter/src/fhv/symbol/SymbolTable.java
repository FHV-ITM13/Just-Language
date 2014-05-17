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

	public Scope enterScope(String identifier) {
		this.curLevel += 1;
		this.curScope = new Scope(this.curScope, this.curLevel, nameList,
				identifier);

		return this.curScope;
	}

	public Scope getCurScope() {
		return curScope;
	}

	public void leaveScope() {
		this.curLevel -= 1;
		this.curScope = this.curScope.getOuter();
	}

	public void insert(Symbol symbol) throws ParseException {
		this.curScope.insert(symbol);
	}

	public Symbol lookup(String name) throws ParseException {
		Integer id = this.nameList.lookup(name);
		
		return this.curScope.lookup(id);
	}
}
