package com.oguzcan.dto;

import java.io.Serializable;
import java.util.Set;

public class CustomerDTO extends UserDTO  implements Serializable, Comparable<Object>{
	private static final long serialVersionUID = -898475110260482733L;

	private Set<AccountDTO> accountList;  // TreeSet
	private PersonalInformationDTO info;
	private int customerId;

	public CustomerDTO(Builder builder) {
		super(builder.username, builder.password);
		this.accountList = builder.accountList;
		this.info = builder.info;
		this.customerId = builder.customerId;
	}

//  ############################### INNER CLASS ################################
	public static class Builder {
		private Set<AccountDTO> accountList;
		private String username;
		private String password;
		private PersonalInformationDTO info;
		private int customerId = 0;
		
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
		public Builder customerId(int customerId) {
			this.customerId = customerId;
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
	public PersonalInformationDTO getInfo() {
		return info;
	}
	public void setInfo(PersonalInformationDTO info) {
		this.info = info;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	@Override
	public int compareTo(Object o) {
		CustomerDTO customer = (CustomerDTO) o;
		
		if(customerId == customer.getCustomerId()) {
			return 0;
		}
		
		return customerId > customer.getCustomerId() ? 1 : -1;
	}
	
	@Override
	public String toString() {
		return  customerId + " " + info.getName() + " " + info.getLastname();
	}
}
