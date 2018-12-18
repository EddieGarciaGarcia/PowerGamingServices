package com.eddie.training.model;

import java.util.Date;
import java.util.List;

public class Biblioteca extends ValueObject{

	private String email=null;
	private Integer idJuego=null;
	private Integer puntuacion=null;
	private Date fechaCompra=null;
	private String Comentario=null;
	
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
		return Comentario;
	}

	public void setComentario(String comentario) {
		Comentario = comentario;
	}
	
}
