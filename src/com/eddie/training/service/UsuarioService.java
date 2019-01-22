package com.eddie.training.service;

import java.util.List;

import com.eddie.training.model.Usuario;

public interface UsuarioService {
	
	public Usuario create(Usuario u) throws Exception;
	
	public void update(Usuario u) throws Exception;
	
	public long delete(Long id) throws Exception;
	
	public Usuario findById(Integer id) throws Exception;
	
	public List<Usuario> findById(String idioma, String nombre) throws Exception; 
}
