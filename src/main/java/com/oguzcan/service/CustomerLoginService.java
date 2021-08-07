package com.oguzcan.service;

import com.oguzcan.dto.CustomerDTO;

public class CustomerLoginService implements LoginService<CustomerDTO>{

	
	
	@Override
	public CustomerDTO login(String username, String password) {
		
		
		return new CustomerDTO();
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	
}
