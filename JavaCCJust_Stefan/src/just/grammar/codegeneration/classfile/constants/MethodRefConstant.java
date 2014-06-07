package just.grammar.codegeneration.classfile.constants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MethodRefConstant extends Constant {
	private ClassConstant classIndex;

	private NameTypeConstant nameAndType;

	public MethodRefConstant(ClassConstant classIndex) {
		this.classIndex = classIndex;
	}

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

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("constant_methodref");
		element.setAttribute("index", getIndex() + "");
		element.appendChild(doc.createComment(nameAndType.getName().getBytes() + ": " + nameAndType.getDescriptor().getBytes()));
		Element clazz = doc.createElement("class_index");
		clazz.appendChild(doc.createTextNode("" + classIndex.getIndex()));
		Element nt = doc.createElement("name_and_type_index");
		nt.appendChild(doc.createTextNode("" + nameAndType.getIndex()));

		element.appendChild(clazz);
		element.appendChild(nt);
		
		return element;
	}
}
