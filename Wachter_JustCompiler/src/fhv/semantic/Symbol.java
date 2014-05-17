package fhv.semantic;

public class Symbol {
	public enum Kind {
		undefKind, constKind, varKind, parKind, funcKind, systemFuncKind, fieldKind
	};

	// naming
	public int spix;
	public Kind kind;

	// data type
	public Type type;

	// vars
	public boolean init;
	public int val;
	public int addr;

	// functions
	public boolean defined;
	public Symbol symbols;

	public Integer scopeLevel;
	public Symbol next;

	public Symbol(int spix, Kind kind) {
		super();
		this.spix = spix;
		this.kind = kind;
	}

	public void insert(Symbol symbol, Integer scopeLevel) {
		if (this.next != null) {
			this.next.insert(symbol, scopeLevel);
		} else {
			this.next = symbol;
			symbol.scopeLevel = scopeLevel;
		}
	}
}
