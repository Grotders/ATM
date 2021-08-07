package com.oguzcan.service;

import com.oguzcan.dto.Client;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.WrongCustomerCredentialsException;

public interface LoginService <T extends Client> {
	
	public T login(String username, String password) throws WrongCustomerCredentialsException, NoSuchClientException;
	public void logout();
}
