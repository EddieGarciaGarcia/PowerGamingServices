package com.eddie.training.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.training.model.TipoEdicion;

public interface TipoEdicionDAO {
	
	public TipoEdicion findbyIdTipoEdicion(Connection conexion, Integer id) throws Exception;
	
	public List<TipoEdicion> findAll(Connection conexion, String idioma) throws Exception;
}
