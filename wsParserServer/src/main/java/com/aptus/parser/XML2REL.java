// XML to REL parser implementation
// XML to REL parser implementation
package com.aptus.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import com.aptus.parser.model.Path;
import com.aptus.parser.model.Element;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML2REL {

	public static com.aptus.parser.model.Element createElement(Node node,
			Integer index, Integer reindex) {
		com.aptus.parser.model.Element res = new com.aptus.parser.model.Element();
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

		while (m < nodes.getLength()) {
			Node item = nodes.item(m);
			if (item.getChildNodes().getLength() > 1) {
				addElements(getElements(item.getChildNodes(), accum), accum);
				if (n == 1) {
					accum.add(createElement(item, n, nodes.getLength() - n));
				}
			} else {
				if (!isText(item)) {
					accum.add(createElement(item, n, nodes.getLength() - n));
					n++;
				}
			}
			m++;
		}
		return res;
	}

	public static List<Element> parse(String xmlpath) {

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		List<com.aptus.parser.model.Element> res = new ArrayList<com.aptus.parser.model.Element>();

		dbf.setNamespaceAware(true);

		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(xmlpath));
			NodeList nl = doc.getChildNodes(); // XREL Documents
			// List<com.aptus.parser.model.Document> docs = new
			// ArrayList<com.aptus.parser.model.Document>();
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

	public static Path getPath(Node node) {
		Path res = new Path();
		if (node.getNodeName().equals("#text")) {
			throw new IllegalArgumentException(
					"Error #text: Se está tomando como elemento un espacio en blanco entre dos etiquetas");
		}
		String path = "#/" + node.getNodeName();
		Node aux = node.getParentNode();
		while (aux != null) {
			if (!(aux.getNodeName().equals("#text"))
					&& !(aux.getNodeName().equals("#document"))) {
				path = "#/" + aux.getNodeName() + path;
			}

			aux = aux.getParentNode();
		}

		res.setPathexp(path);
		return res;
	}

}