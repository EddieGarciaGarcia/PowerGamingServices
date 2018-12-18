package com.eddie.training.model;

import java.util.Date;
import java.util.List;

public class Juego extends ValueObject{
	private Integer idJuego=null;
	private String nombre = null;
	private Date fechaLanzamiento = null;
	private String informacion = null;
	private String requisitos = null;
	private Integer id_creador = null;
	
	private List<Edicion> ediciones = null;	
	public Juego() {
		
	}
	
	public Juego(Integer idJuego, String nombre, String informacion) {
		setIdJuego(idJuego);
		setNombre(nombre);
		setInformacion(informacion);
	}
	
	public Juego(String nombre, String informacion,String requisitos) {
		setNombre(nombre);
		setInformacion(informacion);
		setRequisitos(requisitos);
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

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public List<Edicion> getEdiciones() {
		return ediciones;
	}

	public void setEdiciones(List<Edicion> ediciones) {
		this.ediciones = ediciones;
	}

	
//	@Override
//	public String toString() {
//		return "Juego " + getIdJuego() + ", nombre " + getNombre() + ", fechaLanzamiento " + getFechaLanzamiento()
//				+ ", informacion " + getInformacion() + ", requisitos " + getRequisitos() 
//				+ ", ediciones " + Arrays.toString(ediciones) ;
//	}

	public Integer getId_creador() {
		return id_creador;
	}

	public void setId_creador(Integer id_creador) {
		this.id_creador = id_creador;
	}

	/*SI HUBIERA HERENCIA*/
	
	/* 
	 * super.toString( ) + atributos
	 * 
	 * */
}
