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
				.parse("resources/Composition.xml");
		System.out.println(res.size());
		for (com.aptus.parser.model.Element e : res) {
			
			System.out.println(e.getPath().getPathexp());
		}
		Endpoint.publish("http://localhost:8080/wService", new wService());
	}
}
