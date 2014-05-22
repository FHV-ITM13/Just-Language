package just.grammar.codegeneration.constant;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Constant {
	private int index = -1;
		
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public abstract Element writeXml(Document doc);
	
	public abstract boolean isCompleted();	
}