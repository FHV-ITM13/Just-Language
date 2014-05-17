package fhv.classfile.constant;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ValueConstant extends Constant {
	private String bytes;
	private String type;

	public ValueConstant(String bytes, String type) {
		this.bytes = bytes;
		this.type = type;
	}

	public String getBytes() {
		return bytes;
	}

	public String getType() {
		return type;
	}

	@Override
	public boolean isCompleted() {
		return true;
	}

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("constant_" + this.getType());
		element.setAttribute("id", this.getIndex() + "");

		Element bytes = doc.createElement("bytes");
		bytes.appendChild(doc.createTextNode(this.bytes));
		element.appendChild(bytes);
		
		return element;
	}
}
