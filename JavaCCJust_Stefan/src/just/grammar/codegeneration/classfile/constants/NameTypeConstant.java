package just.grammar.codegeneration.classfile.constants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class NameTypeConstant extends Constant {

	private UTF8Constant name;
	private UTF8Constant descriptor;

	public NameTypeConstant(Constant name, Constant descriptor) {
		this.name = (UTF8Constant) name;
		this.descriptor = (UTF8Constant) descriptor;
	}

	public UTF8Constant getDescriptor() {
		return descriptor;
	}

	public UTF8Constant getName() {
		return name;
	}

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("constant_name_and_type");
		element.setAttribute("index", this.getIndex() + "");
		element.appendChild(doc.createComment(name.getBytes() + ": " + descriptor.getBytes()));
		
		Element nameElem = doc.createElement("name_index");
		nameElem.appendChild(doc.createTextNode("" + name.getIndex()));
		Element typeElem = doc.createElement("descriptor_index");
		typeElem.appendChild(doc.createTextNode("" + descriptor.getIndex()));

		element.appendChild(nameElem);
		element.appendChild(typeElem);

		return element;
	}
}
