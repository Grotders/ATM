package com.oguzcan.service;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;

public interface AdminService <T>{
	
	void create(T t);
	void createAdmin(AdminDTO admin) throws ClientAlreadyExistsException;
	void createCustomer(CustomerDTO customer) throws ClientAlreadyExistsException;
	void createAccount(AccountDTO account) throws ClientAlreadyExistsException;
}
