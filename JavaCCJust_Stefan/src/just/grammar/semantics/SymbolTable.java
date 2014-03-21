package just.grammar.semantics;

import just.grammar.context.Namelist;
import just.grammar.context.Scope;

public class SymbolTable {
	public static SymbolTable SymbolTable = new SymbolTable();

	private int curLevel;
	private Scope currScope;

	public SymbolTable() {
		curLevel = 0;
		currScope = new Scope();
	}

	public void enterScope() {
		++this.curLevel;

		currScope = new Scope(currScope, curLevel);
	}

	public void leaveScope() {
		currScope = currScope.outer;
		--this.curLevel;
	}

	public void insert(Symbol symbol) {
		if (symbol != null) {
			currScope.insert(symbol);
		}
	}

	public Symbol lookup(String name) {
		Integer spix = Namelist.NameList.spixOf(name);
		Symbol curr = currScope.locals;
		
		while(curr != null) {
			if(spix.equals(curr.spix)) {
				return curr;
			}
			
			curr = curr.next;
		}
		
		return null;
	}
	
	public void printSymbols() {
		Symbol curr = currScope.locals;
		
		while(curr != null) {
		    System.out.println(curr.spix + " - " + Namelist.NameList.nameOf(curr.spix));
		}
	}
}
