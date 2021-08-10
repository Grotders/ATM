package com.oguzcan.service;

import java.util.Set;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;

public interface AdminService{
	

	
	public void createAdmin(AdminDTO admin) throws ClientAlreadyExistsException;
	public void createCustomer(CustomerDTO customer) throws ClientAlreadyExistsException;
	public void createAccount(AccountDTO account) ;
	
	public Set<AdminDTO> fetchAdminList() ;
	public Set<CustomerDTO> fetchCustomerList() ;
	public Set<AccountDTO> fetchAccountList(int customerId) ;
	public Set<TransactionHistoryDTO> fetchTransactionHistory(int customerId);
	
	public void updateAccount(AccountDTO updatedAccount);
	
	void deleteAdmin(AdminDTO admin) ;
	void deleteAccount(AccountDTO account) ;
	void deleteCustomer(CustomerDTO customer) ;
	
	
}