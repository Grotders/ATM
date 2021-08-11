package com.oguzcan.factory;

import java.util.HashSet;
import java.util.Set;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.PersonalInformationDTO;

public class CustomerFactoryImpl implements CustomerFactory{
	AccountFactory aFactory = new AccountFactoryImpl();
	
	@Override
	public CustomerDTO create(String username, String password, int customerId, String name,
			String lastname, String phoneNumber) {
	
		
		PersonalInformationDTO info = new PersonalInformationDTO.Builder()
				.name(name).lastname(lastname).phoneNumber(phoneNumber).build();
		
		Set<AccountDTO> aList = new HashSet<AccountDTO>();
		AccountDTO account = aFactory.create(0, 0.0, "basic",0);
		aList.add(account);
		
		CustomerDTO customer = new CustomerDTO.Builder()
				.accountList(aList).username(username)
				.password(password).info(info).customerId(customerId).build();
		
		return customer;
	}

	@Override
	public CustomerDTO copy(CustomerDTO customer) {
		PersonalInformationDTO info = customer.getInfo();
		
		PersonalInformationDTO newInfo = new PersonalInformationDTO.Builder()
				.name(info.getName()).lastname(info.getLastname()).phoneNumber(info.getPhoneNumber()).build();
		
		CustomerDTO copyCustomer = new CustomerDTO.Builder()
				.accountList(customer.getAccountList()).username(customer.getUsername())
				.password(customer.getPassword()).info(newInfo)
				.customerId(customer.getCustomerId()).build();

		return copyCustomer;
	}
}
