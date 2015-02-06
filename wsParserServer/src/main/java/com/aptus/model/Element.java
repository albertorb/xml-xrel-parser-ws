package com.aptus.wsParserServer.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the element database table.
 * 
 */
@javax.persistence.Entity
@NamedQuery(name="Element.findAll", query="SELECT e FROM Element e")
public class Element implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ElementPK id;

	private int index;

	private int reindex;

	//bi-directional many-to-one association to Document
	@ManyToOne
	@JoinColumn(name="docID")
	private Document document;

	//bi-directional many-to-one association to Path
	@ManyToOne
	@JoinColumn(name="pathID")
	private Path path;

	public Element() {
	}

	public ElementPK getId() {
		return this.id;
	}

	public void setId(ElementPK id) {
		this.id = id;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getReindex() {
		return this.reindex;
	}

	public void setReindex(int reindex) {
		this.reindex = reindex;
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