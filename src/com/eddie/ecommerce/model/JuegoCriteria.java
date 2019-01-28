package com.eddie.ecommerce.model;

import java.util.List;

public class JuegoCriteria extends Juego implements ValueObject {
	private List<Categoria> categoria=null;
	private List<Idioma> idioma=null;
	private List<Plataforma> plataforma=null;
	
	public JuegoCriteria() {}

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
