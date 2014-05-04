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

	public XMLClassWriter(String filename) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			this.document = docBuilder.newDocument();
			this.filename = filename;
			
			this.classElement = this.document.createElement("class");
			this.document.appendChild(this.classElement);
			
			this.magicElement = this.document.createElement("magic");
			this.magicElement.appendChild(this.document.createTextNode("magicNumber"));
			this.classElement.appendChild(this.magicElement);
			
			this.minorVersionElement = this.document.createElement("minor_version");
			this.minorVersionElement.appendChild(this.document.createTextNode("0"));
			this.classElement.appendChild(this.minorVersionElement);
			
			this.majorVersionElement = this.document.createElement("majorversion");
			this.majorVersionElement.appendChild(this.document.createTextNode("1"));
			this.classElement.appendChild(this.majorVersionElement);
			
			this.thisClassElement = this.document.createElement("this_class");
			this.thisClassElement.appendChild(this.document.createTextNode("1"));
			this.classElement.appendChild(this.thisClassElement);
			
			this.constantPoolElement = this.document.createElement("constant_pool");
			this.classElement.appendChild(this.constantPoolElement);
			
			this.fieldInfoElement = this.document.createElement("field_info");
			this.classElement.appendChild(this.fieldInfoElement);
			
			this.methodInfoElement = this.document.createElement("method_info");
			this.classElement.appendChild(this.methodInfoElement);
			
		} catch (ParserConfigurationException e) {
			System.out.println("Error during xml-builder-configuration: "+ e.getMessage());
		}
	}

	public void endClassFile() {

		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			DOMSource source = new DOMSource(this.document);
			StreamResult result = new StreamResult(new File(this.filename));

			transformer.transform(source, result);

		} catch (TransformerException e) {
			System.out.println("Error during file generation: "
					+ e.getMessage());
		}
	}

	
	public void addConstantClass(String index, String nameIndex) {
		Element constantClass = document.createElement("constant_class");
		constantClass.setAttribute("index", index);
		
		Element nameIndexEl = document.createElement("name_index");
		nameIndexEl.appendChild(this.document.createTextNode(nameIndex));
		constantClass.appendChild(nameIndexEl);
		
		this.constantPoolElement.appendChild(constantClass);
		
	}

}
