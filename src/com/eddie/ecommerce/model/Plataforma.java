package com.eddie.ecommerce.model;

import java.io.Serializable;

public class Plataforma implements Serializable {
	private Integer idPlatadorma;
	private String nombre;
	
	public Plataforma() {
		
	}
	public Integer getIdPlatadorma() {
		return idPlatadorma;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setIdPlatadorma(Integer idPlatadorma) {
		idPlatadorma = idPlatadorma;
	}
}
