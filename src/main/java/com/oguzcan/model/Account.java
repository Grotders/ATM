package com.oguzcan.model;

import java.io.Serializable;

public abstract class Account implements Serializable{
	private static final long serialVersionUID = -1695262772023643158L;

	private long accNumber;
	private double balance;
	
	
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
