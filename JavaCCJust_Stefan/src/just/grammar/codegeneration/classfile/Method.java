package just.grammar.codegeneration.classfile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import just.grammar.codegeneration.classfile.constants.MethodRefConstant;

public class Method {
	private MethodRefConstant methodRef;
	private Attribute codeAttribute;
	
	public Method(MethodRefConstant constant, Attribute codeAttribute) {
		setMethodRef(constant);
		
		this.codeAttribute = codeAttribute;
	}
	
	public Attribute getAttribute() {
		return codeAttribute;
	}

	public void setAttribute(Attribute codeAttr) {
		codeAttribute = codeAttr;
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
		
		Element nameIndex = doc.createElement("name_index");
		nameIndex.appendChild(doc.createTextNode("" + methodRef.getNameAndType().getName().getIndex()));
		element.appendChild(nameIndex);
		
		Element descriptorIndex = doc.createElement("descriptor_index");
		descriptorIndex.appendChild(doc.createTextNode("" + methodRef.getNameAndType().getDescriptor().getIndex()));
		element.appendChild(descriptorIndex);
		
		Element attributeInfo = doc.createElement("attribute_info");		
		
		attributeInfo.appendChild(codeAttribute.writeXml(doc));		
		
		element.appendChild(attributeInfo);
		return element;
	}
}
