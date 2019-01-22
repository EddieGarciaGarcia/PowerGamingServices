package com.eddie.training.dao.impl;

import java.sql.Connection;

import com.eddie.training.dao.UsuarioDAO;
import com.eddie.training.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO{

	@Override
	public Usuario create(Usuario u, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Usuario u, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Usuario findById(Integer id, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long delete(Long id, Connection connection) throws Exception {
		return id;
		// TODO Auto-generated method stub
		
	}

}
