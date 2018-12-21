package com.eddie.training.model;

import java.util.Date;
import java.util.List;

public class ItemBiblioteca extends AbstractValueObject implements Comparable<ItemBiblioteca>{

	private String email=null;
	private Integer puntuacion=null;
	private Integer idJuego=null;
	private Date fechaCompra=null;
	
	private String comentario=null;
	
	
	public ItemBiblioteca() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(Integer idJuego) {
		this.idJuego = idJuego;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		comentario = comentario;
	}

	@Override
	public int compareTo(ItemBiblioteca b) {
		return this.getPuntuacion()-b.getPuntuacion();
		
	}
	
}
