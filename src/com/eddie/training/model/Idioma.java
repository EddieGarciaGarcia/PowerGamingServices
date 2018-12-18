package com.eddie.training.model;


public class Idioma extends ValueObject{

	private Char idIdioma;
	private String nombre;
	
	public Idioma() {
		
	}
	public Char getIdIdioma() {
		return idIdioma;
	}
	public String getNombre() {
		return nombre;
	}
	public void setIdIdioma(Char idIdioma) {
		this.idIdioma = idIdioma;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
