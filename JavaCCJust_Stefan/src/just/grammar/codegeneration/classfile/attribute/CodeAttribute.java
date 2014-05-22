package just.grammar.codegeneration.classfile.attribute;

import just.grammar.codegeneration.MethodCode;
import just.grammar.codegeneration.constant.UTF8Constant;
import just.grammar.semantics.Scope;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CodeAttribute extends Attribute {

	private UTF8Constant codeConstant;

	private Scope scope;

	private MethodCode code;

	public CodeAttribute(UTF8Constant codeConstant, Scope scope) {
		this.codeConstant = codeConstant;
		this.scope = scope;
		this.code = new MethodCode(scope);
	}

	public MethodCode getCode() {
		return code;
	}

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("attribute");

		Element attrName = doc.createElement("attribute_name_index");
		attrName.appendChild(doc.createTextNode(this.codeConstant.getIndex()
				+ ""));

		Element stack = doc.createElement("max_stack");
		stack.appendChild(doc.createTextNode("10"));

		Element locals = doc.createElement("max_locals");
		locals.appendChild(doc.createTextNode(String.valueOf(scope.nrOfLocals + scope.nrOfParams)));

		Element code = doc.createElement("code");
		code.appendChild(doc.createTextNode(this.code.toString()));

		Element info = doc.createElement("info");
		info.appendChild(stack);
		info.appendChild(locals);
		info.appendChild(code);

		element.appendChild(attrName);
		element.appendChild(info);
		return element;
	}
}
