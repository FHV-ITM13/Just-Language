package just.grammar.codegeneration.code;

import java.util.ArrayList;
import java.util.List;

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
		
		for (CodeLine codeLine : codeLines) {
			sb.append(codeLine.getOpCode());
			sb.append(System.getProperty("line.separator"));
		}
		
		return sb.toString();
	}
}
