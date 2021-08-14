package com.oguzcan.util.validator;

import com.oguzcan.ex.NoProperPasswordException;

public class PasswordValidator implements Validator{

	@Override
	public void validate(String password) throws NoProperPasswordException{
		int length = password.length();
		
		if(password == null | length == 0) {
			throw new NoProperPasswordException("Şifreniz boş olamaz. Tekrar deneyiniz.");
		}

		if(length < 5 || length > 18) {
			throw new NoProperPasswordException("Şifreniz en az 5 karakter ve en fazla 18 karakter olmalıdır. Tekrar deneyiniz.");
		}
	}
}
