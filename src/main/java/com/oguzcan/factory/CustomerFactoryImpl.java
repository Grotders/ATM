package com.oguzcan.factory;

import java.util.Set;
import java.util.TreeSet;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.PersonalInformationDTO;

public class CustomerFactoryImpl implements CustomerFactory{
	AccountFactory aFactory = new AccountFactoryImpl();
	
	@Override
	public CustomerDTO create(String username, String password, String name,
			String lastname, String phoneNumber) {
	
		
		PersonalInformationDTO info = new PersonalInformationDTO.Builder()
				.name(name).lastname(lastname).phoneNumber(phoneNumber).build();
		
		Set<AccountDTO> aList = new TreeSet<AccountDTO>();
		AccountDTO account = aFactory.create(0, 0, "basic");
		aList.add(account);
		
		CustomerDTO customer = new CustomerDTO.Builder()
				.accountList(aList).username(username)
				.password(password).info(info).build();
		
		return customer;
	}
}
