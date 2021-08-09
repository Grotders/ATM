package com.oguzcan.factory;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.BasicAccountDTO;
import com.oguzcan.dto.BusinessAccountDTO;

public class AccountFactoryImpl implements AccountFactory{

	// BasicAccountDTO
	// BusinessAccountDTO
	@Override
	public AccountDTO create(int accNumber, double balance, String accountType){
		
		if(accountType.equals("business")) {
			return new BusinessAccountDTO.Builder()
					.accNumber(accNumber).balance(balance).build();
		}
		
		// basic account
		return new BasicAccountDTO.Builder()
					.accNumber(accNumber).balance(balance).build();
	}
}
