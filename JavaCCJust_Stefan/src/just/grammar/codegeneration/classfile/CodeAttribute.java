package just.grammar.codegeneration.classfile;

import just.grammar.codegeneration.classfile.constants.UTF8Constant;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CodeAttribute {

	private UTF8Constant nameIndex;
	
	public CodeAttribute(UTF8Constant nameIndex) {
		this.nameIndex = nameIndex;
	}
	
	public UTF8Constant getNameIndex() {
		return nameIndex;
	}
	
	public void setNameIndex(UTF8Constant nameIndex) {
		this.nameIndex = nameIndex;
	}
	
	public Element writeXml(Document doc) {
		Element element = doc.createElement("attribute");
		
		Element attNameIndex = doc.createElement("attribute_name_index");
		attNameIndex.appendChild(doc.createTextNode("" + nameIndex.getIndex()));
		
		element.appendChild(attNameIndex);
		
		Element maxStack = doc.createElement("max_stack");
		Element maxLocals = doc.createElement("max_locals");
		Element code = doc.createElement("code");

		Element info = doc.createElement("info");
		info.appendChild(maxStack);
		info.appendChild(maxLocals);
		info.appendChild(code);
		
		element.appendChild(info);

		return element;
	}
}
