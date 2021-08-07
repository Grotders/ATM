package com.oguzcan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.oguzcan.controller.InputController;
import com.oguzcan.dto.AdminDTO;
import com.oguzcan.ex.ClientAlreadyExistsException;
import com.oguzcan.ex.NoSuchClientException;

public class AdminDAO implements GenericDAO<AdminDTO> {
	private ResultSet rs;
	private AdminDTO adminDto;
	private PreparedStatement stmt;
	
	@Override
	public void create(AdminDTO admin) throws ClientAlreadyExistsException{
		String sql = "insert into mydb.admin(username, password) values(?,?)";
		
		try(Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, admin.getUsername());
			stmt.setString(2, admin.getPassword());
			stmt.executeUpdate();
			
			System.out.println("Kullanıcı başarıyla oluşturuldu.");
		} catch (MySQLIntegrityConstraintViolationException ex) {
			throw new ClientAlreadyExistsException("Kullanıcı adı kullanımda. Farklı kullanıcı adıyla tekrar deneyiniz!");
		} catch (SQLException ex) {
			System.out.println("AdminDAO create sqlException.");
		}
		
	}

	@Override
	public void update(AdminDTO admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(AdminDTO admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AdminDTO retrieve(String input) throws NoSuchClientException{
		String sql = "select * from mydb.admin where admin.username=?";
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, input);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				adminDto = new AdminDTO.Builder()
						.username(rs.getString("username")).password(rs.getString("password")).builder();
			}
			if(adminDto == null) {
				throw new NoSuchClientException("Böyle bir kullanıcı yok. Tekrar deneyiniz!\n");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		
		return adminDto;
	}

	@Override
	public void refresh(AdminDTO admin) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		AdminDAO dao = new AdminDAO();
		InputController input = new InputController();
		
		
		
		while (true)
			try {
				System.out.print("Kullanıcı: ");
				String username = input.nextString();
				System.out.print("Sifre: ");
				String pass = input.nextString();
				
				AdminDTO adminTest = new AdminDTO.Builder().username(username).password(pass).builder();
			
				dao.create(adminTest);
				break;
			} catch (ClientAlreadyExistsException ex) {
				System.out.println(ex.getMessage());
				continue;
		}
		
		
	}
	
	
	
}
