package com.oguzcan.service;

import com.oguzcan.dao.GenericDAO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;


public class AdminServiceImpl extends AbstractService implements AdminService{

	GenericDAO dao;
	GenericDAO<AdminDTO> dao2;
	
	
	public void create() {
		
	}


	@Override
	public void createAdmin(AdminDTO admin) throws ClientAlreadyExistsException{
		dao2.create(admin);
		dao.create(admin);
	}
}
