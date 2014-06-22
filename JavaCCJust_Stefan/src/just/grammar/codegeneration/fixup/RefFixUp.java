package just.grammar.codegeneration.fixup;

import just.grammar.codegeneration.Descriptor;
import just.grammar.semantics.Symbol;
import just.grammar.semantics.SymbolTable;

public class RefFixUp extends FixUp {

	public RefFixUp(Descriptor descriptor) {
		super(descriptor);
	}

	@Override
	public boolean fixUp() {
		Symbol symbol = SymbolTable.SymbolTable.findSymbolInScopeTree(descriptor.symbol.spix);
		
		if(symbol == null) return false;
		
		//update reference
		descriptor.symbol.addr = symbol.addr;
		
		return true;
	}

}
