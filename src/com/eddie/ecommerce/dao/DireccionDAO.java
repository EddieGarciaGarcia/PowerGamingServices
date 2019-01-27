package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.model.Direccion;

import java.sql.Connection;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface DireccionDAO {
	
	public Direccion findById(Connection conexion,Integer id) throws InstanceNotFoundException, DataException;
	
	public Direccion create(Connection conexion,Direccion d) throws DuplicateInstanceException, DataException;
	
	public boolean update(Connection conexion,Direccion d) throws InstanceNotFoundException, DataException;
	
	public void delete(Connection conexion,Direccion d) throws DataException;
}
