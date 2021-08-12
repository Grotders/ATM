package com.oguzcan.view;

import java.util.Set;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.TransactionHistoryDTO;

public class CustomerView {

	StringBuilder sb;
	StringBuilder sb2;
	
	public void displayLogin1Menu() {
		sb = new StringBuilder();
		sb.append("\t\t\t").append("-".repeat(70)).append("\n");
		sb.append("\t\t\t").append("|").append("\t\t   ").append("Unicorn ATM BANK: Hoşgeldiniz").append("\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("Kullanıcı Adı: ").append("\t\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("-".repeat(70)).append("\n\n");
		sb.append("--> ");
		System.out.print(sb.toString());
	}
	
	public void displayLogin2Menu(String username) {
		sb = new StringBuilder();
		sb.append("\t\t\t").append("-".repeat(70)).append("\n");
		sb.append("\t\t\t").append("|").append("\t\t   ").append("Unicorn ATM BANK: Hoşgeldiniz").append("\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		
		if(username.length() > 13)
			sb.append("\t\t\t").append("|").append("\t   ").append("Kullanıcı Adı: ").append(username).append("\t\t\t").append("     |\n");
		else if(username.length() > 5)
			sb.append("\t\t\t").append("|").append("\t   ").append("Kullanıcı Adı: ").append(username).append("\t\t\t\t").append("     |\n");
		else
			sb.append("\t\t\t").append("|").append("\t   ").append("Kullanıcı Adı: ").append(username).append("\t\t\t\t\t").append("     |\n");
		
		sb.append("\t\t\t").append("|").append("\t   ").append("Şifre: ").append("\t\t\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("-".repeat(70)).append("\n\n");
		sb.append("--> ");
		System.out.print(sb.toString());
	}
	
	public void displayCustomerMenu() {
		sb = new StringBuilder();
		sb.append("\t\t\t").append("-".repeat(70)).append("\n");
		sb.append("\t\t\t").append("|").append("\t\t   ").append("Unicorn ATM BANK: Ana Menu").append("\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("1. Para Yatır").append("\t\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("2. Para Çek").append("\t\t\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("3. EFT").append("\t\t\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("4. Hesap durumu").append("\t\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("5. İşlem geçmişi").append("\t\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("6. Başvuru yap").append("\t\t\t\t\t").append("     |\n");		
		sb.append("\t\t\t").append("|").append("\t   ").append("7. Hesap Ayarları >").append("\t\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("0. Çıkış").append("\t\t\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("-".repeat(70)).append("\n\n");
		sb.append("--> ");
		System.out.print(sb.toString());
		
	}
//	------------------
//	---------------------------

	public void displayDepositView() {
		sb = new StringBuilder();
		sb.append("\t\t\t").append("-".repeat(70)).append("\n");
		sb.append("\t\t\t").append("|").append("\t\t   ").append("Unicorn ATM BANK: Para yatırma").append("\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append(" Yatırılacak tutarı giriniz:").append("\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("-".repeat(70)).append("\n\n");
		sb.append("--> ");
		System.out.print(sb.toString());
	}
	
	// Para Çekme
	// Para Yatırma
	// Para EFT
	public void displayFetchAccountView(Set<AccountDTO> accList, String message) {
		displaySpace();
		sb = new StringBuilder();
		sb.append("\t\t\t").append("-".repeat(70)).append("\n");
		sb.append("\t\t\t").append("|").append("\t\t   ").append("Unicorn ATM BANK: ").append(message).append("\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("İşlem yapılacak hesabınızı seçiniz:").append("\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("      Hesap Tipi    |    Hesap Numarası   |    Bakiye \t\t").append("     |\n");
		sb2 = new StringBuilder();
		for(AccountDTO temp: accList) {
			sb2.append("\t\t\t").append("|").append("\t").append(temp);
			if(temp.toString().length() < 37) {
				sb2.append("\t\t").append("     |").append(temp.toString().length() + "\n");
			}
			else {
				sb2.append("\t").append("     |").append(temp.toString().length() + "\n");
			}
		}
		sb.append(sb2);
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("0. Geri").append("\t\t\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("-".repeat(70)).append("\n\n");
		sb.append("--> ");
		System.out.print(sb.toString());
	}
	
	public void displayWithdrawView() {
		sb = new StringBuilder();
		sb.append("\t\t\t").append("-".repeat(70)).append("\n");
		sb.append("\t\t\t").append("|").append("\t\t   ").append("Unicorn ATM BANK: Para çekme").append("\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append(" Çekilecek tutarı giriniz:").append("\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("-".repeat(70)).append("\n\n");
		sb.append("--> ");
		System.out.print(sb.toString());
	}
	
	public void displayEFT1View() {
		sb = new StringBuilder();
		sb.append("\t\t\t").append("-".repeat(70)).append("\n");
		sb.append("\t\t\t").append("|").append("\t\t   ").append("Unicorn ATM BANK: EFT").append("\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append(" Gönderilecek hesabın hesap numarasını giriniz:").append("\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("-".repeat(70)).append("\n\n");
		sb.append("--> ");
		System.out.print(sb.toString());
	}
	
	public void displayEFT2View() {
		sb = new StringBuilder();
		sb.append("\t\t\t").append("-".repeat(70)).append("\n");
		sb.append("\t\t\t").append("|").append("\t\t   ").append("Unicorn ATM BANK: EFT").append("\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append(" Gönderilecek tutarı giriniz:").append("\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("-".repeat(70)).append("\n\n");
		sb.append("--> ");
		System.out.print(sb.toString());
	}
	
	public void displayFetchAccountHistoryView(Set<TransactionHistoryDTO> history) {
		sb = new StringBuilder();
		sb.append("\t\t\t").append("-".repeat(70)).append("\n");
		sb.append("\t\t\t").append("|").append("\t\t   ").append("Unicorn ATM BANK: İşlem geçmişi").append("\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("Hesabın işlem geçmişi:").append("\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb2 = new StringBuilder();
		for(TransactionHistoryDTO temp: history) {
			sb2.append("\t\t\t").append("|").append("\t   ").append(temp);
			if(temp.toString().length() > 50) {
				sb2.append("").append("     |").append(temp.toString().length() + "\n");
			}
			else {
				sb2.append("\t").append("     |").append(temp.toString().length() + "\n");
			}
		}
		sb.append(sb2);
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("\t   ").append("0. Geri").append("\t\t\t\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("-".repeat(70)).append("\n\n");
		sb.append("--> ");
		System.out.print(sb.toString());
	}

	public void displaySuccessView() {
		sb = new StringBuilder();
		sb.append("\t\t\t").append("-".repeat(70)).append("\n");
		sb.append("\t\t\t").append("|").append("\t\t   ").append("Unicorn ATM BANK:    ").append("\t\t\t").append("     |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("|").append("   ").append(" İşleminiz başarıyla gerçekleşmiştir. Ana menüye dönülüyor.").append("      |\n");
		sb.append("\t\t\t").append("|").append("\t".repeat(8)).append("     |\n");
		sb.append("\t\t\t").append("-".repeat(70)).append("\n\n");
		sb.append("--> ");
		System.out.print(sb.toString());
	}
	public static void main(String[] args) {
		CustomerView view = new CustomerView();
		view.displayEFT1View();
		view.displayEFT2View();
	}
	public void displaySpace() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	public void displayTestView() {
		displayCustomerMenu();
		this.displayCustomerMenu();
		this.displayDepositView();
		this.displayEFT1View();
		this.displayEFT2View();
		this.displayWithdrawView();
	}
}
