package fhv.symbol;

import java.util.ArrayList;
import java.util.List;

public class MethodSymbol extends Symbol {
	private List<Symbol> params;

	public MethodSymbol(int id, String name, Kind kind) {
		super(id, name, kind);

		this.params = new ArrayList<>();
	}

	public void addParam(Symbol s) {
		this.params.add(s);
	}

	public Symbol[] getParams() {
		return this.params.toArray(new Symbol[0]);
	}

	public String getParamTypes() {
		StringBuilder paramTypes = new StringBuilder();
		int i = 0;
		for (Symbol symbol : this.getParams()) {
			paramTypes.append(symbol.getType().getShortName());
			paramTypes.append(',');
		}
		//remove last ','
		if (paramTypes.length() > 0) {
			paramTypes.setLength(paramTypes.length() - 1);
		}
		
		return paramTypes.toString();
	}
}
