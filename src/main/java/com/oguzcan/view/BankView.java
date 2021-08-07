package com.oguzcan.view;

import java.util.Scanner;

public class BankView {

private Scanner keyboard = new Scanner(System.in);
	
	public void displayWelcome() {
		System.out.println("Unicorn Bankasına hoşgeldiniz!\n");
		System.out.println("Giriş yapınız");
	}
	
	public void displayAdminPanel() {
		System.out.println("Admin Panel:\n");
		System.out.println("1-) Kullanıcı oluştur.");
		System.out.println("2-) Kullanıcı güncelleme.");
		System.out.println("3-) Kullanıcı sil.");
		System.out.println("4-) Kullanıcı inceleme.");
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
	
	
}
