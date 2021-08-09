package com.oguzcan.service;

import com.oguzcan.dao.AdminDAO;
import com.oguzcan.dao.GenericDAO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.ex.WrongClientCredentialsException;

public class AdminLoginService implements LoginService<AdminDTO>{

	private GenericDAO<AdminDTO> adminDao = new AdminDAO();

	
	@Override
	public AdminDTO login(String username, String password) 
			throws WrongClientCredentialsException, NoSuchClientException {
		
		AdminDTO admin = adminDao.retrieve(username);
		System.out.println(admin);
		loginAdmin(admin, password);
		
		return admin;
	}
	
	private void loginAdmin(AdminDTO admin, String password) 
			throws WrongClientCredentialsException{
		
	//	passwordValidator.validate(password);
		checkAdminPassword(admin, password);
		
	}
	
	private void checkAdminPassword(AdminDTO admin, String password) 
			throws WrongClientCredentialsException {
		
		if(!admin.getPassword().equals(password)) {
			throw new WrongClientCredentialsException("Parola Hatalı");
		}
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	
}
