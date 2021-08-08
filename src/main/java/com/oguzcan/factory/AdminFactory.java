package com.oguzcan.factory;

import com.oguzcan.dto.AdminDTO;

public interface AdminFactory {

	public AdminDTO create(String username, String password);
}
