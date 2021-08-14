package com.oguzcan.util.validator;

import com.oguzcan.ex.NoProperNumberException;

public class NumberValidator implements Validator{

	@Override
	public void validate(String info) throws NoProperNumberException {
		int lenght = info.length();
		
		if(info.contains("-"))
			throw new NoProperNumberException("Girdiğiniz değer negatif olmamalıdır. ");
		
		if(lenght > 18) {
			throw new NoProperNumberException("Girdiğiniz sayı çok uzun. Lütfen daha kısa sayılar giriniz.");
		}
		
		for(int i=0; i < lenght; i++) {
			char c = info.charAt(i);
			if(Character.isLetter(c)) {
				throw new NoProperNumberException("Girdiğiniz değer harf içeremez. ");
			}
		}

	}
}
