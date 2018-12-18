package com.eddie.training.model;

public class Formato extends ValueObject{
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
