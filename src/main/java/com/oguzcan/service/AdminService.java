package com.oguzcan.service;

import java.util.Set;

import com.oguzcan.controller.MenuAdminEnums;
import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoProperNumberException;
import com.oguzcan.ex.NoProperPasswordException;
import com.oguzcan.ex.NoProperUsernameException;
import com.oguzcan.ex.NoSuchAccountException;
import com.oguzcan.ex.NoSuchUserException;
import com.oguzcan.ex.ValidationException;

public interface AdminService{
	

	
	public void createAdmin(AdminDTO admin) throws ClientAlreadyExistsException, NoProperUsernameException,
					NoProperPasswordException, ValidationException;
	
	public void createCustomer(CustomerDTO customer) throws ClientAlreadyExistsException, 
					NoProperPasswordException, ValidationException;
	
	public void createAccount(AccountDTO account) ;
	
	public Set<AdminDTO> fetchAdminList() throws NoSuchUserException;
	public Set<CustomerDTO> fetchCustomerList() throws NoSuchUserException;
	public Set<AccountDTO> fetchAccountList(int customerId) throws NoSuchUserException;
	public Set<TransactionHistoryDTO> fetchTransactionHistory(int accountNumber);
	
	public void updateAccount(AccountDTO updatedAccount);
	public void updateAdmin(AdminDTO updatedAdmin) throws NoProperUsernameException, NoProperPasswordException, 
					ValidationException ;
	public void updateCustomer(CustomerDTO updatedCustomer) throws NoProperUsernameException, NoProperPasswordException, 
					ValidationException ;
	
	void deleteAdmin(AdminDTO admin) ;
	void deleteAccount(AccountDTO account) ;
	void deleteCustomer(CustomerDTO customer) ;
	
	public MenuAdminEnums getEnum(String index, int plus);
	
	public AdminDTO findAdmin(Set<AdminDTO> list, String adminId)  throws NoSuchUserException, NoProperNumberException, 
					ValidationException;
	
	public AccountDTO findAccount(Set<AccountDTO> list, String accountNumber) throws NoProperNumberException, NoSuchAccountException,
					ValidationException ;
	
	public CustomerDTO findCustomer(Set<CustomerDTO> list, String customerId)  throws NoSuchUserException, NoProperNumberException, 
					ValidationException;
	
	public double convertProperDouble(String input) throws NoProperNumberException, ValidationException;
}