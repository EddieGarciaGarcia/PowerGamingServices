package com.eddie.ecommerce.dao;

import java.sql.Connection;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Usuario;



public interface UsuarioDAO {
	
	public Usuario create(Usuario u, Connection connection) throws DuplicateInstanceException, DataException;
	
	public boolean update(Usuario u, Connection connection) throws InstanceNotFoundException, DataException;
	
	public long delete(String email, Connection connection) throws DataException;
	
	public Usuario findById(String email, Connection connection) throws InstanceNotFoundException, DataException;

}
