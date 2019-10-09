package com.eddie.ecommerce.model;


import java.io.Serializable;

public class Idioma implements Serializable {

	private String idIdioma;
	private String nombre;
	
	public Idioma() {
		
	}
	public String getIdIdioma() {
		return idIdioma;
	}
	public String getNombre() {
		return nombre;
	}
	public void setIdIdioma(String idIdioma) {
		this.idIdioma = idIdioma;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
