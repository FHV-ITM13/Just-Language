package fhv.code;

import fhv.ParseException;
import fhv.code.fixup.Fixup;
import fhv.code.fixup.VarAddressFixup;
import fhv.code.fixup.VarOpcodeFixup;
import fhv.symbol.Kind;
import fhv.symbol.Symbol;

public class CodeGenerator {

	private MethodCode curCode;

	private int labelNumber = 1;

	public Descriptor getDescriptor(Symbol s) {
		return new Descriptor(s);
	}

	public void setCurrentCode(MethodCode code) {
		this.curCode = code;
	}

	public void load(Descriptor desc) throws ParseException {
		this.emit2(new VarOpcodeFixup(false, desc, this.curCode.getScope()),
				new VarAddressFixup(desc, this.curCode.getScope()));
	}

	public void save(Descriptor desc) throws ParseException {
		this.emit2(new VarOpcodeFixup(true, desc, this.curCode.getScope()),
				new VarAddressFixup(desc, this.curCode.getScope()));
	}

	public void falseJump(Opcode opCode, IfDescriptor desc) {
		desc.setLabel(this.createLabel());
		this.emit2(opCode, desc.getLabel());
	}

	public Label createLabel() {
		return new Label("L" + this.labelNumber++);
	}

	public void jump(Label label) {
		this.emit2(Opcode.GOTO, label);
	}

	public void markByLabel(Label label) {
		this.emit(Opcode.NOP).setLabel(label);
	}

	public CodeLine emit(Opcode opCode) {
		return this.curCode.addLine(new CodeLine(opCode, new Object[] {}));
	}

	public CodeLine emit2(Opcode opCode, Object op) {
		return this.curCode.addLine(new CodeLine(opCode, new Object[] { op }));
	}

	public CodeLine emit2(Opcode opCode, Fixup op) throws ParseException {
		if (op.fix()) {
			return this.emit2(opCode, op.getOp());
		} else {
			return this.curCode.addLine(new CodeLine(opCode,
					new Object[] { op }));
		}
	}

	public CodeLine emit2(Fixup opCode, Fixup op) throws ParseException {
		if (opCode.fix()) {
			return this.emit2((Opcode) opCode.getOp(), op);
		} else {
			return this.curCode.addLine(new CodeLine(opCode,
					new Object[] { op }));
		}
	}
}
