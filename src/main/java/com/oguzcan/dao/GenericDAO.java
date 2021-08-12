package com.oguzcan.dao;

import java.sql.Connection;

import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoSuchClientException;
import com.oguzcan.util.DatabaseUtil;


public interface GenericDAO<T> {

	// CRUD
	int create(T t) throws ClientAlreadyExistsException;
	void update(T t);
	void delete(T t) throws NoSuchClientException;
	T retrieve(String username) throws NoSuchClientException;  // primary key ile getirme


	
	
	default Connection dbConnection() {
		DatabaseUtil databaseUtil = new DatabaseUtil();
		return databaseUtil.databaseConnectionDb();
	}
}
