package com.oguzcan.view;

public class BankView {

	
	public void displayWelcome() {
		System.out.println("Unicorn Bankasına hoşgeldiniz!\n");
		System.out.println("Giriş yapınız");
	}
	
	public void displayAdminPanel() {
		System.out.println("Admin Panel:\n");
		System.out.println("1-) Kullanıcı oluştur.");
		System.out.println("2-) Kullanıcı inceleme.");
		System.out.print("\nSeçiminizi giriniz: ");
	}
	
	public void displayAdminCreatePanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel:\n");
		System.out.println("1-) Admin oluştur.");
		System.out.println("2-) Müşteri oluştur.");
		System.out.print("\nSeçiminizi giriniz: ");
	}
	
	public void displayAdminCreateAdminPanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Admin oluşturma.\n");
		System.out.println("Oluşturulacak admin kullanıcısının bilgilerini giriniz.");
	}
	
	public void displayAdminCreateCustomerPanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Customer oluşturma.\n");
		System.out.println("Oluşturulacak müşteri kullanıcısının bilgilerini giriniz.");
	}
	
	public void displayAdminFetchPanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: incelemek istediğiniz kullanıcının tipini seçiniz.\n");
		System.out.println("1-) Admin ");
		System.out.println("2-) Müşteri ");
		System.out.print("\nSeçiminizi giriniz: ");
	}
	
	public void displayAdminFetchAdminPanel() {
		
	}
	
	public void displayAdminFetchCustomerPanel() {
			
		}

	
}
