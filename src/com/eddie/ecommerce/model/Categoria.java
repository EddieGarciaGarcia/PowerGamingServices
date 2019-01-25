package com.eddie.ecommerce.model;

public class Categoria implements ValueObject{
	
	private Integer idCategria=null;
	private String nombre=null;
	
	private Categoria padre =null;

	
	public Categoria() {
		
	}

	public Integer getIdCategria() {
		return idCategria;
	}

	public void setIdCategria(Integer idCategria) {
		this.idCategria = idCategria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getPadre() {
		return padre;
	}

	public void setPadre(Categoria padre) {
		this.padre = padre;
	}
	
	
}
