package com.eddie.training.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.training.model.Pais;

public interface PaisDAO {
	
	public Pais findById(Connection conexion,String id)throws Exception;
		
	public List<Pais> findAllById(Connection conexion)throws Exception;
}
