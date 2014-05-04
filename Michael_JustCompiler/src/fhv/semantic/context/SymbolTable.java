package fhv.semantic.context;

import fhv.ParseException;
import fhv.semantic.NameList;
import fhv.semantic.Symbol;

public class SymbolTable {
	public static SymbolTable symbolTable = new SymbolTable();

	private SymbolTable() {
		curLevel = 0;
		curScope = new Scope(null, curLevel, "root");
	}

	private Integer curLevel;
	private Scope curScope;

	public void enterScope(String identifier) {
		this.curLevel += 1;
		this.curScope = new Scope(this.curScope, this.curLevel, identifier);
	}

	public void leaveScope() {
		this.curLevel -= 1;
		this.curScope = this.curScope.outer;
	}

	public void insert(Symbol symbol) throws ParseException {
		this.curScope.insert(symbol);
	}

	public Symbol lookup(String name) throws ParseException {
		Scope cur = this.curScope;
		Integer spix = NameList.nameList.lookup(name);
		ParseException inner = null;
		while (cur != null) {
			try{
				Symbol symbol = cur.lookup(spix);
				if (symbol != null) {
					return symbol;
				}
			}catch(ParseException ex){
				if(inner == null){inner = ex;}	
			}
			cur = cur.outer;
		}
		throw inner;
	}
	
	public boolean isCurrentScopeProgram(){
		if(this.curScope.outer.identifier.equals("root")) {
			return true;
		}
		
		return false;
	}
}
