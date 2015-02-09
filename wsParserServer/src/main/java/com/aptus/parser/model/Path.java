package com.aptus.parser.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the path database table.
 * 
 */
@javax.persistence.Entity
@NamedQuery(name="Path.findAll", query="SELECT p FROM Path p")
public class Path implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pathID;

	@Lob
	private String pathexp;

	//bi-directional many-to-one association to Attribute
	@OneToMany(mappedBy="path")
	private List<Attribute> attributes;

	//bi-directional many-to-one association to Element
	@OneToMany(mappedBy="path")
	private List<Element> elements;

	//bi-directional many-to-one association to Text
	@OneToMany(mappedBy="path")
	private List<Text> texts;

	public Path() {
	}

	public int getPathID() {
		return this.pathID;
	}

	public void setPathID(int pathID) {
		this.pathID = pathID;
	}

	public String getPathexp() {
		return this.pathexp;
	}

	public void setPathexp(String pathexp) {
		this.pathexp = pathexp;
	}

	public List<Attribute> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Attribute addAttribute(Attribute attribute) {
		getAttributes().add(attribute);
		attribute.setPath(this);

		return attribute;
	}

	public Attribute removeAttribute(Attribute attribute) {
		getAttributes().remove(attribute);
		attribute.setPath(null);

		return attribute;
	}

	public List<Element> getElements() {
		return this.elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public Element addElement(Element element) {
		getElements().add(element);
		element.setPath(this);

		return element;
	}

	public Element removeElement(Element element) {
		getElements().remove(element);
		element.setPath(null);

		return element;
	}

	public List<Text> getTexts() {
		return this.texts;
	}

	public void setTexts(List<Text> texts) {
		this.texts = texts;
	}

	public Text addText(Text text) {
		getTexts().add(text);
		text.setPath(this);

		return text;
	}

	public Text removeText(Text text) {
		getTexts().remove(text);
		text.setPath(null);

		return text;
	}

}