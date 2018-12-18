package com.eddie.training.model;

public class Plataforma {
	private Integer IdPlatadorma;
	private String nombre;
	
	public Plataforma() {
		
	}
	public Integer getIdPlatadorma() {
		return IdPlatadorma;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setIdPlatadorma(Integer idPlatadorma) {
		IdPlatadorma = idPlatadorma;
	}
}
