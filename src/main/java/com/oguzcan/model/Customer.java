package com.oguzcan.model;

import java.io.Serializable;
import java.util.Set;

public class Customer implements Serializable {
	private static final long serialVersionUID = -898475110260482733L;

	private Set<Account> accountList;
	private String username;
	private String password;
	private PersonalInformation info;

	public Customer(Builder builder) {
		this.accountList = builder.accountList;
		this.username = builder.username;
		this.password = builder.password;
		this.info = builder.info;
	}

//  ############################### INNER CLASS ################################
	public static class Builder {
		private Set<Account> accountList;
		private String username;
		private String password;
		private PersonalInformation info;

		
		public Builder accountList(Set<Account> accountList) {
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
		public Builder info(PersonalInformation info) {
			this.info = info;
			return this;
		}
		public Customer build() {
			return new Customer(this);
		}
	}

//	######################  GETTER & SETTER ################################
	public Set<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(Set<Account> accountList) {
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
	public PersonalInformation getInfo() {
		return info;
	}
	public void setInfo(PersonalInformation info) {
		this.info = info;
	}

	
	@Override
	public String toString() {
		return "Customer [accountList=" + accountList + ", username=" + username + ", password=" + password + ", info="
				+ info + "]";
	}
}
