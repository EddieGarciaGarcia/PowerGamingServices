package com.eddie.training.dao;

import java.sql.Connection;

import com.eddie.training.model.Usuario;

public interface UsuarioDAO {
	
	public Usuario create(Usuario u, Connection connection) throws Exception;
	
	public boolean update(Usuario u, Connection connection) throws Exception;
	
	public long delete(Long id, Connection connection) throws Exception;
	
	public Usuario findById(String email, Connection connection) throws Exception;

}
