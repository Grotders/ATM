package com.oguzcan.model;

public class TestClass {

	public static void main(String[] args) {
		TestClass test = new TestClass();
		
//		test.customerTest();
		test.accountTest();
	}
	
	
	public void customerTest() {
		
		Customer custom = new Customer.Builder().username("oguzcan").password("12345").build();
		
		System.out.println(custom);
		
	}
	
	public void accountTest() {
		
		Account acc = new Account.Builder().accNumber(11222).balance(5000).build();
		System.out.println(acc);
	}
}
