package com.eddie.training.dao;

import com.eddie.training.model.Usuario;

public interface UsuarioDAO {
	
	public Usuario create(Usuario u) throws Exception;
	
	public boolean update(Usuario u) throws Exception;
	
	public void delete(Usuario u) throws Exception;
}
