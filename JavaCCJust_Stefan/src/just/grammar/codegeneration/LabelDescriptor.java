package just.grammar.codegeneration;

import just.grammar.codegeneration.code.Label;

public class LabelDescriptor {
	private Label label;
	
	public LabelDescriptor() {
	}
	
	public LabelDescriptor(Label label)
	{
		this.label = label;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return label.toString();
	}
}
