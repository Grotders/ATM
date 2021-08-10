package com.oguzcan.dto;

public class TransactionHistoryDTO implements Comparable<Object>{
	
	private int transactionId;
	private String transactionType;
	private int accountNumber;
	
	public TransactionHistoryDTO(Builder builder) {
		this.transactionId = builder.transactionId;
		this.transactionType = builder.transactionType;
		this.accountNumber = builder.accountNumber;
	}
	public TransactionHistoryDTO() {
		
	}
	
//  ############################### INNER CLASS ################################
	public static class Builder {
		private int transactionId;
		private String transactionType;
		private int accountNumber;
		
		public Builder transactionId(int transactionId) {
			this.transactionId = transactionId;
			return this;
		}
		public Builder transactionType(String transactionType) {
			this.transactionType = transactionType;
			return this;
		}
		public Builder accountNumber(int accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}
		public TransactionHistoryDTO build() {
			return new TransactionHistoryDTO(this);
		}
	}

	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Override
	public int compareTo(Object o) {
		TransactionHistoryDTO history = (TransactionHistoryDTO) o;
		
		if(transactionId == history.getTransactionId()) {
			return 0;
		}
		
		return transactionId > history.getTransactionId() ? 1 : -1;
	}
	
	@Override
	public String toString() {
		return "No: " + transactionId + " " + transactionType + " ";
	}
	
	
	
}
