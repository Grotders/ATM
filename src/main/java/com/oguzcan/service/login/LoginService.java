package com.oguzcan.service.login;

import com.oguzcan.dto.User;
import com.oguzcan.ex.NoSuchUserException;
import com.oguzcan.ex.WrongClientCredentialsException;

public interface LoginService <T extends User> {
	
	public T login(String username, String password) throws WrongClientCredentialsException, NoSuchUserException;
	
	default void redirecting() {
		try {
			System.out.println("Giriş başarılı! Yönlendiriliyorsunuz ...");
			System.out.println("\n\n\n\n\n\n");
			Thread.sleep(4500);
		} catch(InterruptedException e) {
			System.out.println("Thread interrupted");
		}
	}
}
