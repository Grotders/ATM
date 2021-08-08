package com.oguzcan.service;

import com.oguzcan.dao.AdminDAO;
import com.oguzcan.dao.GenericDAO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;


public class AdminServiceImpl extends AbstractService implements AdminService{

	GenericDAO dao;
	
	
	public void create() {
		
	}


	@Override
	public void createAdmin(AdminDTO admin) throws ClientAlreadyExistsException{
		AdminDAO aDao = (AdminDAO) dao;
		aDao.create(admin);
		
	}
}
