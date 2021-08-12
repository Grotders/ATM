package com.oguzcan.service;

import java.util.Set;

import com.oguzcan.dao.AccountDAO;
import com.oguzcan.dto.AccountDTO;
import com.oguzcan.ex.BackPreviousMenuException;
import com.oguzcan.ex.InsufficientFundsException;
import com.oguzcan.ex.NoProperInfoException;
import com.oguzcan.ex.NoProperNumberException;
import com.oguzcan.ex.NoSuchAccountException;
import com.oguzcan.ex.ValidationException;
import com.oguzcan.util.validator.NumberValidator;
import com.oguzcan.util.validator.Validator;

public class CustomerServiceImpl implements CustomerService{
	Validator numberValidator = new NumberValidator();
	AccountDAO accountDao = new AccountDAO();
	
	
	@Override
	public void changePassword() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(AccountDTO account, String input) throws NoProperNumberException, ValidationException, 
							InsufficientFundsException{
		if(input.trim().equals("0"))
			return;
		
		numberValidator.validate(input);
		double amount = Double.parseDouble(input);
		double balance = account.getBalance();
		if(balance < amount) {
			throw new InsufficientFundsException("Bakiye yetersiz. Tekrar deneyiniz.");
		}
		account.setBalance(balance-amount);
		accountDao.update(account);
	}

	@Override
	public void deposit(AccountDTO account, String input) throws NoProperNumberException, ValidationException{
		if(input.trim().equals("0"))
			return;
			
		numberValidator.validate(input);
		double amount = Double.parseDouble(input);
		double balance = account.getBalance();
		account.setBalance(balance+amount);
		accountDao.update(account);
	}

	@Override
	public void eft() {
		// TODO Auto-generated method stub
		
	}
	
	public AccountDTO findAccount(Set<AccountDTO> list, int accountNumber) throws NoSuchAccountException{
		
		for(AccountDTO temp : list) {
			if(temp.getAccNumber() == accountNumber)
				return temp;
		}
		throw new NoSuchAccountException("Yanlış hesap numarası girdiniz. Tekrar deneyiniz.");
	}

	public int checkInput(String accountId) throws BackPreviousMenuException, NoProperInfoException, ValidationException{
		accountId = accountId.trim();
		if(accountId.equals("0"))
			throw new BackPreviousMenuException();
		
		numberValidator.validate(accountId);
		
		return Integer.parseInt(accountId);
	}

}
