package com.aptus;

import java.util.List;

import javax.xml.ws.Endpoint;

import com.aptus.parser.XML2REL;

/**
 * 
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Elementos obtenidos del XML");
		List<com.aptus.parser.model.Element> res = XML2REL
				.parse("resources/modelExample.xml");
		System.out.println(res.size());
		// Endpoint.publish("http://localhost:8080/wService", new wService());
	}
}
