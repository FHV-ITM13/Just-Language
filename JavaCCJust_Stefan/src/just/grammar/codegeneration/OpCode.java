package just.grammar.codegeneration;

public enum OpCode {
	NOP,
	POP,
	DUP,
	LDC_W, //<index16>
	ISTORE,// <index8>
	ILOAD,// <index8>
	PUTSTATIC,// <index8>
	GETSTATIC,// <index8>
	
	IADD,
	ISUB,
	IMUL,
	IDIV,
	IAND,
	IOR,
	INEG,
	
	IF_ICMPEQ,// <branchoffset16> compare equal
	IF_ICMPNE,// <branchoffset16> compare not equal
	IF_ICMPGE,// <branchoffset16> value2 is less than value1
	IF_ICMPGT,// <branchoffset16> value2 is less than or equal to value1
	IF_ICMPLE,// <branchoffset16> value2 is greater than value1
	IF_ICMPLT,// <branchoffset16> value2 is greater than or equal to value1

	
	GOTO,// <branchoffset16>
	INVOKESTATIC,// <index16>
	RETURN,
	IRETURN,
}
