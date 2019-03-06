package com.eddie.ecommerce.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Juego implements ValueObject, Comparable<Juego>{
	private Integer idJuego=null;
	private String nombre = null;
	private Integer fechaLanzamiento = null;
	private String informacion = null;
	private Integer idCreador = null;
	private Integer anho=null;
	
	public Integer getAnho() {
		return anho;
	}

	public void setAnho(Integer anho) {
		this.anho = anho;
	}

	private List<Categoria> categoria = null;	
	private List<Idioma> idiomas=null;
	private List<Plataforma> plataformas=null;

	
	public Juego() {
		categoria= new ArrayList<Categoria>();
		idiomas=new ArrayList<Idioma>();
		plataformas= new ArrayList<Plataforma>();
	}
	
	public Juego(String nombre, Integer fechaLanzamiento,Integer id_creador) {
		setNombre(nombre);
		setFechaLanzamiento(fechaLanzamiento);
		setIdCreador(id_creador);
	}
	
	public Juego(String nombre, String informacion) {
		setNombre(nombre);
		setInformacion(informacion);

	}
	

	public Integer getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(Integer idJuego) {
		this.idJuego = idJuego;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(Integer fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}


	public Integer getIdCreador() {
		return idCreador;
	}

	public void setIdCreador(Integer id_creador) {
		this.idCreador = id_creador;
	}

	@Override
	public int compareTo(Juego j) {
		return this.getFechaLanzamiento().compareTo(j.getFechaLanzamiento());
		
	}
	
	public int compareTo2(Juego j) {
		return this.getNombre().compareTo(j.getNombre());
	}

	public List<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public List<Plataforma> getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(List<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}


	/*SI HUBIERA HERENCIA*/
	
	/* 
	 * super.toString( ) + atributos
	 * 
	 * */
	
	
	
	
}

