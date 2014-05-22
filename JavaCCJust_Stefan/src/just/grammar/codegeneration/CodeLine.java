package just.grammar.codegeneration;

public class CodeLine {
	private OpCode opCode;

	private Object[] op;

	private Label label;

	public CodeLine(OpCode opCode, Object[] op) {
		this.opCode = opCode;
		this.op = op;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public boolean isReturn() {
		return opCode.equals(OpCode.RETURN) || opCode.equals(OpCode.IRETURN);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (this.opCode.name().equals("")) {
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
