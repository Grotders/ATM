package com.oguzcan.service;

import java.util.Set;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoProperPasswordException;
import com.oguzcan.ex.NoProperUsernameException;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.ValidationException;

public interface AdminService{
	

	
	public void createAdmin(AdminDTO admin) throws ClientAlreadyExistsException, 
					NoProperPasswordException, ValidationException;
	
	public void createCustomer(CustomerDTO customer) throws ClientAlreadyExistsException, 
					NoProperPasswordException, ValidationException;
	
	public void createAccount(AccountDTO account) ;
	
	public Set<AdminDTO> fetchAdminList() throws NoSuchClientException;
	public Set<CustomerDTO> fetchCustomerList() throws NoSuchClientException;
	public Set<AccountDTO> fetchAccountList(int customerId) throws NoSuchClientException;
	public Set<TransactionHistoryDTO> fetchTransactionHistory(int customerId);
	
	public void updateAccount(AccountDTO updatedAccount);
	public void updateAdmin(AdminDTO updatedAdmin) throws NoProperUsernameException, NoProperPasswordException, 
					ValidationException ;
	public void updateCustomer(CustomerDTO updatedCustomer) throws NoProperUsernameException, NoProperPasswordException, 
					ValidationException ;
	
	void deleteAdmin(AdminDTO admin) ;
	void deleteAccount(AccountDTO account) ;
	void deleteCustomer(CustomerDTO customer) ;
	
	
}