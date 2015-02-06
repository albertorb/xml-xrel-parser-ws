package com.aptus.wsParserServer.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the text database table.
 * 
 */
@Embeddable
public class TextPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int docID;

	private int start;

	private int end;

	public TextPK() {
	}
	public int getDocID() {
		return this.docID;
	}
	public void setDocID(int docID) {
		this.docID = docID;
	}
	public int getStart() {
		return this.start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return this.end;
	}
	public void setEnd(int end) {
		this.end = end;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TextPK)) {
			return false;
		}
		TextPK castOther = (TextPK)other;
		return 
			(this.docID == castOther.docID)
			&& (this.start == castOther.start)
			&& (this.end == castOther.end);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.docID;
		hash = hash * prime + this.start;
		hash = hash * prime + this.end;
		
		return hash;
	}
}