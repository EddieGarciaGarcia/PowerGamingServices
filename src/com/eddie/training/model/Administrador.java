package com.eddie.training.model;

import java.util.Date;

public class Administrador extends Usuario implements ValueObject{

	private Boolean permisos=true;
	private String opcionAdmin=null;
	
	public Administrador(String nombre, String apellido1, String apellido2, String password, String email,
			Date fechaNacimiento, Direccion direccion, Integer pais, Integer provincia, Boolean permisos, String opcionAdmin) {
		super(nombre, apellido1, apellido2);
		setPermisos(permisos);
		setOpcionAdmin(opcionAdmin);
	}

	public Boolean getPermisos() {
		return permisos;
	}

	public void setPermisos(Boolean permisos) {
		this.permisos = permisos;
	}

	public String getOpcionAdmin() {
		return opcionAdmin;
	}

	public void setOpcionAdmin(String opcionAdmin) {
		this.opcionAdmin = opcionAdmin;
	}

	public String toString() {
		return super.toString()+", permisos "+getPermisos()+", opcion de administrador "+getOpcionAdmin();
	}
	
}
