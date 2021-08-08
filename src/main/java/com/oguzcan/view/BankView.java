package com.oguzcan.view;

import java.util.Set;

import com.oguzcan.dto.AdminDTO;

public class BankView {

	
	public void displayWelcome() {
		System.out.println("Unicorn Bankasının Admin Sistemine Hoşgeldiniz!\n");
		System.out.println("Giriş yapınız:");
	}
	
	public void displayAdminPanel() {
		System.out.println("Admin Panel:\n");
		System.out.println("1-) Kullanıcı oluştur.");
		System.out.println("2-) Kullanıcı incele.");
		System.out.print("\nSeçiminizi giriniz: ");
	}

//1 ############################### CREATE PANEL ###############################
	public void displayAdminCreatePanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Kullanıcı oluştur.\n");
		System.out.println("PlaceHolder");
		System.out.println("1-) Admin oluştur.");
		System.out.println("2-) Müşteri oluştur.");
		System.out.print("\nSeçiminizi giriniz: ");
	}
	// 1-1
	public void displayCreateAdminPanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Admin oluştur.\n");
		System.out.println("Oluşturulacak admin kullanıcısının bilgilerini giriniz.");
	}
	// 1-2
	public void displayCreateCustomerPanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Müşteri oluştur.\n");
		System.out.println("Oluşturulacak müşteri kullanıcısının bilgilerini giriniz.");
	}

//2 ############################### FETCH PANEL ################################
	public void displayAdminFetchPanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Kullanıcı incele.\n");
		System.out.println("İncelemek istediğiniz kullanıcının tipini seçiniz: ");
		System.out.println("1-) Admin ");
		System.out.println("2-) Müşteri ");
		System.out.print("\nSeçiminizi giriniz: ");
	}
	// 2-1
	public void displayAdminListPanel(Set<AdminDTO> adminList) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Admin incele.\n");
		
		System.out.println("Sisteme kayıtlı adminlerin listesi:");
		System.out.println("Admin List placeholder!");

		System.out.print("Incelemek istediğiniz adminin id'sini giriniz: ");
	}
	// 2-1-1
	public void displayFetchedAdminPanel(int id, String username) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Id=" + id + " username=" + username +"\n");
		
		System.out.println("Gerçekleştirmek istediğiniz işlemi seçiniz:");
		System.out.println("1-) Kullanıcı bilgilerini güncelle.");
		System.out.println("2-) Kullanıcıyı sil.");
	}
	// 2-1-1-1
	public void displayAdminUpdatePanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Admin güncelle.\n");
	}
	// 2-1-1-2
	public void displayAdminDeletePanel(int id, String username) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Admin sil.\n");
		System.out.println("Id=" + id + " " + "username="+ username +" kullanıcı silinecek. Devam etmek için (Y/y) giriniz.");
	}
	// 2-2
	public void displayCustomerListPanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Müşteri incele.\n");
		
		System.out.println("Sisteme kayıtlı müşterilerin listesi:");
		System.out.println("Müşteri List placeholder!");
		
		System.out.print("Incelemek istediğiniz müşterinin id'sini giriniz: ");
	}
	//2-1-1 	#### CUSTOMER ####
	public void displayFetchedCustomerPanel(int id, String name, String lastname) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Id=" + id + " " + name + " " + "lastname" + "\n");
		
		System.out.println("Gerçekleştirmek istediğiniz işlemi seçiniz:");
		System.out.println("1-) Kullanıcı bilgilerini güncelle.");
		System.out.println("2-) Kullanıcıya hesap oluştur. (Basic, Business)");
		System.out.println("3-) Kullanıcı hesaplarını incele.");
		System.out.println("4-) Kullanıcı işlem geçmişini göster.");
		System.out.println("5-) Kullanıcıyı sil.");
	}
	
	// 2-1-1-1
	public void displayCustomerUpdatePanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Müşteri güncelle.\n");
	}
	// 2-1-2-2
	public void displayCreateAccountPanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Hesap oluştur.\n");
		System.out.println("Oluşturulacak müşteri hesabının bilgilerini giriniz.");
	}
	// 2-1-2-3 		
	public void displayAccountListPanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Hesap incele.\n");
		
		System.out.println("Sisteme kayıtlı müşterinin hesaplarının listesi:");
		System.out.println("Account List placeholder!");

		System.out.print("Incelemek istediğiniz hesap numarasını giriniz: ");
	}
	// 2-1-2-3-1 	#### ACCOUNT ####
	public void displayFetchedAccountPanel(int accountNo, double balance, String accountType) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: " + accountNo + " " + balance + " " + accountType + "\n");
		
		System.out.println("Gerçekleştirmek istediğiniz işlemi seçiniz:");
		System.out.println("1-) Hesabın bakiyesini değiştir.");
		System.out.println("2-) Hesap tipini değiştir. (Basic->Business, Business->Basic)");
		System.out.println("3-) Hesabı sil");
	}
	// 2-1-2-3-1-1
	public void displayAccountBalanceUpdatePanel(double balance) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Bakiye güncelle.\n");
		System.out.println("Mevcut bakiye: " + balance);
		System.out.println("Bakiye giriniz (min 0):");
	}
	// 2-1-2-3-1-2
	public void displayAccountTypeChangePanel(String accounType) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Tip değiştir.\n");
		System.out.println("Mevcut hesabınızın tipi: " + accounType);
		System.out.println("Hesap tipi değişecek, devam etmek için (Y/y) giriniz. (Basic->Business, Business->Basic)");
	}
	// 2-1-2-3-1-3
	public void displayAccountDeletePanel(int id, String name, String lastname) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Müşteri sil.\n");
		System.out.println("Id=" + id + " " + name + " " + lastname + " kullanıcı silinecek. Devam etmek için (Y/y) giriniz.");
	}
	
	// 2-1-2-4
	public void displayTransactionHistoryPanel() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: İşlem geçmişi.");
		System.out.println("Kullanıcının işlem geçmişi asağıda görüntülenmektedir.");
		System.out.println("Transaction history place holder");
		System.out.println("Devam etmek için herhangi bir tuşa basınız.");
	}
	// 2-1-2-5
	public void displayCustomerDeletePanel(int id, String name, String lastname) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Müşteri sil.\n");
		System.out.println("Id=" + id + " " + name + " " + lastname + " kullanıcı silinecek. Devam etmek için (Y/y) giriniz.");
	}
	
}
