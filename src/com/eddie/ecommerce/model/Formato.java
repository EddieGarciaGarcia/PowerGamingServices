package com.eddie.ecommerce.model;

import java.io.Serializable;

public class Formato implements Serializable {
	private Integer idFormato=null;
	private String nombre=null;
	
	public Formato() {}

	public Integer getIdFormato() {
		return idFormato;
	}

	public void setIdFormato(Integer idFormato) {
		this.idFormato = idFormato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
