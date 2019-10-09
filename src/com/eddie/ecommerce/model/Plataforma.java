package com.eddie.ecommerce.model;

import java.io.Serializable;

public class Plataforma implements Serializable {
	private Integer IdPlatadorma;
	private String nombre;
	
	public Plataforma() {
		
	}
	public Integer getIdPlatadorma() {
		return IdPlatadorma;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setIdPlatadorma(Integer idPlatadorma) {
		IdPlatadorma = idPlatadorma;
	}
}
