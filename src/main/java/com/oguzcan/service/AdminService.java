package com.oguzcan.service;

import java.util.Set;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;

public interface AdminService<T> {
	
	public void create(T t);
	public void createAdmin(AdminDTO admin) throws ClientAlreadyExistsException;
	public void createCustomer(CustomerDTO customer) throws ClientAlreadyExistsException;
	public void createAccount(AccountDTO account) throws ClientAlreadyExistsException;
	
	public Set<AdminDTO> fetchAdminList() ;
	
	
}
