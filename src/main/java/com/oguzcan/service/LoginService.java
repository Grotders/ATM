package com.oguzcan.service;

import com.oguzcan.dto.Client;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.WrongClientCredentialsException;

public interface LoginService <T extends Client> {
	
	public T login(String username, String password) throws WrongClientCredentialsException, NoSuchClientException;
	public void logout();
}
