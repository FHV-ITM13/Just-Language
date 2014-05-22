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
	IF_ICMPEQ,// <branchoffset16>
	IF_ICMPNE,// <branchoffset16>
	IF_ICMPLT,// <branchoffset16>
	IF_ICMPGE,// <branchoffset16>
	IF_ICMPGT,// <branchoffset16>
	IF_ICMPLE,// <branchoffset16>
	GOTO,// <branchoffset16>
	INVOKESTATIC,// <index16>
	RETURN,
	IRETURN,
	ALOAD,// <index8>
	ASTORE,// <index8>
	NEWARRAY,// <index16>
	IALOAD,
	IASTORE
}