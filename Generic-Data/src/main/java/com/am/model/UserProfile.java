package com.am.model;

import org.mongodb.morphia.annotations.Entity;

@Entity(value = "user_profile")
public class UserProfile extends User {

	private boolean testUser;

	public boolean isTestUser() {
		return testUser;
	}

	public void setTestUser(boolean testUser) {
		this.testUser = testUser;
	}

}
