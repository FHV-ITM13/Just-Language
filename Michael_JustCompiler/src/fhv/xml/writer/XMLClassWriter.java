package fhv.xml.writer;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLClassWriter {

	private Document document;
	private String filename;

	private Element classElement;
	private Element magicElement;
	private Element minorVersionElement;
	private Element majorVersionElement;
	private Element thisClassElement;
	private Element constantPoolElement;
	private Element fieldInfoElement;
	private Element methodInfoElement;

	/**
	 * Inits the basic nodes for the xml class file
	 * @param filename
	 * @param classFile
	 */
	public XMLClassWriter(String filename, String classFile) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			this.document = docBuilder.newDocument();
			this.filename = filename;

			this.classElement = this.document.createElement("classfile");
			this.document.appendChild(this.classElement);

			this.magicElement = this.document.createElement("magic");
			this.magicElement.appendChild(this.document
					.createTextNode(classFile));
			this.classElement.appendChild(this.magicElement);

			this.minorVersionElement = this.document
					.createElement("minor_version");
			this.minorVersionElement.appendChild(this.document
					.createTextNode("0"));
			this.classElement.appendChild(this.minorVersionElement);

			this.majorVersionElement = this.document
					.createElement("majorversion");
			this.majorVersionElement.appendChild(this.document
					.createTextNode("1"));
			this.classElement.appendChild(this.majorVersionElement);

			this.thisClassElement = this.document.createElement("this_class");
			this.thisClassElement
					.appendChild(this.document.createTextNode("1"));
			this.classElement.appendChild(this.thisClassElement);

			this.constantPoolElement = this.document
					.createElement("constant_pool");
			this.classElement.appendChild(this.constantPoolElement);

			this.fieldInfoElement = this.document.createElement("field_info");
			this.classElement.appendChild(this.fieldInfoElement);

			this.methodInfoElement = this.document.createElement("method_info");
			this.classElement.appendChild(this.methodInfoElement);

		} catch (ParserConfigurationException e) {
			System.out.println("Error during xml-builder-configuration: "
					+ e.getMessage());
		}
	}

	/**
	 * Finishes the XML class file and actually writs the file to disk
	 */
	public void endClassFile() {

		try {
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(this.document);
			StreamResult result = new StreamResult(new File(this.filename));

			transformer.transform(source, result);

		} catch (TransformerException e) {
			System.out.println("Error during file generation: "
					+ e.getMessage());
		}
	}

	/**
	 * Adds a constant node for the class (program)
	 * @param index
	 * @param nameIndex
	 */
	public void addConstantClass(String index, String nameIndex) {	
		this.addConstantNode("constant_class", index, "name_index", nameIndex);
	}

	/**
	 * Adds a constant node for any identifier
	 * @param index
	 * @param value
	 */
	public void addConstantUTF8(String index, String value) {
		this.addConstantNode("constant_utf8", index, "bytes", value);
	}

	/**
	 * Adds a constant node for a datatype
	 * @param index
	 * @param value
	 */
	public void addConstantDatatype(String index, String value) {
		String nodeValue = this.getJVMType(value);
		this.addConstantNode("constant_utf8", index, "bytes", nodeValue);
	}
	
	/**
	 * Creates a constant node
	 * @param nodeName
	 * @param index
	 * @param childeNodeName
	 * @param value
	 */
	private void addConstantNode(String nodeName, String index, String childeNodeName, String value) {
		Element constantElement = document.createElement(nodeName);
		constantElement.setAttribute("index", index);

		Element childElement = document.createElement(childeNodeName);
		childElement.appendChild(this.document.createTextNode(value));
		constantElement.appendChild(childElement);
		
		this.constantPoolElement.appendChild(constantElement);
	}

	/**
	 * Returns for a datatype the JVM datatype
	 * @param value
	 * @return
	 */
	private String getJVMType(String value) {

		// TODO add array support
		// http://stackoverflow.com/questions/9909228/what-does-v-mean-in-a-class-signature

		switch (value) {
		case "byte":
			return "B";

		case "char":
			return "C";

		case "double":
			return "d";

		case "float":
			return "F";

		case "long":
			return "J";

		case "short":
			return "S";

		case "boolean":
			return "Z";

		case "void":
			return "V";

		default:
			return "I";

		}
	}

}
