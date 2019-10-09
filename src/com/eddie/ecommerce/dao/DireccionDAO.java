package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Direccion;

import java.sql.Connection;

public interface DireccionDAO {
	
	Direccion findById(Connection conexion, String email) throws DataException;

	boolean create(Connection conexion, Direccion direccion) throws DataException;

	boolean update(Connection conexion, Direccion direccion) throws DataException;

	boolean delete(Connection conexion, String email) throws DataException;
}
