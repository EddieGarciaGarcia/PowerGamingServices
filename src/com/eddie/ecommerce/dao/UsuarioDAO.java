package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Usuario;

import java.sql.Connection;


public interface UsuarioDAO {
	
	boolean create(Connection connection, Usuario usuario) throws DataException;
	
	boolean update(Connection connection, Usuario usuario) throws DataException;
	
	boolean delete(Connection connection, String email) throws DataException;

	Usuario findById(Connection connection, String email) throws DataException;

}
