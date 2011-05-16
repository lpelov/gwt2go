package com.gwt2go.dev.shared;

import java.util.Date;

public class DemoUser {

	private String firstname;
	private String lastname;
	private Date age;

	// required constructor with defensive copy of the age object
	public DemoUser(String firstname, String lastname, Date age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = new Date(age.getTime());
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Date getAge() {
		// make defensive copy here once, to prevent changes outside
		return new Date(age.getTime());
	}

}
