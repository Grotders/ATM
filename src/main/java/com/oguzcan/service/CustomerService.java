package com.oguzcan.service;

import java.util.Set;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.BackPreviousMenuException;
import com.oguzcan.ex.InsufficientFundsException;
import com.oguzcan.ex.NoProperInfoException;
import com.oguzcan.ex.NoProperNumberException;
import com.oguzcan.ex.NoProperPasswordException;
import com.oguzcan.ex.NoSuchAccountException;
import com.oguzcan.ex.NoSuchUserException;
import com.oguzcan.ex.ValidationException;

public interface CustomerService {

	public void deposit(AccountDTO account, String input) throws NoProperNumberException, ValidationException;

	public void withdraw(AccountDTO account, String input) throws NoProperNumberException, ValidationException,
							InsufficientFundsException;
	
	public void eft(AccountDTO account, String target, String amount) throws NoProperNumberException, ValidationException, 
							InsufficientFundsException, NoSuchUserException;
	
	public void changePassword(CustomerDTO customer, String password) throws NoProperPasswordException, ValidationException;
	
	default void redirecting() {
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {
			System.out.println("Thread interrupted");
		}
	}

	AccountDTO findAccount(Set<AccountDTO> list, int accountNumber) throws NoSuchAccountException;

	Set<TransactionHistoryDTO> findHistory(AccountDTO account);

	int checkInput(String accountId) throws BackPreviousMenuException, NoProperInfoException, ValidationException;

	void verifyAccountNumber(String target) throws NoProperNumberException, ValidationException, NoSuchUserException;
}
