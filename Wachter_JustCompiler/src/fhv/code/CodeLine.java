package fhv.code;

import fhv.code.fixup.Fixup;

public class CodeLine {
	private OptCode opt;
	private Fixup[] fixups;

	public CodeLine(OptCode opt, Fixup[] fixups) {
		this.opt = opt;
		this.fixups = fixups;
	}

	public Fixup[] getFixups() {
		return fixups;
	}

	public OptCode getOpt() {
		return opt;
	}
}
