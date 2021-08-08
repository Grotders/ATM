package com.oguzcan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.oguzcan.dto.CustomerDTO;
import com.oguzcan.dto.PersonalInformationDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoSuchClientException;

public class CustomerDAO implements GenericDAO<CustomerDTO>{
	private ResultSet rs;
	private CustomerDTO customerDto;
	private PreparedStatement stmt;
	
	@Override
	public void create(CustomerDTO customer) throws ClientAlreadyExistsException{
		String sql = "insert into mydb.customer(username, password) values(?,?)";
		
		try(Connection connection = dbConnection()) {
			// INSERT customer
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, customer.getUsername());
			stmt.setString(2, customer.getPassword());
			stmt.executeUpdate();
			
		} catch (MySQLIntegrityConstraintViolationException ex) {
			throw new ClientAlreadyExistsException("Kullanıcı adı kullanımda. Farklı kullanıcı adıyla tekrar deneyiniz!");
		} catch (SQLException ex) {
			System.out.println("AdminDAO create sqlException.");
		}
		
		try(Connection connection = dbConnection()) {
			// INSERT personal information
			sql = "insert into mydb.info(first_name, last_name, phone_number) values(?,?,?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, customer.getInfo().getName());
			stmt.setString(2, customer.getInfo().getLastname());
			stmt.setString(3, customer.getInfo().getPhoneNumber());
			stmt.executeUpdate();
	
			System.out.println("Kullanıcı başarıyla oluşturuldu.");
		} catch (MySQLIntegrityConstraintViolationException ex) {
			
		} catch (SQLException ex) {
			System.out.println("AdminDAO create sqlException.");
		}
	}

	@Override
	public void update(CustomerDTO customer, int id) {
		String sql = "update mydb.customer set username=?, password=? where customer_id=?";
		
		try(Connection connection = dbConnection()) {
			// Update customer 
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, customer.getUsername());
			stmt.setString(2, customer.getPassword());
			stmt.setInt(3, id);
			stmt.executeUpdate();
			// Update info
			sql = "update mydb.info set first_name=?, last_name=?, phone_number where customer_id=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, customer.getInfo().getName());
			stmt.setString(2, customer.getInfo().getLastname());
			stmt.setString(3, customer.getInfo().getPhoneNumber());
			stmt.setInt(4, id);
			stmt.executeUpdate();
			
			System.out.println("Güncelleme başarılı.");
		} catch(SQLException ex) {
			System.out.println("hata update");
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void delete(int customerId) throws NoSuchClientException{
		String sql = "delete from mydb.info where customer_id=?";
				
		try (Connection connection = dbConnection()) {
			// DELETE info table
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, customerId);
			stmt.executeUpdate();
			// DELETE transaction history table
			sql = "delete from mydb.transaction_history where customer_id=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, customerId);
			stmt.executeUpdate();
			// DELETE account table
			sql = "delete from mydb.account where customer_id=?";			
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, customerId);
			stmt.executeUpdate();
			// DELETE customer table
			sql = "delete from mydb.customer where customer_id=?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, customerId);
			stmt.executeUpdate();
			
			System.out.println("Kullanıcı başarıyla silindi.");
		} catch (SQLException ex) {
			System.out.println("hata delete");
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public CustomerDTO retrieve(String input) throws NoSuchClientException{
		String sql = "SELECT * FROM mydb.customer inner join mydb.info "
				+ "on customer.customer_id = info.customer_id "
				+ "where customer.customer_id = ?";
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(input));
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				PersonalInformationDTO info = new PersonalInformationDTO.Builder()
						.name(rs.getString("first_name")).lastname("last_name").phoneNumber("phone_number").build();
				customerDto = new CustomerDTO.Builder()
						.accountList(null).username(rs.getString("username")).password(rs.getString("password")).info(info).build();
			}
			if(customerDto == null) {
				throw new NoSuchClientException("Böyle bir müşteri yok. Tekrar deneyiniz!\n");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return customerDto;
		
	}

	@Override
	public CustomerDTO retrieveById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public int bringId(CustomerDTO customer) throws NoSuchClientException {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
