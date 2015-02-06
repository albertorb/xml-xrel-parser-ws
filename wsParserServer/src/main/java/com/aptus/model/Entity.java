package com.aptus.wsParserServer.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the entity database table.
 * 
 */
@javax.persistence.Entity
@NamedQuery(name="Entity.findAll", query="SELECT e FROM Entity e")
public class Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int entityID;

	private int type;

	//bi-directional many-to-one association to Document
	@OneToMany(mappedBy="entity")
	private List<Document> documents;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;

	public Entity() {
	}

	public int getEntityID() {
		return this.entityID;
	}

	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document addDocument(Document document) {
		getDocuments().add(document);
		document.setEntity(this);

		return document;
	}

	public Document removeDocument(Document document) {
		getDocuments().remove(document);
		document.setEntity(null);

		return document;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}