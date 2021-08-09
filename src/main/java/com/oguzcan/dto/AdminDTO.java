package com.oguzcan.dto;

public class AdminDTO implements User, Comparable<Object>{

	private String username;
	private String password;
	private int adminId;
	
	
	public AdminDTO(Builder builder) {
		this.username = builder.username;
		this.password = builder.password;
		this.adminId = builder.adminId;
	}
	
	public AdminDTO() {
		
	}
	
//  ############################### INNER CLASS ################################
	public static class Builder {
		private String username;
		private String password;
		private int adminId = 0;
		
		public Builder username(String username) {
			this.username = username;
			return this;
		}
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		public Builder adminId(int adminId) {
			this.adminId = adminId;
			return this;
		}
		public AdminDTO build() {
			return new AdminDTO(this);
		}
	}

//	######################  GETTER & SETTER ################################
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	@Override
	public int compareTo(Object o) {
		AdminDTO admin = (AdminDTO) o;
		
		if(adminId == admin.getAdminId()) {
			return 0;
		}
		
		return adminId > admin.getAdminId() ? 1 : -1;
	}
	
	
	@Override
	public String toString() {
		return  adminId + " " + username + " " + password;
	}
}
