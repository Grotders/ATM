package com.oguzcan.service;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.oguzcan.controller.MenuAdminEnums;
import com.oguzcan.dao.AccountDAO;
import com.oguzcan.dao.AdminDAO;
import com.oguzcan.dao.CustomerDAO;
import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.BasicAccountDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoProperPasswordException;
import com.oguzcan.ex.NoProperUsernameException;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.ValidationException;
import com.oguzcan.util.validator.CharacterValidator;
import com.oguzcan.util.validator.NumberValidator;
import com.oguzcan.util.validator.PasswordValidator;
import com.oguzcan.util.validator.UsernameValidator;
import com.oguzcan.util.validator.Validator;


public class AdminServiceImpl extends AbstractService implements AdminService{

	
	AccountDAO accountDao = new AccountDAO();
	AdminDAO adminDao = new AdminDAO();
	CustomerDAO customerDao = new CustomerDAO();
	Validator passwordValidator = new PasswordValidator();
	Validator usernameValidator = new UsernameValidator();
	Validator onlyCharacterValidator = new CharacterValidator();
	Validator onlyNumberValidator = new NumberValidator();
	

// ################################## CREATE ##################################
	@Override
	public void createAdmin(AdminDTO admin) throws ClientAlreadyExistsException, NoProperUsernameException,
					NoProperPasswordException, ValidationException{
		usernameValidator.validate(admin.getUsername());
		passwordValidator.validate(admin.getPassword());
		adminDao.create(admin);
	}
	@Override
	public void createCustomer(CustomerDTO customer) throws ClientAlreadyExistsException, NoProperUsernameException,
					NoProperPasswordException, ValidationException {
		usernameValidator.validate(customer.getUsername());
		passwordValidator.validate(customer.getPassword());
		int accNumber = customerDao.create(customer);
		AccountDTO account =  customer.getAccountList().iterator().next();
		account.setCustomerId(accNumber);
		createAccount(account);
		
	}
	@Override
	public void createAccount(AccountDTO account) {
		accountDao.create(account);
		Iterator<AccountDTO> iter = accountDao.retrieveAll(account.getCustomerId()).iterator();
		AccountDTO lastAccount = new BasicAccountDTO();
		while(iter.hasNext()) {
			lastAccount = iter.next();
		}
		accountDao.createTransaction("hesap oluşturma", lastAccount.getAccNumber());
	}

// ################################## FETCH ##################################
	@Override
	public Set<AdminDTO> fetchAdminList() throws NoSuchClientException{
		Set<AdminDTO> list = new TreeSet<AdminDTO>();


		list = adminDao.retrieveAll();
		if(list.isEmpty())
			throw new NoSuchClientException("Hiç admin bulunmamaktadır.");
			
		return list;
	}
	@Override
	public Set<CustomerDTO> fetchCustomerList() throws NoSuchClientException{
		Set<CustomerDTO> list = new TreeSet<CustomerDTO>();
		
		list = customerDao.retrieveAll();
		if(list.isEmpty())
			throw new NoSuchClientException("Hiç müşteri bulunmamaktadır.");
			
		
		return list;
	}
	@Override
	public Set<AccountDTO> fetchAccountList(int accountNumber) throws NoSuchClientException{
		Set<AccountDTO> list = new TreeSet<AccountDTO>();
		list = accountDao.retrieveAll(accountNumber);
		if(list.isEmpty())
			throw new NoSuchClientException("Müşterinin hiç hesabı bulunmamaktadır.");
			
		return list;
	}
	@Override
	public Set<TransactionHistoryDTO> fetchTransactionHistory(int customerId) {
		Set<TransactionHistoryDTO> list = new TreeSet<TransactionHistoryDTO>();
		list = accountDao.retrieveTransactionHistory(customerId);
		
		return list;
	}

// ################################## UPDATE ###################################
	@Override
	public void updateAccount(AccountDTO updatedAccount) {
		
		accountDao.update(updatedAccount);
	}
	@Override
	public void updateAdmin(AdminDTO updatedAdmin) throws NoProperUsernameException, NoProperPasswordException, 
					ValidationException {
		usernameValidator.validate(updatedAdmin.getUsername());
		passwordValidator.validate(updatedAdmin.getPassword());
		adminDao.update(updatedAdmin);
	}
	@Override
	public void updateCustomer(CustomerDTO updatedCustomer) throws NoProperUsernameException, NoProperPasswordException, 
					ValidationException {
		usernameValidator.validate(updatedCustomer.getUsername());
		passwordValidator.validate(updatedCustomer.getPassword());
		onlyCharacterValidator.validate(updatedCustomer.getInfo().getName());
		onlyCharacterValidator.validate(updatedCustomer.getInfo().getLastname());
		onlyNumberValidator.validate(updatedCustomer.getInfo().getPhoneNumber());
		customerDao.update(updatedCustomer);
	}
	
	
// ################################## DELETE ###################################
	@Override
	public void deleteAdmin(AdminDTO admin){
		adminDao.delete(admin);
	}
	@Override
	public void deleteCustomer(CustomerDTO customer){
		customerDao.delete(customer);
	}
	@Override
	public void deleteAccount(AccountDTO account)  {
		accountDao.delete(account);
	}
	
// ################################## UTILS ###################################

	@Override
	public MenuAdminEnums getEnum(int index, int plus) {
		// 9 universal back command :)
		if(index == 9)
			return MenuAdminEnums.BACK;
		else if(index == 8)
			return MenuAdminEnums.EXIT;
		
		MenuAdminEnums[] enumArray = MenuAdminEnums.values();
		
		index += plus;
		if(enumArray.length < index) {
			return MenuAdminEnums.ERROR;
		}
		return enumArray[index];
	}
	
}
