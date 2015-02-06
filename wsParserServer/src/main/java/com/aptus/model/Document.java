package com.aptus.wsParserServer.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the document database table.
 * 
 */
@javax.persistence.Entity
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int docID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	//bi-directional many-to-one association to Attribute
	@OneToMany(mappedBy="document")
	private List<Attribute> attributes;

	//bi-directional many-to-one association to Element
	@OneToMany(mappedBy="document")
	private List<Element> elements;

	//bi-directional many-to-one association to Text
	@OneToMany(mappedBy="document")
	private List<Text> texts;

	//bi-directional many-to-one association to Entity
	@ManyToOne
	@JoinColumn(name="entityID")
	private Entity entity;

	public Document() {
	}

	public int getDocID() {
		return this.docID;
	}

	public void setDocID(int docID) {
		this.docID = docID;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Attribute> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Attribute addAttribute(Attribute attribute) {
		getAttributes().add(attribute);
		attribute.setDocument(this);

		return attribute;
	}

	public Attribute removeAttribute(Attribute attribute) {
		getAttributes().remove(attribute);
		attribute.setDocument(null);

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
		element.setDocument(this);

		return element;
	}

	public Element removeElement(Element element) {
		getElements().remove(element);
		element.setDocument(null);

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
		text.setDocument(this);

		return text;
	}

	public Text removeText(Text text) {
		getTexts().remove(text);
		text.setDocument(null);

		return text;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

}