package com.oguzcan.dto;

import java.io.Serializable;

public class PersonalInformationDTO implements Serializable{
	private static final long serialVersionUID = -2912841060409039243L;
	
	private String first_name;
	private String last_name;
	private String phoneNumber;
	
	public PersonalInformationDTO(Builder builder) {
		this.first_name = builder.first_name;
		this.last_name = builder.last_name;
		this.phoneNumber = builder.phoneNumber;
	}
	public PersonalInformationDTO() {
		
	}

//  ############################### INNER CLASS ################################
	public static class Builder {
		private String first_name;
		private String last_name;
		private String phoneNumber;
		
		public Builder name(String first_name) {
			this.first_name = first_name;
			return this;
		}
		public Builder lastname(String last_name) {
			this.last_name = last_name;
			return this;
		}
		public Builder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		public PersonalInformationDTO build() {
			return new PersonalInformationDTO(this);
		}
	}

//	######################  GETTER & SETTER ################################
	public String getName() {
		return first_name;
	}
	public void setName(String first_name) {
		this.first_name = first_name;
	}
	public String getLastname() {
		return last_name;
	}
	public void setLastname(String last_name) {
		this.last_name = last_name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	@Override
	public String toString() {
		return "PersonalInformation [first_name=" + first_name + ", last_name=" + last_name + ", phoneNumber=" + phoneNumber + "]";
	}
}
