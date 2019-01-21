package com.eddie.training.model;

import java.util.Date;
import java.util.List;

public class JuegoCriteria implements ValueObject{
	private List<Integer> categoria=null;
	private List<String> idioma=null;
	private List<String> plataforma=null;
	
	private Date fechaLanzamiento=null;
	private String creador=null;
	
	public JuegoCriteria() {}

	public List<Integer> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Integer> categoria) {
		this.categoria = categoria;
	}

	public List<String> getIdioma() {
		return idioma;
	}

	public void setIdioma(List<String> idioma) {
		this.idioma = idioma;
	}

	public List<String> getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(List<String> plataforma) {
		this.plataforma = plataforma;
	}

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}
	
	
}
