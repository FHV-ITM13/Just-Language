package fhv.code;

public class CodeLine {
	private Object opCode;

	private Object[] op;

	public CodeLine(Object opCode, Object[] op) {
		this.opCode = opCode;
		this.op = op;
	}

	public boolean isReturn() {
		return this.opCode.toString().startsWith(Opcode.RETURN.name())
				|| this.opCode.toString().startsWith(Opcode.IRETURN.name());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (this.opCode.toString() == "") {
			return "";
		}
		builder.append(opCode.toString());
		for (Object op : this.op) {
			builder.append(" ");
			builder.append(op.toString());
		}
		builder.append('\n');

		return builder.toString();
	}
}
