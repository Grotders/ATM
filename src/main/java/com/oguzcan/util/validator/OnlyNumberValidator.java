package com.oguzcan.util.validator;

import com.oguzcan.ex.NoProperInfoException;

public class OnlyNumberValidator implements Validator{

	@Override
	public void validate(String info) throws NoProperInfoException {
		int lenght = info.length();
		
		if(info.contains("-"))
			throw new NoProperInfoException(info + " negatif olmamalıdır. Tekrar deneyiniz: ");
		
		boolean flag = false;
		for(int i=0; i < lenght; i++) {
			char c = info.charAt(i);
			if(Character.isLetter(c)) {
				flag = true;
				break;
			}
		}
		
		if(flag) {
			throw new NoProperInfoException(info + " harf içeremez. Tekrar deneyiniz.");
		}
	}
}
