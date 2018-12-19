package com.eddie.training.model;

public class Edicion implements ValueObject{

	private Integer id = null;
	private Integer idJuego = null;
	private Integer idFormato=null;
	private Integer idTipoEdicion= null;
	
	
	// Para implementar como enum ou, como unha simple interface con constantes
	private Double precio = null;

	
	public Edicion() {
		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getIdFormato() {
		return idFormato;
	}

	public void setIdFormato(int idFormato2) {
		this.idFormato = idFormato2;
	}

	public int getIdTipoEdicion() {
		return idTipoEdicion;
	}

	public void setIdTipoEdicion(int idTipoEdicion2) {
		this.idTipoEdicion = idTipoEdicion2;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
	
}
