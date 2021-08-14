package com.oguzcan.util.validator;

import com.oguzcan.ex.NoProperUsernameException;

public class UsernameValidator implements Validator{

	@Override
	public void validate(String username) throws NoProperUsernameException{
		int lenght = username.length();
		
		if(username == null | lenght == 0) {
			throw new NoProperUsernameException("Kullanıcı adınız boş olamaz. Tekrar deneyiniz.");
		}


		if(lenght < 5 || lenght > 18) {
			throw new NoProperUsernameException("Kullanıcı adınız, en az 5 karakter ve en fazla 18 karakter olmalıdır. "
					+ "Tekrar deneyiniz.");
		}
		
		
		for(int i=0; i<lenght; i++) {
			char c = username.charAt(i);
			if(Character.isLetter(c) || Character.isDigit(c)) {
				
			}
		}
		
		if(username.matches("[^A-Za-z0-9 ]")) {
			throw new NoProperUsernameException("Kullanıcı adınız sadece harf ve rakamlardan oluşabilir.");
		}
		
		// At least one character needed
		boolean flag = false;
		for(int i=0; i < lenght; i++) {
			char c = username.charAt(i);
			if(Character.isLetter(c)) {
				flag = true;
				break;
			}
		}
		
		if(!flag) {
			throw new NoProperUsernameException("Kullanıcı adınızda en az 1 karakter harf içermeli. Tekrar deneyiniz.");
		}
	}
}
