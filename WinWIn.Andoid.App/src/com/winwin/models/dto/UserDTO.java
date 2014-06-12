package com.winwin.models.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserDTO {
	
	public UserDTO(){
	
	}
	
	public UserDTO(int pId, String pNombre, String pApellido, String pEmail){
		this.id = pId;
		this.nombre = pNombre;
		this.apellido = pApellido;
		this.email = pEmail;
	}
	
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private Date fechaNacimiento;

	@JsonProperty("Id")
	public int getId() {
		return id;
	}
	@JsonProperty("Id")
	public void setId(int id) {
		this.id = id;
	}
	@JsonProperty("Nombre")
	public String getNombre() {
		return nombre;
	}
	@JsonProperty("Nombre")
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@JsonProperty("Apellido")
	public String getApellido() {
		return apellido;
	}
	@JsonProperty("Apellido")
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	@JsonProperty("Email")
	public String getEmail() {
		return email;
	}
	@JsonProperty("Email")
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonProperty("FechaNacimiento")
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	@JsonProperty("FechaNacimiento")
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
}
