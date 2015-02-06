package com.aptus.parser.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@javax.persistence.Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userID;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="user_relation"
		, joinColumns={
			@JoinColumn(name="parentID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="childID")
			}
		)
	private List<User> users1;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="users1")
	private List<User> users2;

	//bi-directional many-to-one association to Entity
	@OneToMany(mappedBy="user")
	private List<Entity> entities;

	public User() {
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public List<User> getUsers1() {
		return this.users1;
	}

	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}

	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}

	public List<Entity> getEntities() {
		return this.entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public Entity addEntity(Entity entity) {
		getEntities().add(entity);
		entity.setUser(this);

		return entity;
	}

	public Entity removeEntity(Entity entity) {
		getEntities().remove(entity);
		entity.setUser(null);

		return entity;
	}

}