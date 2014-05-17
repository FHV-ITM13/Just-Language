package fhv.symbol;

import fhv.classfile.constant.Constant;

public class Symbol {
	private int id;
	private String name;
	private Kind kind;
	private Symbol next;
	private int scopeLevel;
	private Type type;
	private Constant constant;
	private int address = -1;

	public Symbol(int id, String name, Kind kind) {
		this.id = id;
		this.name = name;
		this.kind = kind;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Kind getKind() {
		return kind;
	}

	public Symbol getNext() {
		return next;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getScopeLevel() {
		return scopeLevel;
	}

	public void setConstant(Constant constant) {
		this.constant = constant;
	}

	public Constant getConstant() {
		return constant;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public int getAddress() {
		return address;
	}

	public void insert(Symbol symbol, int scopeLevel) {
		if (this.next != null) {
			this.next.insert(symbol, scopeLevel);
		} else {
			this.next = symbol;
			symbol.scopeLevel = scopeLevel;
		}
	}
}
