package com.oguzcan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
	public void update(AdminDTO admin, int id) {
		String sql = "update mydb.admin set username=?, password=? where id=?";
		
		try(Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, admin.getUsername());
			stmt.setString(2, admin.getPassword());
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
	public void delete(int adminId) throws NoSuchClientException{
		String sql = "delete from mydb.admin where admin_id=?";
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, adminId);
			stmt.executeUpdate();
			System.out.println("Kullanıcı başarıyla silindi.");
		} catch (SQLException ex) {
			System.out.println("hata delete");
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public AdminDTO retrieve(String input) throws NoSuchClientException{
		String sql = "select * from mydb.admin where username=?";
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, input);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				adminDto = new AdminDTO.Builder()
						.username(rs.getString("username")).password(rs.getString("password")).build();
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
	public AdminDTO retrieveById(int id) {
		String sql = "select * from mydb.admin where admin_id";
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				adminDto = new AdminDTO.Builder()
						.username(rs.getString("username")).password(rs.getString("password")).build();
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return adminDto;
	}


	public int bringId(AdminDTO admin) throws NoSuchClientException {
		String sql = "select admin_id from mydb.admin where username=?";
		int id = 0;
		
		try (Connection connection = dbConnection()) {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, admin.getUsername());
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt("admin_id");
			}
			if(id == 0) {
				throw new NoSuchClientException("Böyle bir kullanıcı yok. Tekrar deneyiniz!\n");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return id;
	}
}
