package just.grammar.codegeneration.classfile;

import java.util.ArrayList;
import java.util.List;

import just.grammar.codegeneration.classfile.attribute.Attribute;
import just.grammar.codegeneration.classfile.attribute.CodeAttribute;
import just.grammar.codegeneration.constant.MethodRefConstant;

import org.w3c.dom.*;

public class Method {
	private MethodRefConstant methodRef;
	private List<Attribute> attributes;

	public Method(MethodRefConstant f) {
		this.methodRef = f;
		this.attributes = new ArrayList<>();
	}

	public Element writeXml(Document doc) {
		Element element = doc.createElement("method");
		element.appendChild(doc.createComment(this.methodRef.getNameAndType()
				.getNameIndex().getBytes()
				+ ": "
				+ this.methodRef.getNameAndType().getDescriptorIndex()
						.getBytes()));

		Element name = doc.createElement("name_index");
		name.appendChild(doc.createTextNode(""
				+ this.methodRef.getNameAndType().getNameIndex().getIndex()));
		Element desc = doc.createElement("descriptor_index");
		desc.appendChild(doc.createTextNode(""
				+ this.methodRef.getNameAndType().getDescriptorIndex()
						.getIndex()));

		element.appendChild(name);
		element.appendChild(desc);

		Element attr = doc.createElement("attribute_info");
		for (Attribute attribute : this.attributes) {
			Element e = attribute.writeXml(doc);
			attr.appendChild(e);
		}

		element.appendChild(attr);

		return element;
	}

	public void addAttribute(CodeAttribute attribute) {
		this.attributes.add(attribute);
	}
}
