package fhv.classfile.constant;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fhv.classfile.Method;
import fhv.code.MethodCode;

public class MethodRefConstant extends Constant {
	private ClassConstant classIndex;

	private NameAndTypeConstant nameAndType;

	private Method method;

	private MethodCode code;

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

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public MethodCode getCode() {
		return code;
	}

	public void setCode(MethodCode code) {
		this.code = code;
	}

	@Override
	public boolean isCompleted() {
		return nameAndType != null;
	}

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("constant_methodref");
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
