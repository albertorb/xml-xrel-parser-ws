package com.aptus.parser.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the attribute database table.
 * 
 */
@Embeddable
public class AttributePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable = false, updatable = false)
	private int docID;

	@Column(insertable = false, updatable = false)
	private int pathID;

	public AttributePK() {
	}

	public int getDocID() {
		return this.docID;
	}

	public void setDocID(int docID) {
		this.docID = docID;
	}

	public int getPathID() {
		return this.pathID;
	}

	public void setPathID(int pathID) {
		this.pathID = pathID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AttributePK)) {
			return false;
		}
		AttributePK castOther = (AttributePK) other;
		return (this.docID == castOther.docID)
				&& (this.pathID == castOther.pathID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.docID;
		hash = hash * prime + this.pathID;

		return hash;
	}
}