package com.oguzcan.factory;

import com.oguzcan.dto.TransactionHistoryDTO;

public class TransactionHistoryFactoryImpl implements TransactionHistoryFactory{

	@Override
	public TransactionHistoryDTO create(int transactionId, String transactionType, int accountNumber){
		
		TransactionHistoryDTO history = new TransactionHistoryDTO.Builder()
				.transactionId(transactionId)
				.transactionType(transactionType)
				.transactionDate(transactionType)
				.accountNumber(accountNumber)
				.build();
		
		return history;
	}
}
