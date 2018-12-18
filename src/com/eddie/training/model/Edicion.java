package com.eddie.training.model;

public class Edicion extends ValueObject{

	private Integer id = null;
	//private int idJuego = 0;
	private int idFormato=0;
	private int idTipoEdicion= 0;
	
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
