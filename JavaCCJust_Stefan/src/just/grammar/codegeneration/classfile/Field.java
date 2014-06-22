package just.grammar.codegeneration.classfile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import just.grammar.codegeneration.classfile.constants.FieldRefConstant;

public class Field {
	private FieldRefConstant fieldRef;

	public Field(FieldRefConstant constant) {
		fieldRef = constant;
	}

	public Element writeXml(Document doc) {
		Element element = doc.createElement("field");
		element.appendChild(doc.createComment(fieldRef.getNameAndType().getName().getBytes()));
		
		Element nameIndex = doc.createElement("name_index");
		nameIndex.appendChild(doc.createTextNode(String.valueOf(fieldRef.getNameAndType().getName().getIndex())));
		element.appendChild(nameIndex);

		Element descriptorIndex = doc.createElement("descriptor_index");
		descriptorIndex.appendChild(doc.createTextNode(String.valueOf(fieldRef.getNameAndType().getDescriptor().getIndex())));
		element.appendChild(descriptorIndex);

		return element;
	}
}
