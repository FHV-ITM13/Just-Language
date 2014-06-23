package JustVM.code;

import java.util.ArrayList;
import java.util.List;

public class Code {
	private List<String> code;
	private int lineCounter;
	
	public Code() {
		lineCounter = 0;
		code = new ArrayList<String>();
	}
	
	public Code(List<String> code) {
		this();
		this.code = code;
	}
	
	public int appedLine(String line) {
		code.add(line);		
		return lineCounter++;
	}
	
	public String getLine(int line) {
		return code.get(line);
	}
	
	public int getCodeLineNrByLabel(String label) {
		for (int i = 0; i < code.size(); i++) {
			if(code.get(i).equals(label)) {
				return i;
			}
		}
		
		return -1;
	}
}
