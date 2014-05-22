package just.grammar.codegeneration;

import java.util.LinkedList;

import just.grammar.semantics.Scope;

public class MethodCode {
	private LinkedList<CodeLine> lines = new LinkedList<>();
	private CodeLine last;
	private Scope scope;

	public MethodCode(Scope scope) {
		this.scope = scope;
	}

	public Scope getScope() {
		return scope;
	}

	public CodeLine addLine(CodeLine line) {
		this.lines.add(line);
		this.last = line;
		return line;
	}

	public CodeLine getLast() {
		return last;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append('\n');
		for (CodeLine line : this.lines) {
			builder.append(line.toString());
		}

		if (!lines.peekLast().isReturn()) {
			builder.append(new CodeLine(OpCode.RETURN, new Object[] {}));
			builder.append('\n');
		}

		return builder.toString();
	}
}
