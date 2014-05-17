package fhv.classfile.constant;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MethodRefConstant extends Constant {
	private ClassConstant classIndex;

	private NameAndTypeConstant nameAndType;

	public MethodRefConstant(ClassConstant classIndex) {
		this.classIndex = classIndex;
	}

	public MethodRefConstant(ClassConstant classIndex,
			NameAndTypeConstant nameAndType) {
		this.classIndex = classIndex;
		this.nameAndType = nameAndType;
	}

	public ClassConstant getClassIndex() {
		return classIndex;
	}

	public NameAndTypeConstant getNameAndType() {
		return nameAndType;
	}

	@Override
	public boolean isCompleted() {
		return nameAndType != null;
	}

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("constant_methodref");
		element.setAttribute("id", this.getIndex() + "");

		Element clazz = doc.createElement("class_index");
		clazz.appendChild(doc.createTextNode("" + this.classIndex.getIndex()));
		Element nt = doc.createElement("name_and_type_index");
		nt.appendChild(doc.createTextNode("" + this.nameAndType.getIndex()));

		element.appendChild(clazz);
		element.appendChild(nt);
		
		return element;
	}
}
