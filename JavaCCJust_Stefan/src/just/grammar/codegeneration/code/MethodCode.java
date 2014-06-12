package just.grammar.codegeneration.code;

import java.util.ArrayList;
import java.util.List;

import just.grammar.codegeneration.OpCode;

public class MethodCode {
	private List<CodeLine> codeLines;
	
	public MethodCode() {
		codeLines = new ArrayList<CodeLine>();
	}
	
	public List<CodeLine> getCodeLines() {
		return codeLines;
	}
	
	public void addCodeLine(CodeLine codeLine) {
		codeLines.add(codeLine);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('\n');

		for (CodeLine codeLine : codeLines) {
			sb.append("				");
			sb.append(codeLine);
			sb.append("\n");
		}
		
		if(codeLines.size() == 0 || !codeLines.get(codeLines.size() - 1).isReturnStatement()) {
			sb.append("				");
			sb.append(new CodeLine(OpCode.RETURN));
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
