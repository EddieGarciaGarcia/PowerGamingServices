package com.eddie.training.model;


public class Idioma implements ValueObject{

	private Character idIdioma;
	private String nombre;
	
	public Idioma() {
		
	}
	public Character getIdIdioma() {
		return idIdioma;
	}
	public String getNombre() {
		return nombre;
	}
	public void setIdIdioma(Character idIdioma) {
		this.idIdioma = idIdioma;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
