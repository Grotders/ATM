package com.oguzcan.service;

import java.util.Set;
import java.util.TreeSet;

import com.oguzcan.dao.AccountDAO;
import com.oguzcan.dao.AdminDAO;
import com.oguzcan.dao.CustomerDAO;
import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;


public class AdminServiceImpl<T> extends AbstractService implements AdminService{

	
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
		customerDao.create(customer);
	}
	@Override
	public void createAccount(AccountDTO account) throws ClientAlreadyExistsException {
		accountDao.create(account);
	}

// ################################## FETCH ##################################
	@Override
	public Set<AdminDTO> fetchAdminList() {
		Set<AdminDTO> list = new TreeSet<AdminDTO>();

		AdminDAO dao2 = adminDao;
		list = dao2.retrieveAll();
		
		return list;
	}
	
	@Override
	public void update() {
		
	}
	
	public static void main(String[] args) {
		
	}
}
