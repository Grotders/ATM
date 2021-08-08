package com.oguzcan.factory;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.BasicAccountDTO;
import com.oguzcan.dto.BusinessAccountDTO;
import com.oguzcan.ex.WrongAccountTypeException;

public class AccountFactoryImpl implements AccountFactory{

	// BasicAccountDTO
	// BusinessAccountDTO
	@Override
	public AccountDTO create(int accNumber, double balance, String accountType) throws WrongAccountTypeException{
		AccountDTO account;
		
		if(accountType.equals("BasicAccountDTO")) {
			return account = new BasicAccountDTO.Builder()
					.accNumber(accNumber).balance(balance).build();
			
		}
		else if(accountType.equals("BusinessAccountDTO")) {
			return account = new BusinessAccountDTO.Builder()
					.accNumber(accNumber).balance(balance).build();
		} 
		
		throw new WrongAccountTypeException("Bu tipte account tipi yok. Basic veya Business seçiniz.");
	}
	

}
