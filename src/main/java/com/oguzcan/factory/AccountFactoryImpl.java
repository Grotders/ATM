package com.oguzcan.factory;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.BasicAccountDTO;
import com.oguzcan.dto.BusinessAccountDTO;

public class AccountFactoryImpl implements AccountFactory{

	// BasicAccountDTO
	// BusinessAccountDTO
	@Override
	public AccountDTO create(int accNumber, double balance, String accountType, int customerId){
		
		if(accountType.equals("business")) {
			return new BusinessAccountDTO.Builder()
					.accNumber(accNumber).balance(balance).customerId(customerId).build();
		}
		
		// basic account
		return new BasicAccountDTO.Builder()
					.accNumber(accNumber).balance(balance).customerId(customerId).build();
	}

	@Override
	public AccountDTO copy(AccountDTO account) {
		String accountType = account.getClass().getSimpleName().replace("AccountDTO", "").toLowerCase();
		
		if(accountType.equals("business")) {
			return new BusinessAccountDTO.Builder()
					.accNumber(account.getAccNumber()).balance(account.getBalance())
					.customerId(account.getCustomerId()).build();
		}
		
		// basic account
		return new BasicAccountDTO.Builder()
				.accNumber(account.getAccNumber()).balance(account.getBalance())
				.customerId(account.getCustomerId()).build();
	}
	
}
