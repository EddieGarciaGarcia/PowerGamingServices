package com.eddie.ecommerce.model;

import java.io.Serializable;

public class Plataforma implements Serializable {
	private Integer idPlataforma;
	private String nombre;
	
	public Plataforma() {
		
	}
	public Integer getIdPlataforma() {
		return idPlataforma;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setIdPlatadorma(Integer idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
}
