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
		sb.append('\n');
		
		for (CodeLine codeLine : codeLines) {
			sb.append(codeLine.getOpCode());
			
			if(codeLine.getOp() != null) {
				sb.append(" " + codeLine.getOp());
			}
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
