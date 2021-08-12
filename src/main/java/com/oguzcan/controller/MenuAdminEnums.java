package com.oguzcan.controller;

public enum MenuAdminEnums {
	CREATE, FETCH, 										// 0, 1				-1  		1	
	ADMIN, CUSTOMER,										// 2, 3				+1			2
	BASIC, BUSINESS, TYPE, TRANSACTION_HISTORY,			// 4, 5, 6, 7       +3			3
	EXIT, BACK,											// 8, 9				JOKER		
	UPDATE, DELETE, CREATE_ACCOUNT, FETCH_ACCOUNTS, 		// 10, 11, 12, 13	+9			4
	YES, NO, 												// 14, 15			+13			5
	ERROR; 													// 16				JOKER
	
	//1,2,3
}

enum AdminMenu1 { // 0-1   -1
	CREATE, FETCH, BACK; 
	//1,2
}
enum AdminMenu2 { // 2-3   +1
	ADMIN, CUSTOMER, BACK;
	// 3,4
}

enum AdminMenu3 { // 4-7   +4
	UPDATE, DELETE, CREATE_ACCOUNT, FETCH_ACCOUNTS, BACK;
	// 5,6,7,8
}

enum AdminMenu4 { // 10-13  +10
	BASIC, BUSINESS, TYPE, TRANSACTION_HISTORY;
	// 10,11,12,13
}

enum AdminMenu5 { // 14-15  +13
	YES, NO;
}

