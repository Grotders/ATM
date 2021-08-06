package com.oguzcan.model;

import java.io.Serializable;

public class PersonalInformation implements Serializable{
	private static final long serialVersionUID = -2912841060409039243L;
	
	private String name;
	private String lastname;
	private String phoneNumber;
	
	public PersonalInformation(Builder builder) {
		this.name = builder.name;
		this.lastname = builder.lastname;
		this.phoneNumber = builder.phoneNumber;
	}

//  ############################### INNER CLASS ################################
	public static class Builder {
		private String name;
		private String lastname;
		private String phoneNumber;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder lastname(String lastname) {
			this.lastname = lastname;
			return this;
		}
		public Builder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		public PersonalInformation build() {
			return new PersonalInformation(this);
		}
	}

//	######################  GETTER & SETTER ################################
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	@Override
	public String toString() {
		return "PersonalInformation [name=" + name + ", lastname=" + lastname + ", phoneNumber=" + phoneNumber + "]";
	}
}
