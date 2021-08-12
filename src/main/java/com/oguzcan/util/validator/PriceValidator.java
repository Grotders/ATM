package com.oguzcan.util.validator;

import com.oguzcan.ex.NoProperInfoException;
import com.oguzcan.ex.ValidationException;

public class PriceValidator implements Validator{

	@Override
	public void validate(String input) throws ValidationException {
		input = input.trim();
		int lenght = input.length();
		
		
		if(input.contains("-"))
			throw new NoProperInfoException("Girdiğiniz değer negatif olmamalıdır. Tekrar deneyiniz: ");
		
		for(int i=0; i < lenght; i++) {
			char c = input.charAt(i);
			if(Character.isLetter(c)) {
				throw new NoProperInfoException("Girdiğiniz değer harf içeremez. Tekrar deneyiniz.");
			}
		}
		Double.parseDouble(input);
	}

	
	
}
