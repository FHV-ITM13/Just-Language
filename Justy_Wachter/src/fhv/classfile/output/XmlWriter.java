package fhv.classfile.output;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import fhv.classfile.ClassFile;
import fhv.classfile.Field;
import fhv.classfile.Method;
import fhv.classfile.constant.Constant;

public class XmlWriter {
	public void write(String fileName, ClassFile classFile) {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();
			Element rootElement = doc.createElement("classfile");
			doc.appendChild(rootElement);

			rootElement.appendChild(this.getNode(doc, "magic",
					classFile.getMagic()));
			rootElement.appendChild(this.getNode(doc, "minor_version",
					classFile.getMinor()));
			rootElement.appendChild(this.getNode(doc, "major_version",
					classFile.getMajor()));

			Element constantPool = doc.createElement("constant_pool");
			rootElement.appendChild(constantPool);

			for (Constant constant : classFile.getConstantPool().values()) {
				Element e = constant.writeXml(doc);
				constantPool.appendChild(e);
			}

			Element thisClass = doc.createElement("this_class");
			thisClass.appendChild(doc.createTextNode(classFile
					.getClassConstant().getIndex() + ""));

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
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
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

			System.out.println("\nXML DOM Created Successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Node getNode(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
}
