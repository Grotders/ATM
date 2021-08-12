package com.oguzcan.util.validator;

import com.oguzcan.ex.NoProperInfoException;

public class NumberValidator implements Validator{

	@Override
	public void validate(String info) throws NoProperInfoException {
		info = info.trim();
		int lenght = info.length();
		
		
		if(info.contains("-"))
			throw new NoProperInfoException("Girdiğiniz değer negatif olmamalıdır. Tekrar deneyiniz: ");
		
		for(int i=0; i < lenght; i++) {
			char c = info.charAt(i);
			if(Character.isLetter(c)) {
				throw new NoProperInfoException(" Girdiğiniz değer harf içeremez. Tekrar deneyiniz.");
			}
		}

	}
}
