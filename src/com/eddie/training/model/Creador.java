
package com.eddie.training.model;

public class Creador extends ValueObject{
	
	private Char IdCreador;
	private String nombre;
	
	public Creador() {
		
	}
	
	public Char getIdCreador() {
		return IdCreador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setIdCreador(Char idCreador) {
		IdCreador = idCreador;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
