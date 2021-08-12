package com.oguzcan.util.validator;

import com.oguzcan.ex.NoProperInfoException;
import com.oguzcan.ex.ValidationException;

public class CharacterValidator implements Validator{

	@Override
	public void validate(String info) throws ValidationException {
		int lenght = info.length();
		
		for(int i=0; i < lenght; i++) {
			char c = info.charAt(i);
			if(Character.isDigit(c)) {
				throw new NoProperInfoException(info + " sayı içeremez. Tekrar deneyiniz.");
			}
		}
		
	}
}
