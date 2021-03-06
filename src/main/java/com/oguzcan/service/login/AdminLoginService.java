package com.oguzcan.service.login;

import com.oguzcan.dao.AdminDAO;
import com.oguzcan.dao.GenericDAO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.ex.NoSuchUserException;
import com.oguzcan.ex.WrongClientCredentialsException;

public class AdminLoginService implements LoginService<AdminDTO>{

	private GenericDAO<AdminDTO> adminDao = new AdminDAO();

	
	@Override
	public AdminDTO login(String username, String password) 
			throws WrongClientCredentialsException, NoSuchUserException {
		
		AdminDTO admin = adminDao.retrieve(username);
		loginAdmin(admin, password);
		
		return admin;
	}
	
	private void loginAdmin(AdminDTO admin, String password) 
			throws WrongClientCredentialsException{
		
		checkAdminPassword(admin, password);
		
	}
	
	private void checkAdminPassword(AdminDTO admin, String password) 
			throws WrongClientCredentialsException {
		
		if(!admin.getPassword().equals(password)) {
			throw new WrongClientCredentialsException("Parola Hatalı");
		}
	}
}
