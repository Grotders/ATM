package com.oguzcan.dto;

import java.io.Serializable;

public class BusinessAccountDTO extends AccountDTO implements Serializable{
	private static final long serialVersionUID = 4032117271828292161L;

	public BusinessAccountDTO(Builder builder) {
		super.setAccNumber(builder.accNumber);
		super.setBalance(builder.balance);
	}
	public BusinessAccountDTO() {
		
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
		public BusinessAccountDTO build() {
			return new BusinessAccountDTO(this);
		}
	}
}
