package just.grammar.codegeneration.code;

import just.grammar.codegeneration.OpCode;

public class CodeLine {
	private OpCode opCode;
	private Object op;
	private Label label;

	public CodeLine(OpCode opCode) {
		this(opCode, null);
	}

	public CodeLine(OpCode opCode, Label label) {
		this.opCode = opCode;
		this.label = label;
	}

	public CodeLine(OpCode opCode, Object op) {
		this.opCode = opCode;
		this.op = op;
	}

	public OpCode getOpCode() {
		return opCode;
	}

	public void setOpCode(OpCode opCode) {
		this.opCode = opCode;
	}

	public Object getOp() {
		return op;
	}

	public void setOb(Object op) {
		this.op = op;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public void setOp(Object op) {
		this.op = op;
	}

	public boolean isReturnStatement() {
		return opCode == OpCode.IRETURN || opCode == OpCode.RETURN;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if(label != null)
		{
			sb.append("\t");
			sb.append(label);
			sb.append(": ");
		} else {
			sb.append("\t\t");
		}
		
		sb.append(opCode);

		if(op != null)
		{
			sb.append(" ");
			sb.append(op);
		}		
		return sb.toString();
	}
}
