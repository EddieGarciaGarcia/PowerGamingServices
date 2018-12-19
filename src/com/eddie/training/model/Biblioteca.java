package com.eddie.training.model;

import java.util.Date;
import java.util.List;

public class Biblioteca extends AbstractValueObject implements Comparable<Biblioteca>{

	private String email=null;
	private Integer idJuego=null;
	private Integer puntuacion=null;
	private Date fechaCompra=null;
	private String comentario=null;
	
	private List<Juego> juegos=null;
	
	public Biblioteca() {
		
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
	public int compareTo(Biblioteca b) {
		return this.getPuntuacion()-b.getPuntuacion();
		
	}
	
}
