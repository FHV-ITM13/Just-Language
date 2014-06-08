package just.grammar.codegeneration.code;

import just.grammar.codegeneration.OpCode;

public class CodeLine {
	private OpCode opCode;
	private Object op;
	
	public CodeLine(OpCode opCode) {
		this(opCode, -1);
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
}
