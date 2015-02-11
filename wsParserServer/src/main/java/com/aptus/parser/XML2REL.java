// XML to REL parser implementation
// XML to REL parser implementation
package com.aptus.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aptus.parser.model.Element;
import com.aptus.parser.model.Path;

public class XML2REL {

	private static StringBuilder document = new StringBuilder();
	private static com.aptus.parser.model.Document doc = null;

	public static com.aptus.parser.model.Element createElement(
			com.aptus.parser.model.Document doc, Node node, Integer index,
			Integer reindex, Integer laststart, Integer lastend) {
		com.aptus.parser.model.Element res = new com.aptus.parser.model.Element();
		com.aptus.parser.model.ElementPK res2 = new com.aptus.parser.model.ElementPK();
		// res2.setDocID(doc.getDocID()); TODO Solve NULL
		res.setId(res2);
		res.setPath(getPath(node));
		res.setIndex(index);
		res.setReindex(reindex);
		return res;
	}

	public static void addElements(List<com.aptus.parser.model.Element> e,
			List<com.aptus.parser.model.Element> accum) {
		e.retainAll(accum);
	}

	public static List<com.aptus.parser.model.Element> getElements(
			NodeList nodes, List<com.aptus.parser.model.Element> accum) {

		List<com.aptus.parser.model.Element> res = accum;
		Integer m = 0;
		Integer n = m + 1;
		Integer lastStart = 0;
		Integer lastEnd = 0;
		while (m < nodes.getLength()) {
			Node item = nodes.item(m);
			NodeList childs = item.getChildNodes();

			if (item.getParentNode().equals(null)) {
				doc = new com.aptus.parser.model.Document();
				doc.setDate(null); // TODO setDate DOC

			}

			if (item.getChildNodes().getLength() > 1) {
				addElements(getElements(childs, accum), accum);
				if (n == 1) {
					accum.add(createElement(doc, item, n,
							nodes.getLength() - n, lastStart, lastEnd));
					if (item.hasAttributes()) {
						// Integer docid = doc.getDocID();// TODO Solve NULL
						createAttributes(item, 1);// TODO Solve docid
					}

					if (item.getNodeName().equals("nullFlavor")) {
						System.out.println(item.getTextContent());
						createText(item);
					}
				}
			} else {
				if (!isText(item)) {
					accum.add(createElement(doc, item, n,
							nodes.getLength() - n, lastStart, lastEnd));
					if (item.hasAttributes()) {
						// Integer docid = doc.getDocID();// TODO Solve NULL
						createAttributes(item, 1);// TODO Solve docid
					}

					if (item.getNodeName().equals("nullFlavor")) {
						System.out.println(item.getTextContent());
						createText(item);
					}
					n++;
				}

			}
			m++;
		}
		return res;
	}

	public static com.aptus.parser.model.Text createText(Node node) {
		com.aptus.parser.model.Text res = new com.aptus.parser.model.Text();
		res.setValue(node.getNodeValue());
		com.aptus.parser.model.Path path = getPath(node);
		res.setPath(path);
		return res;
	}

	public static List<com.aptus.parser.model.Attribute> createAttributes(
			Node item, Integer docID) {
		// TODO Auto-generated method stub
		List<com.aptus.parser.model.Attribute> res = new ArrayList<com.aptus.parser.model.Attribute>();
		NamedNodeMap attrs = item.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			Node elem = attrs.item(i);
			// String name = elem.getNodeName();
			String value = elem.getNodeValue();
			com.aptus.parser.model.Attribute temp = new com.aptus.parser.model.Attribute();
			com.aptus.parser.model.AttributePK temp2 = new com.aptus.parser.model.AttributePK();
			temp.setValue(value);
			// com.aptus.parser.model.Path path = new
			// com.aptus.parser.model.Path();
			// path.setPathexp();
			temp.setPath(getPathAttr(elem, item));
			// temp.setDocument();
			// TODO temp2 setDOCID etc
			temp2.setDocID(docID);
			res.add(temp);
		}

		return res;

	}

	public static List<Element> parse(String xmlpath) {

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		List<com.aptus.parser.model.Element> res = new ArrayList<com.aptus.parser.model.Element>();
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(xmlpath));
			String a = new String(encoded);
			document = new StringBuilder(a);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		dbf.setNamespaceAware(true);

		try {
			db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(xmlpath));

			NodeList nl = doc.getChildNodes(); // XREL Documents
			// List<com.aptus.parser.model.Document> docs = new
			// ArrayList<com.aptus.parser.model.Document>();F
			res = getElements(nl,
					new ArrayList<com.aptus.parser.model.Element>());

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	public static Boolean isText(Node node) {
		if (node.getNodeName().equals("#text")) {
			return true;
		}
		return false;
	}

	public static Path getPathAttr(Node node, Node owner) {
		Path res = new Path();
		if (node.getNodeName().equals("#text")) {
			throw new IllegalArgumentException(
					"Error #text: Se está tomando como elemento un espacio en blanco entre dos etiquetas");
		}
		Node nowneratid = owner.getAttributes().getNamedItem("archetypeId");
		String owneratid = "";
		if (nowneratid != null) {
			owneratid = "[" + nowneratid.getNodeValue() + "]";
		}
		String path = "/" + owner.getNodeName() + owneratid + "/@"
				+ node.getNodeName();
		Node aux = owner.getParentNode();
		String atid = "";
		while (aux != null) {
			if (!(aux.getNodeName().equals("#text"))
					&& !(aux.getNodeName().equals("#document"))) {
				Node natid = aux.getAttributes().getNamedItem("archetypeId");
				if (natid != null) {
					atid = "[" + natid.getNodeValue() + "]";
				}
				path = "/" + aux.getNodeName() + atid + path;
			}

			aux = aux.getParentNode();
		}
		System.out.println(path);
		res.setPathexp(path);
		return res;
	}

	public static Path getPath(Node node) {
		Path res = new Path();
		Node noatid = node.getAttributes().getNamedItem("archetypeId");
		String oatid = "";
		if (noatid != null) {
			oatid = "[" + noatid.getNodeValue() + "]";
		}
		String path = "/" + node.getNodeName() + oatid;

		Node aux = node.getParentNode();
		String atid = "";
		while (aux != null) {
			if (!(aux.getNodeName().equals("#text"))
					&& !(aux.getNodeName().equals("#document"))) {
				Node natid = aux.getAttributes().getNamedItem("archetypeId");
				if (natid != null) {
					atid = "[" + natid.getNodeValue() + "]";
				}

				path = "/" + aux.getNodeName() + atid + path;
			} else if (aux.getNodeName().equals("value")) {
				path = "/" + aux.getNodeName() + atid + path;
			}
			aux = aux.getParentNode();

		}
		System.out.println(path);
		res.setPathexp(path);
		return res;
	}

	public static void setStartEnd(Integer s, Integer e, String name,
			String filepath) {

		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			for (String line; (line = br.readLine()) != null;) {
				// process the line.

			}
			// line is not visible here.
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}