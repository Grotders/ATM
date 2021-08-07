package com.oguzcan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.oguzcan.dto.CustomerDTO;
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
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, customer.getUsername());
			stmt.setString(2, customer.getPassword());
			stmt.executeUpdate();
			System.out.println("Kullanıcı başarıyla oluşturuldu.");
		} catch (MySQLIntegrityConstraintViolationException ex) {
			throw new ClientAlreadyExistsException("Kullanıcı adı kullanımda. Farklı kullanıcı adıyla tekrar deneyiniz!");
		} catch (SQLException ex) {
			System.out.println("AdminDAO create sqlException.");
		}
	}

	@Override
	public void update(CustomerDTO customer, int id) {
		String sql = "update mydb.customer set username=?, password=? where id=?";
		
		try(Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, customer.getUsername());
			stmt.setString(2, customer.getPassword());
			stmt.setInt(3, id);
			stmt.executeUpdate();
			System.out.println("Güncelleme başarılı.");
		} catch(SQLException ex) {
			System.out.println("hata update");
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void delete(CustomerDTO customer) {
		String sql = "delete from mydb.admin where admin.username=?";

		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, customer.getUsername());
			stmt.executeUpdate();
			System.out.println("Kullanıcı başarıyla silindi.");
		} catch (SQLException ex) {
			System.out.println("hata delete");
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public CustomerDTO retrieve(String input) {
String sql = "select * from mydb.admin where admin.username=?";
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, input);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				//customerDto = new CustomerDTO.Builder()
				//		.username(rs.getString("username")).password(rs.getString("password")).builder();
			}
			if(customerDto == null) {
				//throw new NoSuchClientException("Böyle bir kullanıcı yok. Tekrar deneyiniz!\n");
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
