package fhv.code;

public class CodeLine {
	private Object opCode;

	private Object[] op;

	private Label label;

	public CodeLine(Object opCode, Object[] op) {
		this.opCode = opCode;
		this.op = op;
	}

	public void setLabel(Label label) {
		this.label = label;
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
		if (this.label != null) {
			builder.append(this.label);
			builder.append(": ");
		}else{
			builder.append("    ");
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
