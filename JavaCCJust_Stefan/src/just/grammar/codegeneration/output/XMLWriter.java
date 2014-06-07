package just.grammar.codegeneration.output;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import just.grammar.codegeneration.classfile.Classfile;
import just.grammar.codegeneration.classfile.Field;
import just.grammar.codegeneration.classfile.Method;
import just.grammar.codegeneration.classfile.constants.Constant;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLWriter {
	public static void write(String fileName, Classfile classFile) {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();
			Element rootElement = doc.createElement("classfile");
			doc.appendChild(rootElement);

			rootElement.appendChild(getNode(doc, "magic", classFile.getMagic()));
			rootElement.appendChild(getNode(doc, "minor_version", classFile.getMinorVers()));
			rootElement.appendChild(getNode(doc, "major_version", classFile.getMajorVers()));

			Element constantPool = doc.createElement("constant_pool");
			rootElement.appendChild(constantPool);

			for (Constant constant : classFile.getConstants().values()) {
				Element e = constant.writeXml(doc);
				constantPool.appendChild(e);
			}

			Element thisClass = doc.createElement("this_class");
			thisClass.appendChild(doc.createTextNode(classFile.getClassConstant().getIndex() + ""));
			rootElement.appendChild(thisClass);

			Element fieldInfo = doc.createElement("field_info");
			rootElement.appendChild(fieldInfo);

			for (Field field : classFile.getFields()) {
				Element e = field.writeXml(doc);
				fieldInfo.appendChild(e);
			}

			Element methodInfo = doc.createElement("method_info");
			rootElement.appendChild(methodInfo);

			for (Method method : classFile.getMethods()) {
				Element e = method.writeXml(doc);
				methodInfo.appendChild(e);
			}

			// output DOM XML to console
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 4);
			Transformer transformer = transformerFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			// initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
			String xmlString = result.getWriter().toString();
			System.out.println(xmlString);

			PrintWriter out = new PrintWriter(fileName);
			out.println(xmlString);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Node getNode(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
}
