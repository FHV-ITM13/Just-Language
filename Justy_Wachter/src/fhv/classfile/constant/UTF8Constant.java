package fhv.classfile.constant;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UTF8Constant extends Constant {

	private String bytes;

	public UTF8Constant(String bytes) {
		this.bytes = bytes;
	}

	public String getBytes() {
		return bytes;
	}

	@Override
	public boolean isCompleted() {
		return true;
	}

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("constant_utf8");
		element.setAttribute("id", this.getIndex() + "");
		Element bytes = doc.createElement("bytes");
		bytes.appendChild(doc.createTextNode(this.bytes));
		element.appendChild(bytes);
		
		return element;
	}
}
