package com.winwin.models.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserLoginDTO {
	
	private String email;
	private String password;
	
	public UserLoginDTO(String pEmail, String pPassword){
		email = pEmail;
		password = pPassword;
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
}
