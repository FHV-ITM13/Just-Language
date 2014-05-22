package just.grammar.codegeneration.constant;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FieldRefConstant extends Constant {
	private ClassConstant classIndex;

	private NameAndTypeConstant nameAndType;

	public FieldRefConstant(ClassConstant classIndex) {
		this.classIndex = classIndex;
	}

	public FieldRefConstant(ClassConstant classIndex,
			NameAndTypeConstant nameAndType) {
		this.classIndex = classIndex;
		this.nameAndType = nameAndType;
	}

	public ClassConstant getClassIndex() {
		return classIndex;
	}

	public void setNameAndType(NameAndTypeConstant nameAndType) {
		this.nameAndType = nameAndType;
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
		Element element = doc.createElement("constant_fieldref");
		element.setAttribute("id", this.getIndex() + "");
		element.appendChild(doc.createComment(this.nameAndType.getNameIndex().getBytes() + ": "
				+ this.nameAndType.getDescriptorIndex().getBytes()));

		Element clazz = doc.createElement("class_index");
		clazz.appendChild(doc.createTextNode("" + this.classIndex.getIndex()));
		Element nt = doc.createElement("name_and_type_index");
		nt.appendChild(doc.createTextNode("" + this.nameAndType.getIndex()));

		element.appendChild(clazz);
		element.appendChild(nt);
		
		return element;
	}
}
