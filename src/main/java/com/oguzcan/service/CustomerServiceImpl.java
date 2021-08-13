package com.oguzcan.service;

import java.util.Set;

import com.oguzcan.dao.AccountDAO;
import com.oguzcan.dao.CustomerDAO;
import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.BackPreviousMenuException;
import com.oguzcan.ex.InsufficientFundsException;
import com.oguzcan.ex.NoProperInfoException;
import com.oguzcan.ex.NoProperNumberException;
import com.oguzcan.ex.NoProperPasswordException;
import com.oguzcan.ex.NoSuchAccountException;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.ValidationException;
import com.oguzcan.util.validator.NumberValidator;
import com.oguzcan.util.validator.PasswordValidator;
import com.oguzcan.util.validator.Validator;

public class CustomerServiceImpl extends AbstractService implements CustomerService{
	Validator numberValidator = new NumberValidator();
	Validator passwordValidator = new PasswordValidator();
	CustomerDAO customerDao = new CustomerDAO();
	AccountDAO accountDao = new AccountDAO();
	
	
	
	
	@Override
	public void deposit(AccountDTO account, String input) throws NoProperNumberException, ValidationException{
		if(input.trim().equals("0"))
			return;
			
		numberValidator.validate(input);
		double amount = Double.parseDouble(input);
		double balance = account.getBalance();
		account.setBalance(balance+amount);
		accountDao.update(account);
		accountDao.createTransaction("Para yatırma " + input + "TL.", account.getAccNumber());
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
		accountDao.createTransaction("Para çekme " + input + "TL.", account.getAccNumber());
	}

	

	@Override
	public void eft (AccountDTO account, String target, String input) throws NoProperNumberException, ValidationException, 
								InsufficientFundsException, NoSuchClientException {
		if(input.equals("0"))
			return;
		
		numberValidator.validate(input);
		double amount = Double.parseDouble(input);
		double balance = account.getBalance();
		if(balance < amount) {
			throw new InsufficientFundsException("Bakiye yetersiz. Tekrar deneyiniz.");
		}
		account.setBalance(balance-amount);
		accountDao.update(account);
		accountDao.createTransaction(target + " numaralı hesaba " + amount + "TL eft gönderme." , account.getAccNumber());
		AccountDTO targetAccount = accountDao.retrieve(target);
		balance = targetAccount.getBalance();
		targetAccount.setBalance(balance + amount);
		accountDao.update(targetAccount);
		accountDao.createTransaction(account.getAccNumber() + " numaralı hesaptan " + amount + "TL eft geldi." , targetAccount.getAccNumber());
	}
	
	public AccountDTO findAccount(Set<AccountDTO> list, int accountNumber) throws NoSuchAccountException{
		for(AccountDTO temp : list) {
			if(temp.getAccNumber() == accountNumber)
				return temp;
		}
		throw new NoSuchAccountException("Yanlış hesap numarası girdiniz. Tekrar deneyiniz.");
	}
	
	public Set<TransactionHistoryDTO> findHistory(AccountDTO account) {
		return accountDao.retrieveTransactionHistory(account.getAccNumber());
	}

	public int checkInput(String accountId) throws BackPreviousMenuException, NoProperInfoException, ValidationException{
		accountId = accountId.trim();
		if(accountId.equals("0"))
			throw new BackPreviousMenuException();
		
		numberValidator.validate(accountId);
		
		return Integer.parseInt(accountId);
	}

	public void verifyAccountNumber(String target) throws NoProperNumberException, ValidationException, NoSuchClientException{
		if(target.equals("0"))
			return;
		
		numberValidator.validate(target);

		AccountDTO account = accountDao.retrieve(target);
	}
	
	@Override
	public void changePassword(CustomerDTO customer, String password) throws NoProperPasswordException, ValidationException{
		passwordValidator.validate(password);
		customer.setPassword(password);
		customerDao.update(customer);
	}

}
