package just.grammar.semantics;

import java.util.Iterator;
import java.util.LinkedList;

import just.grammar.context.Namelist;
import just.grammar.context.Scope;
import just.grammar.semantics.Symbol.Kind;

public class SymbolTable {
	public static SymbolTable SymbolTable = new SymbolTable();
	private LinkedList<Scope> scopes = new LinkedList<Scope>();
	
	private int curLevel;
	private Scope currScope;

	public SymbolTable() {
		curLevel = 0;
		currScope = new Scope();
		scopes.add(currScope);
	}

	public void enterScope() {
		++this.curLevel;

		currScope = new Scope(currScope, curLevel);
		scopes.add(currScope);		
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
		
		if(spix != null) {
			Symbol curr = currScope.locals;
			
			while(curr != null) {
				if(spix.equals(curr.spix)) {
					return curr;
				}
				
				curr = curr.next;
			}
			
			return null; //should never happen!
		}
				
		Integer newSpix = Namelist.NameList.insert(name);
		Symbol newSymbol = new Symbol(newSpix, Kind.undefKind);
		insert(newSymbol);
		
		return newSymbol;
	}
	
	public void printSymbols() {
		for (Iterator<Scope> iter : scopes.iterator()) {
			
		}
		
	}
	
	private void printSymbolsCore(Symbol sy) {			
		while(sy != null) {
		    System.out.println("Spix:" + sy.spix + " - " + Namelist.NameList.nameOf(sy.spix) + " - Level: " + sy.level);
		    
		    if(sy.symbols != null) {
		    	printSymbolsCore(sy.symbols);
		    }
		    
		    sy = sy.next;
		}
	}
}
