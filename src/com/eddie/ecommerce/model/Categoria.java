package com.eddie.ecommerce.model;

import java.io.Serializable;

public class Categoria implements Serializable {
	
	private Integer idCategoria=null;
	private String nombre=null;
	
	private Categoria padre =null;

	
	public Categoria() {
		
	}

	public Integer getIdCategria() {
		return idCategoria;
	}

	public void setIdCategria(Integer idCategria) {
		this.idCategoria = idCategria;
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
