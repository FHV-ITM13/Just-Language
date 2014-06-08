package just.grammar.semantics;

import java.util.ArrayList;
import java.util.List;

public class MethodSymbol extends Symbol {

	private List<Symbol> params;
	
	public MethodSymbol(int spix) {
		this(spix, Type.undefType);		
	}
	
	public MethodSymbol(int spix, Type type) {
		super(spix, Kind.funcKind, type);
		params = new ArrayList<Symbol>();
	}
	
	public List<Symbol> getParams() {
		return params;
	}

	public void addParam(Symbol s) {
		params.add(s);
	}

	public String getParamsAsString() {
		StringBuilder sb = new StringBuilder();
		
		for (Symbol s : params) {
			sb.append(s.type);
			sb.append(",");
		}
		
		//remove last comma
		if(sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1 );
		}
		
		return sb.toString();
	}
}
