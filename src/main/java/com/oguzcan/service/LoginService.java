package com.oguzcan.service;

import com.oguzcan.dto.User;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.WrongClientCredentialsException;

public interface LoginService <T extends User> {
	
	public T login(String username, String password) throws WrongClientCredentialsException, NoSuchClientException;
	public void logout();
}
