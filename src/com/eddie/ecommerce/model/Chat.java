package com.eddie.ecommerce.model;

import java.util.Date;

public class Chat implements ValueObject{
	private Integer idMensage;
	private String mensage;
	private String email;
	private Date fecha;
	
	public Chat() {}
	
	public String getEmail() {
		return email;
	}
	public Date getFecha() {
		return fecha;
	}public Integer getIdMensage() {
		return idMensage;
	}public String getMensage() {
		return mensage;
	}public void setEmail(String email) {
		this.email = email;
	}public void setFecha(Date fecha) {
		this.fecha = fecha;
	}public void setIdMensage(Integer idMensage) {
		this.idMensage = idMensage;
	}public void setMensage(String mensage) {
		this.mensage = mensage;
	}
}
