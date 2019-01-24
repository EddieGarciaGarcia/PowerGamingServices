package com.eddie.training.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.training.model.Formato;

public interface FormatoDAO {
	
	public Formato findbyIdFormato(Connection conexion,Integer id) throws Exception;
	
	public List<Formato> findAll(Connection conexion, String idioma) throws Exception;
}
