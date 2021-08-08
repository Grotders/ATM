package com.oguzcan.dto;

import java.io.Serializable;
import java.util.Set;

public class CustomerDTO implements Serializable, User {
	private static final long serialVersionUID = -898475110260482733L;

	private Set<AccountDTO> accountList;  // TreeSet
	private String username;
	private String password;
	private PersonalInformationDTO info;

	public CustomerDTO(Builder builder) {
		this.accountList = builder.accountList;
		this.username = builder.username;
		this.password = builder.password;
		this.info = builder.info;
	}
	public CustomerDTO() {
		
	}

//  ############################### INNER CLASS ################################
	public static class Builder {
		private Set<AccountDTO> accountList;
		private String username;
		private String password;
		private PersonalInformationDTO info;

		
		public Builder accountList(Set<AccountDTO> accountList) {
			this.accountList = accountList;
			return this;
		}
		public Builder username(String username) {
			this.username = username;
			return this;
		}
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		public Builder info(PersonalInformationDTO info) {
			this.info = info;
			return this;
		}
		public CustomerDTO build() {
			return new CustomerDTO(this);
		}
	}

//	######################  GETTER & SETTER ################################
	public Set<AccountDTO> getAccountList() {
		return accountList;
	}
	public void setAccountList(Set<AccountDTO> accountList) {
		this.accountList = accountList;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public PersonalInformationDTO getInfo() {
		return info;
	}
	public void setInfo(PersonalInformationDTO info) {
		this.info = info;
	}

	
	@Override
	public String toString() {
		return "Customer [accountList=" + accountList + ", username=" + username + ", password=" + password + ", info="
				+ info + "]";
	}
}
