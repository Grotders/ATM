package com.oguzcan.view;

import java.util.Set;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.TransactionHistoryDTO;

public class BankView {

	
	public void displayWelcome() {
		System.out.println("Unicorn Bankasının Admin Sistemine Hoşgeldiniz!\n");
		System.out.println("Giriş yapınız:");
	}
	
	public void displayAdminMenu() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel:\n");
		System.out.println("1-) Kullanıcı oluştur.");
		System.out.println("2-) Kullanıcı incele.");
		System.out.println("3-) Çıkış yap.");
		System.out.print("\nSeçiminizi giriniz: ");
	}

//1 ############################### CREATE PANEL ###############################
	public void displayAdminCreateView() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Kullanıcı oluştur.\n");
		
		System.out.println("1-) Admin oluştur.");
		System.out.println("2-) Müşteri oluştur.");
		System.out.println("3-) Geri.");
		System.out.print("\nSeçiminizi giriniz: ");
	}
	// 1-1
	public void displayCreateAdminView() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Admin oluştur.\n");
		System.out.println("Oluşturulacak admin kullanıcısının bilgilerini giriniz.");
	}
	// 1-2
	public void displayCreateCustomerView() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Müşteri oluştur.\n");
		System.out.println("Oluşturulacak müşteri kullanıcısının bilgilerini giriniz.");
	}

//2 ############################### FETCH PANEL ################################
	public void displayAdminFetchMenuView() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Kullanıcı incele.\n");
		System.out.println("İncelemek istediğiniz kullanıcının tipini seçiniz: ");
		System.out.println("1-) Admin ");
		System.out.println("2-) Müşteri ");
		System.out.println("3-) Geri");		
		System.out.print("\nSeçiminizi giriniz: ");
	}
	// 2-1
	public void displayAdminListView(Set<AdminDTO> adminList) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Admin incele.\n");
		
		System.out.println("Sisteme kayıtlı adminlerin listesi:");
		for(AdminDTO temp: adminList) 
			System.out.println(temp);
		System.out.println();

		System.out.print("Incelemek istediğiniz adminin id'sini giriniz: ");
	}
	// 2-1-1
	public void displayFetchedAdminMenuView(AdminDTO fetchedAdmin) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Admin Id=" + fetchedAdmin.getAdminId() 
							   + " Username=" + fetchedAdmin.getUsername() +"\n");
		
		System.out.println("Gerçekleştirmek istediğiniz işlemi seçiniz:");
		System.out.println("1-) Kullanıcı bilgilerini güncelle.");
		System.out.println("2-) Kullanıcıyı sil.");
		System.out.println("3-) Geri.");
		System.out.print("\nSeçiminizi giriniz: ");
	}
	// 2-1-1-1
	public void displayUpdateAdminView() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Admin güncelle.\n");
		System.out.println("Güncellemek istediğiniz bilgileri giriniz. Boş bıraktığınız bilgiler değişmeyecektir:");
	}
	// 2-1-1-2
	public void displayDeleteAdminView(AdminDTO fetchedAdmin) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Admin sil.\n");
		System.out.println( fetchedAdmin +" kullanıcı silinecek.");
		System.out.print("\nDevam etmek için (Y/y) giriniz:");
	}
	// 2-2
	public void displayCustomerListView(Set<CustomerDTO> customerList) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Müşteri incele.\n");
		
		System.out.println("Sisteme kayıtlı müşterilerin listesi:");
		for(CustomerDTO temp: customerList) 
			System.out.println(temp);
		System.out.println();
		
		System.out.print("Incelemek istediğiniz müşterinin id'sini giriniz: ");
	}
	//2-1-1 	#### CUSTOMER ####
	public void displayFetchedCustomerMenuView(CustomerDTO fetchedCustomer) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: " + fetchedCustomer.getCustomerId() + " " + fetchedCustomer.getInfo().getName() 
										+ " " + fetchedCustomer.getInfo().getLastname() + "\n");
		
		System.out.println("Gerçekleştirmek istediğiniz işlemi seçiniz:");
		System.out.println("1-) Kullanıcı bilgilerini güncelle.");
		System.out.println("2-) Kullanıcıyı sil.");
		System.out.println("3-) Kullanıcıya hesap oluştur. (Basic, Business)");
		System.out.println("4-) Kullanıcı hesaplarını incele.");
		System.out.println("5-) Geri.");
		System.out.print("\nSeçiminizi giriniz: ");
	}
	
	// 2-1-1-1
	public void displayUpdateCustomerView() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Müşteri güncelle.\n");
		System.out.println("Güncellemek istediğiniz bilgileri giriniz. Boş bıraktığınız bilgiler değişmeyecektir:");

	}
	
	// 2-1-1-2
		public void displayDeleteCustomerView(CustomerDTO fetchedCustomer) {
			System.out.println("\n\n\n\n\n\n");
			System.out.println("Admin Panel: Müşteri sil.\n");
			System.out.println(fetchedCustomer + " kullanıcı silinecek.");
			System.out.print("\nDevam etmek için (Y/y) giriniz:");
		}
	// 2-1-2-3
	public void displayCreateAccountView() {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Hesap oluştur.\n");
		System.out.println("Oluşturulacak müşteri hesabının bilgilerini giriniz.");
		System.out.println("Oluşturulacak hesabın tipini belirleyiniz:\n");
		System.out.println("1-) Standart Hesap.");
		System.out.println("2-) Ticari Hesap.");
		System.out.print("\nSeçiminizi giriniz: ");
	}
	// 2-1-2-4 		
	public void displayAccountListView(Set<AccountDTO> accountList) throws NullPointerException {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Hesap incele.\n");
		
		System.out.println("Sisteme kayıtlı müşterinin hesaplarının listesi:");
		
		for(AccountDTO temp: accountList) 
			System.out.println(temp);
		System.out.println();
		System.out.print("Incelemek istediğiniz hesap numarasını giriniz: ");
		
	}
	// 2-1-2-3-1 	#### ACCOUNT ####
	public void displayFetchedAccountMenuView(AccountDTO fetchedAccount) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println(fetchedAccount.getAccNumber() + " " +
				fetchedAccount.getClass().getSimpleName().replace("AccountDTO", "").toLowerCase()
				+ "bakiye: " + fetchedAccount.getBalance());
		
		System.out.println("Gerçekleştirmek istediğiniz işlemi seçiniz:");
		System.out.println("1-) Hesabın bakiyesini değiştir.");
		System.out.println("2-) Hesabı sil");
		System.out.println("3-) Hesap tipini değiştir. (Basic->Business, Business->Basic)");
		System.out.println("4-) Geri.");
		System.out.print("\nSeçiminizi giriniz: ");
	}
	// 2-1-2-3-1-1
	public void displayUpdateBalanceView(AccountDTO fetchedAccount) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Bakiye güncelle.\n");
		System.out.println("Mevcut bakiye: " + fetchedAccount.getBalance());
		System.out.println("Bakiye giriniz (min 0):");
	}
	// 2-1-2-3-1-2
	public void displayDeleteAccountView(AccountDTO fetchedAccount) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Müşteri sil.\n");
		System.out.println( fetchedAccount +" hesap silinecek.");
		System.out.print("\nDevam etmek için (Y/y) giriniz:");
	}
	// 2-1-2-3-1-3
	public void displayChangeAccountTypeView(AccountDTO fetchedAccount) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Tip değiştir.\n");
		System.out.println("Mevcut hesabınızın tipi: " 
				+ fetchedAccount.getClass().getSimpleName().replace("AccountDTO", "").toLowerCase());
		System.out.println("Hesap tipi değişecek, devam etmek için (Y/y) giriniz. (Basic->Business, Business->Basic)");
	}
	// 2-1-2-4
	public void displayTransactionHistoryView(Set<TransactionHistoryDTO> history) {
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Admin Panel: Hesap İşlem geçmişi.");
		System.out.println("Hesabın işlem geçmişi asağıda görüntülenmektedir.");
		for(TransactionHistoryDTO temp: history) 
			System.out.println(temp);
		System.out.println("Devam etmek için herhangi bir tuşa basınız.");
	}
}
