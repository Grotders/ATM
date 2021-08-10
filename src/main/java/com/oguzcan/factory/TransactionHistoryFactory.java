package com.oguzcan.factory;

import com.oguzcan.dto.TransactionHistoryDTO;

public interface TransactionHistoryFactory {

	public TransactionHistoryDTO create(int transactionId, String transactionType, int accountNumber);
}
