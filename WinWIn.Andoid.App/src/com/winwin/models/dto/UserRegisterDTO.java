package com.winwin.models.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserRegisterDTO {

	private String email;
	private String password;
	private String user;
	
	public UserRegisterDTO(String pEmail, String pPassword, String pUser){
		email = pEmail;
		password = pPassword;
		user = pUser;
	}
	
	@JsonProperty("Email")
	public String getEmail() {
		return email;
	}
	@JsonProperty("Email")
	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonProperty("Password")
	public String getPassword() {
		return password;
	}
	
	@JsonProperty("Password")
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonProperty("User")
	private String getUser() {
		return user;
	}
	
	@JsonProperty("User")
	private void setUser(String user) {
		this.user = user;
	}
	
	
}
