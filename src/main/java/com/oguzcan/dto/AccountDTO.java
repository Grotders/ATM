package com.oguzcan.dto;

import java.io.Serializable;

public abstract class AccountDTO implements Serializable, Comparable<Object>{
	private static final long serialVersionUID = -1695262772023643158L;

	private int accNumber;
	private double balance;
	private int customerId;
	
//	######################  GETTER & SETTER ################################
	public int getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	@Override
	public String toString() {
		return accNumber + " balance=" + balance;
	}
}
