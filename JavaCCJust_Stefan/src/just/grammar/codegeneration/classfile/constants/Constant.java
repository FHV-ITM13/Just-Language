package just.grammar.codegeneration.classfile.constants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Constant {
	private int index;
	
	public Constant() {
		this.index = -1;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public abstract Element writeXml(Document doc);
}
