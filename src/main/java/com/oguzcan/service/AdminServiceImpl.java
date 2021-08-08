package com.oguzcan.service;

import com.oguzcan.dao.AccountDAO;
import com.oguzcan.dao.AdminDAO;
import com.oguzcan.dao.CustomerDAO;
import com.oguzcan.dao.GenericDAO;
import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;


public class AdminServiceImpl extends AbstractService implements AdminService<GenericDAO>{

	GenericDAO dao;
	
	
	@Override
	public void create(GenericDAO input) {
		// yok tıkandım
	}


	@Override
	public void createAdmin(AdminDTO admin) throws ClientAlreadyExistsException{
		dao = new AdminDAO();
		dao.create(admin);
	}


	@Override
	public void createCustomer(CustomerDTO customer) throws ClientAlreadyExistsException {
		dao = new CustomerDAO();
		dao.create(customer);
		
	}


	@Override
	public void createAccount(AccountDTO account) throws ClientAlreadyExistsException {
		dao = new AccountDAO();
		dao.create(account);
	}
}
