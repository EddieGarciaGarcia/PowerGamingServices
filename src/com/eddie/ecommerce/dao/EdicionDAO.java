package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Edicion;

import java.sql.Connection;
import java.util.List;

public interface EdicionDAO {
	
	 Edicion findByIdEdicion(Connection conexion, Integer id) throws DataException;

	 List<Edicion> findByIdJuego(Connection conexion, Integer id) throws DataException;

	 List<Edicion> findByIdsJuego(Connection conexion, List<Integer> ids) throws DataException;

	 boolean create(Connection conexion, Edicion edicion) throws DataException;

	 boolean update(Connection conexion, Edicion edicion) throws DataException;
	
}
