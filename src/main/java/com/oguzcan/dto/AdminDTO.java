package com.oguzcan.dto;

public class AdminDTO implements Client{

	private String username;
	private String password;
	
	public AdminDTO(Builder builder) {
		this.username = builder.username;
		this.password = builder.password;
	}
	
	public AdminDTO() {
		
	}
	
//  ############################### INNER CLASS ################################
	public static class Builder {
		private String username;
		private String password;
		
		public Builder username(String username) {
			this.username = username;
			return this;
		}
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		public AdminDTO builder() {
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

	@Override
	public String toString() {
		return "AdminDTO [username=" + username + ", password=" + password + "]";
	}
}
