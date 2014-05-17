package fhv.code.fixup;

import fhv.ParseException;
import fhv.code.Descriptor;
import fhv.symbol.Scope;
import fhv.symbol.Symbol;

public class VarAddressFixup extends Fixup {

	private Descriptor desc;

	private Scope scope;

	public VarAddressFixup(Descriptor desc, Scope scope) {
		this.desc = desc;
		this.scope = scope;
	}

	@Override
	public boolean fix() throws ParseException {
		Symbol s = this.scope.lookup(this.desc.getId());
		if (s != null && s.getAddress() != -1) {
			this.op = s.getAddress();
			return true;
		}
		return false;
	}
}
