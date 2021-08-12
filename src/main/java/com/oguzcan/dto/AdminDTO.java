package com.oguzcan.dto;

public class AdminDTO extends UserDTO implements Comparable<Object>{

	private int adminId;
	
	
	public AdminDTO(Builder builder) {
		super(builder.username, builder.password);
		this.adminId = builder.adminId;
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
		return  adminId + " " + super.getUsername() + " " + super.getPassword();
	}
}
