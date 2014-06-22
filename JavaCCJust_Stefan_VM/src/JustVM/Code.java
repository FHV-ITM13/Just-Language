package JustVM;

public class Code {
	private static String NewLine = "\n";
	
	private String code;
	private int lineCounter;
	
	public Code() {
		lineCounter = 0;
	}
	
	public Code(String code) {
		this();
		this.code = code;
	}
	
	public int appedLine(String line) {
		code += line;
		
		if(!code.endsWith(NewLine))
		{
			code += NewLine;
		}
		
		return lineCounter++;
	}
}
