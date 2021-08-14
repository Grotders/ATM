package com.oguzcan.controller;

public enum MenuAdminEnums {
	BACK,													// 0
	CREATE, FETCH, 										// 1, 2				+0	  		1	
	ADMIN, CUSTOMER,										// 3, 4				+2			2
	BASIC, BUSINESS, TYPE, TRANSACTION_HISTORY,			// 5, 6, 7, 8       +4			3	
	UPDATE, DELETE, CREATE_ACCOUNT, FETCH_ACCOUNTS, 		// 9, 10, 11, 12	+8			4										// 13, 14			+12			5
	ERROR; 													// 13				JOKER
}

