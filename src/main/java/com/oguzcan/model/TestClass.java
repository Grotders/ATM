package com.oguzcan.model;

public class TestClass {

	public static void main(String[] args) {
		TestClass test = new TestClass();
		
//		test.customerTest();
//		test.accountTest();
//		test.basicAccount();
//		test.businessAccount();
		test.personalInformation();
	}
	
	
	public void customerTest() {
		
		Customer custom = new Customer.Builder().username("oguzcan").password("12345").build();
		
		System.out.println(custom);
		
	}
	
	public void accountTest() {
		
		//Account acc = new Account.Builder().accNumber(11222).balance(5000).build();
		//System.out.println(acc);
	}
	
	public void basicAccount() {
		Account acc = new BasicAccount.Builder().accNumber(1122).balance(2222).build();
		System.out.println(acc);
	}
	
	public void businessAccount() {
		BusinessAccount acc = new BusinessAccount.Builder().accNumber(2223).balance(9999).build();
		System.out.println(acc);
	}
	
	public void personalInformation() {
		PersonalInformation info = new PersonalInformation.Builder().name("oguzcan").lastname("bicer").phoneNumber("05111111111").build();
		System.out.println(info);
	}
}
