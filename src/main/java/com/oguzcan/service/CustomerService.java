package com.oguzcan.service;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.ex.InsufficientFundsException;
import com.oguzcan.ex.NoProperNumberException;
import com.oguzcan.ex.NoProperPasswordException;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.ValidationException;

public interface CustomerService {

	public void changePassword(CustomerDTO customer, String password) throws NoProperPasswordException, ValidationException;
	public void withdraw(AccountDTO account, String input) throws NoProperNumberException, ValidationException,
							InsufficientFundsException;
	
	public void deposit(AccountDTO account, String input) throws NoProperNumberException, ValidationException;
	public void eft(AccountDTO account, String target, String amount) throws NoProperNumberException, ValidationException, 
							InsufficientFundsException, NoSuchClientException;
	
	default void redirecting() {
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {
			System.out.println("Thread interrupted");
		}
	}
}
