package just.grammar.codegeneration.fixup;

import just.grammar.codegeneration.Descriptor;
import just.grammar.semantics.NameList;

public abstract class FixUp {
	protected Descriptor descriptor;

	public FixUp(Descriptor descriptor) {
		this.descriptor = descriptor;
	}

	public Descriptor getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(Descriptor descriptor) {
		this.descriptor = descriptor;
	}

	public abstract boolean fixUp();
	
	@Override
	public String toString() {
		if(descriptor.getAddress() != -1 || fixUp()) {
			return String.valueOf(descriptor.getAddress());
		}
		
		throw new RuntimeException("Could not fix up reference to spix " 
					+ descriptor.symbol.spix
					+ ": '" + NameList.NameList.nameOf(descriptor.symbol.spix) + "'");
	}
}
