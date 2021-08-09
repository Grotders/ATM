package com.oguzcan.dto;

import java.io.Serializable;

public class BasicAccountDTO extends AccountDTO implements Serializable{
	private static final long serialVersionUID = 8327228743131606428L;


	public BasicAccountDTO(Builder builder) {
		super.setAccNumber(builder.accNumber);
		super.setBalance(builder.balance);
	}
	public BasicAccountDTO() {
		
	}
	
//  ############################### INNER CLASS ################################
	public static class Builder {
		private int accNumber;
		private double balance;
		
		public Builder accNumber(int accNumber) {
			this.accNumber = accNumber;
			return this;
		}
		public Builder balance(double balance) {
			this.balance = balance;
			return this;
		}
		public BasicAccountDTO build() {
			return new BasicAccountDTO(this);
		}
	}

	@Override
	public int compareTo(Object o) {
		BasicAccountDTO account = (BasicAccountDTO) o;
		
		if(this.getAccNumber() == account.getAccNumber()) {
			return 0;
		}
		
		return this.getAccNumber() > account.getAccNumber() ? 1 : -1;
	}
}
