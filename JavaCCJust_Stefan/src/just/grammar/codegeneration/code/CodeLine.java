package just.grammar.codegeneration.code;

import just.grammar.codegeneration.OpCode;

public class CodeLine {
	private OpCode opCode;
	
	public CodeLine(OpCode opCode) {
		this.opCode = opCode;
	}
	
	public OpCode getOpCode() {
		return opCode;
	}
	public void setOpCode(OpCode opCode) {
		this.opCode = opCode;
	}
}
