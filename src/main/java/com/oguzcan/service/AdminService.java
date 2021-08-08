package com.oguzcan.service;

import com.oguzcan.dto.AdminDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;

public interface AdminService {

	void createAdmin(AdminDTO admin) throws ClientAlreadyExistsException;
	
}
