package just.grammar.semantics;

import java.util.LinkedList;

import just.grammar.semantics.Symbol.Kind;

public class SymbolTable {
	public static SymbolTable SymbolTable = new SymbolTable();
	private LinkedList<Scope> scopes = new LinkedList<Scope>();
	
	private int curLevel = -1;
	private Scope currScope = null;

	private SymbolTable() {
	}

	public Scope getCurrentScope() {
		return currScope;
	}
	
	public void enterScope(String name) {
		++this.curLevel;

		currScope = new Scope(currScope, curLevel, name);
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
		Integer spix = NameList.NameList.spixOf(name);
		
		if(spix != null) {
			Symbol scopeSymbol = findSymbolInScopeTree(spix);
			
			if(scopeSymbol != null) {	
				System.out.println("INFO: SymbolTable lookup: " + name + " exists in this or upper scope!");
				return scopeSymbol;
			}
			
			System.err.println("WARNING: SymbolTable lookup: " + name + " not in current scope but in NameList!");
		}
				
		return null;
	}
	
	private Symbol findSymbolInScopeTree(Integer spix) {
		Scope scope = currScope;
		
		while(scope != null) {
			Symbol curr = scope.locals;

			while(curr != null) {
				if(spix.equals(curr.spix)) {
					return curr;
				}
				
				curr = curr.next;
			}
		
			scope = scope.outer;
		}
		
		return null;
	}

	public void printScopes() {		
		for (Scope scope : scopes) {
			System.out.println("Level " + scope.level + ": " + scope.name);
			printSymbols(scope.locals);
		}
	}
	
	private void printSymbols(Symbol sy) {		
		if(sy == null) {
			System.err.println("WARNING: PrintSymbols: Symbol null - are there any Symbols in this Scope?");
			return;
		}
		
		System.out.println("Spix:" + sy.spix + " - " + NameList.NameList.nameOf(sy.spix) + " - Type:" + sy.type + " - Kind: " + sy.kind);

		if (sy.next != null) {
			printSymbols(sy.next);
		}
	}
}
