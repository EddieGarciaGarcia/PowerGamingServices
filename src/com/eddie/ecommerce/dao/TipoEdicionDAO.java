package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.TipoEdicion;


public interface TipoEdicionDAO {
	
	public List<TipoEdicion> findbyIdsTipoEdicion(Connection conexion, List<Integer> ids, String idioma) throws InstanceNotFoundException, DataException;
	
	public List<TipoEdicion> findAll(Connection conexion, String idioma) throws DataException;
}
