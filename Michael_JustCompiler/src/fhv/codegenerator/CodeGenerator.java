package fhv.codegenerator;

import fhv.xml.writer.XMLClassWriter;

public class CodeGenerator {
	
	private XMLClassWriter xmlWriter;
	public static CodeGenerator codeGenerator = new CodeGenerator();
	
	private CodeGenerator(){
		this.xmlWriter = new XMLClassWriter("class.xml");
	}
	
	public void buildFile(){
		this.xmlWriter.endClassFile();
	}
	
	public void addConstantClass(int index, int nameIndex){
		this.xmlWriter.addConstantClass(String.valueOf(index),String.valueOf(nameIndex));
	}

}
