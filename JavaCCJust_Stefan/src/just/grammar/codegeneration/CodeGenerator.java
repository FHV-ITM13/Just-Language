package just.grammar.codegeneration;

import just.grammar.semantics.Symbol;

public class CodeGenerator {

	public static CodeGenerator CodeGenerator = new CodeGenerator();
	
	private int labelNumber = 0;
	
	public CodeGenerator() {
	}
	
	public Descriptor newDescriptor(Symbol symbol) {
		return new Descriptor(symbol);
	}
	
	public Label createLabel() {
		return new Label("L" + this.labelNumber++);
	}
	
}
