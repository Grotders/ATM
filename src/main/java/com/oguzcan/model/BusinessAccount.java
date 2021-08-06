package com.oguzcan.model;

import java.io.Serializable;

public class BusinessAccount extends Account implements Serializable{
	private static final long serialVersionUID = 4032117271828292161L;

	public BusinessAccount(Builder builder) {
		super.setAccNumber(builder.accNumber);
		super.setBalance(builder.balance);
	}
	
//  ############################### INNER CLASS ################################
	public static class Builder {
		private long accNumber;
		private double balance;
		
		public Builder accNumber(long accNumber) {
			this.accNumber = accNumber;
			return this;
		}
		public Builder balance(long balance) {
			this.balance = balance;
			return this;
		}
		public BusinessAccount build() {
			return new BusinessAccount(this);
		}
	}
}
