package com.oguzcan.model;

import java.io.Serializable;

public class BasicAccount extends Account implements Serializable{
	private static final long serialVersionUID = 8327228743131606428L;


	public BasicAccount(Builder builder) {
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
		public BasicAccount build() {
			return new BasicAccount(this);
		}
	}
}
