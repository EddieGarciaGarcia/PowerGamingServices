package com.eddie.ecommerce.model;

import java.io.Serializable;

public class TipoEdicion implements Serializable {
	private Integer idTipoEdicion=null;
	private String nombre=null;
	
	public TipoEdicion() {
		
	}

	public Integer getIdTipoEdicion() {
		return idTipoEdicion;
	}

	public void setIdTipoEdicion(Integer idTipoEdicion) {
		this.idTipoEdicion = idTipoEdicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
