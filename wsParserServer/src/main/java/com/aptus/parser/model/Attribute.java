package com.aptus.parser.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the attribute database table.
 * 
 */
@javax.persistence.Entity
@NamedQuery(name="Attribute.findAll", query="SELECT a FROM Attribute a")
public class Attribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AttributePK id;

	@Lob
	private String value;

	//bi-directional many-to-one association to Document
	@ManyToOne
	@JoinColumn(name="docID")
	private Document document;

	//bi-directional many-to-one association to Path
	@ManyToOne
	@JoinColumn(name="pathID")
	private Path path;

	public Attribute() {
	}

	public AttributePK getId() {
		return this.id;
	}

	public void setId(AttributePK id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Path getPath() {
		return this.path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

}