package com.am.model;

import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;

/**
 * @author Amany Ali
 * 
 * Validation entity to configure each screen validation via DB
 *
 */
@Entity(value = "validation", noClassnameStored = true)
@Indexes({ @Index(fields = { @Field("screenName") }, options = @IndexOptions(unique = true)) })
public class Validation implements MongoBean {
	@Id
	private ObjectId id;
	
	private String screenName;

	public Set<Validator> fields;

	public Validation() {
		fields = new HashSet<Validator>();
	}

	// for searching purpose with no initial values
	public Validation(boolean search) {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((screenName == null) ? 0 : screenName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Validation other = (Validation) obj;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		return true;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Set<Validator> getFields() {
		return fields;
	}

	public void setFields(Set<Validator> fields) {
		this.fields = fields;
	}

}
