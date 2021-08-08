package com.oguzcan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.oguzcan.dto.AccountDTO;
import com.oguzcan.dto.BasicAccountDTO;
import com.oguzcan.dto.BusinessAccountDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoSuchClientException;

public class AccountDAO implements GenericDAO<AccountDTO> {
	private ResultSet rs;
	private AccountDTO accountDto;
	private PreparedStatement stmt;
	
	@Override
	public void create(AccountDTO account) throws ClientAlreadyExistsException{
		String sql = "insert into mydb.account(balance, account_type) values(?,?)";
		
		try(Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, account.getBalance());
			stmt.setString(2, account.getClass().getSimpleName().replace("AccountDTO", "").toLowerCase());
			stmt.executeUpdate();
			System.out.println("Kullanıcı başarıyla oluşturuldu.");
		} catch (MySQLIntegrityConstraintViolationException ex) {
			throw new ClientAlreadyExistsException("Kullanıcı adı kullanımda. Farklı kullanıcı adıyla tekrar deneyiniz!");
		} catch (SQLException ex) {
			System.out.println("AdminDAO create sqlException.");
		}
	}

	@Override
	public void update(AccountDTO account) {
		String sql = "update mydb.account set username=?, password=? where account_number=?";
		
		try(Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, account.getBalance());
			stmt.setString(2, account.getClass().getSimpleName());
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
		String sql = "delete from mydb.admin where account_number=?";

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

	@Override
	public AccountDTO retrieve(String input) throws NoSuchClientException{
		String sql = "select * from mydb.customer where account_number=?";
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, input);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("account_type").equals("basic")) {
					accountDto = new BasicAccountDTO.Builder()
							.accNumber(rs.getInt("username")).balance(rs.getDouble("balance")).build();
				} else if(rs.getString("account_type").equals("business")) {
					accountDto = new BusinessAccountDTO.Builder()
							.accNumber(rs.getInt("username")).balance(rs.getDouble("balance")).build();
				}
				
			}
			if(accountDto == null) {
				throw new NoSuchClientException("Böyle bir hesap yok. Tekrar deneyiniz!\n");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return accountDto;
	}

	@Override
	public AccountDTO retrieveById(int id) {
		String sql = "select * from mydb.account where account_number=?";
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("account_type").equals("basic")) {
					accountDto = new BasicAccountDTO.Builder()
							.accNumber(rs.getInt("username")).balance(rs.getDouble("balance")).build();
				} else if(rs.getString("account_type").equals("business")) {
					accountDto = new BusinessAccountDTO.Builder()
							.accNumber(rs.getInt("username")).balance(rs.getDouble("balance")).build();
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return accountDto;
	}

}
