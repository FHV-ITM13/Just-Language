package just.grammar.codegeneration.classfile;

import just.grammar.codegeneration.classfile.constants.UTF8Constant;
import just.grammar.codegeneration.code.MethodCode;
import just.grammar.semantics.Scope;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Attribute {

	private UTF8Constant nameIndex;
	private Scope scope;
	private MethodCode methodCode;
	
	public Attribute(UTF8Constant nameIndex, Scope scope) {
		this.nameIndex = nameIndex;
		this.scope = scope;
		methodCode = new MethodCode();
	}
	
	public Scope getScope() {
		return scope;
	}
	
	public UTF8Constant getNameIndex() {
		return nameIndex;
	}
	
	public void setNameIndex(UTF8Constant nameIndex) {
		this.nameIndex = nameIndex;
	}
	
	public MethodCode getMethodCode() {
		return methodCode;
	}

	public void setMethodCode(MethodCode methodCode) {
		this.methodCode = methodCode;
	}

	public Element writeXml(Document doc) {
		Element element = doc.createElement("attribute");
		
		Element attNameIndex = doc.createElement("attribute_name_index");
		attNameIndex.appendChild(doc.createTextNode(String.valueOf(nameIndex.getIndex())));
		
		element.appendChild(attNameIndex);
		
		Element maxStack = doc.createElement("max_stack");
		maxStack.appendChild(doc.createTextNode("1"));

		Element maxLocals = doc.createElement("max_locals");
		maxLocals.appendChild(doc.createTextNode(String.valueOf((scope.nrOfLocals + scope.nrOfParams))));
		
		Element code = doc.createElement("code");
		code.appendChild(doc.createTextNode(methodCode.toString()));

		Element info = doc.createElement("info");
		info.appendChild(maxStack);
		info.appendChild(maxLocals);
		info.appendChild(code);
		
		element.appendChild(info);

		return element;
	}
}
