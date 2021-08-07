package com.oguzcan.service;

public class PasswordValidatorService implements ValidationService{

	@Override
	public void validate(String password) {
		if(password.length() < 10)
			return;
	}

	
}
