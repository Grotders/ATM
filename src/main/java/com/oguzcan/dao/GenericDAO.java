package com.oguzcan.dao;

import java.sql.Connection;

import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.util.DatabaseUtil;


public interface GenericDAO<T> {

	// CRUD
	void create(T t) throws ClientAlreadyExistsException;
	void update(T t);
	void delete(T t);
	T retrieve(String username) throws NoSuchClientException;
	void refresh(T t);
	
	default Connection dbConnection() {
		DatabaseUtil databaseUtil = new DatabaseUtil();
		return databaseUtil.databaseConnectionDb();
	}
}
