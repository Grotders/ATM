package com.oguzcan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.BasicAccountDTO;
import com.oguzcan.dto.BusinessAccountDTO;
import com.oguzcan.dto.TransactionHistoryDTO;
import com.oguzcan.ex.NoSuchClientException;

public class AccountDAO implements GenericDAO<AccountDTO> {
	private ResultSet rs;
	private AccountDTO accountDto;
	private PreparedStatement stmt;
	
	@Override
	public int create(AccountDTO account) {
		String sql = "insert into mydb.account(balance, account_type, customer_id) values(?,?,?)";
		try(Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, account.getBalance());
			stmt.setString(2, account.getClass().getSimpleName().replace("AccountDTO", "").toLowerCase());
			stmt.setInt(3, account.getCustomerId());
			stmt.executeUpdate();
			System.out.println("Kullanıcı başarıyla oluşturuldu.");
		} catch (SQLException ex) {
			System.out.println(ex);
			System.out.println(ex.getMessage()+ "ACCDAO BRO");
		}
		return 0;
	}

	@Override
	public void update(AccountDTO account) {
		String sql = "update mydb.account set username=?, password=? where account_number=?";
		
		try(Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, account.getBalance());
			stmt.setString(2, account.getClass().getSimpleName().replace("AccountDTO", "").toLowerCase());
			stmt.setInt(0, account.getAccNumber());
			stmt.executeUpdate();
			System.out.println("Güncelleme başarılı.");
		} catch(SQLException ex) {
			System.out.println("hata update");
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void delete(AccountDTO account) {
		String sql = "delete from mydb.account where account_number=?";

		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, account.getAccNumber());
			stmt.executeUpdate();
			System.out.println("Kullanıcı başarıyla silindi.");
		} catch (SQLException ex) {
			System.out.println("hata delete");
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
	}

	@Override // account_number
	public AccountDTO retrieve(String input) throws NoSuchClientException{
		String sql = "select * from mydb.account where account_number=?";
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(input));
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("account_type").equals("basic")) {
					accountDto = new BasicAccountDTO.Builder()
							.accNumber(rs.getInt("account_number")).balance(rs.getDouble("balance"))
							.customerId(rs.getInt("customer_id")).build();
				} else if(rs.getString("account_type").equals("business")) {
					accountDto = new BusinessAccountDTO.Builder()
							.accNumber(rs.getInt("account_number")).balance(rs.getDouble("balance"))
							.customerId(rs.getInt("customer_id")).build();
				}
				
			}
			if(accountDto == null) {
				throw new NoSuchClientException("Böyle bir hesap yok. Tekrar deneyiniz!\n");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
		return accountDto;
	}

	@Override // accountNo
	public AccountDTO retrieveById(int id) {
		String sql = "select * from mydb.account where accountNo=?";
		Set<AccountDTO> accountList = new TreeSet<AccountDTO>();
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("account_type").equals("basic")) {
					accountDto = new BasicAccountDTO.Builder()
							.accNumber(rs.getInt("account_number")).balance(rs.getDouble("balance"))
							.customerId(rs.getInt("customer_id")).build();
					
				} else if(rs.getString("account_type").equals("business")) {
					accountDto = new BusinessAccountDTO.Builder()
							.accNumber(rs.getInt("account_number")).balance(rs.getDouble("balance"))
							.customerId(rs.getInt("customer_id")).build();
					accountList.add(accountDto);
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
		return accountDto;
	}


	public Set<AccountDTO> retrieveAll(int customerId) {
		String sql = "select * from mydb.account where customer_id = ?";
		Set<AccountDTO> accountList = new TreeSet<AccountDTO>();
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, customerId);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("account_type").equals("basic")) {
					accountDto = new BasicAccountDTO.Builder()
							.accNumber(rs.getInt("account_number")).balance(rs.getDouble("balance"))
							.customerId(rs.getInt("customer_id")).build();
					
					accountList.add(accountDto);
				} else if(rs.getString("account_type").equals("business")) {
					accountDto = new BusinessAccountDTO.Builder()
							.accNumber(rs.getInt("account_number")).balance(rs.getDouble("balance"))
							.customerId(rs.getInt("customer_id")).build();
					accountList.add(accountDto);
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
		return accountList;
	}
//	String sql = "insert into mydb.account(balance, account_type, customer_id) values(?,?,?)";

	public void createTransaction(String type, int accountNumber) {
		String sql = "insert into mydb.transaction_history(transaction_type, transaction_date, account_number) values(?,?,?)";
		
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		
		try(Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, type);
			stmt.setString(2, currentTime);
			stmt.setInt(3, accountNumber);
			stmt.executeUpdate();
			System.out.println(type + " başarılı.");
		} catch (SQLException ex) {
			System.out.println(ex);
			System.out.println(ex.getMessage()+ "ACCDAO BRO");
		}
	}
	
	public Set<TransactionHistoryDTO> retrieveTransactionHistory(int accountNumber) {
		String sql = "select * from mydb.transaction_history where account_number = ?";
		Set<TransactionHistoryDTO> historyList = new TreeSet<TransactionHistoryDTO>();
		TransactionHistoryDTO history;
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, accountNumber);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String date;
				history = new TransactionHistoryDTO.Builder()
						.transactionId(rs.getInt("transaction_id"))
						.transactionType(rs.getString("transaction_type"))
						.accountNumber(rs.getInt("account_number")).build();
				historyList.add(history);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
		return historyList;
	}
}
