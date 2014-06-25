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
		label += ":"; //anchor label has colon at the end
		//otherwise it is possible to also find jump labels
		
		for (int i = 0; i < code.size(); i++) {
			if(code.get(i).contains(label)) {
				return i;
			}
		}
		
		return -1;
	}
}
