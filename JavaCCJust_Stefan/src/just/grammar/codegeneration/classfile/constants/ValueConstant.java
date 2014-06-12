package just.grammar.codegeneration.classfile.constants;

import just.grammar.semantics.Symbol;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ValueConstant extends Constant {
	private Symbol symbol;

	public ValueConstant(Symbol symbol) {
		this.symbol = symbol;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	@Override
	public Element writeXml(Document doc) {
		Element element = doc.createElement("constant_"	+ symbol.type.getLongName());
		element.setAttribute("index", String.valueOf(getIndex()));

		Element bytesElem = doc.createElement("bytes");
		bytesElem.appendChild(doc.createTextNode(String.valueOf(symbol.val)));

		element.appendChild(bytesElem);

		return element;
	}
}
