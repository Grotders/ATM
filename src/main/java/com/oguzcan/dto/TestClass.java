package com.oguzcan.dto;

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
		
		CustomerDTO custom = new CustomerDTO.Builder().username("oguzcan").password("12345").build();
		
		System.out.println(custom);
		
	}
	
	public void accountTest() {
		
		//Account acc = new Account.Builder().accNumber(11222).balance(5000).build();
		//System.out.println(acc);
	}
	
	public void basicAccount() {
		AccountDTO acc = new BasicAccountDTO.Builder().accNumber(1122).balance(2222).build();
		System.out.println(acc);
	}
	
	public void businessAccount() {
		BusinessAccountDTO acc = new BusinessAccountDTO.Builder().accNumber(2223).balance(9999).build();
		System.out.println(acc);
	}
	
	public void personalInformation() {
		PersonalInformationDTO info = new PersonalInformationDTO.Builder().name("oguzcan").lastname("bicer").phoneNumber("05111111111").build();
		System.out.println(info);
	}
}
