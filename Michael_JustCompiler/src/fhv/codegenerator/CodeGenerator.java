package fhv.codegenerator;

import fhv.xml.writer.XMLClassWriter;

public class CodeGenerator {

	public enum ConstantType {
		CLAZZ, DATATYPE, UTF8
	};

	private XMLClassWriter xmlWriter;

	public CodeGenerator(String classFile) {
		this.xmlWriter = new XMLClassWriter(classFile + ".xml", "justclassfile");
	}

	public void buildFile() {
		this.xmlWriter.endClassFile();
	}

	public void addConstant(int index, String value, ConstantType type) {
		switch (type) {
		case CLAZZ:
			this.xmlWriter.addConstantClass(String.valueOf(index), value);
			break;
		case DATATYPE:
			this.xmlWriter.addConstantDatatype(String.valueOf(index), value);
			break;
		default:
			this.xmlWriter.addConstantUTF8(String.valueOf(index), value);
			break;
		}

	}

}
