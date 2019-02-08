package com.eddie.ecommerce.model;

import java.util.List;

public class JuegoCriteria extends Juego implements ValueObject {
	private List<Categoria> categoria=null;
	private List<Idioma> idioma=null;
	private List<Plataforma> plataforma=null;
	private Integer anho=null;
	
	public JuegoCriteria() {}
	
	

	public Integer getAnho() {
		return anho;
	}



	public void setAnho(Integer anho) {
		this.anho = anho;
	}



	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}

	public List<Idioma> getIdioma() {
		return idioma;
	}

	public void setIdioma(List<Idioma> idioma) {
		this.idioma = idioma;
	}

	public List<Plataforma> getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(List<Plataforma> plataforma) {
		this.plataforma = plataforma;
	}



	
	
}
