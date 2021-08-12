package com.oguzcan.util.validator;

import com.oguzcan.ex.NoProperNumberException;
import com.oguzcan.ex.ValidationException;

public class SwitchNumberValidator implements Validator{

	@Override
	public void validate(String input) throws ValidationException {
		
		if(input.length() > 1) {
			throw new NoProperNumberException("Hatalı giriş yaptınız. Tekrar deneyiniz.");
		}
		
		char c = input.charAt(0);
		if(Character.isLetter(c)) {
			throw new NoProperNumberException("Lütfen numara giriniz.");
		}
		
		
	}

}
