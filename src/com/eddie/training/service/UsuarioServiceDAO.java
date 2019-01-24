package com.eddie.training.service;

import java.util.List;

import com.eddie.training.model.Usuario;

public interface UsuarioServiceDAO {
	
	public Usuario create(Usuario u) throws Exception;
	
	public void update(Usuario u) throws Exception;
	
	public long delete(Long id) throws Exception;
	
	public Usuario findById(String email) throws Exception;
	
	public Usuario login(String email, String password)throws Exception;
	
	public List<Usuario> findById(String idioma, String nombre) throws Exception; 
}
