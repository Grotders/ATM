package com.oguzcan.model;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = -1695262772023643158L;

	private long accNumber;
	private double balance;
	
	public Account(Builder builder) {
		this.accNumber = builder.accNumber;
		this.balance = builder.balance;
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
		public Account build() {
			return new Account(this);
		}
	}


	
//	######################  GETTER & SETTER ################################
	public long getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(long accNumber) {
		this.accNumber = accNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	@Override
	public String toString() {
		return "Account [accNumber=" + accNumber + ", balance=" + balance + "]";
	}
}
