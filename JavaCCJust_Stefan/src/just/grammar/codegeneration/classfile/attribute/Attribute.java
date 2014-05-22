package just.grammar.codegeneration.classfile.attribute;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Attribute {

	public abstract Element writeXml(Document doc);
}
