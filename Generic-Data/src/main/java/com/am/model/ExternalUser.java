package com.am.model;

import org.mongodb.morphia.annotations.Entity;

@Entity(value = "external_user")
public class ExternalUser extends User {
	private Boolean testUser;

	public Boolean isTestUser() {
		return testUser;
	}

	public void setTestUser(Boolean testUser) {
		this.testUser = testUser;
	}
}
