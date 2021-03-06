package com.oguzcan.dto;

import java.io.Serializable;

public class BusinessAccountDTO extends AccountDTO implements Serializable{
	private static final long serialVersionUID = 4032117271828292161L;

	// Ticari hesap
	
	public BusinessAccountDTO(Builder builder) {
		super.setAccNumber(builder.accNumber);
		super.setBalance(builder.balance);
		super.setCustomerId(builder.customerId);
	}
	public BusinessAccountDTO() {
		
	}
	
//  ############################### INNER CLASS ################################
	public static class Builder {
		private int accNumber;
		private double balance;
		private int customerId;
		
		public Builder accNumber(int accNumber) {
			this.accNumber = accNumber;
			return this;
		}
		public Builder balance(double balance) {
			this.balance = balance;
			return this;
		}
		public Builder customerId(int customerId) {
			this.customerId = customerId;
			return this;
		}
		public BusinessAccountDTO build() {
			return new BusinessAccountDTO(this);
		}
	}

	@Override
	public int compareTo(Object o) {
		AccountDTO account = (AccountDTO) o;
		
		if(this.getAccNumber() == account.getAccNumber()) {
			return 0;
		}
		
		return this.getAccNumber() > account.getAccNumber() ? 1 : -1;
	}
}
