package fhv.classfile.attribute;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fhv.classfile.constant.UTF8Constant;
import fhv.symbol.Scope;

public class CodeAttribute extends Attribute {

	private UTF8Constant codeConstant;

	private Scope scope;

	public CodeAttribute(UTF8Constant codeConstant, Scope scope) {
		this.codeConstant = codeConstant;
		this.scope = scope;
	}

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("attribute");
		
		Element attrName = doc.createElement("attribute_name_index");
		attrName.appendChild(doc.createTextNode(this.codeConstant.getIndex()+""));
		
		Element stack = doc.createElement("max_stack");
		stack.appendChild(doc.createTextNode("10"));
		
		Element locals = doc.createElement("max_locals");
		locals.appendChild(doc.createTextNode(String.valueOf(scope.getNumberOfLocals())));
		
		Element code = doc.createElement("code");
		code.appendChild(doc.createComment("TODO add code"));
		
		Element info = doc.createElement("info");
		info.appendChild(stack);
		info.appendChild(locals);
		info.appendChild(code);
		
		element.appendChild(attrName);
		element.appendChild(info);
		return element;
	}
}
