package com.oguzcan.service;

import java.util.Set;
import java.util.TreeSet;

import com.oguzcan.dao.AccountDAO;
import com.oguzcan.dao.AdminDAO;
import com.oguzcan.dao.CustomerDAO;
import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;


public class AdminServiceImpl extends AbstractService implements AdminService{

	
	AccountDAO accountDao = new AccountDAO();
	AdminDAO adminDao = new AdminDAO();
	CustomerDAO customerDao = new CustomerDAO();

	

// ################################## CREATE ##################################
	@Override
	public void createAdmin(AdminDTO admin) throws ClientAlreadyExistsException{
		adminDao.create(admin);
	}
	@Override
	public void createCustomer(CustomerDTO customer) throws ClientAlreadyExistsException {
		int accNumber = customerDao.create(customer);
		
		
	}
	@Override
	public void createAccount(AccountDTO account) {
		accountDao.create(account);
		Set<AccountDTO> list = accountDao.retrieveAll(account.getCustomerId());
		for(AccountDTO temp: list) {
			if(temp.getBalance() == 0 && account.getClass() == temp.getClass()) {
				account = temp;
			}
		}
		accountDao.createTransaction("hesap oluşturma", account.getAccNumber());
	}

// ################################## FETCH ##################################
	@Override
	public Set<AdminDTO> fetchAdminList() {
		Set<AdminDTO> list = new TreeSet<AdminDTO>();


		list = adminDao.retrieveAll();
		
		return list;
	}
	@Override
	public Set<CustomerDTO> fetchCustomerList() {
		Set<CustomerDTO> list = new TreeSet<CustomerDTO>();
		
		list = customerDao.retrieveAll();
		return list;
	}
	@Override
	public Set<AccountDTO> fetchAccountList(int customerId) {
		Set<AccountDTO> list = new TreeSet<AccountDTO>();
		list = accountDao.retrieveAll(customerId);
		
		return list;
	}
	@Override
	public Set<TransactionHistoryDTO> fetchTransactionHistory(int customerId) {
		Set<TransactionHistoryDTO> list = new TreeSet<TransactionHistoryDTO>();
		list = accountDao.retrieveTransactionHistory(customerId);
		
		return list;
	}
	
	@Override
	public void updateAccount(AccountDTO updatedAccount) {
		
		accountDao.update(updatedAccount);
	}
	
	
	
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
	
}
