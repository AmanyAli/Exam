package com.am.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;

/**
 * @author Amnay Ali
 * 
 *         Role Entity
 *
 *         Each role has certain authorities.
 */
@Entity(value = "role")
@Indexes({ @Index(fields = { @Field("role") }, options = @IndexOptions(unique = true)) })

public class Role implements MongoBean {
	@Id
	private ObjectId id;

	private String role;

	private String type;

	private Integer order;

	public static final String ROLE_PROPERTY = "role";

	public static final String INTERNAL_USER_ROLE_TYPE = "in";
	public static final String EXTERNAL_USER_ROLE_TYPE = "ex";

	public String getRole() {
		return role;
	}

	public Role() {
	}

	public Role(String role) {
		this.role = role;

	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Role other = (Role) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
