package com.eddie.training.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.eddie.training.model.comparator.UsuarioComparator;

public class Usuario extends AbstractValueObject implements Comparable<Usuario>{
	
	private Direccion direccion=null;
	private String nombre=null;
	private String apellido1=null;
	private String apellido2=null;
	private String nombreUser=null;
	private String password=null;
	private String email=null;
	private Date fechaNacimiento=null;
	private String telefono=null;
	private Character genero=null;
	
	
	
	public Usuario(String nombre, String apellido1, String apellido2) {
		this(nombre,apellido1,apellido2,null,null);
	}
	
	public Usuario(String nombre, String apellido1, String apellido2, String password, String email) {
		this(nombre,apellido1,apellido2,password,email,null,null);
	}
	
	public Usuario(String nombre, String apellido1, String apellido2, String password, String email, Date fechaNacimiento, Direccion direccion) {
		setNombre(nombre);
		setApellido1(apellido1);
		setApellido2(apellido2);
		setPassword(password);
		setEmail(email);
		setFechaNacimiento(fechaNacimiento);
		setDireccion(direccion);
		
	}
	

	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getNombreUser() {
		return nombreUser;
	}
	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public boolean equals(Object o) {
		String otroEmail=((Usuario) o).getEmail();
		if(this.getEmail()==null && otroEmail==null) {
			return true;
		}
		if(this.getEmail()==null || otroEmail==null) {
			return false;
		}
		
		return getEmail().equalsIgnoreCase(otroEmail);
	}
	
	@Override
	public final int compareTo(Usuario u) {
		if(u.getNombre()==null && this.getNombre()==null) {
			return 0;
		}
		if(u.getNombre()==null) return 1;
		if(this.getNombre()==null)return -1;
		
		return this.getNombre().compareTo(u.getNombre());
		
	}
	
}
