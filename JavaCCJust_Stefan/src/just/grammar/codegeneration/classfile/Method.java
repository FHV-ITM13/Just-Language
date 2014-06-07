package just.grammar.codegeneration.classfile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import just.grammar.codegeneration.classfile.constants.MethodRefConstant;

public class Method {
	private MethodRefConstant methodRef;
	
	
	public Method(MethodRefConstant constant) {
		setMethodRef(constant);
	}

	public MethodRefConstant getMethodRef() {
		return methodRef;
	}

	public void setMethodRef(MethodRefConstant methodRef) {
		this.methodRef = methodRef;
	}
 
	public Element writeXml(Document doc) {
		Element element = doc.createElement("method");
		element.appendChild(doc.createComment(methodRef.getNameAndType().getName().getBytes()));
		
		return element;
	}
}
