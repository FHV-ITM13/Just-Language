package just.grammar.codegeneration.classfile.constants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ClassConstant extends Constant {
	private UTF8Constant nameConst;

	public ClassConstant() {
	}

	public ClassConstant(Constant name) {
		this.nameConst = (UTF8Constant) name;
	}

	public UTF8Constant getName() {
		return nameConst;
	}

	public void setName(UTF8Constant name) {
		this.nameConst = name;
	}

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("constant_class");
		element.setAttribute("index", this.getIndex() + "");
		Element name = doc.createElement("name_index");
		name.appendChild(doc.createTextNode(String.valueOf(nameConst.getIndex())));
		element.appendChild(name);

		return element;
	}

}
