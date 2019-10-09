package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.TipoEdicion;

import java.sql.Connection;
import java.util.List;


public interface TipoEdicionDAO {
	
	List<TipoEdicion> findbyIdsTipoEdicion(Connection conexion, List<Integer> ids, String idioma) throws DataException;
	
	List<TipoEdicion> findAll(Connection conexion, String idioma) throws DataException;
}
