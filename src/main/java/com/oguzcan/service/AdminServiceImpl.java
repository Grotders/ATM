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
import com.oguzcan.dto.PersonalInformationDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoProperNumberException;
import com.oguzcan.ex.NoProperPasswordException;
import com.oguzcan.ex.NoProperUsernameException;
import com.oguzcan.ex.NoSuchAccountException;
import com.oguzcan.ex.NoSuchUserException;
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
	
	Validator usernameValidator = new UsernameValidator();
	Validator passwordValidator = new PasswordValidator();
	Validator characterValidator = new CharacterValidator();
	Validator numberValidator = new NumberValidator();
	

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
		PersonalInformationDTO info = customer.getInfo();
		
		usernameValidator.validate(customer.getUsername());
		passwordValidator.validate(customer.getPassword());
		characterValidator.validate(info.getName());
		characterValidator.validate(info.getLastname());
		numberValidator.validate(info.getPhoneNumber());
		
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
	public Set<AdminDTO> fetchAdminList() throws NoSuchUserException{
		Set<AdminDTO> list = new TreeSet<AdminDTO>();


		list = adminDao.retrieveAll();
		if(list.isEmpty())
			throw new NoSuchUserException("Hiç admin bulunmamaktadır.");
			
		return list;
	}
	@Override
	public Set<CustomerDTO> fetchCustomerList() throws NoSuchUserException{
		Set<CustomerDTO> list = new TreeSet<CustomerDTO>();
		
		list = customerDao.retrieveAll();
		if(list.isEmpty())
			throw new NoSuchUserException("Hiç müşteri bulunmamaktadır.");
			
		
		return list;
	}
	@Override
	public Set<AccountDTO> fetchAccountList(int accountNumber) throws NoSuchUserException{
		Set<AccountDTO> list = new TreeSet<AccountDTO>();
		list = accountDao.retrieveAll(accountNumber);
		if(list.isEmpty())
			throw new NoSuchUserException("Müşterinin hiç hesabı bulunmamaktadır.");
			
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
		characterValidator.validate(updatedCustomer.getInfo().getName());
		characterValidator.validate(updatedCustomer.getInfo().getLastname());
		numberValidator.validate(updatedCustomer.getInfo().getPhoneNumber());
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
	public MenuAdminEnums getEnum(String index, int plus) {
		// 9 universal back command :)
		if(index.equals("0"))
			return MenuAdminEnums.BACK;
		
		// Input Check
		try {
			numberValidator.validate(index);
			
		} catch (ValidationException ex) {
			return MenuAdminEnums.ERROR;
		} 
		int input = Integer.parseInt(index);
		
		
		MenuAdminEnums[] enumArray = MenuAdminEnums.values();
		input += plus;
		
		if(enumArray.length < input) {
			return MenuAdminEnums.ERROR;
		}
		
		return enumArray[input];
	}
	
	@Override
	public AdminDTO findAdmin(Set<AdminDTO> adminList, String input) throws NoSuchUserException, NoProperNumberException, 
						ValidationException{
		numberValidator.validate(input);
		int adminId = Integer.parseInt(input);
		

		for(AdminDTO temp:adminList) {
			if(temp.getAdminId() == adminId) {
				return temp;
			} 
		}
		throw new NoSuchUserException("Adminin id'si yanlış girdiniz. ");
	}
	
	@Override
	public CustomerDTO findCustomer(Set<CustomerDTO> customerList, String input)  throws NoSuchUserException, NoProperNumberException, 
						ValidationException {
		numberValidator.validate(input);
		int customerId = Integer.parseInt(input);
		
		for(CustomerDTO temp:customerList) {
			if(temp.getCustomerId() == customerId) {
				return temp;
			} 
		}
		throw new NoSuchUserException("Müşterinin id'si yanlış girdiniz. ");
	}
	
	@Override
	public AccountDTO findAccount(Set<AccountDTO> list, String input) throws NoProperNumberException, NoSuchAccountException,
							ValidationException {
		
		numberValidator.validate(input);
		int accountNumber = Integer.parseInt(input);
		
		for(AccountDTO temp : list) {
			if(temp.getAccNumber() == accountNumber)
				return temp;
		}
		throw new NoSuchAccountException("Yanlış hesap numarası girdiniz. Tekrar deneyiniz.");
	}
	
	@Override
	public double convertProperDouble(String input) throws NoProperNumberException, ValidationException{
		numberValidator.validate(input);
		return Double.parseDouble(input);
	}
}
