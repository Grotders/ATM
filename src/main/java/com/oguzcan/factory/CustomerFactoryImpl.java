package com.oguzcan.factory;

import java.util.TreeSet;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.PersonalInformationDTO;

public class CustomerFactoryImpl implements CustomerFactory{

	@Override
	public CustomerDTO create(String username, String password, String name,
			String lastname, String phoneNumber) {

		PersonalInformationDTO info = new PersonalInformationDTO.Builder()
				.name(name).lastname(lastname).phoneNumber(phoneNumber).build();
		
		CustomerDTO customer = new CustomerDTO.Builder()
				.accountList(new TreeSet<AccountDTO>()).username(username)
				.password(password).info(info).build();
		
		return customer;
	}
}
