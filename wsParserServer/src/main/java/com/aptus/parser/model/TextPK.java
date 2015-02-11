package com.aptus.parser.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the text database table.
 * 
 */
@Embeddable
public class TextPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable = false, updatable = false)
	private int docID;

	public TextPK() {
	}

	public int getDocID() {
		return this.docID;
	}

	public void setDocID(int docID) {
		this.docID = docID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TextPK)) {
			return false;
		}
		TextPK castOther = (TextPK) other;
		return (this.docID == castOther.docID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.docID;

		return hash;
	}
}