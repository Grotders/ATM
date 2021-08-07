package com.oguzcan.dto;

public class TestClass {

	public static void main(String[] args) {
		TestClass test = new TestClass();
		
//		test.customerTest();
//		test.accountTest();
//		test.basicAccount();
//		test.businessAccount();
//		test.personalInformation();
		
		test.interesting();
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
		AccountDTO acc = new BusinessAccountDTO.Builder().accNumber(2223).balance(9999).build();
		System.out.println(acc);
	}
	
	public void personalInformation() {
		PersonalInformationDTO info = new PersonalInformationDTO.Builder().name("oguzcan").lastname("bicer").phoneNumber("05111111111").build();
		System.out.println(info);
	}
	

	private void interesting() {
		AccountDTO acc = new BasicAccountDTO.Builder().accNumber(1122).balance(2222).build();
		AccountDTO acc2 = new BusinessAccountDTO.Builder().accNumber(2223).balance(9999).build();	
		
		System.out.println(acc.getClass().getName());
		System.out.println(acc.getClass());
		System.out.println();
		System.out.println(acc2.getClass().getName());
		System.out.println(acc2.getClass());
		System.out.println();
		
		AccountDTO acc3 = acc;
	
		System.out.println(acc3.getClass().getSimpleName());
		System.out.println(acc3.getClass());
		
		System.out.println("BusinessAccountDTO".length());
		interesting2(acc2);
		interesting2(acc);
	}
	private void interesting2(AccountDTO account) {
		System.out.println(account.getClass().getSimpleName());
	}
}
