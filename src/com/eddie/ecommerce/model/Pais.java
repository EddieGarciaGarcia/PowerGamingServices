package com.eddie.ecommerce.model;

import java.io.Serializable;

public class Pais implements Serializable {
	private Integer idPais=null;
	private String nombre=null;
	
	
	
	public Pais() {
		
	}
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
