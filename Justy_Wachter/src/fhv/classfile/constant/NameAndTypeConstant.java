package fhv.classfile.constant;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class NameAndTypeConstant extends Constant {

	private UTF8Constant nameIndex;
	private UTF8Constant descriptorIndex;

	public NameAndTypeConstant(Constant nameIndex, Constant descriptorIndex) {
		this.nameIndex = (UTF8Constant) nameIndex;
		this.descriptorIndex = (UTF8Constant) descriptorIndex;
	}

	public UTF8Constant getDescriptorIndex() {
		return descriptorIndex;
	}

	public UTF8Constant getNameIndex() {
		return nameIndex;
	}

	@Override
	public boolean isCompleted() {
		return true;
	}

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("constant_name_and_type");
		element.setAttribute("id", this.getIndex() + "");
		element.appendChild(doc.createComment(this.nameIndex.getBytes() + ": "
				+ this.descriptorIndex.getBytes()));

		Element name = doc.createElement("name_index");
		name.appendChild(doc.createTextNode("" + this.nameIndex.getIndex()));
		Element desc = doc.createElement("descriptor_index");
		desc.appendChild(doc.createTextNode(""
				+ this.descriptorIndex.getIndex()));

		element.appendChild(name);
		element.appendChild(desc);

		return element;
	}
}
