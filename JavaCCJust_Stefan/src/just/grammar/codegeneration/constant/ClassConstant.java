package just.grammar.codegeneration.constant;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ClassConstant extends Constant {
	private UTF8Constant nameIndex;

	public ClassConstant() {
	}

	public ClassConstant(Constant name) {
		this.nameIndex = (UTF8Constant) name;
	}

	public UTF8Constant getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(UTF8Constant nameIndex) {
		this.nameIndex = nameIndex;
	}

	@Override
	public boolean isCompleted() {
		return nameIndex != null;
	}

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("constant_class");
		element.setAttribute("id", this.getIndex() + "");
		Element name = doc.createElement("name_index");
		name.appendChild(doc.createTextNode(this.nameIndex.getIndex() + ""));
		element.appendChild(name);

		return element;
	}
}
