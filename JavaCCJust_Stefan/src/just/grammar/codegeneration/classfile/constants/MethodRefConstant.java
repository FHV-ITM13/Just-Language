package just.grammar.codegeneration.classfile.constants;

import just.grammar.codegeneration.code.MethodCode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MethodRefConstant extends Constant {
	private ClassConstant classIndex;

	private NameTypeConstant nameAndType;

	private MethodCode methodCode;
	
	public MethodRefConstant(ClassConstant classIndex, NameTypeConstant nameAndType) {
		this.classIndex = classIndex;
		this.nameAndType = nameAndType;
	}

	public ClassConstant getClassIndex() {
		return classIndex;
	}

	public void setNameAndType(NameTypeConstant nameAndType) {
		this.nameAndType = nameAndType;
	}

	public NameTypeConstant getNameAndType() {
		return nameAndType;
	}
	
	public MethodCode getMethodCode() {
		return methodCode;
	}

	public void setMethodCode(MethodCode methodCode) {
		this.methodCode = methodCode;
	}
	
	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("constant_methodref");
		element.setAttribute("index", String.valueOf(getIndex()));
		element.appendChild(doc.createComment(nameAndType.getName().getBytes() + ": " + nameAndType.getDescriptor().getBytes()));
		Element clazz = doc.createElement("class_index");
		clazz.appendChild(doc.createTextNode(String.valueOf(classIndex.getIndex())));
		Element nt = doc.createElement("name_and_type_index");
		nt.appendChild(doc.createTextNode(String.valueOf(nameAndType.getIndex())));

		element.appendChild(clazz);
		element.appendChild(nt);
		
		return element;
	}
}
