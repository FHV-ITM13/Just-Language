package fhv.code.fixup;

import fhv.ParseException;
import fhv.code.Descriptor;
import fhv.code.Opcode;
import fhv.symbol.Scope;
import fhv.symbol.Symbol;

public class VarOpcodeFixup extends Fixup {

	private boolean save;

	private Descriptor desc;

	private Scope scope;

	public VarOpcodeFixup(boolean save, Descriptor desc, Scope scope) {
		this.save = save;
		this.desc = desc;
		this.scope = scope;
	}

	@Override
	public boolean fix() throws ParseException {
		Symbol s = this.scope.lookup(this.desc.getId());
		if (s != null && s.getAddress() != -1) {
			switch (s.getKind()) {
			case paramKind:
				this.op = save ? Opcode.ISTORE : Opcode.ILOAD;
				break;
			case localKind:
				this.op = save ? Opcode.ISTORE : Opcode.ILOAD;
				break;
			case fieldKind:
				this.op = save ? Opcode.PUTSTATIC : Opcode.GETSTATIC;
				break;
			default:
			case stackKind:
				if (save) {
					return false;
				} else {
					this.op = "";
				}
				break;
			}

			return true;
		}
		return false;
	}

}
