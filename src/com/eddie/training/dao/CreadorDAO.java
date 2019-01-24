package com.eddie.training.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.training.model.Creador;

public interface CreadorDAO {

	public Creador findbyIdFormato(Connection conexion,Integer id) throws Exception;
	
	public List<Creador> findAll(Connection conexion) throws Exception;
}
