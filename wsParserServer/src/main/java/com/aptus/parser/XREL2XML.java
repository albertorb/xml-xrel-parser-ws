/**
 * 
 */
package com.aptus.parser;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMImplementation;

/**
 * @author alberto
 *
 */
public class XREL2XML {

	/**
	 * 
	 * @param query
	 *            XPATH format
	 * @return Document
	 */
	// TODO query
	public static com.aptus.parser.model.Document query(String query) {
		com.aptus.parser.model.Document res = new com.aptus.parser.model.Document();

		return null;

	}

	public static DOMImplementation createXML(
			com.aptus.parser.model.Document doc) {
		DOMImplementation res = null;
		res.createDocument(null, null, "xml");
		XPathFactory xpath = XPathFactory.newInstance();
		XPath xx = xpath.newXPath();
		
		return res;

	}

}
